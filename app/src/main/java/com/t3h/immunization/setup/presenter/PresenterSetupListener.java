package com.t3h.immunization.setup.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.setup.view.SetupView;

public interface PresenterSetupListener<V extends SetupView>extends MvpPresenter<V> {
    void onchangePass(String pass);
}
