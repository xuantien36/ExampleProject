package com.t3h.immunization.other.presenter;
import com.t3h.immunization.R;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.other.model.Other;
import com.t3h.immunization.other.view.OtherView;
import java.util.ArrayList;

public class PresenterOther<V extends OtherView> extends BasePresenter<V> implements PresenterOtherListener<V> {
    private ArrayList<Other> data;

    @Override
    public void onshowListOther() {
        data = new ArrayList<>();
        data.add(new Other(R.drawable.ic_notification,context.getString(R.string.notifi)));
        data.add(new Other(R.drawable.ic_group_99,context.getString(R.string.information_note)));
        data.add(new Other(R.drawable.ic_danh_gia,context.getString(R.string.vote)));
        data.add(new Other(R.drawable.ic_share, context.getString(R.string.share_app)));
        data.add(new Other(R.drawable.ic_gop_y,context.getString(R.string.feedback)));
        data.add(new Other(R.drawable.ic_ung_dung_khac,context.getString(R.string.another_app)));
        data.add(new Other(R.drawable.ic_group_286,context.getString(R.string.change_language_title)));
        data.add(new Other(R.drawable.ic_group_720,context.getString(R.string.log_out)));
        if (getMvpView()!=null){
            getMvpView().onshowListOther(data);
        }

    }
}
