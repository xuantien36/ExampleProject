package com.t3h.immunization.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.t3h.immunization.R;
import com.t3h.immunization.activity.EditInjectionsActivity;
import com.t3h.immunization.adapter.ExpandableListAdapter;
import com.t3h.immunization.itemModel;
import java.util.ArrayList;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticalFragment extends Fragment {
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    String title[] = {"Lao Phổi", "Viêm Gan A", "Viêm Gan B", "Viêm Não", "Thủy Đậu", "Thương Hàn", "Cảm Cúm", "Bạch Hầu"};
    int fruitImage[] = {R.drawable.ic_ellipse_200, R.drawable.ic_ellipse_200, R.drawable.ic_ellipse_200, R.drawable.ic_ellipse_200};
    String fruits[] = {"Mũi 1/5", "Mũi 2/5", "Mũi 3/5", "Mũi 4/5"};
    String date[] = {"22/02/2019", "22/02/2019", "22/02/2019", "22/02/2019"};
    int animalImage[] = {R.drawable.ic_ellipse_200, R.drawable.ic_ellipse_200, R.drawable.ic_ellipse_200,R.drawable.ic_ellipse_200};
    String animal[] = {"Mũi 1/5", "Mũi 2/5", "Mũi 3/5","Mũi 4/5"};
    String date_time[] = {"22/02/2019", "22/02/2019", "22/02/2019", "22/02/2019"};
    ArrayList<itemModel> arrayList, arrayList1;
    HashMap<String, ArrayList<itemModel>> hashMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistical_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }


    private void init() {
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();

        for (int i = 0; i < fruitImage.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setName(fruits[i]);
            itemModel.setDate(date[i]);
            itemModel.setImage(fruitImage[i]);
            arrayList.add(itemModel);
            hashMap.put(title[0], arrayList);

        }
        for (int i = 0; i < animalImage.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setName(animal[i]);
            itemModel.setImage(animalImage[i]);
            itemModel.setDate(date_time[i]);
            arrayList1.add(itemModel);
            hashMap.put(title[1], arrayList1);
            hashMap.put(title[2], arrayList);
            hashMap.put(title[3], arrayList1);
            hashMap.put(title[4], arrayList);
            hashMap.put(title[5], arrayList1);
            hashMap.put(title[6], arrayList);
            hashMap.put(title[7], arrayList1);

        }
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getContext(), title, hashMap);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Intent intent = new Intent(getContext(), EditInjectionsActivity.class);
            startActivity(intent);
            return true;
        });
    }
}

