package com.xiaoyezi.networklib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class NetworkDetector {
    private BroadcastReceiver broadcastReceiver;
    private List<NetStateObserver> observers;

    private NetworkDetector() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                    NetworkType networkType = NetworkUtils.getNetworkType(context);
                    notifyObservers(networkType);
                }
            }
        };

        observers = new CopyOnWriteArrayList<>();
    }

    public static NetworkDetector getInstance() {
        return InstanceHolder.Instance;
    }

    /**
     * 初始化网络监听
     */
    public void init(@NonNull Context context) {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * 注销网络监听
     */
    public void deInit(@NonNull Context context) {
        context.unregisterReceiver(broadcastReceiver);
    }

    /**
     * 注册网络变化Observer
     */
    public void addObserver(NetStateObserver observer) {
        if (observer == null)
            return;
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * 取消网络变化Observer的注册
     */
    public void removeObserver(NetStateObserver observer) {
        if (observer == null)
            return;
        observers.remove(observer);
    }

    public void removeAllObservers() {
        observers.clear();
    }

    private void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            NetworkType networkType = NetworkUtils.getNetworkType(context);
            notifyObservers(networkType);
        }
    }

    /**
     * 通知所有的Observer网络状态已经发生变化
     */
    private void notifyObservers(NetworkType networkType) {
        if (networkType == NetworkType.NETWORK_NO) {
            for(NetStateObserver observer : observers) {
                observer.onDisconnected();
            }
        } else {
            for(NetStateObserver observer : observers) {
                observer.onConnected(networkType);
            }
        }
    }

    private static class InstanceHolder {
        private static final NetworkDetector Instance = new NetworkDetector();
    }
}
