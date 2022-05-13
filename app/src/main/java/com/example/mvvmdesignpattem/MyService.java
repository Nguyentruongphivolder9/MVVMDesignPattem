package com.example.mvvmdesignpattem;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;



public class MyService implements DefaultLifecycleObserver {

    private CallBack callBack;
    private Lifecycle lifecycle;
    private boolean isEnable = false;

    public MyService(Lifecycle lifecycle, CallBack callBack) {
        this.callBack = callBack;
        this.lifecycle = lifecycle;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onCreate(owner);
        callBack.onListen("onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        callBack.onListen("onStart");
        if (!isEnable){
            new CountDownTimer(2000,1500){

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    startService();
                }
            }.start();
            startService();
        }
    }
    private void startService() {
        isEnable = true;
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
            Log.d("BBB","Start Service");
        }else {
            Log.d("BBB","Can nor start bc activity not response");
        }
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onResume(owner);
        callBack.onListen("onCreate");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onPause(owner);
        callBack.onListen("onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
        callBack.onListen("onStop");
    }

    interface CallBack{
        void onListen(String lifectcle);
    }
}
