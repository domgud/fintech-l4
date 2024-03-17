package com.example.fintech_l3.dataloader;

import java.util.List;

/**
 * Listener class, which should be used in activity to respond on data fetch success/failure
 * @param <T> DTO type
 */
public interface DataLoaderListener<T> {
    void onDataLoaded(List<T> currencies);
    void onDataLoadFailed();
}
