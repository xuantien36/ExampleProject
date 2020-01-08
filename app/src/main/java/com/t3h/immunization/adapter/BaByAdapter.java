package com.t3h.immunization.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.immunization.R;
import com.t3h.immunization.activity.EditBaByActivity;
import com.t3h.immunization.model.GetBaby;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaByAdapter extends RecyclerView.Adapter<BaByAdapter.VaccineHolder> {
    private List<GetBaby> data;
    private LayoutInflater inflater;
    private ItemClickListener listener;
    private Context context;
    private int poss = 0;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public BaByAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void setData(List<GetBaby> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VaccineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_baby, parent, false);
        return new VaccineHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, final int position) {
        poss = position;
        GetBaby name = data.get(position);
        Log.e("bbbb", String.valueOf(data.get(position)));
        holder.bindData(name);
        if (listener != null) {
            holder.itemView.setOnClickListener(view -> listener.onClicked(position));
            holder.itemView.setOnLongClickListener(view -> {
                listener.onLongClicked(position);
                return true;
            });
        }

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class VaccineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_baby)
        TextView tvName;
        @BindView(R.id.have_injected)
        TextView tvHave_injected;
        @BindView(R.id.miss_injected)
        TextView tvMiss;
        @BindView(R.id.not_injected)
        TextView tvnot_injected;
        @BindView(R.id.btn_repair)
        Button btnRepair;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(GetBaby item) {
            tvName.setText(item.getName());
            tvHave_injected.setText("Đã tiêm :" + item.getInjected());
            tvMiss.setText("Bỏ lỡ :" +item.getMissInjected());
            tvnot_injected.setText("Chưa tiêm:" +item.getNotInjected());
            btnRepair.setOnClickListener(view -> {
                Intent intent = new Intent(context.getApplicationContext(), EditBaByActivity.class);
                intent.putExtra("data", data.get(poss));
                Log.e("aaa", String.valueOf(data.get(poss)));
                context.startActivity(intent);
            });

        }

    }

    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }
}
