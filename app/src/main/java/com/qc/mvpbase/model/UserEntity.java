package com.qc.mvpbase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mohammadnaz on 3/3/18.
 */
@Entity(tableName = "User")

public class UserEntity {

    @PrimaryKey
    @NonNull
    public String id;
    @ColumnInfo(name="name")
    public String name;
    @ColumnInfo(name="email")
    public String email;
    @ColumnInfo(name="cadidate")
    public String candidate;

    public UserEntity(@NonNull String id, String name, String email, String candidate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.candidate = candidate;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
