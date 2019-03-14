package com.androiddesdecero.rxudemyfinal;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;


public class RX06BusActivity extends AppCompatActivity {

    private RX06BusFragment fragment;
    private FragmentTransaction transaction;
    private Button btRxBus;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx06_bus);

        fragment = new RX06BusFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame2, fragment);
        transaction.commit();

        compositeDisposable = new CompositeDisposable();

        btRxBus = findViewById(R.id.btRxBus);
        btRxBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RX06Bus.getInstance().setEvents("Hola soy el Bus");
            }
        });

        Observable observable = RX06Bus.getInstance().getEvents();
        compositeDisposable.add(
                observable.subscribe(
                        e->{
                            Log.d("TAG1", "RX06Activity: " + e);
                        }
                )
        );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
