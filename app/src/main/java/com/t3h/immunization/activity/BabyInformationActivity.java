package com.t3h.immunization.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.immunization.R;
import com.t3h.immunization.model.GetBaby;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BabyInformationActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.im_edit)
    ImageView imEdit;
    @BindView(R.id.im_baby)
    ImageView imBaby;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_name_baby)
    TextView tvName;
    private GetBaby baBy;
    private int REQUEST_CODE = 1;
    String name;
    String note;
    @BindView(R.id.tv_note)
    TextView tvNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_information);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        baBy = (GetBaby) intent.getSerializableExtra("baby");
        tvBirthday.setText(baBy.getBirthday());
        tvName.setText(baBy.getName());
        tvNote.setText(baBy.getNote());
        imBack.setOnClickListener(this);
        imEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.im_edit:
                Intent intent = new Intent(this, EditBaByActivity.class);
                intent.putExtra("edit", baBy.getName());
                intent.putExtra("note", baBy.getNote());
                Log.e("edit::", baBy.getName());
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            name = data.getStringExtra("data");
            note = data.getStringExtra("noteEdit");
            tvName.setText(name);
            tvNote.setText(note);

        }
    }
}
