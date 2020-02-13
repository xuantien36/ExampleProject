package com.t3h.immunization.basemvp;
import android.content.Context;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;
    public Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }
    public V getMvpView() {
        return mMvpView;
    }

}


