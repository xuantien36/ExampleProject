package com.t3h.immunization.statiscal.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.statiscal.view.StatiscalView;

public interface ModelPresenterStatiscalListener<V extends StatiscalView> extends MvpPresenter<V> {
    void onshowList();
}