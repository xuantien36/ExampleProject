package com.t3h.immunization.other.presenter;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.t3h.immunization.MyApplication;
import com.t3h.immunization.R;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.other.model.Other;
import com.t3h.immunization.other.view.OtherView;

import java.util.ArrayList;

public class PresenterOther<V extends OtherView> extends BasePresenter<V> implements ModelPresenterOtherListener<V> {
    private ArrayList<Other> data;

    @Override
    public void onshowListOther() {
        data = new ArrayList<>();
        data.add(new Other(R.drawable.ic_notification,MyApplication.getInstance().getString(R.string.notification)));
        data.add(new Other(R.drawable.ic_group_99,context.getString(R.string.notification)));
        data.add(new Other(R.drawable.ic_danh_gia, MyApplication.getInstance().getString(R.string.vote)));
        data.add(new Other(R.drawable.ic_share, MyApplication.getInstance().getString(R.string.share_app)));
        data.add(new Other(R.drawable.ic_gop_y,MyApplication.getInstance().getString(R.string.feedback)));
        data.add(new Other(R.drawable.ic_ung_dung_khac, MyApplication.getInstance().getString(R.string.another_app)));
        data.add(new Other(R.drawable.ic_group_286,MyApplication.getInstance().getString(R.string.change_language_title)));
        data.add(new Other(R.drawable.ic_group_720, MyApplication.getInstance().getString(R.string.log_out)));
        if (getMvpView()!=null){
            getMvpView().onshowListOther(data);
            Log.e("other", "onshowListOther: "+data );
        }

    }
}
