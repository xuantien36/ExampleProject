package com.t3h.immunization.other.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.other.view.OtherView;

public interface PresenterOtherListener<V extends OtherView>extends MvpPresenter<V> {
    void onshowListOther();
}
