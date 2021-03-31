package com.breeze.googlemapjava.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FormEntity {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "Name")
    public String name;
    @ColumnInfo(name = "Email")
    public String email;
    @ColumnInfo(name = "Address")
    public String address;
    @ColumnInfo(name = "Latitude")
    public String latitude;
    @ColumnInfo(name = "Longitude")
    public String longitude;

    public FormEntity(String name, String email, String address,String latitude,String longitude) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public FormEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
