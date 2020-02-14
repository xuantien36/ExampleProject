package com.t3h.immunization.fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.t3h.immunization.vacxin.model.InjectionGroup;
import com.t3h.immunization.vacxin.presenter.PresenterVacxin;
import com.t3h.immunization.respone.ResponeInjections;
import com.t3h.immunization.vacxin.view.VacxinView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineFragment extends Fragment implements VaccineAdapter.ItemClickListener, VacxinView {
    private ArrayList<InjectionGroup> data;
    private VaccineAdapter adapter;
    @BindView(R.id.lv_vaccine)
    RecyclerView recyclerView;
    private Handler handler=new Handler();
    private ProgressDialog progressDialog;
    private PresenterVacxin presenterVacxin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vaccine_fragment, container, false);
        ButterKnife.bind(this, view);
//        callApi();
        data = new ArrayList<>();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    public void callApi() {
        progressDialog = new ProgressDialog(getContext(),R.style.CustomDialog);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getActivity().getString(R.string.message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        ApiBuilder.getInstance().getVaccine("vi").enqueue(new Callback<ResponeInjections>() {
            @Override
            public void onResponse(Call<ResponeInjections> call, Response<ResponeInjections> response) {
                List<InjectionGroup> injectionGroup = response.body().getInjectionGroup();
                if (injectionGroup != null) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();

                        }
                    },500);
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
        progressDialog = new ProgressDialog(getContext(),R.style.CustomDialog);
        progressDialog.setMessage(getActivity().getString(R.string.message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        presenterVacxin=new PresenterVacxin();
        presenterVacxin.onAttach(this);
        adapter = new VaccineAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);
        presenterVacxin.onshowListVacxin();

    }

    @Override
    public void onClicked(int position) {
        Intent intent = new Intent(getContext(),DetailActivity.class);
        intent.putExtra("webview", data.get(position).getLinkUrl());
        startActivity(intent);

    }
    @Override
    public void onLongClicked(int position) {

    }

    @Override
    public void onshowList(List<InjectionGroup> injectionGroup) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

            }
        },500);
        adapter.setData((ArrayList<InjectionGroup>) injectionGroup);
        data.clear();
        data.addAll(injectionGroup);

    }
}

