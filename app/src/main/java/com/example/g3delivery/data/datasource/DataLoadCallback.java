package com.example.g3delivery.data.datasource;

import com.example.g3delivery.data.model.Restaurant;
import java.util.List;

public interface DataLoadCallback {
    void onDataLoaded(List<Restaurant> restaurantList);
}
