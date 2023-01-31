package com.system;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private Leave leaveObj = new Leave();
    private Account account;
    private int id;
    private String name;
    private int type;
    private String userId;
    private String pwd;

    public User(String emailId, String pwd, int type){
        this.userId = emailId;
        this.pwd = pwd;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPwd(){
        return pwd;
    }

    public Leave getLeaveObj() {
        return leaveObj;
    }

    public int  getLeaveNum(){
        if(this.leaveObj == null){return 0;}
        return this.leaveObj.leaveNum;
    }

    public Account getAccount() {
        return account;
    }

    public ArrayList<Date> getLeaveList(){
        if(leaveObj!=null)
            return this.leaveObj.leaveList;
        return null;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
