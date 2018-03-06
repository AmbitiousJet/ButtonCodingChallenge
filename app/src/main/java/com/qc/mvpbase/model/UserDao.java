package com.qc.mvpbase.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mohammadnaz on 3/3/18.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleRecord(UserEntity... userEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultipleListRecord(List<UserEntity> userEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlySingleRecord(UserEntity userEntity);

    @Query("SELECT * FROM user")
    List<UserEntity> fetchAll();

    @Delete
    void deleteRecord(UserEntity userEntity);


}
