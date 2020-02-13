package com.t3h.immunization.presenter.vacxin;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.respone.ResponeInjections;
import com.t3h.immunization.view.vacxin.VacxinView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class PresenterVacxin<V extends VacxinView>extends BasePresenter<V>implements ModelPresenterVacxinListener<V> {
    @Override
    public void onshowListVacxin() {
        ApiBuilder.getInstance().getVaccine("vi").enqueue(new Callback<ResponeInjections>() {
            @Override
            public void onResponse(Call<ResponeInjections> call, Response<ResponeInjections> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                if (injectionGroup != null) {
                    getMvpView().onshowList(injectionGroup);
                }
            }
            @Override
            public void onFailure(Call<ResponeInjections> call, Throwable t) {
            }
        });
    }
}
