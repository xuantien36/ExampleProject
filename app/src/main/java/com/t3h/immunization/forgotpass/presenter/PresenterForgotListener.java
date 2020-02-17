package com.t3h.immunization.forgotpass.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.forgotpass.view.ForgotView;

public interface PresenterForgotListener<V extends ForgotView>extends MvpPresenter<V> {
    void onHandleForgot(String email);

}
