package com.t3h.immunization.login.model;

import android.app.ProgressDialog;
import android.util.Log;

import com.t3h.immunization.MyApplication;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.login.presenter.ModelPresenterLoginListener;
import com.t3h.immunization.respone.ResponeLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelLogin {
    private ModelPresenterLoginListener callback;

    public ModelLogin(ModelPresenterLoginListener callback) {
        this.callback = callback;
    }

    public void handleLogin(String user_name, String password) {

        if (user_name.isEmpty() && password.isEmpty()) {
            callback.onLoginFail();
        }

        ApiBuilder.getInstance().login(user_name, password).enqueue(new Callback<ResponeLogin>() {
            @Override
            public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {
                if (response.body().getStatus() == true) {
                    Log.e("TAG", "CHECK LOGIN SUCCESS!");
                    User.getInstans().setId(response.body().getData().getId());
                    callback.onLoginSuccess();
                } else {
                    callback.onLoginFail();
                }
            }

            @Override
            public void onFailure(Call<ResponeLogin> call, Throwable t) {
                Log.e("TAG", "CHECK LOGIN failed!");
                t.printStackTrace();
            }
        });
    }

}
