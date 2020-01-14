package com.t3h.immunization.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.immunization.R;
import com.t3h.immunization.model.InjectionGroup;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineBookAdapter extends RecyclerView.Adapter<VaccineBookAdapter.VaccinationBookHolder> {
    private ArrayList<InjectionGroup> data;
    private LayoutInflater inflater;
    private ItemClickListener listener;


    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public VaccineBookAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setData(ArrayList<InjectionGroup> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VaccinationBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_lv_muitiem, parent, false);
        return new VaccinationBookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccinationBookHolder holder, final int position) {
        InjectionGroup name = data.get(position);
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

    public class VaccinationBookHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        TextView tvDate;
        @BindView(R.id.txtMui)
        TextView tvMui;
        @BindView(R.id.txtTenMuiTiem)
        TextView tvName;
        @BindView(R.id.txtNgayTiem)
        TextView tvngayTiem;
        @BindView(R.id.txtNgayConLai)
        TextView tvNgayConLai;
        @BindView(R.id.imgTrangThai)
        ImageView imtrangThai;

        public VaccinationBookHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(InjectionGroup item) {
            tvName.setText(item.getGroupTitle());
//            tvngayTiem.setText("Ngày tiêm : " +item.getNgayTiem());
//            tvNgayConLai.setText("Còn lại : " +item.getConLai());
//            Glide.with(imtrangThai).load(item.getTrangThai()).into(imtrangThai);

        }
    }

    public interface ItemClickListener {
        void onClicked(int position);
        void onLongClicked(int position);

    }

}
