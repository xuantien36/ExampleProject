package com.t3h.immunization.presenter.bookinjection;

import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.basemvp.MvpPresenter;
import com.t3h.immunization.view.bookinjection.InjectionBookView;

public interface ModelPresenterBookInjectionListener<V extends InjectionBookView>extends MvpPresenter<V> {
    void  onshowList();
}
