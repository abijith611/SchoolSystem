package com.DB;

import com.utility.Authenticator;
import com.system.Secretary;

import java.util.ArrayList;
import java.util.Collections;

public class SecretaryDB {
    private final ArrayList<Secretary> secretaryList= new ArrayList<>();
    public  Secretary getUser(String username){
        for(Secretary secretary: secretaryList){
            if(username.equals(secretary.getUserId())){
                return secretary;
            }
        }
        return null;
    }

    public  Secretary getUser(int id){
        for(Secretary secretary: secretaryList){
            if(id ==secretary.getId()){
                return secretary;
            }
        }
        return null;
    }

    public void addSecretary(String name, String username, String password, int id, Authenticator auth, int type){
            auth.register(username, password, type);
            secretaryList.add(new Secretary(name,username, password, id));
    }

    public void addSecretary(String name, String username, String password, int id, long accNo, String ifsc, Authenticator auth, int type){
           Secretary p = new Secretary(name, username, password, id);
           auth.register(username, password, type);
            secretaryList.add(p);
                p.setAcc(accNo,ifsc);
    }

    public String getSecretaryList(){
        Collections.sort(secretaryList);
        StringBuilder secretaryDetails= new StringBuilder();
        for(Secretary secretary: secretaryList){
            secretaryDetails.append(secretary).append("\n");
            secretaryDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return secretaryDetails.toString();
    }

    public String getSecretaryLeaveList() {
        Collections.sort(secretaryList);
        StringBuilder secretaryDetails = new StringBuilder();
        for (Secretary secretary : secretaryList) {
            secretaryDetails.append(secretary).append(secretary.leaveString()).append("\n");
            secretaryDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return secretaryDetails.toString();
    }

    public int getSize(){
        if(secretaryList.isEmpty()){return 0;}
        return secretaryList.size();
    }

}
