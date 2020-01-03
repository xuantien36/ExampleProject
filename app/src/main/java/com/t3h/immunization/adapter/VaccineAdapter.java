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
import com.t3h.immunization.model.Vaccine;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineHolder> {
    private ArrayList<Vaccine> searches;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public VaccineAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setData(ArrayList<Vaccine> searches) {
        this.searches = searches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VaccineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_vaccine, parent, false);
        return new VaccineHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, final int position) {
        Vaccine name = searches.get(position);
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
        @BindView(R.id.name_vaccine)
        TextView tvName;
        @BindView(R.id.im_vaccine)
        ImageView imVacxin;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Vaccine item) {
            tvName.setText(item.getNameVaccine());
            Glide.with(imVacxin).load(item.getImage()).into(imVacxin);

        }
    }
    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }

}
