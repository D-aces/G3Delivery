package com.example.g3delivery.data.datasource;

public interface DataLoadCallback<T> {
    void onDataLoaded(T data);  // Generic data type
    void onError(Exception e);
}
