# NetworkDetector
对BroadcastReceiver做了简单的封装和处理，用于监听Android网络变化。

## 如何添加
### 1:Gradle添加：
#### 1.在Project的build.gradle中添加仓库地址

``` gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

#### 2.在app目录下的build.gradle中添加依赖
``` gradle
dependencies {
     compile 'com.github.shuai132:NetworkDetector:v0.1.0'
}
```
v0.1.0可替换为其他版本的tag或commit-id

### 2:源码方式添加：
#### 导入module后添加

``` gradle
compile project(':networkdetector')
```


## 使用方法
``` java
...
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
```

``` java
NetworkDetector.getInstance().addObserver(new NetStateObserver() {
    @Override
    public void onDisconnected() {
    }

    @Override
    public void onConnected(NetworkType networkType) {
    }
});
```
