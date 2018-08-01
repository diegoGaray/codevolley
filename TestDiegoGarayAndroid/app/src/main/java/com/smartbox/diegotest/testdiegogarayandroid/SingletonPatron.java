package com.smartbox.diegotest.testdiegogarayandroid;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonPatron {

    private static SingletonPatron mInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    private SingletonPatron(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonPatron getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingletonPatron(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }
}
