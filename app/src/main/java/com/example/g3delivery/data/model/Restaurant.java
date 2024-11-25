package com.example.g3delivery.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;

import org.w3c.dom.Document;

import java.util.List;

public class Restaurant implements Parcelable {
    private String name;
    private String description;
    private String address;
    private GeoPoint geoLocation;
    private List<String> labels;
    private DocumentReference menu;
    private String logoImage;
    private double rating;

    protected Restaurant(Parcel in) {
        name = in.readString();
        description = in.readString();
        address = in.readString();
        geoLocation = new GeoPoint(in.readDouble(), in.readDouble());
        labels = in.createStringArrayList();
        logoImage = in.readString();
        rating = in.readDouble();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName(){
        return name;
    }
    public String getDescription(){return description;}
    public String getAddress(){
        return address;
    }
    public GeoPoint getGeolocation(){
        return geoLocation;
    }
    public List<String> getLabels(){
        return labels;
    }
    public String getLogoImage(){return logoImage;}
    public DocumentReference getMenu(){
        return menu;
    }
    public double getRating(){return rating;}

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setAddress(String address) {this.address = address;}
    public void setGeolocation(GeoPoint geolocation) {this.geoLocation = geolocation;}
    public void setLabels(List<String> labels) {this.labels = labels;}
    public void setMenu(DocumentReference menu) {this.menu = menu;}
    public void setRating(double rating) {this.rating = rating;}
    public void setLogoImage(String logoImage){this.logoImage = logoImage;}

    public Restaurant() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(address);
        if (geoLocation != null) {
            dest.writeDouble(geoLocation.getLatitude());
            dest.writeDouble(geoLocation.getLongitude());
        } else {
            dest.writeDouble(0);
            dest.writeDouble(0);
        }
        dest.writeStringList(labels);
        dest.writeString(logoImage);
        dest.writeDouble(rating);
    }
}
