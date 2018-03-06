package com.qc.mvpbase.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by mohammadnaz on 3/3/18.
 */

@Database(entities = {UserEntity.class, TransferEntity.class}, version = 2, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_NAME = "User.db";
    private static volatile UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    @NonNull
    private static UserDatabase create(final Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, DB_NAME).fallbackToDestructiveMigration()
                .build();

    }

    public abstract UserDao getUserDao();
    public abstract TransferDao getTransferDao();

}