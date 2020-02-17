package com.t3h.immunization.login.presenter;
import android.util.Log;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.login.view.LoginView;
import com.t3h.immunization.respone.ResponeLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogin<V extends LoginView>extends BasePresenter<V>implements PresenterLoginListener<V> {
    @Override
    public void hadleLogin(String userName, String pssWord) {
        if (userName.isEmpty() && pssWord.isEmpty()) {
            getMvpView().onLoginFail();
        }
        ApiBuilder.getInstance().login(userName, pssWord).enqueue(new Callback<ResponeLogin>() {
            @Override
            public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {
                if (response.body().getStatus() == true) {
                    Log.e("TAG", "CHECK LOGIN SUCCESS!");
                    User.getInstans().setId(response.body().getData().getId());
                    if (getMvpView() != null) {
                        getMvpView().onLoginSuccess();

                    } else {
                        getMvpView().onLoginFail();
                    }
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
