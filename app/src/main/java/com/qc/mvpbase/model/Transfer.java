package com.qc.mvpbase.model;

/**
 * Created by mohammadnaz on 3/5/18.
 */

public class Transfer {

    private String amount;
    private String user_id;
    private String candidate;

    public Transfer(String amount, String user_id, String candidate) {
        this.amount = amount;
        this.user_id = user_id;
        this.candidate = candidate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
