package com.system;

public class Secretary extends Staff{
    private int amount = 75000, hra = 10000, da = 3500, tra = 2000;
    public boolean salaryCredited = false;
    public Secretary(String name,String emailID, String pwd, int staffID){
        super(emailID, pwd, 4);
        setName(name);
        setId(staffID);
    }

    @Override
    public float calculateSalary() {
        float basicPay = amount-(amount*calculateTax(amount));
        setSalary(basicPay + hra + da + tra);
        return getSalary();
    }

    public String toString(){
        String details = "";
        details += "ID: "+getId() +" | "+ " Name: " + getName();
        return details;
    }


}
