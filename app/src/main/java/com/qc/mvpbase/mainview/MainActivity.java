package com.qc.mvpbase.mainview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qc.mvpbase.model.UserDao;
import com.qc.mvpbase.model.UserDatabase;
import com.qc.mvpbase.R;
import com.qc.mvpbase.transferview.TransferActivity;

public class MainActivity extends AppCompatActivity implements
        MainContract.View,
        AddUserDialogFragment.AddUserCallBack {

    private RecyclerView recyclerView;
    private MainPresenter mainPresenter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.loadingPanel);
        recyclerView = findViewById(R.id.RV);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        UserDao userDao = UserDatabase.getInstance(getApplicationContext()).getUserDao();
        mainPresenter = new MainPresenter(userDao);
        recyclerView.setAdapter(new UserAdapter(mainPresenter));


        FloatingActionButton addTaskFab = findViewById(R.id.add_user_fab);
        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.addUsers();
            }
        });
    }


    @Override
    public void showDialogBox() {
        AddUserDialogFragment fragment = new AddUserDialogFragment();
        fragment.show(getSupportFragmentManager(), "Add User");
    }

    @Override
    public void populateRecyclerView(final int pos) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.getAdapter().notifyDataSetChanged();
                linearLayoutManager.scrollToPosition(pos);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void notifyDataInserted(int pos) {
        recyclerView.getAdapter().notifyItemInserted(pos);
        linearLayoutManager.scrollToPosition(pos);
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void showDisconnectedFromInternetMessage() {
        Toast.makeText(getApplicationContext(), "can't add user, no internet connection",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void itemDeleted(int pos) {
        recyclerView.getAdapter().notifyItemRemoved(pos);
    }

    @Override
    public void transferMessage() {
        Toast.makeText(getApplicationContext(), "Transfer Made",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void addUserCallBackListener(String name, String email, String candidate) {
        progressBar.setVisibility(View.VISIBLE);
        mainPresenter.addUser(name, email, candidate);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(MainActivity.this, TransferActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detach();
    }


}
