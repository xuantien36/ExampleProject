package com.t3h.immunization.editinjection.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.editinjection.view.EditInjectionView;

public interface PresenterEditInjectionListener<V extends EditInjectionView>extends MvpPresenter<V> {
    void editInjection(String baby_id, String id, String user_id, String note, String injected_date, String medicine, int isInjected);
}
