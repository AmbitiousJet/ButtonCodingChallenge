package com.qc.mvpbase.mainview;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.qc.mvpbase.R;


public class AddUserDialogFragment extends DialogFragment {
    private AddUserCallBack addUserCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addUserCallBack = (AddUserCallBack) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement AddUserCallBack");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_user_dialog_view, null);
        final TextInputEditText name1 = view.findViewById(R.id.name1);
        final TextInputEditText email1 = view.findViewById(R.id.email1);
        final TextInputEditText candid1 = view.findViewById(R.id.candidate1);

        builder.setTitle("Add User")
                .setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("addUser", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addUserCallBack.addUserCallBackListener(
                                name1.getText().toString(),
                                email1.getText().toString(),
                                candid1.getText().toString());
                    }
                });
        return builder.create();
    }

    public interface AddUserCallBack {
        void addUserCallBackListener(String name, String email, String candidate);
    }
}
