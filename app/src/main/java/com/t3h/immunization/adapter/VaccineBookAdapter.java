package com.t3h.immunization.adapter;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.t3h.immunization.R;
import com.t3h.immunization.model.InjectionGroup;
import com.t3h.immunization.model.Injections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineBookAdapter extends RecyclerView.Adapter<VaccineBookAdapter.VaccinationBookHolder> {
//    private List<Injections> data;
    private LayoutInflater inflater;
    private ItemClickListener listener;
    private List<InjectionGroup> dataList;
    private List<List<Injections>> dataInjection;


    public void setDataList(List<List<Injections>> dataInjection,List<InjectionGroup> groups) {
        this.dataInjection = dataInjection;
        this.dataList=groups;
    }


    public void setOnListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public VaccineBookAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public VaccinationBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_lv_muitiem, parent, false);
        return new VaccinationBookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccinationBookHolder holder, final int position) {
//        InjectionGroup name = getTileGroup(dataInjection.get(position).get());
//        holder.bindData(name);
        if (listener != null) {
            holder.itemView.setOnClickListener(view -> listener.onClicked(position));
            holder.itemView.setOnLongClickListener(view -> {
                listener.onLongClicked(position);
                return true;
            });
        }
//        if (data.get(position).getIsInjected().equalsIgnoreCase("1")){
//            holder.imtrangThai.setImageResource(R.drawable.ic_ellipse_200);
//        }else if (data.get(position).getIsInjected().equalsIgnoreCase("0")){
//            holder.imtrangThai.setImageResource(R.drawable.ic_ellipse_202);
//
//        }else {
//            holder.imtrangThai.setImageResource(R.drawable.ic_ellipse2);
//        }

    }

    @Override
    public int getItemCount() {
        return dataInjection == null ? 0 : dataInjection.size();
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

        }
    }

    public interface ItemClickListener {
        void onClicked(int position);
        void onLongClicked(int position);

    }
    public long getMilliFromDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = formatter.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date);
        return date.getTime();

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    private String getTileGroup(String i){
        for (InjectionGroup group: dataList) {
            if (group.getGroupInjection().equalsIgnoreCase(i)){
                return group.getGroupTitle();
            }
        }
        return "";

    }

}
