package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
//    ExpandableListView expandableListView;
//    String title[] = {"Fruits", "Animals","hhf","1234","12wer"};
//    int fruitImage[] = {R.drawable.ic_ellipse_200,R.drawable.ic_ellipse_200,R.drawable.ic_ellipse_200,R.drawable.ic_ellipse_200};
//    String fruits[] = {"Mũi 1/5", "Mũi 2/5", "Mũi 3/5", "Mũi 4/5"};
//    String date[] = {"22/02/2019", "22/02/2019", "22/02/2019", "22/02/2019"};
//    int animalImage[] = {R.drawable.ic_ellipse_200,R.drawable.ic_ellipse_200,R.drawable.ic_ellipse_200};
//    String animal[] = {"Mũi 1/5", "Mũi 2/5", "Mũi 3/5"};
//    String date_time[] = {"22/02/2019", "22/02/2019", "22/02/2019", "22/02/2019"};
//    ArrayList<itemModel> arrayList, arrayList1;
//    HashMap<String, ArrayList<itemModel>> hashMap;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
//
//        hashMap = new HashMap<>();
//        arrayList = new ArrayList<>();
//        arrayList1 = new ArrayList<>();
//
//        for (int i = 0; i < fruitImage.length; i++) {
//            itemModel itemModel = new itemModel();
//            itemModel.setName(fruits[i]);
//            itemModel.setDate(date[i]);
//            itemModel.setImage(fruitImage[i]);
//            arrayList.add(itemModel);
//            //title and child data
//            hashMap.put(title[0], arrayList);
//
//        }
//
//        for (int i = 0; i < animalImage.length; i++) {
//            itemModel itemModel = new itemModel();
//            itemModel.setName(animal[i]);
//            itemModel.setImage(animalImage[i]);
//            itemModel.setDate(date_time[i]);
//            arrayList1.add(itemModel);
//            //title and child data
//            hashMap.put(title[1], arrayList1);
//            hashMap.put(title[2],arrayList);
//            hashMap.put(title[3], arrayList1);
//            hashMap.put(title[4],arrayList);
//
//        }
//
//        //child click listener
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getApplicationContext(), hashMap.get(title[groupPosition]).get(childPosition).getName(), Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });
//    }
}