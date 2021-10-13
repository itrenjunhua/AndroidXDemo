package com.renj.androidx.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2021-10-13   16:41
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BaseViewModel extends AndroidViewModel {
    /**
     * 当在Fragment中使用 BaseViewModel 时，可能为 null
     */
    @SuppressLint("StaticFieldLeak")
    protected @Nullable
    Activity activity;
    /**
     * 当在Activity中使用 BaseViewModel 时，一定为 null
     */
    protected @Nullable
    Fragment fragment;

    public BaseViewModel(@NonNull Application application, @NonNull Activity activity) {
        super(application);
        this.activity = activity;
        this.fragment = null;
    }

    public BaseViewModel(@NonNull Application application, @NonNull Fragment fragment) {
        super(application);
        this.fragment = fragment;
        this.activity = fragment.getActivity();
    }
}
