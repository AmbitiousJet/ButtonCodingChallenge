package com.qc.mvpbase.network;

import com.qc.mvpbase.model.Transfer;
import com.qc.mvpbase.model.User;
import com.qc.mvpbase.model.TransferEntity;
import com.qc.mvpbase.model.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ButtonService {

    @POST("user")
    Call<UserEntity> PostDataToUser(@Body User user);

    @DELETE("user/{id}")
    Call<Void> Delete(@Path("id") String id, @Query("candidate") String candidate);

    @GET("transfer")
    Call<List<TransferEntity>> getDataFromTransfer(@Query("candidate") String candidate);

    @GET("user")
    Call<List<UserEntity>> getDataFromUser(@Query("candidate") String candidate);

    @POST("transfer")
    Call<TransferEntity> transfer(@Body Transfer transfer);
}

