package com.t3h.immunization.editinjection.presenter;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.editinjection.view.EditInjectionView;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.respone.ResponeRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterEditInjection<V extends EditInjectionView> extends BasePresenter<V> implements PresenterEditInjectionListener<V> {
    @Override
    public void editInjection(String baby_id, String id, String user_id, String note, String injected_date, String medicine, int isInjected) {
        if (medicine.isEmpty() || note.isEmpty()) {
            getMvpView().showToast();
        } else {
            ApiBuilder.getInstance().updateInjections(String.valueOf(GetBaby.getInstance().getBabyId()),
                    id,
                    String.valueOf(User.getInstans().getId()), note, injected_date, medicine, isInjected)
                    .enqueue(new Callback<ResponeRegister>() {

                        @Override
                        public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                            if (response.body().getStatus() == true) {
                                if (getMvpView() != null) {
                                    getMvpView().editSuccess();
                                }
                            } else {
                                getMvpView().onFail();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponeRegister> call, Throwable t) {

                        }
                    });
        }
    }
}
