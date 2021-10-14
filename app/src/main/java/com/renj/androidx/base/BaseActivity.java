package com.renj.androidx.base;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.renj.androidx.utils.SystemBarUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = initViewModel();
        SystemBarUtils.setStatusWhiteAndDark(this);
        initData(viewDataBinding, viewModel);
        initListener(viewDataBinding, viewModel);
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
                                return modelClass.getConstructor(Application.class, getCurrentActivity().getClass())
                                        .newInstance(getApplication(), getCurrentActivity());
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

    protected abstract BaseActivity<DB, VM> getCurrentActivity();

    @LayoutRes
    protected abstract int getLayoutId();


    protected abstract void initData(DB viewDataBinding, VM viewModel);

    protected void initListener(DB viewDataBinding, VM viewModel) {
    }
}
