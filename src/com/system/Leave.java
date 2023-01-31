package com.system;

import java.util.ArrayList;
import java.util.Date;

public class Leave {
    public int leaveNum;
    public ArrayList<Date> leaveList = new ArrayList<>();

    public boolean setLeave(Date leaveDate) {
        if(leaveList.contains(leaveDate)){return false;}
        this.leaveList.add(leaveDate);
        this.leaveNum++;
        return true;
    }
}
