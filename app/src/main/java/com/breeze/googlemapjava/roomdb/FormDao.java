package com.breeze.googlemapjava.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FormDao {
    @Insert
    void insert(FormEntity form);

    @Query("SELECT * FROM FormEntity")
    List<FormEntity> getFormDetails();

    @Query(value = "SELECT id,latitude,longitude FROM FormEntity")
    List<FormEntity> getLatLng();

    @Query("SELECT * FROM FormEntity WHERE id > :Id")
    public FormEntity[] getDataByID(int Id);

}
