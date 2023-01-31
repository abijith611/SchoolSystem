package com.DB;

import com.utility.Authenticator;
import com.system.*;

import java.util.ArrayList;
import java.util.Collections;

public class PrincipalDB {
    private final ArrayList<Principal> principalList= new ArrayList<>();
    public  Principal getUser(String username){
        for(Principal principal: principalList){
            if(username.equals(principal.getUserId())){
                return principal;
            }
        }
        return null;
    }

    public  Principal getUser(int id){
        for(Principal principal: principalList){
            if(id ==principal.getId()){
                return principal;
            }
        }
        return null;
    }

    public void addPrincipal(String name, String username, String password, int id, String block, Authenticator auth, int type){
        auth.register(username, password, type);
        principalList.add(new Principal(name,username, password, id, block));
    }

    public void addPrincipal(String name, String username, String password, int id, String sub, long accNo, String ifsc, Authenticator auth, int type){
            Principal p = new Principal(name, username, password, id, sub);
            auth.register(username, password, type);
            principalList.add(p);
            p.setAcc(accNo,ifsc);
    }

    public int removePrincipal(Principal principal, Authenticator auth){
            if(principal == null){return -1;}
            if(getSize()==0){return 0;}
            auth.remove(principal.getUserId());
            principalList.remove(principal); return 1;
    }

    public int getSize(){
        if(principalList.isEmpty()){return 0;}
        return principalList.size();}

    public String getPrincipalList(){
        Collections.sort(principalList);
        StringBuilder principalDetails= new StringBuilder();
        for(Principal principal: principalList){
            principalDetails.append(principal).append("\n");
            principalDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return principalDetails.toString();
    }

    public String getPrincipalLeaveList(){
        Collections.sort(principalList);
        StringBuilder principalDetails= new StringBuilder();
        for(Principal principal: principalList){
            principalDetails.append(principal).append(principal.leaveString()).append("\n");
            principalDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return principalDetails.toString();
    }




    public boolean isBlockPresent(String block){
        for(Principal principal: principalList){
            if(principal.block.equals(block)){
                return true;
            }
        }
        return false;
    }
}

