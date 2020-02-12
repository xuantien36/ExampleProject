package com.t3h.immunization.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.ChangeLanguageActivity;
import com.t3h.immunization.view.login.LoginActivity;
import com.t3h.immunization.activity.NotificationActivity;
import com.t3h.immunization.adapter.OtherAdapter;
import com.t3h.immunization.model.Other;
import com.t3h.immunization.utils.SaveData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherFragment extends Fragment implements OtherAdapter.ItemClickListener, View.OnClickListener {
    private ArrayList<Other> data;
    private OtherAdapter adapter;
    @BindView(R.id.lv_other)
    RecyclerView recyclerView;
    private Dialog dialog;
    private Handler handler=new Handler();
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_fragment, container, false);
        SaveData.updateLangua(getContext());
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
        data.add(new Other(R.drawable.ic_notification, getActivity().getString(R.string.notification)));
        data.add(new Other(R.drawable.ic_group_99, getActivity().getString(R.string.information_note)));
        data.add(new Other(R.drawable.ic_danh_gia, getActivity().getString(R.string.vote)));
        data.add(new Other(R.drawable.ic_share, getActivity().getString(R.string.share_app)));
        data.add(new Other(R.drawable.ic_gop_y, getActivity().getString(R.string.feedback)));
        data.add(new Other(R.drawable.ic_ung_dung_khac, getActivity().getString(R.string.another_app)));
        data.add(new Other(R.drawable.ic_group_286, getActivity().getString(R.string.change_language_title)));
        data.add(new Other(R.drawable.ic_group_720, getActivity().getString(R.string.log_out)));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },1000);
        adapter.setData(data);

    }

    private void initView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getActivity().getString(R.string.message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        adapter = new OtherAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);

    }

    @Override
    public void onClicked(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
                break;
            case 1:
                showDialogInfor();
                break;
            case 2:
              Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=vnshine.com.sotiemchung");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case 3:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Ứng dụng 'Sổ Tiêm Phòng - " +
                        "MamaCare' là ứng dụng giúp cho bạn dễ dàng hơn trong việc quản lý lịch tiêm phòng của bé. " +
                        "Mamacare có nhiều chức năng hữu ích như: Ghi lại nhật ký các mũi tiêm: tên vắc xin, ngày tiêm, ghi chú. " +
                        "Thống kê tất cả các mũi tiêm ở 3 trạng thái: Đã tiêm, Chưa tiêm, Bõ lỡ." +
                        " Bạn sẽ được thông báo mũi tiêm sắp tới để không bỏ lỡ bất kỳ mũi tiêm nào của bé. " +
                        "Bạn có thể xem thông tin về vắc xin, lịch tiêm chủng." +
                        " Tải miễn phí tại 'https://play.google.com/store/apps/details?id=vnshine.com.sotiemchung'");
                startActivity(shareIntent);
                break;
            case 4:
                feedback(getContext(), "mamacarestudio@gmail.com", "abc", "xyz");
                break;
            case 5:
                Uri uri = Uri.parse("http://api.mamacare.info/docs/otherapp.htm");
                Intent ui = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(ui);
                break;
            case 6:
                Intent language = new Intent(getContext(), ChangeLanguageActivity.class);
                startActivity(language);
                break;
            case 7:
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }

    }
    public static void feedback(Context context, String email, String chude, String noidung) {
        Uri uri = Uri.parse("mailto:" + email);
        final Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_CC, chude);
        intent.putExtra(Intent.EXTRA_SUBJECT, noidung);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (infos == null || infos.size() <= 0) {
            Toast.makeText(context, "No email application", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(intent);
    }
    @Override
    public void onLongClicked(int position) {

    }

    public void showDialogInfor() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog_information);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button btnOk = dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        dialog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                dialog.dismiss();
                break;
        }

    }
}

