package com.t3h.immunization.vacxin.presenter;
import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.vacxin.view.VacxinView;

public interface PresenterVacxinListener<V extends VacxinView>extends MvpPresenter<V>{
    void onshowListVacxin();
}
