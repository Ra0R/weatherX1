package com.mySampleApplication.client;

import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

public interface MySampleApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
    void getEntries(String year, AsyncCallback<ArrayList<String[]>> async);
    void filterEntries(String query,int index, AsyncCallback<ArrayList<String[]>> async);

}
