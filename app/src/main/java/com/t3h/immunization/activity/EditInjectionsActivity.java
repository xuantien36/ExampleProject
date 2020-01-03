package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.t3h.immunization.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditInjectionsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        btnBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                Toast.makeText(this, "ooookkkkkk", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save:
                Toast.makeText(this, "ooookkkkkk", Toast.LENGTH_SHORT).show();

                break;
        }

    }
}
