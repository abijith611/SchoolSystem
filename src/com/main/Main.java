package com.main;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws ParseException {
        String choice;
        Scanner sc = new Scanner(System.in);
        Ui s = new Ui();
        System.out.println("welcome to the school management system....");
        s.init();
        outer:
        while(true) {
            System.out.println("enter the choice to select action:");
            System.out.println(" 1. Login \n 2. Exit");
            choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                s.login();
                continue;
            }
            else if(choice.equals("2")){
                break;
            }
            System.out.println("invalid input: enter valid choice");
        }
    }
}
