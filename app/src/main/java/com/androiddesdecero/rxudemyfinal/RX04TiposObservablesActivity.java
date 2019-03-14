package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class RX04TiposObservablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx04_tipos_observables);

        /*
        Tipos de Observables
         */
        /*
        1.- Observable: Emite 0 o n ítems y termina con exíto o con error.
         */
        //observableObserver();
        /*
        2.- Single: Emite un único ítem o lanza error.
         */
        //singleAndSingleObserver();
        /*
        3.- Maybe: Emite un único ítem o ninguno o lanzar error.
         */
        //maybeAndMaybeObserver();
        /*
        4.- Completable: No emite ítems, pero finaliza con exíto o con error.
         */
        //completableAndCompletableObserver();
        /*
        5.- Flowable: Emite 0 o n ítems y termina con éxito o con error.
        Ideal para cuando se emiten más ítem de los que el observador
        puede manejar, lo que se conoce como BackPresure.
         */
        flowableAndObserver();
    }

    private void flowableAndObserver() {
        Log.d("TAG1", "----------------Flowable-Observer----------------");
        Flowable<Integer> flowable = Flowable.range(1, 5);
        SingleObserver<Integer> singleObserver = new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d("TAG1", "onSuccess: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        flowable.reduce(0, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(singleObserver);

    }

    private void completableAndCompletableObserver(){
        Log.d("TAG1", "----------------Completable-CompletableObserver----------------");

        Completable completable = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onComplete();
            }
        });

        CompletableObserver completableObserver = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onComplete: Actualizado El servidor correctamente");
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        completable.subscribe(completableObserver);
    }

    private void maybeAndMaybeObserver() {
        Log.d("TAG1", "----------------Maybe-MaybeObserver----------------");
        Maybe<Empleado> empleadoMaybe = Maybe.create(new MaybeOnSubscribe<Empleado>() {
            @Override
            public void subscribe(MaybeEmitter<Empleado> emitter) throws Exception {
                Empleado empleado = new Empleado(1, "Alberto", "Android", new Date(), 1000.0, 100.0);
                emitter.onSuccess(empleado);
            }
        });

        Maybe<Empleado> empleadoMaybeEmpty = Maybe.empty();

        MaybeObserver<Empleado> empleadoMaybeObserver = new MaybeObserver<Empleado>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Empleado empleado) {
                Log.d("TAG1", "onSuccess: " + empleado.getNombre());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onComplete");

            }
        };

        //empleadoMaybe.subscribe(empleadoMaybeObserver);
        empleadoMaybeEmpty.subscribe(empleadoMaybeObserver);

    }

    private void singleAndSingleObserver() {
        Log.d("TAG1", "----------------Single-SingleObserver----------------");
        Single<Empleado> empleadoSingle = Single.create(new SingleOnSubscribe<Empleado>() {
            @Override
            public void subscribe(SingleEmitter<Empleado> emitter) throws Exception {
                Empleado empleado = new Empleado(1, "Alberto", "Android", new Date(), 1000.0, 100.0);
                emitter.onSuccess(empleado);
            }
        });

        SingleObserver<Empleado> singleObserver = new SingleObserver<Empleado>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Empleado empleado) {
                Log.d("TAG1", "onSuccess: " + empleado.getNombre());
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        empleadoSingle.subscribe(singleObserver);

    }

    private void observableObserver(){
        Log.d("TAG1", "----------------Observable-Observer----------------");
        List<Empleado> empleados = Empleado.setUpEmpleados();
        Observable<Empleado> empleadoObservable = Observable.create(
                emitter -> {
                    for(Empleado empleado: empleados){
                        emitter.onNext(empleado);
                    }
                    emitter.onComplete();
                }
        );

        Observer<Empleado> empleadoObserver = new Observer<Empleado>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Empleado empleado) {
                Log.d("TAG1", "onNext: " + empleado.getNombre());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onComplete: ");
            }
        };

        empleadoObservable
                .subscribe(empleadoObserver);

    }


}
