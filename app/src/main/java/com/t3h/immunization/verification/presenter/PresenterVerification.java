package com.t3h.immunization.verification.presenter;

import android.content.Intent;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.activity.SetupActivity;
import com.t3h.immunization.activity.VerificationActivity;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.respone.ResponeRegister;
import com.t3h.immunization.verification.view.VerificationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterVerification<V extends VerificationView>extends BasePresenter<V>implements PresenterVerificationListener<V> {


    @Override
    public void handleVerification(String code) {
        ApiBuilder.getInstance().verifycode(code).enqueue(new Callback<ResponeRegister>() {
            @Override
            public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                if (response.body().getStatus() == true){
                    if (getMvpView()!=null){
                        getMvpView().onVerificationSuccess();
                    }
                }else {
                    getMvpView().onVerificationFail();
                }
            }
            @Override
            public void onFailure(Call<ResponeRegister> call, Throwable t) {

            }
        });

    }
}
