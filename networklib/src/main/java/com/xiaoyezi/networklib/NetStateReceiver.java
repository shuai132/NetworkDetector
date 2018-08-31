package com.xiaoyezi.networklib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class NetStateReceiver extends BroadcastReceiver {

    private static class InstanceHolder {
        private static final NetStateReceiver Instance = new NetStateReceiver();
    }

    private List<NetStateObserver> observers = new ArrayList<>();

    public NetStateReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            NetworkType networkType = NetworkUtils.getNetworkType(context);
            notifyObservers(networkType);
        }
    }

    /**
     * 注册网络监听
     */
    public static void register(@NonNull Context context) {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(InstanceHolder.Instance, intentFilter);
    }

    /**
     * 取消网络监听
     */
    public static void unregister(@NonNull Context context) {
        context.unregisterReceiver(InstanceHolder.Instance);
    }

    /**
     * 注册网络变化Observer
     */
    public static void registerObserver(NetStateObserver observer) {
        if (observer == null)
            return;
        if (!InstanceHolder.Instance.observers.contains(observer)) {
            InstanceHolder.Instance.observers.add(observer);
        }
    }

    /**
     * 取消网络变化Observer的注册
     */
    public static void unregisterObserver(NetStateObserver observer) {
        if (observer == null)
            return;
        if (InstanceHolder.Instance.observers == null)
            return;
        InstanceHolder.Instance.observers.remove(observer);
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
}
