package com.xiaoyezi.networkdetector;

public interface NetStateObserver {
    void onDisconnected();

    void onConnected(NetworkType networkType);
}
