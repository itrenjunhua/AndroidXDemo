package com.renj.jetpack.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.renj.jetpack.utils.SystemBarUtils;
import com.renj.jetpack.utils.VMProviderUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2021-10-13   16:34
 * <p>
 * 描述：Activity基类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected DB viewDataBinding;
    protected VM viewModel;

    private Map<Class<? extends BaseLifecycleListener>, BaseLifecycleListener> lifecycleListeners;

    public BaseActivity() {
        super();
        lifecycleListeners = new HashMap<>();
        structureMethod();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewDataBinding.setLifecycleOwner(this);
        viewModel = initViewModel();
        SystemBarUtils.setStatusWhiteAndDark(this);
        initData(viewDataBinding, viewModel);
        initListener(viewDataBinding, viewModel);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                handlerOnBack();
            }
        });
    }

    @SuppressWarnings("all")
    protected VM initViewModel() {
        // 通过反射获取泛型的Class
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            try {
                Class<VM> clazz = (Class<VM>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
                if (clazz == null) return null;
                return VMProviderUtils.createViewModel(getApplication(), getCurrentActivity(), clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 构造方法中调用，在 onCreate() 方法之前执行
     */
    protected void structureMethod() {

    }

    protected abstract BaseActivity<DB, VM> getCurrentActivity();

    @LayoutRes
    protected abstract int getLayoutId();


    protected abstract void initData(DB viewDataBinding, VM viewModel);

    // ====================== 生命周期监听 ====================== //

    public <T extends BaseLifecycleListener> void addLifecycleListener(T lifecycleListener) {
        if (lifecycleListener != null) {
            lifecycleListeners.put(lifecycleListener.getClass(), lifecycleListener);
            lifecycleListener.setLifecycle(getLifecycle());
            getLifecycle().addObserver(lifecycleListener);
        }
    }

    public <T extends BaseLifecycleListener> void removeLifecycleListener(T lifecycleListener) {
        if (lifecycleListener != null) {
            getLifecycle().removeObserver(lifecycleListener);
            lifecycleListeners.remove(lifecycleListener.getClass());
        }
    }

    public Map<Class<? extends BaseLifecycleListener>, BaseLifecycleListener> getLifecycleListeners() {
        if (lifecycleListeners == null) return new HashMap<>();
        return lifecycleListeners;
    }

    public <T extends BaseLifecycleListener> T getLifecycleListener(Class<T> clazz) {
        return (T) lifecycleListeners.get(clazz);
    }

    protected void initListener(DB viewDataBinding, VM viewModel) {
    }

    protected void handlerOnBack() {
        finish();
    }
}
