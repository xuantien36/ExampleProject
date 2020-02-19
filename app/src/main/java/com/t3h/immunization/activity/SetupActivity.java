package com.t3h.immunization.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.muddzdev.styleabletoast.StyleableToast;
import com.t3h.immunization.R;
import com.t3h.immunization.basemvp.BaseActivity;
import com.t3h.immunization.setup.presenter.PresenterSetup;
import com.t3h.immunization.setup.view.SetupView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SetupActivity extends BaseActivity<PresenterSetup> implements View.OnClickListener, SetupView {
    @BindView(R.id.back_setup)
    ImageView imSetup;
    @BindView(R.id.btn_setup)
    Button btnSetup;
    @BindView(R.id.edt_password)
    EditText edtPass;
    @BindView(R.id.confirm_password)
    EditText edtConfirm;
    private ProgressDialog progressDialog;
    private Handler handler=new Handler();
    @Override
    protected PresenterSetup loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initListener() {
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setup;
    }
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        imSetup.setOnClickListener(this);
        btnSetup.setOnClickListener(this);
        mPresenter=new PresenterSetup();
        mPresenter.onAttach(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_setup:
                finish();
                break;
            case R.id.btn_setup:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(R.string.message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                btnSetup.setBackgroundColor(getResources().getColor(R.color.colorBG1));
                String confirm = edtConfirm.getText().toString();
                String pass = edtPass.getText().toString();
                confirm.equals(pass);
                mPresenter.onchangePass(pass);

                    break;
                }
        }
    @Override
    public void onSetupSuccess() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        Intent intent=new Intent(SetupActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onSetupFail() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        StyleableToast.makeText(this,getResources().getString(R.string.error),R.style.ColoredText).show();

    }
}

