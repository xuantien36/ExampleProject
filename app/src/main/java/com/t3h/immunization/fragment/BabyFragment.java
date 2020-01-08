package com.t3h.immunization.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.AddBabyActivity;
import com.t3h.immunization.activity.BabyInformationActivity;
import com.t3h.immunization.adapter.BaByAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.BaByRespone;
import com.t3h.immunization.model.GetBaby;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabyFragment extends Fragment implements View.OnClickListener, BaByAdapter.ItemClickListener {
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.lv_baby)
    RecyclerView recyclerView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    private BaByAdapter adapter;
    private ArrayList<GetBaby> arr;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.btn_next)
    ImageButton btnNext;
    private int poss = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.baby_fragment, container, false);
        ButterKnife.bind(this, view);
        arr = new ArrayList<>();
        btnNext.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void callApi() {
        ApiBuilder.getInstance().getBaBy(1).enqueue(new Callback<BaByRespone>() {
            @Override
            public void onResponse(Call<BaByRespone> call, Response<BaByRespone> response) {
                List<GetBaby> data = response.body().getData();
                if (data != null && data.size() > 0) {
                    Log.e("BABY", "onResponse: " + response.body().getData().size());
                    arr.clear();
                    arr.addAll(data);
                    adapter.setData(arr);
                    Log.e("arr", "onResponse: "+ arr.size() );
                    if (poss < arr.size() - 1) {
                        btnNext.setVisibility(View.VISIBLE);
                    } else {
                        btnNext.setVisibility(View.GONE);
                    }
                    recyclerView.setVisibility(View.VISIBLE);
                    tvEmpty.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<BaByRespone> call, Throwable t) {

            }
        });

    }
    private void init() {
        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new BaByAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                recyclerView.stopScroll();
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(getContext(), AddBabyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                Log.e("testttBack:::", String.valueOf(arr.size()));
                btnNext.setVisibility(View.VISIBLE);
                if (poss <= arr.size() - 1) {
                    poss--;
                }
                if (poss == 0) {
                    btnBack.setVisibility(View.GONE);
                }
                recyclerView.scrollToPosition(poss);
                break;
            case R.id.btn_next:
                Log.e("testttNext:::", String.valueOf(arr.size()));
                getActivity().runOnUiThread(() -> {
                    btnBack.setVisibility(View.VISIBLE);
                    if (poss >= 0) {
                        poss++;
                    }
                    if (poss == arr.size() - 1) {
                        btnNext.setVisibility(View.GONE);
                    }
                    recyclerView.scrollToPosition(poss);
                });
                break;
        }
    }

    @Override
    public void onClicked(int position) {
        Intent intent = new Intent(getContext(), BabyInformationActivity.class);
        intent.putExtra("baby", arr.get(position));
        startActivity(intent);
    }

    @Override
    public void onLongClicked(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        callApi();
        Log.e("BABY", "onResume: ");
    }
}
