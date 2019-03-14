package com.androiddesdecero.rxudemyfinal;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RX06Bus {

    private static RX06Bus instance;

    private PublishSubject<Object> bus = PublishSubject.create();

    public static RX06Bus getInstance(){
        if(instance==null){
            instance = new RX06Bus();
        }
        return instance;
    }

    public void setEvents(Object message){
        bus.onNext(message);
    }

    public Observable<Object> getEvents(){
        return bus;
    }
}
