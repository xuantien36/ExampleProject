package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.t3h.immunization.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView imBack;
    @BindView(R.id.sending)
    ImageView imSending;
    private Handler handler=new Handler();

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

    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.back:
              finish();
              break;
          case R.id.sending:
              showDialog();
              break;
      }

    }
    public void showDialog(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_sending);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                finish();
            }
        },2000);
        dialog.show();

    }
}
