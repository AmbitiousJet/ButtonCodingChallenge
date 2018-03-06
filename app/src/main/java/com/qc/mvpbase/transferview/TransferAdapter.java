package com.qc.mvpbase.transferview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qc.mvpbase.R;

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
        transferPresenter.onBindUserViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return transferPresenter.getTransferListSize();
    }

    public static class transferViewHolder extends RecyclerView.ViewHolder
            implements TransferPresenter.TransferViewsSetup {
        private TextView transferId;
        private TextView tCandidate;
        private TextView tAmount;
        private TextView tUserId;

        public transferViewHolder(View itemView) {
            super(itemView);
            transferId = itemView.findViewById(R.id.tid);
            tUserId = itemView.findViewById(R.id.user_id);
            tCandidate = itemView.findViewById(R.id.tcadidate);
            tAmount = itemView.findViewById(R.id.amount);
        }


        @Override
        public void setId(int id) {
            transferId.setText(String.valueOf(id));
        }

        @Override
        public void setAmount(String amount) {
            tAmount.setText(amount);
        }

        @Override
        public void setCandidate(String candidate) {
            tCandidate.setText(candidate);
        }

        @Override
        public void setUserId(int id) {
            tUserId.setText(String.valueOf(id));
        }
    }
}
