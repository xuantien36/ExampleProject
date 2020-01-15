package com.t3h.immunization.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.AddBabyActivity;
import com.t3h.immunization.activity.BabyInformationActivity;
import com.t3h.immunization.adapter.BaByAdapter;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.model.User;
import com.t3h.immunization.respone.BaByRespone;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.util.DisplayUtil;

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
    private int Inposs = 0;
    private LinearLayoutManager layoutManager;

    private Handler mHandler = new Handler();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.baby_fragment, container, false);
        ButterKnife.bind(this, view);
        arr = new ArrayList<>();
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void callApi() {
        Log.e("CAP", "callApi: USER ID " + User.getInstans().getId());
        ApiBuilder.getInstance().getBaBy(User.getInstans().getId()).enqueue(new Callback<BaByRespone>() {
            @Override
            public void onResponse(Call<BaByRespone> call, Response<BaByRespone> response) {
                List<GetBaby> data = response.body().getData();
                if (data != null && data.size() > 0) {
                    Log.e("BABY", "DATA SIZE " + response.body().getData().size());
                    arr.clear();
                    arr.addAll(data);
                    adapter.setData(arr);
                    if (adapter != null) {
                        adapter.getItemBaby(Inposs+1);
                    }
                    Log.e("call", "onResponse: " + GetBaby.getInstance().getBabyId());
                    Log.e("arr", "onResponse: " + arr.size());
                    if (poss < arr.size() - 1) {
                        btnNext.setVisibility(View.VISIBLE);
                    } else if (poss == 0) {
                        btnNext.setVisibility(View.GONE);
                        btnBack.setVisibility(View.GONE);
                    } else if (poss - 1 == arr.size()) {
                        btnNext.setVisibility(View.GONE);
                        btnBack.setVisibility(View.VISIBLE);
                        Log.e("arr", "onResponse: " + arr.size());
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
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new BaByAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setOnListener(this);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                RecyclerView.LayoutManager manager = parent.getLayoutManager();
                int position = parent.getChildAdapterPosition(view);
                if (manager instanceof GridLayoutManager) {
                    if (position % 2 == 0) {
                        outRect.set(DisplayUtil.dpToPx(10f), 0, DisplayUtil.dpToPx(5f), DisplayUtil.dpToPx(10f));
                    } else {
                        outRect.set(DisplayUtil.dpToPx(5f), 0, DisplayUtil.dpToPx(10f), DisplayUtil.dpToPx(10f));
                    }
                } else if (manager instanceof LinearLayoutManager) {
                    outRect.set(DisplayUtil.dpToPx(3f), 0, DisplayUtil.dpToPx(3f), 0);
                }
            }
        });
    }

    private void scrollToPositionRight() {
        final int currentPosition = layoutManager.findLastVisibleItemPosition();
        Inposs=currentPosition;
        Log.e("Inposs 1111:::", "scrollToPositionRight: "+Inposs );
        if (currentPosition >= 0) {

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.smoothScrollToPosition(currentPosition + 1);
                }
            }, 300);

        }
    }

    private void scrollToPositionLeft() {
        final int currentPosition = layoutManager.findFirstVisibleItemPosition();
       int IPostion= (Inposs - 1);
       Inposs = currentPosition;
        Log.e("Inposs 22222:::", "scrollToPositionLeft: "+IPostion );

        if (currentPosition > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.smoothScrollToPosition(currentPosition - 1);
                }
            }, 300);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(getContext(), AddBabyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                scrollToPositionLeft();
                btnNext.setVisibility(View.VISIBLE);
                if (Inposs==1){
                    btnBack.setVisibility(View.GONE);
                }
//                if ( poss>0 && poss <= arr.size() - 1) {
//                    poss--;
//                }
//                if (poss == 0) {
//                    btnBack.setVisibility(View.GONE);
//
//                }
//                Log.e("testttBack:::", String.valueOf(poss));
//
//                recyclerView.scrollToPosition(poss);
                adapter.getItemBaby(Inposs-1);//thêm
                break;
            case R.id.btn_next:
                btnBack.setVisibility(View.VISIBLE);
                scrollToPositionRight();
                if (Inposs == arr.size()-2) {
                    btnNext.setVisibility(View.GONE);
//                getActivity().runOnUiThread(() -> {

//                    if (poss >= 0) {
//                        poss++;
//                    }
//                    if (poss == arr.size() - 1) {
//                        btnNext.setVisibility(View.GONE);
//
//                    }
//                    Log.e("testttNext:::",String.valueOf(poss));
                }
                    adapter.getItemBaby(Inposs+1);//thêm
                    break;
                }
        }

        @Override
        public void onClicked ( int position){
            Intent intent = new Intent(getContext(), BabyInformationActivity.class);
            intent.putExtra("baby", arr.get(position));
            startActivity(intent);
        }

        @Override
        public void onLongClicked ( int position){

        }

        @Override
        public void onResume () {
            super.onResume();
            Log.e("BABY", "onResume: " + poss);
//        poss = 0;
            callApi();

        }

    }
