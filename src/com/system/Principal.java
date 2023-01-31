package com.system;

public class Principal extends Staff{

    private int amount = 45000, hra = 7500, da = 2000, tra = 1500;
    public String block;

    public Principal(String name,String emailID, String pwd, int principalID, String block){
        super(emailID, pwd, 3);
        setName(name);
        setId(principalID);
        this.block = block;
    }

    public float calculateSalary() {
        int leave;
        if(this.getLeaveNum()<3){leave = 0;}
        else{leave = this.getLeaveNum()-2;}
        int amount = this.amount - ((this.amount /30) * leave);
        float basicPay = amount - (amount * calculateTax(amount));
        setSalary(basicPay  + hra + da+ tra);
        return getSalary();
    }

    public String formatter(String word){
        return word.replace("_"," ");
    }

    public String toString(){
        String details = "";
        details += "ID: "+ getId() +" | "+ " Name: " + getName()+" | " + " Block: "+ formatter(this.block);
        return details;
    }


}
