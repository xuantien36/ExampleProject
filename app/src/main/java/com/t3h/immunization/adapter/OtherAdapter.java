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
import com.t3h.immunization.model.Other;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.OtherHolder> {
    private ArrayList<Other> searches;
    private LayoutInflater inflater;
    private ItemClickListener listener;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public OtherAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setData(ArrayList<Other> searches) {
        this.searches = searches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OtherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_other, parent, false);
        return new OtherHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherHolder holder, final int position) {
        Other name = searches.get(position);
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

    public class OtherHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_infor)
        TextView tvName;
        @BindView(R.id.im_infor)
        ImageView im;

        public OtherHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Other item) {
            tvName.setText(item.getName());
            Glide.with(im).load(item.getImage()).into(im);

        }
    }
    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }

}
