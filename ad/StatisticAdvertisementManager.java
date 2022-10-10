package com.javarush.task.task27.task2712.ad;

import java.util.*;

public class StatisticAdvertisementManager {
    private static  final StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage= AdvertisementStorage.getInstance();
    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public List<Advertisement> getActiveVideo(){
        List<Advertisement> activeVideo = new ArrayList<>();
        for(Advertisement a: advertisementStorage.list()){
            if(a.isActive())
            activeVideo.add(a);

        }
        Collections.sort(activeVideo, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        return activeVideo;
    }

    public List<Advertisement> getArchivedVideo(){
        List<Advertisement> archivedVideo = new ArrayList<>();
        for(Advertisement a: advertisementStorage.list()){
            if(!a.isActive())
                archivedVideo.add(a);
        }
        Collections.sort(archivedVideo, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return archivedVideo;
    }
}
