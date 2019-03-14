package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class RX05SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx05_subject);

        //publishSubjectExample();
        //replaySubjectExample();
        //asyncSubjectExample();
        subjectComoObserveryObservable();
    }

    private void subjectComoObserveryObservable(){
        Log.d("TAG1", "----------------Subject Como Observer y Observable----------------");
        Observable<String> observable = Observable.just("Alberto", "Marta");
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        observable.subscribe(replaySubject);
        Observer<String> primerObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Primer Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Primer Observer onComplete");
            }
        };

        Observer<String> segundoObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Segundo Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Segundo Observer onComplete");
            }
        };
        replaySubject.subscribe(primerObserver);
        replaySubject.subscribe(segundoObserver);
    }

    private void asyncSubjectExample(){
        Log.d("TAG1", "----------------AsyncSubject----------------");
        AsyncSubject<String> source = AsyncSubject.create();

        Observer<String> primerObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Primer Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Primer Observer onComplete");
            }
        };

        Observer<String> segundoObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Segundo Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Segundo Observer onComplete");
            }
        };

        source.subscribe(primerObserver);
        source.onNext("A");
        source.onNext("l");
        source.onNext("b");
        source.onNext("e");
        source.onNext("r");
        source.onNext("t");
        source.onNext("o");
        source.onComplete();
        source.subscribe(segundoObserver);

    }

    private void replaySubjectExample() {
        Log.d("TAG1", "----------------ReplaySubject----------------");
        ReplaySubject<String> source = ReplaySubject.create();

        Observer<String> primerObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Primer Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Primer Observer onComplete");
            }
        };

        Observer<String> segundoObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Segundo Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Segundo Observer onComplete");
            }
        };

        source.subscribe(primerObserver);
        source.onNext("A");
        source.onNext("l");
        source.onNext("b");
        source.subscribe(segundoObserver);
        source.onNext("e");
        source.onNext("r");
        source.onNext("t");
        source.onNext("o");
        source.onComplete();

    }

    private void publishSubjectExample(){
        Log.d("TAG1", "----------------PublishSubject----------------");
        PublishSubject<String> source = PublishSubject.create();

        Observer<String> primerObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Primer Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Primer Observer onComplete");
            }
        };

        Observer<String> segundoObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("TAG1", "Segundo Observer onNext value: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "Segundo Observer onComplete");
            }
        };

        source.subscribe(primerObserver);
        source.onNext("A");
        source.onNext("l");
        source.onNext("b");
        source.subscribe(segundoObserver);
        source.onNext("e");
        source.onNext("r");
        source.onNext("t");
        source.onNext("o");
        source.onComplete();
    }
}
