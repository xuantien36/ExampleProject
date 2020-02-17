package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.forgotpass.presenter.PresenterForgot;
import com.t3h.immunization.forgotpass.view.ForgotView;
import com.t3h.immunization.respone.ResponeRegister;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ForgotActivity extends AppCompatActivity implements View.OnClickListener, ForgotView {
    @BindView(R.id.back_forgot)
    ImageView imBack;
    @BindView(R.id.btn_forgot)
    Button btnForgot;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    private ProgressDialog progressDialog;
    private PresenterForgot presenterForgot;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        presenterForgot = new PresenterForgot();
        presenterForgot.onAttach(this);
        imBack.setOnClickListener(this);
        btnForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_forgot:
                finish();
                break;
            case R.id.btn_forgot:
                progressDialog = new ProgressDialog(this,R.style.CustomDialog);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                btnForgot.setBackgroundColor(getResources().getColor(R.color.colorBG1));
                String email = edtEmail.getText().toString();
                presenterForgot.onHandleForgot(email);
                break;
        }

    }
    @Override
    public void onSuccess() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        Intent intent = new Intent(ForgotActivity.this, VerificationActivity.class);
        startActivity(intent);

    }

    @Override
    public void onFail() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        StyleableToast.makeText(this,getResources().getString(R.string.error),R.style.ColoredText).show();

    }
}
