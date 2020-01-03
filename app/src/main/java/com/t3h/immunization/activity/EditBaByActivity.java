package com.t3h.immunization.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.t3h.immunization.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditBaByActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.back_edit)
    ImageView imBack;
    @BindView(R.id.save_edit)
    ImageView imSave;
    @BindView(R.id.edt_name_baby)
    EditText edtName_Edit;
    private String name;
    private String note_edit;
    private Handler handler = new Handler();
    @BindView(R.id.edt_note)
    EditText edtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ba_by);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("edit");
        note_edit = intent.getStringExtra("note");
        imBack.setOnClickListener(this);
        imSave.setOnClickListener(this);
        edtName_Edit.setText(name);
        edtNote.setText(note_edit);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_edit:
                finish();
                break;
            case R.id.save_edit:
                showDialog();
                String edit_name = edtName_Edit.getText().toString();
                String editNote=edtNote.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("data", edit_name);
                intent.putExtra("noteEdit",editNote);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }

    }

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_sending);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                finish();
            }
        }, 2000);
        dialog.show();

    }
}
