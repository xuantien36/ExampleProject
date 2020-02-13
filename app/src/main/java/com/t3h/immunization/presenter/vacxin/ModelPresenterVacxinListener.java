package com.t3h.immunization.presenter.vacxin;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.view.vacxin.VacxinView;

public interface ModelPresenterVacxinListener<V extends VacxinView>extends MvpPresenter<V>{
    void onshowListVacxin();
}
