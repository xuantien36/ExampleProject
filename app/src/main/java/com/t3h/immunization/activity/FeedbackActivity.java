package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.t3h.immunization.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView imBack;
    @BindView(R.id.sending)
    ImageView imSending;
    @BindView(R.id.edt_to)
    EditText edtTo;
    @BindView(R.id.nd)
    EditText edtNd;
    @BindView(R.id.chude)
    EditText edtChude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imBack.setOnClickListener(this);
        imSending.setOnClickListener(this);
        edtTo.setText("mamacarestudio@gmail.com");
        edtChude.setText("abc");
        edtNd.setText("xyz");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sending:

                showDialog();
                finish();
                break;
        }
    }

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_sending);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }



}
