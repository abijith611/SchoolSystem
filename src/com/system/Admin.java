package com.system;

import java.util.HashMap;
import java.util.Map;

public class Admin extends User{
    private float revenue = 1000000f;
    HashMap<String, Float> transaction = new HashMap<>();
    public Admin(){
        super("abi123", "abcdefgh", 5);
       setName("abijith");
        setId(1);
        setAccount(new Account(5010098767541234L, "KVBS0000123"));
    }

    public float getRevenue() {
        return revenue;
    }

    public HashMap<String, Float> getTransaction(){
        return transaction;
    }

    public void addTransaction(String name, float amount){
        float val = 0;
        this.revenue+=amount;
        for (Map.Entry<String,Float> entry : transaction.entrySet()){
            if(entry.getKey().equals(name)){ val = entry.getValue();}}

        transaction.put(name, amount+val);
    }

    public boolean giveSalary(float amount){
        if(this.revenue < amount){ return false;}
        this.revenue -= amount;
        return true;
    }





}
