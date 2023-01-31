package com.utility;

//import java.util.Scanner;

import com.system.Account;
import com.system.Subject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
public class Validator {
    public boolean isNameValid(String name){
        try {
            if (name.length() > 25) {
                System.out.println("name too long");
                return false;
            }
            else if(!isAlpha(name)){
                System.out.println("invalid name");
                return false;
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isUsernameValid(String name){
        try {
            if (name.length() > 25) {
                System.out.println("invalid input: username too long");
                return false;
            }
            else if(!isUn(name)){
                System.out.println("invalid username: enter again");
                return false;
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isAlpha(String name){
        return((name!= null) && (!name.equals(" ")) && (name.matches("^[a-zA-Z_. ]*$"))  && (!name.equals("")) );
    }

    public boolean isUn(String name){
        return((name!= null) && (!name.equals(" ")) && (name.matches("^[a-zA-Z][a-zA-Z_ .@0-9]{2,}$"))  && (!name.equals("")) );
    }

    public boolean isPwd(String name){
        return((name != null) && (name.length() >= 8));
    }

    public boolean isSunday(Date date){
        return date.getDay() == 0;
    }

    public boolean isNotValidDate(Date date){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateCurrent = new Date();
            String stringDate = dateFormat.format(dateCurrent);
            Date currentDate = dateFormat.parse(stringDate);
            return date.compareTo(currentDate) < 0;
        }
        catch(ParseException e){
            return false;
        }
    }

    public boolean isNotValidMonthYear(Date date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateCurrent = new Date();
            String stringDate = dateFormat.format(dateCurrent);
            Date currentDate = dateFormat.parse(stringDate);
            return date.getMonth() != currentDate.getMonth() || date.getYear() != currentDate.getYear();
        }
        catch(ParseException e){
            return false;
        }

    }

    public boolean isRemainingDaysAvailable(int leaveNum){
        int count=0;
        Date dateCurrent = new Date();
        YearMonth yearMonthObject = YearMonth.of(dateCurrent.getYear(), dateCurrent.getMonth());
        for(int i = dateCurrent.getDate(); i <= yearMonthObject.lengthOfMonth(); i++){
            dateCurrent.setDate(i);
            if(dateCurrent.getDay()!=0){
                count++;
            }
        }
        return leaveNum <= count;
    }

    public boolean isAccount(Account account){
        return account != null;
    }

    public boolean isNotSubjectValid(String subjectName, ArrayList<Subject> subjectArrayList){
        for(Subject subject: subjectArrayList){
            if(subject.getSubjName().equals(subjectName)){
                return true;
            }
        }
        return false;
    }

}
