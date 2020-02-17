package com.t3h.immunization.login.view;

import com.t3h.immunization.basemvp.MvpView;

public interface LoginView extends MvpView {
    void  onLoginSuccess();
    void  onLoginFail();
}
