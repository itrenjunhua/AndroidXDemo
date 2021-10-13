package com.renj.androidx.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.renj.androidx.utils.Logger;


/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2021-10-13   11:06
 * <p>
 * 描述：懒加载 Fragment，兼容 add()、replace()、ViewPager中使用形式
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class LazyFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<DB, VM> {
    private static final int FRAGMENT_STATUS_UN_INIT = -1;
    private static final int FRAGMENT_STATUS_INIT_FINISH = 0;
    private static final int FRAGMENT_STATUS_FIRST_VISIBLE = 1;
    private static final int FRAGMENT_STATUS_VISIBLE = 2;
    private static final int FRAGMENT_STATUS_FIRST_INVISIBLE = 3;
    private static final int FRAGMENT_STATUS_INVISIBLE = 4;

    // -1：初始化未完成 0：初始化完成 1：第一次对用户可见 2：第一次对用户不可见 3：对用户可见  4：对用户不可见
    private int currentFragment = FRAGMENT_STATUS_UN_INIT;
    // onHiddenChanged() 或者 setUserVisibleHint() 方法当前状态是否为对用户可见状态
    private boolean hiddenAndVisibleStatusVisible = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i(this + " onCreateView ============= ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.i(this + " onActivityCreated ============= ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i(this.getClass().getSimpleName() + " onResume ============= " + currentFragment + " -- " + hiddenAndVisibleStatusVisible);

        if (hiddenAndVisibleStatusVisible) {
            if (currentFragment == FRAGMENT_STATUS_INIT_FINISH) {
                userFirstVisible();
                currentFragment = FRAGMENT_STATUS_FIRST_VISIBLE;
            } else if (currentFragment == FRAGMENT_STATUS_FIRST_INVISIBLE
                    || currentFragment == FRAGMENT_STATUS_INVISIBLE
                    || currentFragment == FRAGMENT_STATUS_VISIBLE) {
                userVisible();
                currentFragment = FRAGMENT_STATUS_VISIBLE;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.i(this.getClass().getSimpleName() + " onPause ============= " + currentFragment + " -- " + hiddenAndVisibleStatusVisible);

        if (hiddenAndVisibleStatusVisible) {
            if (currentFragment == FRAGMENT_STATUS_FIRST_VISIBLE) {
                userFirstInVisible();
                currentFragment = FRAGMENT_STATUS_INVISIBLE;
            } else if (currentFragment == FRAGMENT_STATUS_VISIBLE
                    || currentFragment == FRAGMENT_STATUS_INVISIBLE) {
                userInVisible();
                currentFragment = FRAGMENT_STATUS_INVISIBLE;
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        hiddenAndVisibleStatusVisible = !hidden;
        Logger.i(this.getClass().getSimpleName() + " onHiddenChanged ============= hidden: " + hidden);

        if (!hidden) {
            if (currentFragment == FRAGMENT_STATUS_INIT_FINISH) {
                userFirstVisible();
                currentFragment = FRAGMENT_STATUS_FIRST_VISIBLE;
            } else if (currentFragment == FRAGMENT_STATUS_FIRST_INVISIBLE
                    || currentFragment == FRAGMENT_STATUS_INVISIBLE) {
                userVisible();
                currentFragment = FRAGMENT_STATUS_VISIBLE;
            }
        } else {
            if (currentFragment == FRAGMENT_STATUS_FIRST_VISIBLE) {
                userFirstInVisible();
                currentFragment = FRAGMENT_STATUS_FIRST_INVISIBLE;
            } else if (currentFragment == FRAGMENT_STATUS_VISIBLE) {
                userInVisible();
                currentFragment = FRAGMENT_STATUS_INVISIBLE;
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        hiddenAndVisibleStatusVisible = isVisibleToUser;
        Logger.i(this.getClass().getSimpleName() + " setUserVisibleHint ============= isVisibleToUser: " + isVisibleToUser);

        if (isVisibleToUser) {
            if (currentFragment == FRAGMENT_STATUS_INIT_FINISH) {
                userFirstVisible();
                currentFragment = FRAGMENT_STATUS_FIRST_VISIBLE;
            } else if (currentFragment == FRAGMENT_STATUS_FIRST_INVISIBLE
                    || currentFragment == FRAGMENT_STATUS_INVISIBLE) {
                userVisible();
                currentFragment = FRAGMENT_STATUS_VISIBLE;
            }
        } else {
            if (currentFragment == FRAGMENT_STATUS_FIRST_VISIBLE) {
                userFirstInVisible();
                currentFragment = FRAGMENT_STATUS_FIRST_INVISIBLE;
            } else if (currentFragment == FRAGMENT_STATUS_VISIBLE) {
                userInVisible();
                currentFragment = FRAGMENT_STATUS_INVISIBLE;
            }
        }
    }

    /**
     * 用户 <b>第一次可见，用于延迟加载数据</b>。非第一次可见时不会执行该方法，只会执行 {@link #userVisible()}
     */
    protected void userFirstVisible() {
        Logger.i(this.getClass().getSimpleName() + " fistVisible ============= 用户第一次可见");
    }

    /**
     * 用户<b>非第一次可见</b>。第一次可见时不会执行该方法，只会执行 {@link #userFirstVisible()}
     */
    protected void userVisible() {
        Logger.i(this.getClass().getSimpleName() + " visible ============= 用户可见");
    }

    /**
     * 用户<b>第一次不可见</b>。非第一次不可见时不会执行该方法，只会执行 {@link #userInVisible()}
     */
    protected void userFirstInVisible() {
        Logger.i(this.getClass().getSimpleName() + " fistInVisible ============= 用户第一次不可见");
    }

    /**
     * 用户<b>非第一次不可见</b>。第一次不可见时不会执行该方法，只会执行 {@link #userFirstInVisible()}
     */
    protected void userInVisible() {
        Logger.i(this.getClass().getSimpleName() + " inVisible ============= 用户不可见");
    }
}
