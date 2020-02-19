package com.t3h.immunization.basemvp;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public abstract class BaseFragment<V extends BasePresenter>extends Fragment {

    protected View vFragmentLayout;
    protected Activity activity;
    public V presenter;
    protected View.OnClickListener onclick;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        vFragmentLayout = setLayoutFragment(inflater,container);
        if (vFragmentLayout==null){
            Toast.makeText(activity, "null layout", Toast.LENGTH_SHORT).show();
        }
        presenter = getPresenter();
        presenter.setContext(getActivity());

        return vFragmentLayout;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //khai bÃ¡o view trong createView
    protected abstract View setLayoutFragment(LayoutInflater inflater,ViewGroup container);
    //láº¥y presenter
    protected abstract V getPresenter();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
