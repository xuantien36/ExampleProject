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
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.MvpView;
import com.t3h.immunization.respone.ResponeRegister;
import com.t3h.immunization.verification.presenter.PresenterVerification;
import com.t3h.immunization.verification.view.VerificationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener, VerificationView {
    @BindView(R.id.back_verification)
    ImageView imVerification;
    @BindView(R.id.btn_verification)
    Button btnVerification;
    @BindView(R.id.edt_verification)
    EditText edtVerification;
    private ProgressDialog progressDialog;
    private PresenterVerification verification;
    private Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        imVerification.setOnClickListener(this);
        btnVerification.setOnClickListener(this);
        verification=new PresenterVerification();
        verification.onAttach(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_verification:
                finish();
                break;
            case R.id.btn_verification:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                btnVerification.setBackgroundColor(getResources().getColor(R.color.colorBG1));
                String code =edtVerification.getText().toString();
                verification.handleVerification(code);

                break;
        }

    }

    @Override
    public void onVerificationSuccess() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        Intent intent=new Intent(VerificationActivity.this,SetupActivity.class);
        startActivity(intent);
    }

    @Override
    public void onVerificationFail() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        StyleableToast.makeText(this,getResources().getString(R.string.error),R.style.ColoredText).show();

    }
}
