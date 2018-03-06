package com.qc.mvpbase.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mohammadnaz on 3/3/18.
 */
@Dao
public interface TransferDao {
    @Query("SELECT * FROM transfer WHERE user_id=:user_id")
    List<TransferEntity> ForUser(final int user_id);
}
