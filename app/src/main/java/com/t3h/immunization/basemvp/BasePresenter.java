package com.t3h.immunization.basemvp;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

}


