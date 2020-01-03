package com.t3h.immunization.fragment;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.Resource;
import com.t3h.immunization.R;
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
        data.add(new Other(R.drawable.ic_reset,"Reset Ứng Dụng"));
        data.add(new Other(R.drawable.ic_notification,"Cài Đặt Thông Báo"));
        data.add(new Other(R.drawable.ic_group_99,"Thông Tin Lưu Ý"));
        data.add(new Other(R.drawable.ic_danh_gia,"Đánh Giá Ứng Dụng"));
        data.add(new Other(R.drawable.ic_share,"Chia Sẻ Ứng Dụng"));
        data.add(new Other(R.drawable.ic_gop_y,"Góp Ý Ứng Dụng"));
        data.add(new Other(R.drawable.ic_ung_dung_khac,"Ứng Dụng Khác"));
        data.add(new Other(R.drawable.ic_group_286,"Ngôn Ngữ"));
        data.add(new Other(R.drawable.ic_group_720,"Đăng Xuất"));
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
                showDialog();
                break;
            case 1:
                Intent intent=new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
                break;
            case 2:
                showDialogInfor();
                break;
            case 3:

                break;
            case 4:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
                startActivity(shareIntent);
                break;
            case 5:
                Intent it=new Intent(getContext(), FeedbackActivity.class);
                startActivity(it);
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                Intent intent1=new Intent(getContext(), LoginActivity.class);
                startActivity(intent1);
                break;
        }

    }
    @Override
    public void onLongClicked(int position) {

    }
    public void showDialog(){
        dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog_reset);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button btn_No = dialog.findViewById(R.id.btn_no);
        Button btn_Agree = dialog.findViewById(R.id.bnt_agree);
        btn_No.setOnClickListener(this);
        btn_Agree.setOnClickListener(this);
        dialog.show();
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
            case R.id.btn_no:
                dialog.dismiss();
                break;
            case R.id.bnt_agree:
                break;
            case R.id.btn_ok:
                dialog.dismiss();
                break;
        }

    }
}

