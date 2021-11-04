package com.renj.jetpack.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.renj.jetpack.utils.Logger;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2021-11-04   16:00
 * <p>
 * 描述：生命周期监听，不需要在 Activity 和 Fragment 中重写方法，并且还可以在 Fragment 中直接监听宿主 Activity 的生命周期
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BaseLifecycleListener implements DefaultLifecycleObserver {
    private Lifecycle lifecycle;

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public Lifecycle.State getCurrentState() {
        if (lifecycle != null)
            return lifecycle.getCurrentState();
        return Lifecycle.State.DESTROYED;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Logger.i(owner.getClass().getSimpleName() + " Lifecycle -- onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Logger.i(owner.getClass().getSimpleName() + " Lifecycle -- onStart");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Logger.i(owner.getClass().getSimpleName() + " Lifecycle -- onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Logger.i(owner.getClass().getSimpleName() + " Lifecycle -- onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Logger.i(owner.getClass().getSimpleName() + " Lifecycle -- onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Logger.i(owner.getClass().getSimpleName() + " Lifecycle -- onDestroy");
    }
}
