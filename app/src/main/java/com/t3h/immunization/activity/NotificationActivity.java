package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import com.t3h.immunization.R;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView imBack;
    @BindView(R.id.save)
    ImageView imSave;
    @BindView(R.id.radio_on_time)
    RadioButton radio_OnTime;
    @BindView(R.id.radio_before)
    RadioButton radio_Before;
    private Handler handler = new Handler();
    private Dialog dialog;
    private NumberPicker hour;
    private NumberPicker minute;
    private Button btnClose, btnAgree;
    @BindView(R.id.tv_Time)
    TextView tvTime;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                //get current date and to set popup date picker

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NotificationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date = month + "/" + day + "/" + year;

                tvTime.setText(date);

            }
        };

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.save:
                showDialog();
                finish();
                break;
//            case R.id.tv_Time:
//                showNumberpickerDialog();

            case R.id.btn_close:
                dialog.dismiss();
                break;
            case R.id.btn_agree:
                break;
        }
    }

    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_sending);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        handler.postDelayed(() -> dialog.dismiss(), 2000);
        dialog.show();
    }

    public void showTimePicker() {
        dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_notification);
        hour = dialog.findViewById(R.id.gio);
        minute = dialog.findViewById(R.id.phut);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        hour.setMaxValue(23);
        hour.setMinValue(0);
        minute.setMaxValue(59);
        minute.setMinValue(0);
        hour.setWrapSelectorWheel(false);
        minute.setWrapSelectorWheel(false);
        btnClose = dialog.findViewById(R.id.btn_close);
        btnAgree = dialog.findViewById(R.id.btn_agree);
        btnClose.setOnClickListener(this);
        btnAgree.setOnClickListener(this);
        dialog.show();

    }

    public void showNumberpickerDialog() {

        NumberPicker mynumberpicker_1 = new NumberPicker(this);
        NumberPicker mynumberpicker_2 = new NumberPicker(this);
        mynumberpicker_1.setMaxValue(23);
        mynumberpicker_1.setMinValue(0);
        mynumberpicker_2.setMaxValue(59);
        mynumberpicker_2.setMinValue(0);
        NumberPicker.OnValueChangeListener myvaluechange_2  =  new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        };
        NumberPicker.OnValueChangeListener myvaluechange_1  =  new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        };
        mynumberpicker_2.setOnValueChangedListener(myvaluechange_2);
        mynumberpicker_1.setOnValueChangedListener(myvaluechange_1);
        AlertDialog.Builder builder= new AlertDialog.Builder(this).setView(mynumberpicker_1).setView(mynumberpicker_2);
        builder.setTitle("abcddd");
        builder.setIcon(R.drawable.avatar);
        builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        builder.show();
    }
}
