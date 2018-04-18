package com.example.bill;

import android.app.Application;

/**
 * Created by SCWANG on 2017/6/11.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
