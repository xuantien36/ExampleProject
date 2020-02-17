package com.t3h.immunization.babydetail.presenter;

import com.t3h.immunization.babydetail.view.BabyDetailView;
import com.t3h.immunization.basemvp.MvpPresenter;

public interface PresenterBabyDetailListener<V extends BabyDetailView>extends MvpPresenter<V> {
    void deleteBaby(int babyId);

}
