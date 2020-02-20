package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.babydetail.presenter.PresenterBabyDetail;
import com.t3h.immunization.babydetail.view.BabyDetailView;
import com.t3h.immunization.basemvp.BaseActivity;

import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;


public class BabyInformationActivity extends BaseActivity<PresenterBabyDetail> implements View.OnClickListener, BabyDetailView {
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.im_baby)
    ImageView imBaby;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_name_baby)
    TextView tvName;
    private GetBaby baBy;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    private Dialog dialog;
    @BindView(R.id.im_edit)
    ImageView imEdit;
    @BindView(R.id.image_gender)
    ImageView imageGender;
    private Handler handler=new Handler();



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.btn_delete:
                deleteBaby();
                break;
            case R.id.im_edit:
                Intent intent = new Intent(this, EditBaByActivity.class);
                intent.putExtra("data", baBy);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void deleteBaby() {
        mPresenter.deleteBaby(baBy.getBabyId());
    }
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(BabyInformationActivity.this);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

            }
        },2000);
        dialog.setContentView(R.layout.custom_dialog_delete);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void deleteSuccess() {

        showDialog();
    }
    @Override
    public void onFail() {
        StyleableToast.makeText(this,getResources().getString(R.string.error),R.style.ColoredText).show();

    }

    @Override
    protected PresenterBabyDetail loadPresenter() {
        return new PresenterBabyDetail();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        baBy = (GetBaby) intent.getSerializableExtra("baby");
        Log.e("detail", "init: " + baBy.getName());
        tvBirthday.setText(baBy.getBirthday());
        tvName.setText(baBy.getName());
        tvNote.setText("'' " + baBy.getNote() + " ''");
        if (baBy.getGender().equalsIgnoreCase("Nam")) {
            imBaby.setImageResource(R.drawable.group_730);
            imageGender.setImageResource(R.drawable.ic_nam);

        } else {
            imBaby.setImageResource(R.drawable.group_731);
            imageGender.setImageResource(R.drawable.ic_nu);
        }
        imBack.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        imEdit.setOnClickListener(this);
        mPresenter.onAttach(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baby_information;
    }
}
