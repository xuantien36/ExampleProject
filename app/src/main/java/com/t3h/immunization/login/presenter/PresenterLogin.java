package com.t3h.immunization.login.presenter;
import com.t3h.immunization.login.model.ModelLogin;
import com.t3h.immunization.login.view.LoginView;

public class PresenterLogin implements ModelPresenterLoginListener {
    private ModelLogin login;
    private LoginView callback;

    public PresenterLogin(LoginView callback) {
        this.callback = callback;
    }

    public void receivedHandlrLogin(String user_name, String password){
        login=new ModelLogin(this);
        login.handleLogin(user_name,password);

    }

    @Override
    public void onLoginSuccess() {
        callback.onLoginSuccess();

    }

    @Override
    public void onLoginFail() {
        callback.onLoginFail();

    }
}
