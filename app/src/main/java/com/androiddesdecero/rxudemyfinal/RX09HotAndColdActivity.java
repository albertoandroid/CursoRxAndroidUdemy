package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;


public class RX09HotAndColdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx09_hot_and_cold);
        //coldObservable();
        hotObservable();


    }

    private void hotObservable(){
        ConnectableObservable<Long> hot = Observable.interval(500, TimeUnit.MILLISECONDS).publish();
        hot.connect();
        hot.subscribe(
                e-> Log.d("TAG1", "Subcriber Number 1: " + e)
        );
        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }
        hot.subscribe(
                e-> Log.d("TAG1", "Subcriber Number 2: " + e)
        );
    }



    private void coldObservable(){
        Observable<Long> cold = Observable.interval(500, TimeUnit.MILLISECONDS);
        cold.subscribe(
                e-> Log.d("TAG1", "Subcriber Number 1: " + e)
        );
        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }
        cold.subscribe(
                e-> Log.d("TAG1", "Subcriber Number 2: " + e)
        );
    }
}
