package com.t3h.immunization.fragment;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.immunization.R;
import com.t3h.immunization.activity.ChangeLanguageActivity;
import com.t3h.immunization.activity.FeedbackActivity;
import com.t3h.immunization.activity.LoginActivity;
import com.t3h.immunization.activity.NotificationActivity;
import com.t3h.immunization.adapter.OtherAdapter;
import com.t3h.immunization.model.Other;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherFragment extends Fragment implements OtherAdapter.ItemClickListener,View.OnClickListener {
    private ArrayList<Other> data;
    private OtherAdapter adapter;
    @BindView(R.id.lv_other)
    RecyclerView recyclerView;
    private  Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new Other(R.drawable.ic_notification,getActivity().getString(R.string.notification)));
        data.add(new Other(R.drawable.ic_group_99,getActivity().getString(R.string.information_note)));
        data.add(new Other(R.drawable.ic_danh_gia,getActivity().getString(R.string.vote)));
        data.add(new Other(R.drawable.ic_share,getActivity().getString(R.string.share_app)));
        data.add(new Other(R.drawable.ic_gop_y,getActivity().getString(R.string.feedback)));
        data.add(new Other(R.drawable.ic_ung_dung_khac,getActivity().getString(R.string.another_app)));
        data.add(new Other(R.drawable.ic_group_286,getActivity().getString(R.string.change_language_title)));
        data.add(new Other(R.drawable.ic_group_720,getActivity().getString(R.string.log_out)));
        adapter.setData(data);
    }
    private void initView() {
        adapter = new OtherAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);

    }

    @Override
    public void onClicked(int position) {
        switch (position){
            case 0:
                Intent intent=new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
                break;
            case 1:
                showDialogInfor();
                break;
            case 2:
                break;
            case 3:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
                startActivity(shareIntent);
                break;
            case 4:
                Intent it=new Intent(getContext(), FeedbackActivity.class);
                startActivity(it);
                break;
            case 5:
                Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=SuSu+Studio");
                Intent ui = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(ui);
                break;
            case 6:
                Intent language =new Intent(getContext(), ChangeLanguageActivity.class);
                startActivity(language);
                break;
            case 7:
                Intent intent1=new Intent(getContext(), LoginActivity.class);
                startActivity(intent1);
                break;
        }

    }
    @Override
    public void onLongClicked(int position) {

    }
    public void showDialogInfor(){
        dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog_information);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button btnOk=dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        dialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                dialog.dismiss();
                break;
        }

    }
}

