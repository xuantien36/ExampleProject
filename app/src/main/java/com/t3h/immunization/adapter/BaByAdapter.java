package com.t3h.immunization.adapter;

import android.content.Context;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaByAdapter extends RecyclerView.Adapter<BaByAdapter.VaccineHolder> {
    private ArrayList<GetBaby> searches;
    private LayoutInflater inflater;
    private ItemClickListener listener;
    private Context context;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public BaByAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void setData(ArrayList<GetBaby> searches) {
        this.searches = searches;
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
        GetBaby name = searches.get(position);
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
        return searches == null ? 0 : searches.size();
    }

    public class VaccineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_baby)
        TextView tvName;
        @BindView(R.id.have_injected)
        TextView tvHave_injected;
        @BindView(R.id.miss)
        TextView tvMiss;
        @BindView(R.id.about_inject)
        TextView tvAbout_injected;
        @BindView(R.id.btn_repair)
        Button btnRepair;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(GetBaby item) {
            tvName.setText(item.getName());
            tvHave_injected.setText("Đã tiêm :" + item.getInjected());
            tvMiss.setText("Bỏ lỡ :" + item.getMissInjected());
            tvAbout_injected.setText("Sắp tiêm :" + item.getPrepareInject());
            btnRepair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context.getApplicationContext(), EditBaByActivity.class);
                    context.startActivity(intent);
                }
            });

        }

    }

    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }
}

