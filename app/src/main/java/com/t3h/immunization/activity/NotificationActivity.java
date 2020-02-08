package com.t3h.immunization.activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
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
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.t3h.immunization.R;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.Injections;
import com.t3h.immunization.utils.AlarmReceiver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
    private Dialog dialog;
    @BindView(R.id.tv_Time)
    TextView tvTime;
    private SharedPreferences sharedPreferences;
    private int ihours, iminute;
    String timeSet;
    @BindView(R.id.status)
    Switch status;
    private Handler handler=new Handler();
    String currentTime;
    String checkedBox;
    @BindView(R.id.radioGroup)
    RadioGroup group;
    long time;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
       currentTime  = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        Log.e("time", "init: "+currentTime );
        tvTime.setText(currentTime+"");
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        sharedPreferences = getSharedPreferences("SaveNotification", Context.MODE_PRIVATE);
        tvTime.setText(sharedPreferences.getString("time",tvTime.getText().toString()));
        radio_OnTime.setChecked(sharedPreferences.getBoolean("onTime", true));
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
                    Toast.makeText(NotificationActivity.this, "" + checkedBox, Toast.LENGTH_SHORT).show();

                } else {
                    long milis = ((System.currentTimeMillis())-((24*60*60*1000)));
                    checkedBox = getDate(milis,"dd/MM/yyyy") ;
                    Toast.makeText(NotificationActivity.this, "" +getDate(milis,"dd/MM/yyyy"), Toast.LENGTH_SHORT).show();

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
                showNotification();
//                showNotification(this,"Lịch tiêm phòng cho bé : "+ GetBaby.getInstance().getName(),"Ngày"+checkedBox);
                editor.commit();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("status");
                editor.commit();
            }
        });
        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarm){
            Intent itAlarm = new Intent("ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,itAlarm,0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),60000, pendingIntent);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public  void showNotification() {
        ArrayList<Injections> data =new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        if ((tvTime.getText().toString()).equals(currentTime) && status.isChecked()) {
            if (radio_OnTime.isChecked()) {
                for (Injections injections:data){
                    if (((Long.parseLong(injections.getDate())*(24*60*60*1000) +
                            GetBaby.getInstance().getBirthday()).equals(checkedBox))){
                        showNotification(this,"Lịch tiêm phòng cho bé : "+ GetBaby.getInstance().getName(),"Ngày "+checkedBox
                        );
                       time = Long.parseLong((Long.parseLong(injections.getDate())*(24*60*60*1000) +
                               (GetBaby.getInstance().getBirthday())));
                        Log.e("show", "showNotification:4 "+ injections.getDate());
                    }

                }
            }else {
                showNotification(this,"Lịch tiêm phòng cho bé : "+ GetBaby.getInstance().getName(),"Ngày "+checkedBox
                );
            }
        }
        Log.e("show", "showNotification: 1 " + tvTime.getText().toString());
        Log.e("show", "showNotification: 2 " + currentTime);
        Log.e("show", "showNotification: 3 " + currentDateTime);


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
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("time", timeSet);
                editor.putBoolean("onTime", radio_OnTime.isChecked());
                editor.putBoolean("before", radio_Before.isChecked());
                showNotification(this,"Lịch tiêm phòng cho bé : "+ GetBaby.getInstance().getName(),"Ngày "+checkedBox
                );
                editor.commit();
                break;
        }
    }
    public void showDialog() {

        if (dialog==null){
            dialog = new Dialog(NotificationActivity.this);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

            }
        },2000);
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
    private void handleNotification() {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(Context context, String title, String body) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri);
        Intent intent = new Intent(context, CategoriActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());

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


