package com.qc.mvpbase.mainview;


import android.support.annotation.NonNull;

import com.qc.mvpbase.model.Transfer;
import com.qc.mvpbase.model.TransferEntity;
import com.qc.mvpbase.model.UserDao;
import com.qc.mvpbase.model.User;
import com.qc.mvpbase.model.UserEntity;
import com.qc.mvpbase.network.ButtonService;
import com.qc.mvpbase.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter {
    private UserDao userDao;
    private MainContract.View view;
    private List<UserEntity> userEntities = new ArrayList<>();

    MainPresenter(UserDao userDao) {
        this.userDao = userDao;
    }

    private void getUserEntityData() {
        RetrofitInstance retroFit = RetrofitInstance.getInstance();
        ButtonService buttonService = retroFit.getButtonService();
        Call<List<UserEntity>> call = buttonService.getDataFromUser("droidbutton");
        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                userEntities = response.body();

                view.populateRecyclerView(userEntities.size() - 1);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            userDao.insertMultipleListRecord(userEntities);
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<List<UserEntity>> call, Throwable t) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            userEntities = userDao.fetchAll();
                            view.populateRecyclerView(userEntities.size() - 1);
                        }
                    }
                }).start();
            }
        });
    }


    public void addUser(String name, String email, String candidate) {

        User user = new User(name, email, candidate);
        RetrofitInstance retroFit = RetrofitInstance.getInstance();
        ButtonService buttonService = retroFit.getButtonService();
        Call<UserEntity> addUser = buttonService.PostDataToUser(user);
        addUser.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, final Response<UserEntity> response) {
                userEntities.add(response.body());
                view.notifyDataInserted(userEntities.size() - 1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.insertOnlySingleRecord(response.body());
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                view.showDisconnectedFromInternetMessage();

            }
        });
    }

    public void deleteUser(final int p){
        final UserEntity userEntity= userEntities.get(p);
        RetrofitInstance retroFit = RetrofitInstance.getInstance();
        ButtonService buttonService = retroFit.getButtonService();

        Call<Void> delete= buttonService.Delete(userEntity.getId(),userEntity.getCandidate());

        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                userEntities.remove(p);
                view.itemDeleted(p);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void transfer(final int p, String amount){
        Transfer transfer= new Transfer(amount, userEntities.get(p).getId(),userEntities.get(p).getCandidate());
        RetrofitInstance retroFit = RetrofitInstance.getInstance();
        ButtonService buttonService = retroFit.getButtonService();

        Call<TransferEntity> transferEntityCall= buttonService.transfer(transfer);

        transferEntityCall.enqueue(new Callback<TransferEntity>() {
            @Override
            public void onResponse(Call<TransferEntity> call, Response<TransferEntity> response) {
                    view.transferMessage();
            }

            @Override
            public void onFailure(Call<TransferEntity> call, Throwable t) {

            }
        });




    }


    public void addUsers() {
        if (view != null) {
            view.showDialogBox();
        }
    }

    public void attach(MainContract.View mainActivity) {
        this.view = mainActivity;
        getUserEntityData();

    }

    public void detach() {
        view = null;
    }

    public void onBindUserViewHolder(UserViewSetup userViewSetup, int position) {
        UserEntity userEntity = userEntities.get(position);
        userViewSetup.setImage();
        userViewSetup.setId(userEntity.getId());
        userViewSetup.setEmail(userEntity.getEmail());
        userViewSetup.setName(userEntity.getName());
        userViewSetup.setCandidate(userEntity.getCandidate());
    }

    public int getUserListSize() {
        return userEntities.size();
    }


    public interface UserViewSetup {
        void setImage();

        void setId(String id);

        void setName(String name);

        void setEmail(String email);

        void setCandidate(String candidate);

    }


}