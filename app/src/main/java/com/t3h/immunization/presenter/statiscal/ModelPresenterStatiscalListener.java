package com.t3h.immunization.presenter.statiscal;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.view.baby.BabyView;
import com.t3h.immunization.view.statiscal.StatiscalView;

public interface ModelPresenterStatiscalListener<V extends StatiscalView> extends MvpPresenter<V> {
    void onshowList();
}
