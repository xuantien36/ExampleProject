package com.t3h.immunization.addbaby.presenter;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.addbaby.view.AddbabyView;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.respone.ResponeRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterAddBaby<V extends AddbabyView>extends BasePresenter<V>implements PresenterAddListener<V> {
    @Override
    public void onAddBaby(int id, String name, String gender, String birthday,
                          String link_avatar, String note, boolean isSavedOnServer) {
        if (name.isEmpty() || gender.isEmpty() || birthday.isEmpty()) {
            getMvpView().addFail();
        } else {
            ApiBuilder.getInstance().addBaby(User.getInstans().getId(), name, gender, birthday, "", note, true).enqueue(new Callback<ResponeRegister>() {
                @Override
                public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                    if (response.body().getStatus() == true) {
                        if (getMvpView() != null) {
                            getMvpView().addSuccess();
                        }

                    } else {
                        getMvpView().addFail();
                    }
                }
                @Override
                public void onFailure(Call<ResponeRegister> call, Throwable t) {

                }
            });

        }
    }
}

