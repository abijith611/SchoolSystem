package com.utility;

import com.system.User;

import java.util.ArrayList;

public class Authenticator {

private final ArrayList<User> userList = new ArrayList<>();

    public void register(String username, String password, int type){
            userList.add(new User(username, password, type));
    }


    public String login(String uname, String pword){
        userList.add(new User("abi123","abcdefgh", 5));
        for(User u: userList){
            if(u.getUserId().equals(uname)){
                if(u.getPwd().equals(pword)) return "1";
                else return "incorrect password!!";
            }
        }
        return "not registered user!!";
    }

     public int getType(String uname){
        for(User u: userList){
            if(u.getUserId().equals(uname)) return u.getType();
        }
        return 0;
    }


    public boolean isUniqueUsername(String username){
        for(User u: userList){
            if(u.getUserId().equals(username)){
                return false;
            }
        }
        return true;
    }

    private User getUser(String username){
        for(User ua: userList){
            if(ua.getUserId().equals(username)){
                return ua;
            }
        }
        return null;
    }

    public void remove(String username){
        userList.remove(getUser(username));
    }

     public void exit(){
        System.exit(1);
    }
}
