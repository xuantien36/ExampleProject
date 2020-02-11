package com.t3h.immunization.adapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.immunization.R;
import com.t3h.immunization.activity.EditBaByActivity;
import com.t3h.immunization.model.GetBaby;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BaByAdapter extends RecyclerView.Adapter<BaByAdapter.VaccineHolder> {
    private List<GetBaby> data;
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
    public void setData(List<GetBaby> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void getItemBaby(int position) {
        Log.e("BUG", "getItemBaby:    " + position + "   //  list size " + data.size());
        if (data.size() == 0) {
            GetBaby.getInstance().setBabyId(-1);

        } else if (position <= data.size() - 1) {
            GetBaby.getInstance().setBabyId(data.get(position).getBabyId());
            GetBaby.getInstance().setBirthday(data.get(position).getBirthday());
            GetBaby.getInstance().setName(data.get(position).getName());
            GetBaby.getInstance().setGender(data.get(position).getGender());
        }
    }
    @NonNull
    @Override
    public VaccineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_baby, parent, false);
        return new VaccineHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull VaccineHolder holder, final int position) {
        GetBaby name = data.get(position);

        holder.bindData(name);
        holder.btnRepair.setOnClickListener(view -> {
            Intent intent = new Intent(context.getApplicationContext(), EditBaByActivity.class);
            intent.putExtra("data", data.get(position));
            Log.e("bin", "bindData: " + position);
            context.startActivity(intent);
        });
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClicked(position);

                }
            });
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
        @BindView(R.id.avatar)
        ImageView imAvatar;

        public VaccineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void bindData(GetBaby item) {
            tvName.setText(item.getName());
            tvHave_injected.setText(context.getResources().getString(R.string.injected) + item.getInjected());
            tvMiss.setText(context.getResources().getString(R.string.miss) + item.getMissInjected());
            tvnot_injected.setText(context.getResources().getString(R.string.not_injected) + item.getNotInjected());
            if (item.getGender().equalsIgnoreCase("Nam")) {
                imAvatar.setImageResource(R.drawable.avatar_2);
            } else {
                imAvatar.setImageResource(R.drawable.avatar);
            }

        }

    }
    public interface ItemClickListener {
        void onClicked(int position);

        void onLongClicked(int position);

    }
}

