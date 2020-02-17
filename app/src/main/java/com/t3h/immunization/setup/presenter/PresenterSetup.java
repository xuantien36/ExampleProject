package com.t3h.immunization.setup.presenter;

import android.content.Intent;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.activity.LoginActivity;
import com.t3h.immunization.activity.SetupActivity;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.respone.ResponeRegister;
import com.t3h.immunization.setup.view.SetupView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSetup<V extends SetupView>extends BasePresenter<V>implements PresenterSetupListener<V> {
    @Override
    public void onchangePass(String pass) {
        ApiBuilder.getInstance().changepass(pass).enqueue(new Callback<ResponeRegister>() {
            @Override
            public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                if (response.body().getStatus() == true) {
                    if (getMvpView()!=null){
                        getMvpView().onSetupSuccess();
                    }
                }else {
                    getMvpView().onSetupFail();

                }
            }
            @Override
            public void onFailure(Call<ResponeRegister> call, Throwable t) {

            }
        });

    }
}
