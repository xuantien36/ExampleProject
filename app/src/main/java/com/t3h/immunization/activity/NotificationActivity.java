package com.t3h.immunization.activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.t3h.immunization.R;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.statiscal.model.Injections;
import com.t3h.immunization.vacxin.model.InjectionGroup;
import com.t3h.immunization.login.model.User;
import com.t3h.immunization.respone.BaByRespone;
import com.t3h.immunization.respone.ResponeStatistical;
import com.t3h.immunization.utils.AlarmReceiver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_notification)
    ImageView imBack;
    @BindView(R.id.save)
    ImageView imSave;
    @BindView(R.id.radio_on_time)
    RadioButton radio_OnTime;
    @BindView(R.id.radio_before)
    RadioButton radio_Before;
    private Dialog dialog;
    @BindView(R.id.tv_Time)
    TextView tvTime;
    private SharedPreferences sharedPreferences;
    private int ihours, iminute;
    String timeSet;
    @BindView(R.id.status)
    Switch status;
    private Handler handler = new Handler();
    String currentTime;
    String checkedBox;
    @BindView(R.id.radioGroup)
    RadioGroup group;
    int mDay, mMonth, mYear;
    private Calendar mCalendar;
    private List<String> section = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        callApiData();
//        callApi();
        currentTime = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        Log.e("time", "init: " + currentTime);
        tvTime.setText(currentTime + "");
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        sharedPreferences = getSharedPreferences("SaveNotification", Context.MODE_PRIVATE);
        tvTime.setText(sharedPreferences.getString("time", tvTime.getText().toString()));
        radio_OnTime.setChecked(sharedPreferences.getBoolean("onTime", false));
        radio_Before.setChecked(sharedPreferences.getBoolean("before", false));
        status.setChecked(sharedPreferences.getBoolean("status", false));
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int checkedRadio = radioGroup.getCheckedRadioButtonId();
                RadioButton checkedRadioButton = findViewById(checkedRadio);
                checkedBox = checkedRadioButton.getText().toString();
                if ((checkedBox).equals(radio_OnTime.getText().toString())) {
                    checkedBox = currentDateTime;

                    mDay = Integer.valueOf(checkedBox.split("/")[0]);
                    mMonth = Integer.valueOf(checkedBox.split("/")[1]);
                    mYear = Integer.valueOf(checkedBox.split("/")[2]);
                } else {
                    long milis = ((System.currentTimeMillis()) - ((24 * 60 * 60 * 1000)));
                    checkedBox = getDate(milis, "dd/MM/yyyy");
                    mDay = Integer.valueOf(checkedBox.split("/")[0]);
                    mMonth = Integer.valueOf(checkedBox.split("/")[1]);
                    mYear = Integer.valueOf(checkedBox.split("/")[2]);
                }
            }
        });
        tvTime.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            ihours = calendar.get(Calendar.HOUR_OF_DAY);
            iminute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog;
            timePickerDialog = new TimePickerDialog(NotificationActivity.this, (timePicker, i, i1) -> {
                ihours = i;
                iminute = i1;
                timeSet = new StringBuffer().append((ihours)).append(" : ").append(iminute).toString();
                tvTime.setText(timeSet);

            }, ihours, iminute, true);
            timePickerDialog.show();
        });
        status.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("status", true);
                editor.commit();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("status");
                editor.commit();
            }
        });
    }
    public void callApi() {
        ApiBuilder.getInstance().getinjected(GetBaby.getInstance().getBabyId()).enqueue(new Callback<ResponeStatistical>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ResponeStatistical> call, Response<ResponeStatistical> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                List<Injections> data = response.body().getData();
                for (Injections injections : data) {
                    if (injections.getIsInjected().equalsIgnoreCase("0")) {
                        long temp = (getMilliFromDate(GetBaby.getInstance().getBirthday()) +
                                (Long.parseLong(String.valueOf(Long.parseLong(injections.getDate()) *
                                        Long.parseLong("" + (24 * 60 * 60 * 1000))))));
                        String dateInjection = getDate(temp, "dd/MM/yyyy");
                        section.add(dateInjection);
                        Log.e("data:::::", "onResponse: " + section);
                    }

                }
            }
            @Override
            public void onFailure(Call<ResponeStatistical> call, Throwable t) {

            }
        });
    }
    public void callApiData(){
        ApiBuilder.getInstance().getBaBy(User.getInstans().getId()).enqueue(new Callback<BaByRespone>() {
            @Override
            public void onResponse(Call<BaByRespone> call, Response<BaByRespone> response) {
                List<GetBaby> data = response.body().getData();
                for (GetBaby baby:data){
                    Log.e("sssss::::", "onResponse: "+baby.getName() );
                    Log.e("bbbbb::::", "onResponse: "+baby.getBirthday() );
                }
            }

            @Override
            public void onFailure(Call<BaByRespone> call, Throwable t) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_notification:
                finish();
                break;

            case R.id.save:
                showDialog();

                Log.e("testttt:::", String.valueOf(mMonth));
                Log.e("ihours:::", String.valueOf(ihours));
                Log.e("iminute:::", String.valueOf(iminute));
                mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.MONTH, --mMonth);
                mCalendar.set(Calendar.YEAR, mYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, ihours);
                mCalendar.set(Calendar.MINUTE, iminute);
                mCalendar.set(Calendar.SECOND, 0);
                new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("time", timeSet);
                editor.putBoolean("onTime", radio_OnTime.isChecked());
                editor.putBoolean("before", radio_Before.isChecked());
                editor.commit();
                break;
        }
    }

    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(NotificationActivity.this);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

            }
        }, 2000);
        dialog.setContentView(R.layout.custom_dialog_sending);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public long getMilliFromDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date);
        return date.getTime();
    }

}


