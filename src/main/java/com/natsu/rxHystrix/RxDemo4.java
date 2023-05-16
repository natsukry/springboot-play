package com.natsu.rxHystrix;

import lombok.extern.java.Log;
import rx.Observable;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * @author : natsukry
 * @create : 5/16/2023 8:18 PM
 */
@Log
public class RxDemo4 {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3");
        // doOnNext
        observable.doOnNext(s->{
            log.info("doOnNext start-->"+s);
            log.info("---"+s+"---");
            log.info("doOnNext end<--"+s);

        }).subscribe(s->{
            log.log(Level.SEVERE,"~~~~~~~~~subscribe~~~~~~~~~~~"+s);
        });
    }

}
