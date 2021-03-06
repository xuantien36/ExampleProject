package com.t3h.immunization.forgotpass.presenter;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.forgotpass.view.ForgotView;
import com.t3h.immunization.respone.ResponeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterForgot<V extends ForgotView>extends BasePresenter<V>implements PresenterForgotListener<V> {

    @Override
    public void onHandleForgot(String email) {
        ApiBuilder.getInstance().forgot(email).enqueue(new Callback<ResponeRegister>() {
            @Override
            public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                if (response.body().getStatus()==true){
                    if (getMvpView()!=null){
                        getMvpView().onSuccess();
                    }

                }else {
                    getMvpView().onFail();
                }

            }
            @Override
            public void onFailure(Call<ResponeRegister> call, Throwable t) {

            }
        });

    }

    }

