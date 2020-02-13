package com.t3h.immunization.bookinjection.presenter;

import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.bookinjection.view.InjectionBookView;

public interface ModelPresenterBookInjectionListener<V extends InjectionBookView>extends MvpPresenter<V> {
    void  onshowList();
}
