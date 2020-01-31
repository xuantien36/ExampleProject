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
    private LinearLayoutManager layoutManager;
    int currentPosition = 0;
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
                        adapter.getItemBaby(currentPosition);
                    }

                    Log.e("call", "onResponse: " + GetBaby.getInstance().getBabyId());
                    if (currentPosition < arr.size() - 1) {
                        btnNext.setVisibility(View.VISIBLE);
                    } else if (currentPosition == arr.size() - 1) {
                        btnNext.setVisibility(View.GONE);
                    }else if (currentPosition == 0) {
                        btnBack.setVisibility(View.GONE);
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
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    recyclerView.stopScroll();
                }
            });
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
        currentPosition = layoutManager.findLastVisibleItemPosition();

        if (currentPosition < arr.size() - 1) {
            currentPosition++;
            mHandler.postDelayed(() -> {
                recyclerView.smoothScrollToPosition(currentPosition);
                Log.e("IPos 1111:::", "scrollToPositionRight: " + (currentPosition));
            }, 50);

        }
    }
    private void scrollToPositionLeft() {
        currentPosition = layoutManager.findFirstVisibleItemPosition();
        if (currentPosition > 0 && currentPosition <= arr.size() - 1) {
            currentPosition--;
            mHandler.postDelayed(() -> {
                recyclerView.smoothScrollToPosition(currentPosition);
                Log.e("IPos 22222:::", "scrollToPositionLeft: " + (currentPosition));
            }, 50);
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
                btnNext.setVisibility(View.VISIBLE);
                scrollToPositionLeft();
                Log.e("currentPosBack::::::", String.valueOf(currentPosition));
                if (currentPosition == 0) {
                    Log.e("LEFT", "onClick: " + (currentPosition));
                    btnBack.setVisibility(View.GONE);
                }
                adapter.getItemBaby(currentPosition);//thêm
                break;
            case R.id.btn_next:
                btnBack.setVisibility(View.VISIBLE);
                scrollToPositionRight();
                Log.e("currentPosNext::::::", String.valueOf(currentPosition));
                if (currentPosition == arr.size() - 1) {
                    Log.e("RIGHT", "onClick: " + (currentPosition));
                    btnNext.setVisibility(View.GONE);
                }
                adapter.getItemBaby(currentPosition);//thêm
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
        Log.e("BABY", "onResume: " + currentPosition);
        callApi();

    }

}
