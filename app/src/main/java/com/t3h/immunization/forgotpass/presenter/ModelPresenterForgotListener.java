package com.t3h.immunization.forgotpass.presenter;

import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.forgotpass.view.ForgotView;

public interface ModelPresenterForgotListener {
    void onForgotSuccess();
    void onForgotFail();

}
