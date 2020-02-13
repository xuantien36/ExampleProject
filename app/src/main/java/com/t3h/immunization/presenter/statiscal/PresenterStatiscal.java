package com.t3h.immunization.presenter.statiscal;

import android.util.Log;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;
import com.t3h.immunization.respone.ResponeStatistical;
import com.t3h.immunization.view.statiscal.StatiscalView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterStatiscal<V extends StatiscalView>
        extends BasePresenter<V> implements ModelPresenterStatiscalListener<V> {
    @Override
    public void onshowList() {
        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
            @Override
            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                List<Injections> data = response.body().getData();
                Log.e("babyid", "onResponse: tttttt" +GetBaby.getInstance().getBabyId() );
                getMvpView().showListStatiscal(injectionGroup,data);
                Log.e("statiscal:::", "onResponse: "+injectionGroup+"\\\\\\\\"+data );
            }
            @Override
            public void onFailure(Call<ResponeStatistical> call, Throwable t) {

            }
        });

    }
    }

