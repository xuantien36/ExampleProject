package com.t3h.immunization.presenter.baby;
import android.util.Log;
import com.t3h.immunization.api.ApiBuilder;
import com.t3h.immunization.basemvp.BasePresenter;
import com.t3h.immunization.model.GetBaby;
import com.t3h.immunization.model.User;
import com.t3h.immunization.respone.BaByRespone;
import com.t3h.immunization.view.baby.BabyView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterBaby<V extends BabyView> extends BasePresenter<V> implements ModelPresenterBabyListener<V> {
    @Override
    public void onshowList() {
        ApiBuilder.getInstance().getBaBy(User.getInstans().getId()).enqueue(new Callback<BaByRespone>() {
            @Override
            public void onResponse(Call<BaByRespone> call, Response<BaByRespone> response) {
                List<GetBaby> data = response.body().getData();
                if (data != null && data.size() > 0) {
                    Log.e("BABY", "DATA SIZE " + response.body().getData().size());
                    if (getMvpView() != null) {
                        getMvpView().showList(data);
                        Log.e("presenter", "onResponse: "+data );

                    }

                }
            }
            @Override
            public void onFailure(Call<BaByRespone> call, Throwable t) {
                Log.e("BUG", "onFailure: " + t.toString());
            }
        });

    }
    }

