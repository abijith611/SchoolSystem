package com.system;
public class Teacher extends Staff{
    private int amount = 30000, hra = 5000, da = 1000;
    private String subHandled;
    private int cls;

    public Teacher(String name, String emailID, String pwd, int staffID, String subHandled, int cls){
        super(emailID, pwd,2);
        setName(name);
        this.subHandled = subHandled;
        setId(staffID);
        this.cls = cls;
    }

    public String getSubHandled() {
        return subHandled;
    }

    public int getCls() {
        return cls;
    }

    @Override
    public float calculateSalary() {
        int leave;
        if(this.getLeaveNum()<3){leave = 0;}
        else{leave = this.getLeaveNum()-2;}
        int amount = this.amount - ((this.amount /30) * leave);
        float basicPay = amount - (amount * calculateTax(amount));
        setSalary(basicPay  + hra + da);
        return getSalary();
    }

    public String toString(){
        String details = "";
        details += "ID: "+getId() +" | "+ " Name: " + getName()+" | " + " Subject Handled: "+ this.subHandled+" | "+"class handled: "+this.cls;
        return details;
    }
}
