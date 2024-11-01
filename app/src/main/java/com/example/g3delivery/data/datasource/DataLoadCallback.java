package com.example.g3delivery.data.datasource;
import com.example.g3delivery.data.model.Restaurant;
import java.util.List;

public interface DataLoadCallback<T> {
    void onDataLoaded(T data);
    void onError(Exception e);
}
