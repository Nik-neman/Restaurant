package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private final String name;
    private boolean busy;
    private LinkedBlockingQueue queue = new LinkedBlockingQueue();

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public void startCookingOrder(Order order){
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {

        }
        setChanged();
        notifyObservers(order);
        CookedOrderEventDataRow row = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticManager.getInstance().register(row);

        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {

                try {
                    while (true) {
                        Thread.sleep(10);
                        if (!queue.isEmpty()) {
                                    this.startCookingOrder((Order) queue.take());

                        }
                    }
                } catch (InterruptedException e) {
          }




    }
}
