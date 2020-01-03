package com.t3h.immunization.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.immunization.R;
import com.t3h.immunization.model.VaccineList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTabAdapter extends RecyclerView.Adapter<ListTabAdapter.VaccineHolder> {
    private ArrayList<VaccineList> searches;
    private LayoutInflater inflater;
    private ItemClickListener listener;
    private int poss = 2;
    private Context context;

    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public ListTabAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, final int position) {
        VaccineList name = searches.get(position);
        holder.bindData(name);
        if (searches.get(position).getImage() == poss) {
            holder.imageView.setImageResource(R.drawable.babygroup);
        }

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClicked(position);
                    poss = position;
                    notifyDataSetChanged();

                }
            });
            holder.itemView.setOnLongClickListener(view -> {
                listener.onLongClicked(position);
                return true;
            });
            if (position == poss) {
                switch (position) {
                    case 0:
                        if (position == 0) {
                            holder.imageView.setImageResource(R.drawable.syringe);
                        } else {
                            holder.imageView.setImageResource(R.drawable.ic_syringe_2);
                        }
                        break;
                    case 1:
                        if (position == 1) {
                            holder.imageView.setImageResource(R.drawable.test);
                        } else {
                            holder.imageView.setImageResource(R.drawable.ic_test_2);
                        }
                        break;
                    case 2:
                        if (position == 2) {
                            holder.imageView.setImageResource(R.drawable.babygroup);
                        } else {
                            holder.imageView.setImageResource(R.drawable.ic_avatar);
                        }
                        break;
                    case 3:
                        if (position == 3) {
                            holder.imageView.setImageResource(R.drawable.group249);

                        } else {
                            holder.imageView.setImageResource(R.drawable.ic_notebook);
                        }
                        break;
                    case 4:
                        if (position == 4) {
                            holder.imageView.setImageResource(R.drawable.group252);

                        } else {
                            holder.imageView.setImageResource(R.drawable.ic_chat);
                        }
                        break;
                }
                holder.textView.setTextColor(context.getResources().getColor(R.color.colorBG1));

            } else {
                holder.textView.setTextColor(context.getResources().getColor(R.color.colortexttab));

            }
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
