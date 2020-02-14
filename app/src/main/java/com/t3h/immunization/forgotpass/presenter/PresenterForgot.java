package com.t3h.immunization.forgotpass.presenter;

import com.t3h.immunization.forgotpass.model.ModelForgot;
import com.t3h.immunization.forgotpass.view.ForgotView;

public class PresenterForgot implements ModelPresenterForgotListener {
    private ModelForgot forgot;
    private ForgotView callback;

    public PresenterForgot(ForgotView callback) {
        this.callback = callback;
    }

    @Override
    public void onForgotSuccess() {
        callback.onSuccess();

    }

    @Override
    public void onForgotFail() {
        callback.onFail();

    }

    public void onHandleForgot(String email) {
        forgot=new ModelForgot(this);
        forgot.handleForgot(email);


    }
}
