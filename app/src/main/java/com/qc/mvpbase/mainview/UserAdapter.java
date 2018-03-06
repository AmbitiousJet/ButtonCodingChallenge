package com.qc.mvpbase.mainview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qc.mvpbase.R;
import com.squareup.picasso.Picasso;

/**
 * Created by mohammadnaz on 3/3/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public MainPresenter mainPresenter;

    public UserAdapter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        mainPresenter.onBindUserViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mainPresenter.getUserListSize();
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder implements
            MainPresenter.UserViewSetup, View.OnClickListener {
        private TextView id;
        private TextView name;
        private TextView email;
        private TextView candidate;
        private ImageView imageView;
        private Button  deleteButton;
        private Button transferButton;
        private EditText addAmount;

        public UserViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.userId);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
            candidate = itemView.findViewById(R.id.userCandidate);
            imageView = itemView.findViewById(R.id.buttonImage);
            deleteButton = itemView.findViewById(R.id.delete);
            deleteButton.setOnClickListener(this);
            transferButton = itemView.findViewById(R.id.transfer);
            transferButton.setOnClickListener(this);
            addAmount = itemView.findViewById(R.id.addAmount);


        }

        @Override
        public void setImage() {
            Picasso.with(imageView.getContext()).
                    load("https://pbs.twimg.com/profile_images/558107544510423040/qG3JSObq_400x400.png")
                    .fit()
                    .into(imageView);
        }

        @Override
        public void setId(String id) {
            this.id.setText(id);
        }

        @Override
        public void setName(String name) {
            this.name.setText(name);
        }

        @Override
        public void setEmail(String email) {
            this.email.setText(email);
        }

        @Override
        public void setCandidate(String candidate) {
            this.candidate.setText(candidate);
        }


        @Override
        public void onClick(View v) {
            if(v.getId()==deleteButton.getId()) {
                mainPresenter.deleteUser(getAdapterPosition());
            }
            else if(v.getId()==transferButton.getId()){
                String amount= addAmount.getText().toString();
                if (amount.matches("")) {
                    Toast.makeText(v.getContext().getApplicationContext(), "You did not enter an amount",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mainPresenter.transfer(getAdapterPosition(), amount);
                    addAmount.getText().clear();
                }
            }
        }
    }



}
