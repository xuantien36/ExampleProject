package com.t3h.immunization.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FourFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.btn_starst)
    Button btn_Starst;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.four_slide,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        btn_Starst.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

    }
}
