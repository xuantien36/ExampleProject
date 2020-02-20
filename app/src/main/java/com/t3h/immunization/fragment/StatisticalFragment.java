package com.t3h.immunization.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.EditInjectionsActivity;
import com.t3h.immunization.adapter.ExpandableListAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.baby.model.GetBaby;
import com.t3h.immunization.basemvp.BaseFragment;
import com.t3h.immunization.statiscal.model.Injections;
import com.t3h.immunization.statiscal.view.StatiscalView;
import com.t3h.immunization.vacxin.model.InjectionGroup;
import com.t3h.immunization.statiscal.presenter.PresenterStatiscal;
import com.t3h.immunization.respone.ResponeStatistical;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticalFragment extends BaseFragment<PresenterStatiscal> implements StatiscalView {
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    private ArrayList<Injections> arrayList;
    ExpandableListAdapter expandableListAdapter;
    @BindView(R.id.name_baby_injected)
    TextView tvName;
    private Handler handler = new Handler();
    private ProgressDialog progressDialog;
    @BindView(R.id.empty)
    TextView textView;
    @BindView(R.id.im_avatar)
    ImageView imAvaTar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected View setLayoutFragment(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.statistical_fragment, container, false);
        ButterKnife.bind(this, view);
        arrayList = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    @Override
    protected PresenterStatiscal getPresenter() {
        return new PresenterStatiscal();
    }

    private void initView() {
        progressDialog = new ProgressDialog(getContext(), R.style.CustomDialog);
        progressDialog.setMessage(getActivity().getString(R.string.message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        presenter.onAttach(this);
        tvName.setText(GetBaby.getInstance().getName());

    }
    @Override
    public void onResume() {
        super.onResume();
        if (GetBaby.getInstance().getBabyId() != null) {
            presenter.onshowList();
//            callApi();
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 500);
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showListStatiscal(List<InjectionGroup> injectionGroup, List<Injections> data) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 500);
        expandableListAdapter = new ExpandableListAdapter(getContext(), injectionGroup);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListAdapter.setDataStatis(injectionGroup, data);
        tvName.setText(GetBaby.getInstance().getName());
        if (GetBaby.getInstance().getGender().equalsIgnoreCase("Nam")) {
            imAvaTar.setImageResource(R.drawable.group_730);
        } else {
            imAvaTar.setImageResource(R.drawable.group_731);
        }
        expandableListAdapter.setChildListener(new ExpandableListAdapter.callBackChild() {
            @Override
            public void onclickChild(int position, String date, String name, Injections injections) {
                Intent intent = new Intent(getContext(), EditInjectionsActivity.class);
                intent.putExtra("child", date);
                intent.putExtra("title", name);
                intent.putExtra("object", injections);
                startActivity(intent);

            }
        });

    }
}

