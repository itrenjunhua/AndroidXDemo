package com.renj.androidx.base;

import android.app.Application;
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
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
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
                return new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory() {
                    @NonNull
                    @Override
                    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                        if (AndroidViewModel.class.isAssignableFrom(modelClass)) {
                            try {
                                return modelClass.getConstructor(Application.class, getCurrentFragment().getClass())
                                        .newInstance(getActivity().getApplication(), getCurrentFragment());
                            } catch (Exception e) {
                                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                            }
                        }
                        return super.create(modelClass);
                    }
                }).get(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
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

    protected void initListener(DB viewDataBinding, VM viewModel) {
    }
}
