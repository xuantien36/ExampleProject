package com.t3h.immunization.addbaby.presenter;

import com.t3h.immunization.addbaby.view.AddbabyView;
import com.t3h.immunization.basemvp.MvpPresenter;

import retrofit2.http.Field;

public interface PresenterAddListener<V extends AddbabyView> extends MvpPresenter<V> {
    void onAddBaby(int id, String name, String gender, String birthday, String link_avatar, String note, boolean isSavedOnServer);
}
