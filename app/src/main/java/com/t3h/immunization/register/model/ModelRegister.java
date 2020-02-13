package com.t3h.immunization.register.model;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.register.presenter.ModelPresenterRegisterListener;
import com.t3h.immunization.respone.ResponeRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelRegister {
    private ModelPresenterRegisterListener callback;

    public ModelRegister(ModelPresenterRegisterListener callback) {
        this.callback = callback;
    }

    public void handleRegister(String user_name, String name, String password, String phone, String email) {
        ApiBuilder.getInstance().register(user_name, password, "", name, phone, email, "", "")
                .enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus() == true) {
//                            progressDialog.dismiss();
                            callback.onRegisterSuccess();
                        }else {
                            callback.onRegisterFail();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponeRegister> call, Throwable t) {
                    }
                });
    }
}
