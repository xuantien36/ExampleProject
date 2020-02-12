package com.t3h.immunization.basemvp;

public interface MvpPresenter<V extends MvpView> {
    void onAttach(V mvpView);

}
