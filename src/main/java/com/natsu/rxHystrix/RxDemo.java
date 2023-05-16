package com.natsu.rxHystrix;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

import java.util.Arrays;

/**
 * @author : natsukry
 * @create : 5/16/2023 8:18 PM
 */
public class RxDemo {
    public static void main(String[] args) {
        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hi, Naruto");
            }
        });

        Observer<String> receiver = new Observer<String>() {
            @Override
            public void onCompleted() {
                // 数据接收完成时调用
                System.out.println("数据接收完成时调用 onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                // 发生错误调用
                System.out.println("发生错误调用 onError");

            }

            @Override
            public void onNext(String s) {
                // 正常接收数据调用
                System.out.println("正常接收数据调用 onNext");
                System.out.println(s);
            }
        };

        sender.subscribe(receiver);

    }
}
