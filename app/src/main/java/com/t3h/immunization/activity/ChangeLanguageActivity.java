package com.t3h.immunization.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.t3h.immunization.R;
import com.t3h.immunization.utils.SaveData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeLanguageActivity extends AppCompatActivity {
    String lang = "";
    String country = "";
    @BindView(R.id.radioGroup_language)
    RadioGroup radioGroup;
    //    @BindView(R.id.radio_vietnamese)
//    RadioButton vi;
//    @BindView(R.id.radio_english)
//    RadioButton en;
    @BindView(R.id.im_back_language)
    ImageView imLanguageBack;
    private SharedPreferences sharedPreferences;
    @BindView(R.id.radio_english)
    RadioButton radioButton_En;
    @BindView(R.id.radio_vietnamese)
    RadioButton radioButton_Vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        ButterKnife.bind(this);
        init();
//        sharedPreferences=getSharedPreferences("Save",MODE_PRIVATE);
//        radioButton_En.setChecked(sharedPreferences.getBoolean("check_en", false));
//        radioButton_Vi.setChecked(sharedPreferences.getBoolean("check_vi", true));

//        radioButton_Vi.setOnCheckedChangeListener(listenerRadio);
//        radioButton_En.setOnCheckedChangeListener(listenerRadio);

    }
    private void init() {
        sharedPreferences = getSharedPreferences("SaveChecked", Context.MODE_PRIVATE);
        radioButton_En.setChecked(sharedPreferences.getBoolean("check_vi", false));
        radioButton_Vi.setChecked(sharedPreferences.getBoolean("check_en", false));
        imLanguageBack.setOnClickListener(v -> finish());
    }

    public void change(View view) {
        int idChecked = radioGroup.getCheckedRadioButtonId();
        switch (idChecked) {
            case R.id.radio_english:
                lang = "en";
                country = "en";
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("check_en", radioButton_En.isChecked());
                editor.commit();
                Toast.makeText(this, ""+ radioButton_En.isChecked(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ChangeLanguageActivity.this, CategoriActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

                break;
            case R.id.radio_vietnamese:
                lang = "vi";
                country = "vi";
                SharedPreferences.Editor editor_vi = sharedPreferences.edit();
                editor_vi.putBoolean("check_vi", radioButton_Vi.isChecked());
                editor_vi.commit();
                Toast.makeText(this, ""+radioButton_Vi.isChecked(), Toast.LENGTH_SHORT).show();
                Intent t = new Intent(ChangeLanguageActivity.this, CategoriActivity.class);
                t.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(t);
                break;
        }
        SaveData.savingPreferences(getApplicationContext(), "lang", lang);
        SaveData.savingPreferences(getApplicationContext(), "country", country);
        SaveData.updateLangua(getApplicationContext());
    }
    CompoundButton.OnCheckedChangeListener listenerRadio = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Intent intent = new Intent(ChangeLanguageActivity.this, CategoriActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("checked",isChecked);
            editor.commit();
            Toast.makeText(ChangeLanguageActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();

        }
    };
}
