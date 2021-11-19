package com.renj.jetpack.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

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
 * 描述：Fragment 基类，不是懒加载的 Fragment，如果需要使用懒加载请使用 {@link LazyFragment}
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class BaseFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    protected DB viewDataBinding;
    protected VM viewModel;

    private Map<Class<? extends BaseLifecycleListener>, BaseLifecycleListener> lifecycleListeners;

    public BaseFragment() {
        super();
        lifecycleListeners = new HashMap<>();
        structureMethod();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        viewDataBinding.setLifecycleOwner(this);
        viewModel = initViewModel();
        initData(viewDataBinding, viewModel);
        initListener(viewDataBinding, viewModel);
        return viewDataBinding.getRoot();
    }

    @SuppressWarnings("all")
    protected VM initViewModel() {
        // 通过反射获取泛型的Class
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            try {
                Class<VM> clazz = (Class<VM>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
                if (clazz == null) return null;
                return VMProviderUtils.createViewModel(getActivity().getApplication(), getCurrentFragment(), clazz);
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

    protected abstract BaseFragment<DB, VM> getCurrentFragment();

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化数据，<b>注意：该方法并非延迟执行方法。延迟方法：{@link LazyFragment#userFirstVisible()}</b><br/>
     *
     * @see LazyFragment#userFirstInVisible()
     * @see LazyFragment#userVisible()
     * @see LazyFragment#userFirstInVisible()
     * @see LazyFragment#userInVisible()
     */
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
}
