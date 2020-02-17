package com.t3h.immunization.editbaby.presenter;

import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.editbaby.view.EditBabyView;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.respone.ResponeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterEditBaby<V extends EditBabyView> extends BasePresenter<V> implements PresenterEditBabyListener<V> {
    @Override
    public void onEditBaby(int id, int baby_id, String name, String gender, String birthday, String link_avatar, String note) {
        ApiBuilder.getInstance().editBaby(User.getInstans().getId(), baby_id, name, gender, birthday, "", note)
                .enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.body().getStatus() == true) {
                            if (getMvpView()!=null){
                                getMvpView().editSuccess();
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



