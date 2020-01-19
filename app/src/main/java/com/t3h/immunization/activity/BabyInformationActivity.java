package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.respone.ResponeRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyInformationActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_information);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        baBy = (GetBaby) intent.getSerializableExtra("baby");
        tvBirthday.setText(baBy.getBirthday());
        tvName.setText(baBy.getName());
        tvNote.setText(baBy.getNote());
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
    }

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
        ApiBuilder.getInstance().deleteBaby(baBy.getBabyId()).enqueue(new Callback<ResponeRegister>() {
            @Override
            public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                if (response.body().getStatus() == true) {
                    showDialog();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponeRegister> call, Throwable t) {

            }
        });
    }

    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_delete);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
