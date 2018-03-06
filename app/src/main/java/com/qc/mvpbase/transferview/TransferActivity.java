package com.qc.mvpbase.transferview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.qc.mvpbase.R;


public class TransferActivity extends AppCompatActivity
        implements TransferContract.transferView {

    private RecyclerView recyclerView;
    private TransferPresenter transferPresenter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private TransferAdapter transferAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        progressBar = findViewById(R.id.loadingPanel1);
        recyclerView = findViewById(R.id.tRV);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        transferPresenter = new TransferPresenter();
        transferPresenter.getTransfer();
        transferAdapter = new TransferAdapter(transferPresenter);
        recyclerView.setAdapter(transferAdapter);


    }


    @Override
    public void showTransfer() {
        recyclerView.getAdapter().notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        transferPresenter.attach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        transferPresenter.detach();
    }
}
