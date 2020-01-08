package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.t3h.immunization.R;
import com.t3h.immunization.util.AppPreferences;
import com.t3h.immunization.util.Constant;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, AppPreferences.getInstance(getApplicationContext())
                        .getBoolean(Constant.KEY_NEXT )? LoginActivity.class : WelcomeActivity.class));

            }
        },3000);
    }
}
