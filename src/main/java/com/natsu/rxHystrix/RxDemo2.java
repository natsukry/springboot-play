package com.natsu.rxHystrix;

import lombok.extern.java.Log;
import org.apache.logging.log4j.Level;
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
public class RxDemo2 {
    public static void main(String[] args) {
        Observable<String> normalObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //发射一个"create1"的String
                subscriber.onNext("create1");

                //发射一个"create2"的String
                subscriber.onNext("create2");

                //发射完成,这种方法需要手动调用onCompleted，才会回调Observer的onCompleted方法
                subscriber.onCompleted();
            }
        });

        //使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据：
        Observable<String> justObservable = Observable.just("just1", "just2");

        // 使用from( )，遍历集合，发送每个item：
        // 注意，just()方法也可以传list，但是发送的是整个list对象，而from（）发送的是list的一个item
        List<String> list = Arrays.asList("from1", "from2", "from3");
        Observable<String> fromObservable = Observable.from(list);

        // .使用defer( )，有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
        Observable<String> deferObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("deferObservable");
            }
        });

        // 使用interval( ),创建一个按固定时间间隔发射整数序列的Observable，可用作定时器
        Observable<Long> intervalObservable = Observable.interval(1, TimeUnit.SECONDS);

        // 使用range( ),创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常
        Observable<Integer> rangeObservable = Observable.range(10, 5);

        // 使用timer( ),创建一个Observable，它在一个给定的延迟后发射一个特殊的值，
        // 等同于Android中Handler的postDelay( )方法
        Observable<Long> timerObservable = Observable.timer(3, TimeUnit.SECONDS);

        // 使用repeat( ),创建一个重复发射特定数据的Observable
        // 重复发射3次
        Observable<String> repeatObservable = Observable.just("repeatObservable").repeat(3);


        /**
         * 创建接收源 Observer
         */
        Observer<String> mObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                log.info(s);
            }
        };

        /**
         * 关联发射源,接收源
         */
        justObservable.subscribe(mObserver);

        // 不需要Observer的onCompleted()和onError()方法，
        // 可使用Action1，subscribe()支持将Action1作为参数传入,
        // RxJava将会调用它的call方法来接收数据
        justObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                log.info(s);
            }
        });

        /**
         * flatMap
         */
        justObservable.flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s+"-->flatmap");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                log.info(s);
            }
        });

        /**
         * 简化难懂的Lambda
         */
        justObservable.flatMap(s -> Observable.just(s+"-->flatmap"))
                .subscribe(log::info);










    }
}
