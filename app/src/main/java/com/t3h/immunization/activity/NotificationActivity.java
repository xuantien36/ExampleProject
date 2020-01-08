package com.t3h.immunization.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.t3h.immunization.R;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_notification)
    ImageView imBack;
    @BindView(R.id.save)
    ImageView imSave;
    @BindView(R.id.radio_on_time)
    RadioButton radio_OnTime;
    @BindView(R.id.radio_before)
    RadioButton radio_Before;
    private Handler handler = new Handler();
    private Dialog dialog;
    @BindView(R.id.tv_Time)
    TextView tvTime;
    private SharedPreferences sharedPreferences;
    private int ihours, iminute;
    String timeSet;
    @BindView(R.id.status)
    Switch status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("SaveNotification", Context.MODE_PRIVATE);
        tvTime.setText(sharedPreferences.getString("time", ""));
        radio_OnTime.setChecked(sharedPreferences.getBoolean("onTime", true));
        radio_Before.setChecked(sharedPreferences.getBoolean("before", false));
        status.setChecked(sharedPreferences.getBoolean("status", false));
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        tvTime.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            ihours = calendar.get(Calendar.HOUR_OF_DAY);
            iminute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog;
            timePickerDialog = new TimePickerDialog(NotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    ihours = i;
                    iminute = i1;
                    timeSet = new StringBuffer().append((ihours)).append(":").append(iminute).toString();
                    tvTime.setText(timeSet);

                }
            }, ihours, iminute, true);
            timePickerDialog.show();

        });
        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("status", true);
                    editor.commit();

                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("status");
                    editor.commit();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_notification:
                finish();
                break;

            case R.id.save:
                showDialog();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("time", timeSet);
                editor.putBoolean("onTime", radio_OnTime.isChecked());
                editor.putBoolean("before", radio_Before.isChecked());
                editor.commit();
                break;

            case R.id.btn_close:
                dialog.dismiss();
                break;
            case R.id.btn_agree:
        }
    }

    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_sending);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        finish();
    }
}

