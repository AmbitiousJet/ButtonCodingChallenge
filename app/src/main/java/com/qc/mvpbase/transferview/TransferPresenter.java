package com.qc.mvpbase.transferview;

import com.qc.mvpbase.model.TransferEntity;
import com.qc.mvpbase.network.ButtonService;
import com.qc.mvpbase.network.RetrofitInstance;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohammadnaz on 3/5/18.
 */

public class TransferPresenter {
    private TransferContract.transferView transferView;
    private List<TransferEntity> transferEntities = Collections.emptyList();


    public void getTransfer() {

        RetrofitInstance retroFit = RetrofitInstance.getInstance();
        ButtonService buttonService = retroFit.getButtonService();

        Call<List<TransferEntity>> getTransfer = buttonService.getDataFromTransfer("droidbutton");

        getTransfer.enqueue(new Callback<List<TransferEntity>>() {
            @Override
            public void onResponse(Call<List<TransferEntity>> call, Response<List<TransferEntity>> response) {
                transferEntities = response.body();
                transferView.showTransfer();
            }

            @Override
            public void onFailure(Call<List<TransferEntity>> call, Throwable t) {

            }
        });


    }

    public void onBindUserViewHolder(TransferViewsSetup transferViewsSetup, int position) {
        TransferEntity transferEntity = transferEntities.get(position);
        transferViewsSetup.setAmount(transferEntity.getAmount());
        transferViewsSetup.setCandidate(transferEntity.getCandidate());
        transferViewsSetup.setId(transferEntity.getId());
        transferViewsSetup.setUserId(transferEntity.getUser_id());


    }

    public int getTransferListSize() {
        return transferEntities.size();
    }

    public void attach(TransferContract.transferView transferView) {
        this.transferView = transferView;

    }

    public void detach() {
        transferView = null;
    }

    public interface TransferViewsSetup {
        void setId(int id);

        void setAmount(String amount);

        void setCandidate(String candidate);

        void setUserId(int id);
    }
}
