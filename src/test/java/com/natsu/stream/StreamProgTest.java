package com.natsu.stream;

import com.natsu.bean.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : natsukry
 * @create : 5/15/2023 9:44 PM
 */
class StreamProgTest {

    ArrayList<Cat> catList;

    @BeforeEach
    void prepareData() {
        catList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Cat.CatBuilder catBuilder = Cat.builder().age(String.valueOf(i));
            if (i % 2 == 0) {
                catBuilder.color("milk");
            } else {
                catBuilder.color("orange");
            }
            catList.add(catBuilder.build());
        }
    }

    @Test
    public void group() {

        Map<String, List<Cat>> map = catList.stream()
                .collect(Collectors.groupingBy(Cat::getColor));
        // {
        // orange=[Cat(name=1, color=orange), Cat(name=3, color=orange), Cat(name=5, color=orange), Cat(name=7, color=orange), Cat(name=9, color=orange)],
        // milk=[Cat(name=0, color=milk), Cat(name=2, color=milk), Cat(name=4, color=milk), Cat(name=6, color=milk), Cat(name=8, color=milk)]
        // }
        System.out.println(map);
    }

    @Test
    public void partial() {
        Map<Boolean, List<Cat>> map = catList.stream()
                .collect(Collectors.partitioningBy(c -> c.getColor().equals("orange")));
        // {
        // false=[Cat(name=0, color=milk), Cat(name=2, color=milk), Cat(name=4, color=milk), Cat(name=6, color=milk), Cat(name=8, color=milk)],
        // true=[Cat(name=1, color=orange), Cat(name=3, color=orange), Cat(name=5, color=orange), Cat(name=7, color=orange), Cat(name=9, color=orange)]
        // }
        System.out.println(map);
    }

    @Test
    public void reduce() {
        Optional<String> reduce = catList.stream().map(Cat::getAge)
                .reduce((a, b) -> String.valueOf(Integer.parseInt(a) + Integer.parseInt(b)));
        // 45
        reduce.ifPresent(System.out::println);
    }

    @Test
    public void sort() {
        Stream<Cat> sorted = catList.stream().sorted(Comparator.comparingInt(o -> Integer.parseInt(o.getAge())));
        System.out.println(sorted.collect(Collectors.toList()));
        //[Cat(age=0, color=milk), Cat(age=1, color=orange), Cat(age=2, color=milk), Cat(age=3, color=orange), Cat(age=4, color=milk), Cat(age=5, color=orange), Cat(age=6, color=milk), Cat(age=7, color=orange), Cat(age=8, color=milk), Cat(age=9, color=orange)]
    }


}