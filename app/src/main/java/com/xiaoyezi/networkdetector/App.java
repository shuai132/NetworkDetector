package com.xiaoyezi.networkdetector;

import android.app.Application;

import com.xiaoyezi.networklib.NetStateReceiver;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 注册BroadcastReceiver
        NetStateReceiver.register(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 取消BroadcastReceiver注册
        NetStateReceiver.unregister(this);
    }
}
