package com.t3h.immunization.register.presenter;
import com.t3h.immunization.register.model.ModelRegister;
import com.t3h.immunization.register.view.RegisterView;

public class PresenterRegister implements ModelPresenterRegisterListener {
    private ModelRegister register;
    private RegisterView callback;

    public PresenterRegister(RegisterView callback) {
        this.callback = callback;
    }
    public void receiedHandleRegister(String user_name, String name, String password, String phone, String email) {
        register=new ModelRegister(this);
        register.handleRegister(user_name,name,password,phone,email);

    }
    @Override
    public void onRegisterSuccess() {
        callback.onRegisterSuccess();
    }
    @Override
    public void onRegisterFail() {
        callback.onRegisterFail();
    }
}