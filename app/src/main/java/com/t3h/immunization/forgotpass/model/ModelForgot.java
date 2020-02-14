package com.t3h.immunization.forgotpass.model;

import android.content.Intent;
import android.widget.Toast;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.ForgotActivity;
import com.t3h.immunization.activity.VerificationActivity;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.forgotpass.presenter.ModelPresenterForgotListener;
import com.t3h.immunization.respone.ResponeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelForgot {
    private ModelPresenterForgotListener callback;

    public ModelForgot(ModelPresenterForgotListener callback) {
        this.callback = callback;
    }
    public void handleForgot(String email){
        ApiBuilder.getInstance().forgot(email).enqueue(new Callback<ResponeRegister>() {
            @Override
            public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                if (response.body().getStatus()==true){

                   callback.onForgotSuccess();
                }else {
                   callback.onForgotFail();
                }

            }
            @Override
            public void onFailure(Call<ResponeRegister> call, Throwable t) {

            }
        });

    }
}
