package com.t3h.immunization.baby.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.baby.view.BabyView;

public interface ModelPresenterBabyListener <V extends BabyView> extends MvpPresenter<V> {
    void onshowList();
}
