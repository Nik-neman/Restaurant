package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue ORDER_QUEUE = new LinkedBlockingQueue(200);

    public static void main(String[] args) {

        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(ORDER_QUEUE);
        Thread thread1 = new Thread(cook1);
        Cook cook2 = new Cook("Diego");
        cook2.setQueue(ORDER_QUEUE);
        Thread thread2 = new Thread(cook2);
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
//        StatisticManager statisticManager = StatisticManager.getInstance();
//        statisticManager.register(cook);
//        statisticManager.register(cook2);
        List<Tablet> tablets = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_QUEUE);
//            OrderManager orderManager = new OrderManager();
//            tablet.addObserver(orderManager);
            tablets.add(tablet);
        }
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(randomOrderGeneratorTask);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();



        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
