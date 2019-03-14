package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RX08BackPressureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx08_back_pressure);
        //generarBackPressure();
        //backPressureBuffer();
        backPressureDrop();
    }

    private void backPressureDrop(){
        Flowable<Long> source = Flowable.interval(1, TimeUnit.MILLISECONDS);
        source
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .subscribe(
                        e->{
                            try{
                                Log.d("TAG1", "Consumiendo Observables: " +e);
                                Thread.sleep(100);
                            }catch (Exception ex){

                            }
                        },
                        error->Log.d("TAG1", "onError: " + error)
                );
    }

    private void backPressureBuffer(){
        Flowable<Long> source = Flowable.interval(1, TimeUnit.MILLISECONDS);
        source
                .onBackpressureBuffer(1000)
                .observeOn(Schedulers.computation())
                .subscribe(
                        e->{
                            try{
                                Log.d("TAG1", "Consumiendo Observables: " +e);
                                Thread.sleep(100);
                            }catch (Exception ex){

                            }
                        },
                        error->Log.d("TAG1", "onError: " + error)
                );
    }

    private void generarBackPressure(){
        PublishSubject<Integer> source = PublishSubject.create();
        source
                .observeOn(Schedulers.io())
                .subscribe(
                        e->operacionLargaDuracion(e),
                        e->Log.d("TAG1", "onError" + e),
                        ()->Log.d("TAG1", "onComplete")
                );

        for(int i=0; i<10; i++){
            source.onNext(i);
            Log.d("TAG1", "creando item Observable " + 1);
        }
        source.onComplete();
    }

    private void operacionLargaDuracion(int entero){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("TAG1", "Consumiendo Observable: " + entero);
    }
}
