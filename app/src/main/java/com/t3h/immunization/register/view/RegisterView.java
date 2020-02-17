package com.t3h.immunization.register.view;

import com.t3h.immunization.basemvp.MvpView;

public interface RegisterView extends MvpView {
    void  onRegisterSuccess();
    void onRegisterFail();
}
