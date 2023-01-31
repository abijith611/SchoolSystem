package com.utility;

public class Display {
    public void displayRemoveOptions(int val, int type){
        String user="";
        switch (type){
            case 2->user = "student";
            case 3->user = "teacher";
            case 4->user = "principal";
        }
        switch(val){
            case -1->System.out.println("no such "+user+"!!");
            case 0->System.out.println("no "+user+" available to remove!!");
            case 1->System.out.println(user+" removed!!");
        }
    }

    public void displaySalaryOptions(int val, float sal){
        switch (val){
            case -1->System.out.println("give account details to get salary!!");
            case 0->System.out.println("salary is being processed. please wait!!");
            case 1->System.out.println("salary amount of "+sal+"rs has been credited!!");
        }
    }

    public void displayHeaderFormat(String msg){
        String format ="*************************----------------------------"+msg+"----------------------------*************************";
        System.out.println(format);
    }

    public void showDetails(String msg){
        if(msg.equals("")) System.out.println("there are none to display");
        else System.out.println(msg);
    }


}
