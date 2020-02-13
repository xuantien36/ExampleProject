package com.t3h.immunization;

import android.app.Application;

public class MyApplication extends Application {
    static MyApplication myApplication;

    public MyApplication() {
        myApplication = this;
    }

    public static MyApplication getInstance() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }
        return myApplication;

    }
}
