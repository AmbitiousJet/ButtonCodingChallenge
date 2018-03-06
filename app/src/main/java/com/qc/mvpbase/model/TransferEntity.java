package com.qc.mvpbase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/**
 * Created by mohammadnaz on 3/3/18.
 */
@Entity(tableName = "transfer",
        indices = {@Index(value = "user_id")},
        foreignKeys = @ForeignKey(entity = UserEntity.class,
                parentColumns = "id",
                childColumns = "user_id",
                onDelete = CASCADE
        ))

public class TransferEntity {
    @PrimaryKey
    @NonNull
    public int id;
    @ColumnInfo(name = "amount")
    public String amount;
    @ColumnInfo(name = "candidate")
    public String candidate;
    @ColumnInfo(name = "user_id")
    public int user_id;

    public TransferEntity(@NonNull int id, String amount, String candidate, int user_id) {
        this.id = id;
        this.amount = amount;
        this.candidate = candidate;
        this.user_id = user_id;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
