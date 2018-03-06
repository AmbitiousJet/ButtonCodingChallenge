package com.qc.mvpbase.mainview;



public interface MainContract {
    interface View {
       void showDialogBox();
       void populateRecyclerView(int pos);
       void notifyDataInserted(int pos);
       void showDisconnectedFromInternetMessage();
       void itemDeleted(int pos);
       void transferMessage();
    }


}