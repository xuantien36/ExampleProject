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
import com.t3h.immunization.model.VaccineList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineListAdapter extends RecyclerView.Adapter<VaccineListAdapter.VaccineHolder> {
    private ArrayList<VaccineList> searches;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public VaccineListAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setData(ArrayList<VaccineList> searches) {
        this.searches = searches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VaccineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_tab, parent, false);
        return new VaccineHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, final int position) {
        VaccineList name = searches.get(position);
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
        @BindView(R.id.im_choose_vaccine)
        ImageView imageView;
        @BindView(R.id.tv_name_vaccine)
        TextView textView;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(VaccineList item) {
            textView.setText(item.getName());
            Glide.with(imageView).load(item.getImage()).into(imageView);

        }
    }
    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }

}
