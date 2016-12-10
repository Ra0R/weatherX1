package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.server.Data;
import java.util.ArrayList;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {
    // Implementation of sample interface method
     Data database = new Data();


    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
    public ArrayList<String[]> getEntries(String query){
        return database.getData(query);
    }
    public ArrayList<String[]> filterEntries(String query,int index) {return database.filter(query,index);}

}