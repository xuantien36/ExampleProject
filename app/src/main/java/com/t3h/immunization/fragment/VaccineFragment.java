package com.t3h.immunization.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.DetailActivity;
import com.t3h.immunization.adapter.VaccineAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.respone.ResponeInjections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineFragment extends Fragment implements VaccineAdapter.ItemClickListener {
    private ArrayList<InjectionGroup> data;
    private VaccineAdapter adapter;
    @BindView(R.id.lv_vaccine)
    RecyclerView recyclerView;
    private int poss=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaccine_fragment, container, false);
        ButterKnife.bind(this, view);
        callApi();
        data = new ArrayList<>();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    public void callApi() {
        ApiBuilder.getInstance().getVaccine("vi").enqueue(new Callback<ResponeInjections>() {
            @Override
            public void onResponse(Call<ResponeInjections> call, Response<ResponeInjections> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                if (injectionGroup != null) {
                    adapter.setData((ArrayList<InjectionGroup>) injectionGroup);
                    data.clear();
                    data.addAll(injectionGroup);
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponeInjections> call, Throwable t) {

            }
        });

    }

    private void initView() {
        adapter = new VaccineAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);

    }

    @Override
    public void onClicked(int position) {
        poss=position;
        showDialog();

    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Lưu ý");
        builder.setCancelable(false);
        builder.setMessage("Những thông tin trong này chỉ mang tính chất tham khảo . Bạn có muốn mở không ?");
        builder.setNegativeButton("Không",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                      dialog.dismiss();
                    }
                });
        builder.setPositiveButton("Có",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(getContext(),DetailActivity.class);
                        intent.putExtra("webview", data.get(poss).getLinkUrl());
                        Log.e("vac", "onClick: "+poss );
                        startActivity(intent);
                    }
                });

        builder.show();
    }


    @Override
    public void onLongClicked(int position) {

    }
}

