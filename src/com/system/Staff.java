package com.system;

import org.jetbrains.annotations.NotNull;

public abstract class Staff extends User implements Comparable<Staff>{
    private float salary;
    private boolean salaryCredited = false;

    public Staff(String username, String password, int type){
        super(username, password, type);
    }

    public float getSalary() {
        return salary;
    }

    public int getSalaryStatus() {
        if (this.getAccount() == null) {
            return -1;
        } else {
            if (this.salaryCredited) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setSalaryCredited(boolean salaryCredited) {
        this.salaryCredited = salaryCredited;
    }

    public void setAcc(long accNum, String ifsc){
        setAccount(new Account(accNum,ifsc));
    }

    public boolean isSalaryCredited() {
        return salaryCredited;
    }

    public abstract float calculateSalary();

    public float calculateTax(int amount){
        if(amount*12 >= 300000 && amount*12 < 500000){return 0.05f;}
        else if(amount*12 >= 500000 && amount < 1000000 ){return 0.2f;}
        else if(amount*12 >= 1000000){return 0.3f;}
        return 0.0f;
    }

    public String leaveString(){
        return this+ " | "+ " No. of leave "+ this.getLeaveNum();
    }

    @Override
    public int compareTo(@NotNull Staff st) {
        return this.getId()-st.getId();
    }
}
