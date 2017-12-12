package com.tv.cec.mvpbase;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T t);
    void detachView();
}
