package com.t3h.immunization.activity;
import android.content.Intent;
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
    @BindView(R.id.radio_vietnamese)
    RadioButton vi;
    @BindView(R.id.radio_english)
    RadioButton en;
    @BindView(R.id.im_back_language)
    ImageView imLanguageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        ButterKnife.bind(this);

        vi.setOnCheckedChangeListener(listenerRadio);
        en.setOnCheckedChangeListener(listenerRadio);
        imLanguageBack.setOnClickListener(v -> finish());

    }
    public void change(View view) {
        int idChecked = radioGroup.getCheckedRadioButtonId();
        switch (idChecked) {
            case R.id.radio_english:
                lang = "en";
                country = "en";
                en.setChecked(true);
                break;
            case R.id.radio_vietnamese:
                lang = "vi";
                country = "vi";
                vi.setChecked(true);
                break;
        }

        SaveData.savingPreferences(getApplicationContext(), "lang", lang);
        SaveData.savingPreferences(getApplicationContext(), "country", country);
        SaveData.updateLangua(getApplicationContext());

        Log.e("LANG ", lang);

    }
    CompoundButton.OnCheckedChangeListener listenerRadio = (buttonView, isChecked) -> {
        Intent intent = new Intent(ChangeLanguageActivity.this, CategoriActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    };

}
