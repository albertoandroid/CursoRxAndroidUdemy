package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RX00IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx00_intro);

        Observable<String> numerosObservable =
                Observable.just("1","2","3","4","5","6","7","8","9","10");

        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG1", "onSubscribe" + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String numero) {
                Log.d("TAG1", "onNext: Numero: "+ numero + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG1", "onError" + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onComplete: Se han emitido todos los datos" + " Hilo: " + Thread.currentThread().getName());
            }
        };

        numerosObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(numerosObserver);
    }
}
