package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava2.math.MathObservable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import io.reactivex.subjects.Subject;

public class RX03OperadoresActivity extends AppCompatActivity {

    private EditText etQuery;
    private TextView tvQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx03_operadores);
        etQuery = findViewById(R.id.etQuery);
        tvQuery = findViewById(R.id.tvQuery);

        /*
        Operadores que Crean Observables
         */

        //probarJust();
        //probarJustArray();
        //probarFromArray();
        //probarRange();
        //probarRepeat();
        //probarInterval();
        //probarCreate();
        //probarCreateException();
        ///probarCreateLargaDuracion();
        //probarCreateLargaDuracionLambda();

        /*
        Operadores que Transforman Observables
         */

        //probarBuffer();
        //probarMap();
        //probarFlatMap();
        //probarGroupBy();
        //probarScan();
        //probarWindow();

         /*
        Operadores que Selectivamente emiten item de un observable
         */

         //probarDebounce();
         //probarDistinct();
         //probarElementAt();
         //probarFilter();
        //probarFirst();
        //probarIngnoreElements();
        //probarLast();
        //probarSample();
        //probarSkip();
        //probarSkipLast();
        //probarTake();
        //probarTakeLast();

        /*
        Operadores que trabajan con multiples observables para crear un Single Observable
         */

        //probarcombineLatest();
        //probarJoin();
        //probarMege();
        //probarZip();

        /*
        Operadores que ayudan a recuperar errores de notificación en los observables
         */
        //probarRetryWhen();

        /*
        Operadores Útiles de RX
         */

        //probarDelay();
        //probarDo();
        //probarObserverOnSubscribeOn();
        //probarTimeInterval();
        //probarTimeOut();
        //probarTimeStamp();
        //probarUsing();

        /*
        Operadores Booleanos y Condicionales
         */
        //probarAll();
        //probarAmb();
        //probarContains();
        //probarDefaultIfEmpty();
        //probarSequenceEqual();
        //probarSkipUntil();
        //probarSkipWhile();
        //probarTakeUntil();
        //probarTakeWhile();

        /*
        Operadores Matemáticos
         */

        //probarAverage();
        //probarCount();
        //probarMaxMinSum();
        probarReduce();
    }

    private void probarReduce(){
        Log.d("TAG1", "----------------Reduce----------------");
        Observable.just(2,2,2,2)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer * integer2;
                    }
                })
                .subscribe(
                        e->Log.d("TAG1", "Resutado: " + e)
                );

    }

    private void probarMaxMinSum(){
        Log.d("TAG1", "----------------MaxMinSum----------------");
        Observable<Integer> observable = Observable.fromArray(1,34,55,1000,-234,33,567);
        MathObservable
                .max(observable)
                .subscribe(
                        e->Log.d("TAG1", "max: " + e)
                );

        MathObservable
                .min(observable)
                .subscribe(
                        e->Log.d("TAG1", "min: " + e)
                );

        MathObservable
                .sumInt(observable)
                .subscribe(
                        e->Log.d("TAG1", "min: " + e)
                );


    }

    private void probarCount() {
        Log.d("TAG1", "----------------Count----------------");
        Observable<Integer> numeroObservable = Observable.fromArray(1,34,55,33,567);
        numeroObservable
                .count()
                .subscribe(
                        e->Log.d("TAG1", "count: " + e)
                );

    }

    private void probarAverage(){
        Log.d("TAG1", "----------------Average----------------");
        Observable<Integer> numeroObservable = Observable.fromArray(1,34,55,33,567);
        MathObservable.averageDouble(numeroObservable)
                .subscribe(
                        e->Log.d("TAG1", "average: " + e)
                );
    }

    private void probarTakeWhile(){
        Log.d("TAG1", "----------------TakeWhile----------------");
        Observable
                .create(emitter ->{
                    for(int i=0; i<10; i++){
                        try {
                            Thread.sleep(500);
                            emitter.onNext(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    emitter.onComplete();
                })
                .takeWhile(
                        e->(Integer)e<=4
                )
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );
    }

    private void probarTakeUntil() {
        Log.d("TAG1", "----------------TakeUntil----------------");
        Observable<Integer> observable1 = Observable
                .create(emitter ->{
                    for(int i=0; i<10; i++){
                        try {
                            Thread.sleep(500);
                            emitter.onNext(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    emitter.onComplete();
                });

        Observable<Long> observable2 = Observable
                .create(emitter ->{
                    emitter.onNext(4);
                    emitter.onComplete();
                })
                .timer(3, TimeUnit.SECONDS);

        observable1.takeUntil(observable2)
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );
    }

    private void probarSkipWhile() {
        Log.d("TAG1", "----------------SkipWhile----------------");
        Observable
                .create(emitter ->{
                    for(int i=0; i<10; i++){
                        try {
                            Thread.sleep(400);
                            emitter.onNext(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    emitter.onComplete();
                })
                .skipWhile(e->(Integer) e<=4)
                .subscribe(e->Log.d("TAG1", "onNext: " + e)
                );
    }

    private void probarSkipUntil(){
        Log.d("TAG1", "----------------SkipUntil----------------");

        Observable<Integer> observable1 = Observable
                .create(emitter ->{
                            for(int i=0; i<10; i++){
                                try {
                                    Thread.sleep(500);
                                    emitter.onNext(i);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            emitter.onComplete();
                });

        Observable<Long> observable2 = Observable
                .create(emitter ->{
                    emitter.onNext(4);
                    emitter.onComplete();
                })
                .timer(3, TimeUnit.SECONDS);

        observable1.skipUntil(observable2)
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );
    }

    private void probarSequenceEqual() {
        Log.d("TAG1", "----------------SequenceEqual----------------");
        Observable<Integer> numeroObservable1 = Observable.just(1,-1,2,-6,4,-78);
        Observable<Integer> numeroObservable2 = Observable.just(1,-1,2,-6,4,-78);

        Observable.sequenceEqual(numeroObservable1, numeroObservable2)
                .subscribe(
                        e->Log.d("TAG1", "onSuccess: " + e)
                );
    }

    private void probarDefaultIfEmpty(){
        Log.d("TAG1", "----------------DefaultIfEmpty----------------");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                int num = 100;
                if(num%2==0){
                    emitter.onNext(num);
                }
                emitter.onComplete();
            }
        })
                .defaultIfEmpty(0)
                .subscribe(
                        e->Log.d("TAG1", "onNext: " +e)
                );

    }

    private void probarContains() {
        Log.d("TAG1", "----------------Contains----------------");
        Observable<Integer> numeroObservable = Observable.just(2,0,-2,66,100,-478);
        numeroObservable
                .contains(-477)
                .subscribe(
                    e->Log.d("TAG1", "onSuccess " + e)
                );
    }

    private void probarAmb() {
        Log.d("TAG1", "----------------Amb----------------");
        Observable<Integer> numeroObservable1 = Observable.just(1,-1,2,-6,4,-78);
        Observable<Integer> numeroObservable2 = Observable.just(2,0,-2,66,100,-478);

        Observable
                .ambArray(numeroObservable1.delay(10,TimeUnit.SECONDS), numeroObservable2)
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );
    }

    private void probarAll() {
        Log.d("TAG1", "----------------All----------------");
        Observable<Integer> numeroObservable = Observable.just(1,-1,2,-6,4,-78);
        numeroObservable.all(e->e>0)
                .subscribe(
                        e->Log.d("TAG1", "onSuccess: " +e)
                );
    }

    private void probarUsing() {
        Log.d("TAG1", "----------------Using----------------");
        Observable.using(
                new Callable<String>() {
                    @Override
                    public String call() {
                        return "Using";
                    }
                },
                new Function<String, ObservableSource<Character>>() {
                    @Override
                    public ObservableSource<Character> apply(String s) throws Exception {
                        return Observable.create(
                                e -> {
                                    for (Character c : s.toCharArray()) {
                                        e.onNext(c);
                                    }
                                    e.onComplete();
                                });
                    }
                },
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TAG1", "Disposase: " + s);
                    }
                })
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );


    }

    private void probarTimeStamp() {
        Log.d("TAG1", "----------------TimeStamp----------------");
        Observable<String> letrasObservable = Observable.create(
                e -> {
                    e.onNext("A");
                    e.onNext("B");
                    e.onNext("C");
                });
        letrasObservable
                .timestamp()
                .subscribe(new Observer<Timed<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<String> stringTimed) {
                        Log.d("TAG1", "onNext " + stringTimed);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void probarTimeOut() {
        Log.d("TAG1", "----------------TimeOut----------------");
        Observable<String> letrasObservable = Observable.create(
                e -> {
                    e.onNext("A");
                    e.onNext("B");
                    e.onNext("C");
                });

        letrasObservable
                .timer(1, TimeUnit.SECONDS)
                .timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(
                     e->Log.d("TAG1", "onNext " + e),
                     e->Log.d("TAG1", "onError " + e)
                );


    }

    private void probarTimeInterval(){
        Log.d("TAG1", "----------------TimeInterval----------------");
        Observable<String> letrasObservable = Observable.create(
                e->{
                    e.onNext("A");
                    e.onNext("B");
                    e.onNext("C");
                });

        letrasObservable.interval(500, TimeUnit.MILLISECONDS)
                .take(3)
                .timeInterval()
                .subscribe(new Subject<Timed<Long>>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super Timed<Long>> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<Long> longTimed) {
                        Log.d("TAG1", "onNext: " + longTimed);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void probarObserverOnSubscribeOn(){
        Log.d("TAG1", "----------------ObserverOn-SubscribeOn----------------");
        Observable<String> observable = Observable.create(
                e->{
                    Log.d("TAG1", "observable ejecutandose en hilo: " + Thread.currentThread().getName());
                    e.onNext("Emitiendo item");
                });

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    e-> Log.d("TAG1", "Observer ejecutandose en hilo: " + Thread.currentThread().getName())
                );

    }

    private void probarDo() {
        Log.d("TAG1", "----------------Do----------------");
        Observable<String> numeroObservable = Observable.just("1", "4", "89", "45", "0");
        numeroObservable
                .doOnNext(e->Log.d("TAG1", "doOnNext:" + e))
                .doAfterNext(e->Log.d("TAG1", "doAfterNext: " + e))
                .doOnComplete(()->Log.d("TAG1", "doOnComplete "))
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );
    }


    private void probarDelay(){
        Log.d("TAG1", "----------------Delay----------------");
        Observable<String> numeroObservable = Observable.just("1","4","89","45", "0");
        numeroObservable
                .delay(5, TimeUnit.SECONDS)
                .subscribe(
                        e->Log.d("TAG1", "onNext: con 5 segundos de retraso" + e)
                );
    }

    private void probarRetryWhen(){
        Log.d("TAG1", "----------------RetryWhen----------------");
        Observable
                .create(e->{
                    e.onNext("probando Retry");
                    e.onError(new Throwable("test"));
                        })
                .retryWhen(error->error.retry())
                .subscribe(
                    e->Log.d("TAG1", "onNext " + e),
                    e->Log.d("TAG1", "onError" + e),
                        ()->Log.d("TAG1", "onComplete")
                );
    }

    private void probarZip() {
        Log.d("TAG1", "----------------Zip----------------");
        Observable<String> observable1 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e-> "Grupo1: " + e);

        Observable<String> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e-> "Grupo2: " + e);

        Observable.zip(observable1, observable2, (x,y)->x + " - " + y)
                .subscribe(
                        e->Log.d("TAG1", e)
                );
    }


    private void probarMege(){
        Log.d("TAG1", "----------------Merge----------------");
        Observable<String> observable1 = Observable
                .interval(2, TimeUnit.SECONDS)
                .map(e-> "Grupo1: " + e);

        Observable<String> observable2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e-> "Grupo2: " + e);

        Observable.merge(observable1, observable2)
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );
    }

    private void probarJoin() {
        Log.d("TAG1", "----------------Join----------------");
        Long LEFTWINDOWDURATION = 0L;
        Long RIGHTWINDOWDURATION = 0L;

        Observable<Long> left = Observable
                .interval(70, TimeUnit.MILLISECONDS).take(10);

        Observable<Long> right = Observable
                .interval(100, TimeUnit.MILLISECONDS).take(10);

        left.join(
                right,
                e->Observable.timer(LEFTWINDOWDURATION, TimeUnit.MILLISECONDS),
                e->Observable.timer(RIGHTWINDOWDURATION, TimeUnit.MILLISECONDS),
                (l,r)->{
                    Log.d("TAG1", "left: " + l + " right: " + r);
                    return l + r;
                })
                .subscribe(
                        e->Log.d("TAG1", "result: " + e)
                );


    }

    private void probarcombineLatest() {
        Log.d("TAG1", "----------------combineLatest----------------");
        Observable<Long> observable1 = Observable.interval(100, TimeUnit.MILLISECONDS).take(10);
        Observable<Long> observable2 = Observable.interval(50, TimeUnit.MILLISECONDS).take(20);
        
        Observable
                .combineLatest(observable1, observable2, new BiFunction<Long, Long, String>() {
                    @Override
                    public String apply(Long aLong, Long aLong2) {
                        return "Observable1: " + aLong + " Observable2: " + aLong2;
                    }
                })
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );



    }

    private void probarTakeLast() {
        Log.d("TAG1", "----------------TakeLast----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .takeLast(5)
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );
    }

    private void probarTake() {
        Log.d("TAG1", "----------------Take----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .take(5)
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );
    }


    private void probarSkipLast() {
        Log.d("TAG1", "----------------Skip Last----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .skipLast(3)
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );
    }

    private void probarSkip(){
        Log.d("TAG1", "----------------Skip----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .skip(3)
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );
    }

    private void probarSample() {
        Log.d("TAG1", "----------------Sample----------------");
        Observable
                .interval(490, TimeUnit.MILLISECONDS)
                .take(10)
                .sample(2000, TimeUnit.MILLISECONDS)
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );


    }

    private void probarLast() {
        Log.d("TAG1", "----------------Last----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .last(0)
                .subscribe(
                        e->Log.d("TAG1", "onNext: Es el Ultimo " + e)
                );
    }

    private void probarIngnoreElements() {
        Log.d("TAG1", "----------------IngnoreElements----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .ignoreElements()
                .subscribe(
                        ()->Log.d("TAG1", "onComplete")
                );


    }

    private void probarFirst() {
        Log.d("TAG1", "----------------First----------------");
        Observable<Integer> numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78);
        numeroObservable
                .first(0)
                .subscribe(
                        e->Log.d("TAG1", "onNext " + e)
                );


    }

    private void probarFilter(){
        Log.d("TAG1", "----------------Filter----------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,9,7,3,81,98,78);
        numeroObservable
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer%2==0;
                    }
                })
                .subscribe(
                        e->Log.d("TAG1", "onNext: Es par" +e)
                );


    }

    private void probarElementAt(){
        Log.d("TAG1", "----------------ElementAt----------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,2,2,3,78,98,78);
        numeroObservable
                .elementAt(7)
                .subscribe(
                        e->Log.d("TAG1", "onNext: " + e)
                );

    }

    private void probarDistinct() {
        Log.d("TAG1", "----------------Distinct----------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,2,2,3,78,98,78);
        numeroObservable
                .distinct()
                .subscribe(
                        e->Log.d("TAG1", "onNext No Repetidos" + e)
                );
    }

    private void probarDebounce() {
        Log.d("TAG1", "----------------Debounce----------------");
        Observable<String> observable = (Observable<String>) RxTextView.textChanges(etQuery)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(e->e.toString())
                .subscribe(
                   e->{
                       Log.d("TAG1", "onNext - String de Busqueda: " +e);
                       tvQuery.setText("Query: " + e);
                   }
                );
    }

    private void probarWindow() {
        Log.d("TAG1", "----------------Window----------------");
        Observable<Observable<Integer>> observableObservable = Observable
                .range(1, 150)
                .window(3);
        observableObservable
                .subscribe(
                    e -> {
                        Log.d("TAG1", "siguiente ventana");
                        e.subscribe(
                           n-> Log.d("TAG1", "item en ventana: " + n)
                        );
                    }
                );
    }

    private void probarScan() {
        Log.d("TAG1", "----------------Scan----------------");
        Observable.just(1,2,3,4,5,6,7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(
                   e->Log.d("TAG1", "onNext " + e)
                );

    }

    private void probarGroupBy() {
        Log.d("TAG1", "----------------GroupBy----------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,5,6,7,8,9);
        Observable<GroupedObservable<String, Integer>> groupedObservableObservable =
                numeroObservable
                .groupBy(integer -> {
                    if(integer%2==0){
                        return "PAR";
                    }else {
                        return "IMPAR";
                    }
                });

        groupedObservableObservable
                .subscribe(new Observer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
                        stringIntegerGroupedObservable.subscribe(
                                new Observer<Integer>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Integer integer) {
                                        if(stringIntegerGroupedObservable.getKey().equals("PAR")){
                                            Log.d("TAG1", "es Par: " + integer);
                                        }else {
                                            Log.d("TAG1", "es Impar: " + integer);
                                        }

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                }
                        );

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void probarFlatMap() {
        Log.d("TAG1", "----------------FlatMap----------------");
        Observable
                .just("item2")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        Log.d("TAG1", "inside the flapMat" + s);
                        return Observable.just(s + " 1 ", s + " 2 ", s + " 3 ");
                    }
                })
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("TAG1", s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarMap() {
        Log.d("TAG1", "----------------Map----------------");
        List<Empleado> empleados = Empleado.setUpEmpleados();
        Observable.fromArray(empleados)
                .map(new Function<List<Empleado>, List<String>>() {
                    @Override
                    public List<String> apply(List<Empleado> empleados) {
                        List<String> nombres = new ArrayList<>();
                        for(Empleado e: empleados){
                            nombres.add(e.getNombre());
                        }
                        return nombres;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<String> strings) {
                                Log.d("TAG1", "Map->items: " + strings);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarBuffer() {
        Log.d("TAG1", "----------------Buffer----------------");
        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6,7,8,9);
        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(
                        new Observer<List<Integer>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Integer> integers) {
                                Log.d("TAG1", "Buffer onNext");
                                for(Integer integer:integers){
                                    Log.d("TAG1", "Buffer item-> "  + integer);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    Disposable disposable;

    private void probarCreateLargaDuracionLambda(){
        Log.d("TAG1", "----------------Create LargaDuracion Lambda----------------");
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    try{
                        emitter.onNext(largaDuracion());
                    }catch (Exception e){
                        emitter.onError(e);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s->Log.d("TAG1", "onNext: " + s),
                        e->Log.d("TAG1", "onError: " + e.getMessage()),
                        ()->Log.d("TAG1", "onComplete"),
                        d -> disposable = d
                );
    }

    private void probarLambda(){

        /*
        (argumentos)->{cuerpo o body}
        (art1, arg2)->{body}
         */
    }

    Sumar sumarL = (a,b)->a+b;

    Sumar sumar = new Sumar() {
        @Override
        public int apply(int a, int b) {
            return a+b;
        }
    };

    private String largaDuracion(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Terminado";
    }

    private void probarCreateLargaDuracion(){
        Log.d("TAG1", "----------------Create LargaDuracion----------------");
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) {
                        try{
                            emitter.onNext(largaDuracion());
                        }catch (Exception e){
                            emitter.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("TAG1", "onNext: " + s);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAG1", "onError: " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarCreateException(){
        Log.d("TAG1", "----------------Create Exception----------------");
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) {
                        try{
                            emitter.onNext(15/3);
                            emitter.onNext(3/0);
                        }catch (Exception e){
                            emitter.onError(e);
                        }
                    }
                })
                .subscribe(
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d("TAG1", "onNext " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAG1", "onError: " + e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarCreate(){
        Log.d("TAG1", "----------------Create----------------");
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) {
                        try{
                            Log.d("TAG1", "subscribe + hilo: " + Thread.currentThread().getName());
                            emitter.onNext("A");
                            emitter.onNext("l");
                            emitter.onNext("b");
                            emitter.onNext("e");
                            emitter.onNext("r");
                            emitter.onNext("t");
                            emitter.onNext("o");
                        }catch (Exception e){
                            emitter.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("TAG1", "onNext: " + s + " hilo " + Thread.currentThread().getName());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarInterval(){
        Log.d("TAG1", "----------------Interval----------------");
        Observable
                .interval(1, TimeUnit.SECONDS)
                .take(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Long aLong) {
                                Log.d("TAG1", "Interval-> onNext: " + aLong);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarRepeat(){
        Log.d("TAG1", "----------------Repeat----------------");
        Observable
                .range(10,3)
                .repeat(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d("TAG1", "Repeat->onNext: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }

    private void probarRange() {
        Log.d("TAG1", "----------------Range----------------");
        Observable.range(7,17)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("TAG1", "range->onNest: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarFromArray() {
        Log.d("TAG1", "----------------FromArray----------------");
        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Observable.fromArray(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("TAG1", "fromArray->onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarJustArray(){
        Log.d("TAG1", "----------------JustArray----------------");
        String[] numeros = {"1","2","3","4","5","6","7","8","9","10"};
        Observable.just(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String[]>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String[] strings) {
                                Log.d("TAG1", "JustArray->onNext " + strings.length);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarJust(){
        Log.d("TAG1", "----------------Just----------------");
        Observable.just("1","2","3","4","5","6","7","8","9","10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.d("TAG1", "Just->onNext " + s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }



}
