package com.t3h.immunization.bookinjection.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.respone.ResponeStatistical;
import com.t3h.immunization.bookinjection.view.InjectionBookView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterJnjectionBook<V extends InjectionBookView>extends
        BasePresenter<V>implements ModelPresenterBookInjectionListener<V> {
    @Override
    public void onshowList() {
        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
                if (response.body().getStatus() == true) {
                    getMvpView().onshowList(response.body().getInjectionGroup(),response.body().getData());

                }
            }
            @Override
            public void onFailure(Call<ResponeStatistical> call, Throwable t) {
            }
        });

    }
}
