package com.xiaoyezi.networkdetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xiaoyezi.networklib.NetStateObserver;
import com.xiaoyezi.networklib.NetworkDetector;
import com.xiaoyezi.networklib.NetworkType;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.network);
        NetworkDetector.getInstance().addObserver(new NetStateObserver() {
            @Override
            public void onDisconnected() {
                Log.d(TAG, "onDisconnected: ");
                textView.setText("onDisconnected");
            }

            @Override
            public void onConnected(NetworkType networkType) {
                Log.d(TAG, "onConnected: " + networkType.toString());
                textView.setText(networkType.toString());
            }
        });
    }
}
