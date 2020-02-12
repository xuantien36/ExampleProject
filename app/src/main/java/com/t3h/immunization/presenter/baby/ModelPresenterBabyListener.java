package com.t3h.immunization.presenter.baby;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.view.baby.BabyView;

public interface ModelPresenterBabyListener <V extends BabyView> extends MvpPresenter<V> {
    void onshowList();
}
