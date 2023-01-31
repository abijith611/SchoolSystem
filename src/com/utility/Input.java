package com.utility;

import com.system.Block;
import com.system.JuniorSubject;
import com.system.Subject;
import com.system.SeniorSubject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Input {
    //SwitchChoice sw = new SwitchChoice();
    Validator valid = new Validator();
    Scanner sc = new Scanner(System.in);

    public String getName(String msg) {
        String name;
        while (true) {
            System.out.println(msg);
            name = sc.nextLine();
            if (valid.isNameValid(name)) return name;
        }
    }

    public String getUsername(String msg, Authenticator auth) {
        String username;
        while (true) {
            System.out.println(msg);
            username = sc.nextLine().trim();
            if (!auth.isUniqueUsername(username)) {
                System.out.println("username already exists");
                continue;
            }
            if (valid.isUsernameValid(username))
                return username;
        }
    }

    public String getUsername(String msg) {
        String username;
        while (true) {
            System.out.println(msg);
            username = sc.nextLine().trim();
            if (valid.isUsernameValid(username))
                return username;
        }
    }

    public String getStrongPassword(String msg) {
        String password;
        while (true) {
            System.out.println(msg);
            password = sc.nextLine().trim();
            String reg = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$";
            if (!password.matches(reg)) {
                System.out.println("enter strong password");
                continue;
            } else if (!valid.isPwd(password)) {
                System.out.println("password must be minimum of 8 characters");
                continue;
            }
            if (valid.isPwd(password)) return password;

        }
    }

    public String getPassword(String msg) {
        String password;
        while (true) {
            System.out.println(msg);
            password = sc.nextLine().trim();
            if (!(password.isEmpty())) {
                break;
            }
            System.out.println("invalid input: enter something!!");
        }
        return password;

    }

    public void getConfirmPassword(String password) {
        String confirmPassword;
            while(true) {
                confirmPassword = getPassword("enter the password again to confirm");
                if (confirmPassword.equals(password)) break;
                System.out.println("passwords do not match: enter again");
            }
    }


    public int getId(String msg) {
        String id;
        while (true) {
            //try {
            System.out.println(msg+" | press 0 to exit");
            id = sc.nextLine().trim();
            if (id.matches("[0-9][0-9]{0,2}"))
                return Integer.parseInt(id);
            else
                System.out.println("invalid input: enter id between range 1-999");
        }
    }



    public int getCls(String msg) {
        String cls;
        while (true) {
            System.out.println(msg);
            cls = sc.nextLine().trim();
            if (cls.matches("([1-9]|1[0-2])")) {
                return Integer.parseInt(cls);
            } else {
                System.out.println("invalid input: enter class from 1 to 12");
                //sc.nextLine();
            }
        }
    }

    public String getBlock(String msg) {
        String str;
        while (true) {
            System.out.println(msg + ". enter 0 to exit");
            int i = 1;
            for (Block item : Block.values()) {
                System.out.print(i + ". " + item + "\t");
                i++;
            }
            System.out.println();
            i--;
            String regex = "[0-" + i + "]";
            str = sc.nextLine();
            if (str.matches(regex)) {
                if (str.equals("0")) return "0";
                return Block.values()[Integer.parseInt(str) - 1].toString();
            } else {
                System.out.println("invalid input: enter valid choice");
                //sc.nextLine();
            }
        }
    }

    public int getMark(String msg) {
        String mark;
        while (true) {
            System.out.println(msg);
            mark = sc.nextLine().trim();
            if (mark.matches("[0-9]{1,2}|100")) {
                return Integer.parseInt(mark);
            } else {
                System.out.println("invalid input: mark must be from 0 to 100");
                //sc.nextLine();
            }
        }
    }

    public long getAcc(String msg) {
        String accNum;
        while (true) {
            System.out.println(msg);
            accNum = sc.nextLine().trim();
            if (accNum.matches("[0-9]{9,18}")) {
                return Long.parseLong(accNum);
            } else {
                System.out.println("invalid acc number: enter 9-18 digits");
                //sc.nextLine();
            }
        }
    }

    public String getIfsc(String msg) {
        String ifsc;
        while (true) {
            System.out.println(msg);
            ifsc = sc.nextLine().trim();
            if (ifsc.matches("[a-zA-Z0-9]{11}")) {
                return ifsc;
            } else {
                System.out.println("invalid ifsc code: enter 11 digits");
            }
        }
    }

    public int getLeaveDays(String msg) {
        String leaveNum;
        while (true) {
            System.out.println(msg);
            leaveNum = sc.nextLine().trim();
            if (leaveNum.matches("[0-7]")) {
                return Integer.parseInt(leaveNum);
            } else {
                System.out.println("can apply leave for only 1 to 7 days");
                //sc.nextLine();
            }
        }
    }

    public Date getDate(String msg) {
        String date;
        Date date1;
        while (true) {
            try {
                System.out.println(msg);
                date = sc.nextLine().trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date1 = dateFormat.parse(date);
                return date1;
            } catch (Exception e) {
                System.out.println("invalid input: enter in correct format");
            }
        }
    }

    public int getSubNum(String msg) {
        String subjNum;
        while (true) {
            System.out.println(msg);
            subjNum = sc.nextLine();
            if (subjNum.matches("[3-6]")) {
                return Integer.parseInt(subjNum);
            } else {
                System.out.println("invalid input: must enter 3 - 6 subjects");
            }
        }
    }


    public String getChoice(String msg, String pattern) {
        String choice;
        while (true) {
            System.out.println("enter the option to do");
            System.out.println(msg);
            choice = sc.nextLine();
            if (choice.matches(pattern)) {
                return choice;
            } else {
                System.out.println("invalid input: enter valid choice");
            }
        }
    }
    public String getSubjectHandled(String msg, int cls){
        String str;
        while (true) {
            System.out.println(msg + ". enter 0 to exit");
            int i = 1;
            if(cls<9){
                for (JuniorSubject item : JuniorSubject.values()) {
                    System.out.print(i + ". " + item + "\t");
                    i++;
                }
                System.out.println();
            }
            else {
                for (SeniorSubject item : SeniorSubject.values()) {
                    System.out.print(i + ". " + item + "\t");
                    i++;
                }
                System.out.println();
            }
            str = sc.nextLine();
            if (str.matches("[1-6]")) {
                if (str.equals("0")) return "0";
                if(cls<9) return JuniorSubject.values()[Integer.parseInt(str) - 1].toString();
                else return SeniorSubject.values()[Integer.parseInt(str) - 1].toString();
            } else {
                System.out.println("invalid input: enter valid choice");
            }
        }
    }

    public ArrayList<Subject> getSubject(String msg, int cls) {
        String subjName, subjCode;
        int subjNum, subjMark;
        ArrayList<Subject> subList = new ArrayList<>();
        subjNum = getSubNum("enter the no. of subjects");
        for (int i = 0; i < subjNum; i++) {
            System.out.println(msg);
            int c = 1;
            if(cls<9) {
                for (JuniorSubject item : JuniorSubject.values()) {
                    System.out.print(c + ". " + item + "    ");
                    c++;
                }
                System.out.println();
            }
            else{
                for (SeniorSubject item : SeniorSubject.values()) {
                    System.out.print(c + ". " + item + "    ");
                    c++;
                }
                System.out.println();
            }
            while (true) {
                String option = sc.nextLine();
                if (option.matches("[1-6]")) {
                    if (option.equals("0")) {
                        return null;
                    }
                    int opt = Integer.parseInt(option);
                    if(cls<9){
                        subjName = JuniorSubject.values()[opt - 1].toString();
                        subjCode = JuniorSubject.values()[opt - 1].subCode;
                    }
                    else {
                        subjName = SeniorSubject.values()[opt - 1].toString();
                        subjCode = SeniorSubject.values()[opt - 1].subCode;
                    }
                    if (valid.isNotSubjectValid(subjName, subList)) {
                        System.out.println("this subject mark already entered!!");
                        continue;
                    }
                    break;
                } else {
                    System.out.println("invalid input: enter the valid choice");
                }
            }
            subjMark = getMark("enter the subject mark");
            subList.add(new Subject(subjCode, subjName, subjMark));
        }
        return subList;
    }

    public float getFees(String msg) {
        while (true) {
            System.out.println(msg);
            String amt = sc.nextLine();
            if (amt.matches("[0-9]+|([0-9]+\\.[0-9]*)")) {
                return Float.parseFloat(amt);
            } else {
                System.out.println("invalid input: enter the valid amount");
            }

        }
    }

    public  int getTypeOfUser(String msg){
        String type;
        while(true) {
            System.out.println(msg);
            type = sc.nextLine();
            if (type.matches("[1-4]")) {
                return Integer.parseInt(type);
            }
            else{System.out.println("invalid input: enter valid choice");
                //sc.nextLine();
                }
        }
    }
}