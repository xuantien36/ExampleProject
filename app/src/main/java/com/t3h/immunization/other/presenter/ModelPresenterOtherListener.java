package com.t3h.immunization.other.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.other.view.OtherView;

public interface ModelPresenterOtherListener<V extends OtherView>extends MvpPresenter<V> {
    void onshowListOther();
}
