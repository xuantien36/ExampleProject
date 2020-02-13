package com.t3h.immunization.other.view;
import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.other.model.Other;
import java.util.ArrayList;

public interface OtherView extends MvpView {
    void onshowListOther(ArrayList<Other> data);
}
