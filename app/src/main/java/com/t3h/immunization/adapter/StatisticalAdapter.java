package com.t3h.immunization.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.t3h.immunization.R;
import com.t3h.immunization.model.Statistical;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticalAdapter extends RecyclerView.Adapter<StatisticalAdapter.StatisticalHolder> {
    private ArrayList<Statistical> searches;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public StatisticalAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setData(ArrayList<Statistical> searches) {
        this.searches = searches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_statistical, parent, false);
        return new StatisticalHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticalHolder holder, final int position) {
        Statistical name = searches.get(position);
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

    public class StatisticalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_statistical)
        TextView tvName;
        @BindView(R.id.tv_not_injected)
        TextView tvChuatiem;
        @BindView(R.id.tv_injected)
        TextView tvDatiem;
        @BindView(R.id.tv_miss)
        TextView tvBolo;
        @BindView(R.id.image)
        ImageView imVacxin;

        public StatisticalHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Statistical item) {
            tvName.setText(item.getName());
            Glide.with(imVacxin).load(item.getImage()).into(imVacxin);

        }
    }
    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }

}
