package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> tablets = new ArrayList<>();
    private int timeOut;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.timeOut = interval;
    }

    @Override
    public void run() {
        tablets.get((int)Math.random()*tablets.size()).createTestOrder();
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
        }
    }
}
