package com.natsu.rxHystrix;

import lombok.extern.java.Log;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : natsukry
 * @create : 5/16/2023 8:18 PM
 */
@Log
public class RxDemo3 {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3).repeat(5);

        observable.subscribe(System.out::println);
        observable.flatMap(s -> Observable.just(s + "ssss"))
                .subscribe(System.out::println);


        Observable<Long> timer_observer = Observable.timer(3, TimeUnit.SECONDS);
        timer_observer.subscribe(System.out::println);


        observable.flatMap(s->Observable.just(s+100))
                .flatMap(s->{
                    log.info("---------flatmap-----");
                    return Observable.just(s+9999);
                })
                .subscribe(s->log.info(String.valueOf(s)));
    }

}
