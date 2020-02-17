package com.t3h.immunization.editbaby.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.editbaby.view.EditBabyView;

import retrofit2.http.Field;

public interface PresenterEditBabyListener<V extends EditBabyView>extends MvpPresenter<V> {
    void onEditBaby( int id, int baby_id, String name, String gender, String birthday, String link_avatar, String note);
}
