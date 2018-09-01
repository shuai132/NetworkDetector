package com.xiaoyezi.networkdetectordemo;

import android.app.Application;

import com.xiaoyezi.networkdetector.NetworkDetector;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkDetector.getInstance().init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        NetworkDetector.getInstance().deInit(this);
    }
}
