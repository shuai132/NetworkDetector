package com.xiaoyezi.networklib;

public interface NetStateObserver {
    void onDisconnected();

    void onConnected(NetworkType networkType);
}
