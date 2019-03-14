package com.androiddesdecero.rxudemyfinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;


public class RX06BusFragment extends Fragment {

    private TextView tvFragment;
    private CompositeDisposable compositeDisposable;

    public RX06BusFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx06_bus, container, false);

        configFragment(view);
        return view;
    }

    private void configFragment(View view){
        tvFragment = view.findViewById(R.id.tvFragment);
        compositeDisposable = new CompositeDisposable();
        Observable observable = RX06Bus.getInstance().getEvents();
        compositeDisposable.add(
                observable.subscribe(
                        e->{
                            tvFragment.setText((String)e);
                        }
                )
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
