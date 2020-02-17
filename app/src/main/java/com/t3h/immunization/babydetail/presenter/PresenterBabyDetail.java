package com.t3h.immunization.babydetail.presenter;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.babydetail.view.BabyDetailView;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.respone.ResponeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterBabyDetail<V extends BabyDetailView>extends BasePresenter<V>implements PresenterBabyDetailListener<V> {
    @Override
    public void deleteBaby(int babyId) {
        ApiBuilder.getInstance().deleteBaby(babyId).enqueue(new Callback<ResponeRegister>() {
            @Override
            public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                if (response.body().getStatus() == true) {
                    if (getMvpView()!=null){
                        getMvpView().deleteSuccess();
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
