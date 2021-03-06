package com.t3h.immunization.register.presenter;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.register.view.RegisterView;
import com.t3h.immunization.respone.ResponeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterRegister<V extends RegisterView> extends BasePresenter<V> implements PresenterRegisterListener<V> {
    @Override
    public void receiedHandleRegister(String user_name, String name, String password, String phone, String email) {
        ApiBuilder.getInstance().register(user_name, password, "", name, phone, email, "", "")
                .enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus() == true) {
                            if (getMvpView() != null) {
                                getMvpView().onRegisterSuccess();
                            }
                        } else {
                            getMvpView().onRegisterFail();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeRegister> call, Throwable t) {
                    }
                });
    }

}
