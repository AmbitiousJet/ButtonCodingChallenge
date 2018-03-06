package com.qc.mvpbase.transferview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qc.mvpbase.R;
import com.qc.mvpbase.model.Transfer;
import com.qc.mvpbase.model.TransferEntity;
import com.qc.mvpbase.model.UserEntity;

import java.util.Collections;
import java.util.List;

/**
 * Created by mohammadnaz on 3/5/18.
 */

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.transferViewHolder> {
    private TransferPresenter transferPresenter;

    public TransferAdapter(TransferPresenter transferPresenter) {
        this.transferPresenter = transferPresenter;
    }



    @Override
    public transferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transfer_cardview, parent, false);
        return new TransferAdapter.transferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(transferViewHolder holder, int position) {
        transferPresenter.onBindUserViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return transferPresenter.getTransferListSize();
    }

    public static class transferViewHolder extends RecyclerView.ViewHolder
            implements TransferPresenter.TrasferViewsSetup {
        private TextView idd;
       private TextView tcandidate;
       private TextView tamount;
       private TextView tuserId;

        public transferViewHolder(View itemView) {
            super(itemView);
            idd = itemView.findViewById(R.id.tid);
            tuserId= itemView.findViewById(R.id.user_id);
            tcandidate = itemView.findViewById(R.id.tcadidate);
            tamount = itemView.findViewById(R.id.amount);
        }


        @Override
        public void setId(int id) {
            idd.setText(String.valueOf(id));
        }

        @Override
        public void setAmount(String amount) {
            tamount.setText(amount);
        }

        @Override
        public void setCandidate(String candidate) {
            tcandidate.setText(candidate);
        }

        @Override
        public void setUserId(int id) {
            tuserId.setText(String.valueOf(id));
        }
    }
}
