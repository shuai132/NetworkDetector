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
     implementation "com.github.shuai132:NetworkDetector:${version}"
}
```

### 2:源码方式添加：
#### 导入module后添加

``` gradle
implementation project(':networkdetector')
```


## 使用方法
1. 初始化
``` java
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
2. 使用
``` java
NetworkDetector.getInstance().addObserver(...);
NetworkDetector.getInstance().removeObserver(...);
```
具体示例请参考MainActivity.java
