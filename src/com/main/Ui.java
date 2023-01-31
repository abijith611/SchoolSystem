package com.main;

import com.DB.PrincipalDB;
import com.DB.SecretaryDB;
import com.DB.StudentDB;
import com.DB.TeacherDB;
import com.utility.*;
import com.system.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Ui {
private final Input in = new Input();
private final Admin admin = new Admin();
private final StudentDB studentDB= new StudentDB();
private final TeacherDB teacherDB = new TeacherDB();
private final PrincipalDB principalDB = new PrincipalDB();
private final SecretaryDB secretaryDB = new SecretaryDB();
private final Authenticator auth = new Authenticator();
private final Display display = new Display();
private final Validator valid = new Validator();

private final Scanner sc = new Scanner(System.in);
    //COMMON OPERATIONS
    public void init() {
        //student data
        studentDB.addStudent("aadhi", 1, 10, "aadhi123", "aadhi123", auth,1);
        studentDB.addStudent("ashwin", 1, 11, "ashwin123", "ashwin123", auth,1);
        studentDB.addStudent("akshaya", 2, 10, "akshaya123", "akshaya123", auth,1);
        studentDB.addStudent("diwakar", 3, 10, "diwakar123", "diwakar123", auth,1);
        studentDB.addStudent("balaji", 2, 11, "balaji123", "balaji123", auth,1);

        //teacher data
        teacherDB.addTeacher("shyam", "shyam123", "venkatesh", 1, "MATHS", 1, 10023456712312L,"KVBL0021260", auth,2);
        teacherDB.addTeacher("rahul", "rahul123", "rahul", 2, "ENGLISH", 2, 21056456123312L,"SBIP0056760", auth,2);
        teacherDB.addTeacher("john", "john123", "john", 4, "TAMIL", 3, 10023456712312L,"KVBL0021260", auth,2);
        teacherDB.addTeacher("jack", "jack123", "jack", 5, "COMPUTER_SCIENCE", 4, 21056456123312L,"SBIP0056760", auth,2);
        teacherDB.addTeacher("david", "david123", "david", 3, "MATHS", 5, 10023456712312L,"KVBL0021260", auth,2);
        teacherDB.addTeacher("karthik", "karthik123", "karthik", 7, "TAMIL", 6, 21056456123312L,"SBIP0056760", auth,2);
        teacherDB.addTeacher("arun", "arun123", "arun", 6, "MATHS", 7, 10023456712312L,"KVBL0021260", auth,2);
        teacherDB.addTeacher("sethu", "sethu123", "sethu", 10, "ENGLISH", 8, 21056456123312L,"SBIP0056760", auth,2);
        teacherDB.addTeacher("vimal", "vimal123", "vimal", 12, "PHYSICS", 9, 10023456712312L,"KVBL0021260", auth,2);
        teacherDB.addTeacher("vishnu", "vishnu123", "vishnu", 11, "CHEMISTRY", 10, 21056456123312L,"SBIP0056760", auth,2);
        teacherDB.addTeacher("venkatesh", "venky123", "venkatesh", 9, "MATHS", 11, 10023456712312L,"KVBL0021260", auth,2);
        teacherDB.addTeacher("lakshmi", "lakshmi123", "lakshmi", 8, "PHYSICS", 12, 21056456123312L,"SBIP0056760", auth,2);

        //principal data
        principalDB.addPrincipal("suresh", "suresh123", "sureshaa", 1, "MAIN_BLOCK", 11108972345690L, "SBIB9876123", auth,3);

        //secretary data
        secretaryDB.addSecretary("venkatachalapathy", "chals", "smvec123", 1, 567890123456778L, "ICIC8976321", auth, 4);
    }

    private void signOut(){
        System.out.println("signing out......");
    }

    //LOGIN
    public void login(){
        display.displayHeaderFormat("login");
        String username = in.getUsername("enter the username");
        String password = in.getPassword("enter the password");
        String msg = auth.login(username, password);
        if (msg.equals("1")) {
            System.out.println("Login successful!");
            int type = auth.getType(username);
            loginOptions(type,username);
        }
        else{
            System.out.println(msg);
        }
    }

    private void loginOptions(int choice, String username)  {
        System.out.println("you are logged in as: "+username);
        Map<Object, Choice> choiceExecutableMap = new HashMap<>();
        choiceExecutableMap.put(1,new Choice("Student Login", () -> loginStudent(username)));
        choiceExecutableMap.put(2, new Choice("Teacher Login",()-> loginTeacher(choice, username)));
        choiceExecutableMap.put(3, new Choice("Principal Login",()-> loginPrincipal(choice, username)));
        choiceExecutableMap.put(4, new Choice("Secretary Login",()-> loginSecretary(choice, username)));
        choiceExecutableMap.put(5, new Choice("Admin Login", this::loginAdmin));
        choiceExecutableMap.get(choice).getChoiceExecutable().execute();
    }

    private void loginStudent(String username){
        Student currentStudent = studentDB.getUser(username);
        display.displayHeaderFormat("Student Login");
        while (true) {
            Map<Object, Choice> choiceExecutableMap = new HashMap<>();
            choiceExecutableMap.put("1", new Choice(" view mark sheet",()->displayMarkSheet(currentStudent)));
            choiceExecutableMap.put("2", new Choice(" pay fees",()->payFees(currentStudent)));
            choiceExecutableMap.put("3", new Choice(" sign out", this::signOut));
            boolean flag= executeChoice(choiceExecutableMap);
            if (!flag) return;
        }
    }

    private void loginTeacher(int type, String username){
        Teacher currentTeacher = teacherDB.getUser(username);
        display.displayHeaderFormat("Teacher Login");
        while (true) {
            int cls = currentTeacher.getCls();
            Map<Object, Choice> choiceExecutableMap = new HashMap<>();
            choiceExecutableMap.put("1", new Choice("Add Subject Marks",()->addSubjectMarks(cls)));
            choiceExecutableMap.put("2", new Choice("Add Student",()->addStudent(type)));
            choiceExecutableMap.put("3", new Choice("Remove Student",()->removeStudent(type, cls)));
            choiceExecutableMap.put("4", new Choice("View Class Students List",()->viewClassStudentList(cls)));
            choiceExecutableMap.put("5", new Choice("View Students Mark Sheet",()->viewStudentMarkSheet(cls)));
            choiceExecutableMap.put("6", new Choice("Apply Leave",()->applyLeave(currentTeacher)));
            choiceExecutableMap.put("7", new Choice("Add Account Details",()->getAccountDetails(currentTeacher)));
            choiceExecutableMap.put("8", new Choice("Get Salary Status",()->getSalaryStatus(currentTeacher)));
            choiceExecutableMap.put("9", new Choice("Sign Out", this::signOut));
            boolean flag= executeChoice(choiceExecutableMap);
            if (!flag) return;
        }
    }

    private void loginPrincipal(int type, String username){
        Principal currentPrincipal = principalDB.getUser(username);
        display.displayHeaderFormat("Principal Login");
        while (true) {
            Map<Object, Choice> choiceExecutableMap = new HashMap<>();
            choiceExecutableMap.put("1", new Choice("Add Teacher",()->addTeacher(type)));
            choiceExecutableMap.put("2", new Choice("Remove Teacher",()->removeTeacher(type)));
            choiceExecutableMap.put("3", new Choice("View Teachers List", this::viewTeacherList));
            choiceExecutableMap.put("4", new Choice("View Total Students List", this::viewStudentsList));
            choiceExecutableMap.put("5", new Choice("Apply Leave",()->applyLeave(currentPrincipal)));
            choiceExecutableMap.put("6", new Choice("Add Account Details",()->getAccountDetails(currentPrincipal)));
            choiceExecutableMap.put("7", new Choice("Get Salary Status",()->getSalaryStatus(currentPrincipal)));
            choiceExecutableMap.put("8", new Choice("Sign Out", this::signOut));
            boolean flag= executeChoice(choiceExecutableMap);
            if (!flag) return;
        }
    }

    private void loginSecretary(int type, String username){
        Secretary currentSecretary = secretaryDB.getUser(username);
        display.displayHeaderFormat("Secretary Login");
        while (true) {
            Map<Object, Choice> choiceExecutableMap = new HashMap<>();
            choiceExecutableMap.put("1", new Choice("Add Principal",()->addPrincipal(type)));
            choiceExecutableMap.put("2", new Choice("Remove Principal",()->removePrincipal(type)));
            choiceExecutableMap.put("3", new Choice("View Principals List", this::viewPrincipalDetails));
            choiceExecutableMap.put("4", new Choice("Add Account Details",()->getAccountDetails(currentSecretary)));
            choiceExecutableMap.put("5", new Choice("Get Salary Status",()->getSalaryStatus(currentSecretary)));
            choiceExecutableMap.put("6", new Choice("Sign Out", this::signOut));
            boolean flag= executeChoice(choiceExecutableMap);
            if (!flag) return;
        }
    }

    private void loginAdmin(){
        display.displayHeaderFormat("Admin Login");
        while (true) {
            Map<Object, Choice> choiceExecutableMap = new HashMap<>();
            choiceExecutableMap.put("1", new Choice("View School Details", this::viewSchoolDetails));
            choiceExecutableMap.put("2", new Choice("View Leave Details", this::viewLeaveDetails));
            choiceExecutableMap.put("3", new Choice("Credit Salary", this::creditSalary));
            choiceExecutableMap.put("4", new Choice("View Fees Collected", this::viewFees));
            choiceExecutableMap.put("5", new Choice("Register User", this::register));
            choiceExecutableMap.put("6", new Choice("Sign Out", this::signOut));
            boolean flag= executeChoice(choiceExecutableMap);
            if (!flag) return;
        }
    }

    //STUDENT OPERATIONS
    private void displayMarkSheet(Student student){
        String markSheet =student.ObtainMarkSheet();
        System.out.println(markSheet);
    }

    private void payFees(Student student){
        float amt;
        display.displayHeaderFormat("Pay Fees");
        while(true){
            if(student.isFeesPaid() && student.getFeesRequired()==0){System.out.println("full fees already paid.."); break;}
            else System.out.println("fees to be paid: "+student.getFeesRequired());
            if(student.getFeesRequired()>10000) {
                amt = in.getFees("Enter the amount to pay fees");
            }
            else{
                amt = student.getFeesRequired();
            }
            if (amt > student.getFeesRequired()){
                System.out.println("the amount paid is more than required amount..");
            }
            else if(amt < 10000 && student.getFeesRequired() > 10000){
                      System.out.println("amount paid should be at least 10000rs");
                  }
            else {
                System.out.println("are you sure.. you want to pay the amount: "+amt +" | press yes to continue..");
                if(sc.nextLine().equalsIgnoreCase("yes")){
                    System.out.println("balance fees: "+student.payFees(amt));
                    admin.addTransaction(student.getName(),amt);
                    System.out.println("Transaction successful!!");
                }
                else{
                    System.out.println("exiting...");
                }
                break;
            }
        }
    }

    //TEACHER OPERATIONS
    private void addSubjectMarks(int cls){
        display.displayHeaderFormat("Subject Marks");
        int rollNo;
        Student student;
        while(true){
            rollNo = in.getId("enter the roll number");
            if(rollNo==0){return;}
            if (studentDB.isStudentIdPresent(rollNo, cls)) {
                break;
            }
            System.out.println("no such student in class "+cls);
        }
        student=studentDB.getStudentByCls(rollNo,cls);
        if (student == null) {
            System.out.println("there is no such student");
            return;
        }
        else if(student.getSubList()!=null){
            System.out.println("subject values already set...");
            System.out.println("do you want to change marks?? | if so type yes");
            if(sc.nextLine().equalsIgnoreCase("yes")){
               System.out.println("proceeding...");
            }
            else{
                return;
            }

        }
        ArrayList<Subject>  subList = in.getSubject("choose the subject values. press 0 to exit", cls);
        if(subList==null){return;}
        student.setSubject(subList);
        System.out.println("subject values set!!");
    }

    private void addStudent(int type){
        int rollNo;
        String stud_username, stud_password;
        display.displayHeaderFormat("Add Student");
        //sc.nextLine();
        String name = in.getName("enter the student name");
        int cls = in.getCls("enter the class that the student belongs to");
        while(true) {
            rollNo = in.getId("enter the roll no");
            if(rollNo==0){return;}
            if(!studentDB.isStudentIdPresent(rollNo, cls)){
                break;
            }
            System.out.println("roll no already available in class "+cls);
        }
        stud_username = in.getUsername("set the username for the student", auth);
        stud_password = in.getPassword("set the password for the student");
        //rollNo = concat(cls,rollNo);
        studentDB.addStudent(name, rollNo, cls, stud_username, stud_password, auth, type-1);
        System.out.println("student added...");
    }

    private void removeStudent(int type, int cls){
        display.displayHeaderFormat("Remove Student");
        int rollNo;
        Student student;
        while(true){
            rollNo = in.getId("enter the roll no to delete");
            if(rollNo==0){return;}
            if (studentDB.isStudentIdPresent(rollNo, cls)) {
                break;
            }
            System.out.println("no such student in class "+cls);
        }
        student=studentDB.getStudentByCls(rollNo,cls);
        display.displayRemoveOptions(studentDB.removeStudent(student,auth), type);
    }

    private void viewStudentMarkSheet(int cls){
        int rollNo;
        display.displayHeaderFormat("View Mark Sheet");
        Student student;
        while(true){
            rollNo = in.getId("enter the roll no to view the mark sheet");
            if(rollNo==0){return;}
            if (studentDB.isStudentIdPresent(rollNo, cls)) {
                break;
            }
            System.out.println("no such student in class "+cls);
        }

        student=studentDB.getStudentByCls(rollNo,cls);
        if (student == null) {
            System.out.println("there is no such student");
            return;
        }
        displayMarkSheet(student);
    }

    private void viewClassStudentList(int cls){
        display.displayHeaderFormat("Class Students Details");
        display.showDetails(studentDB.getClassStudentsList(cls));
    }

    //PRINCIPAL OPERATIONS
    private void addTeacher(int type){
        int id;
        display.displayHeaderFormat("Add Teacher");
        String name = in.getName("enter the teacher name");
        while(true) {
            id = in.getId("enter the teacher id");
            if(id==0){return;}
            if (teacherDB.getUser(id) == null) {
                break;
            }
            else{
                System.out.println("id already exists");
            }
        }
        int cls = in.getCls("enter the class to be handled");
        String subjectHandled = in.getSubjectHandled("enter the subject handled",cls);
        if (subjectHandled.equals("0")) {
            return;
        }
        if(teacherDB.isNotValidTeacher(subjectHandled, cls)){
            System.out.println("already teacher available for the same class and subject!!");
            System.out.println("teacher not added!!");
            return;
        }
        String username = in.getUsername("set the username for the teacher", auth);
        String password = in.getPassword("set the password for the staff");
        teacherDB.addTeacher(name, username, password, id, subjectHandled, cls, auth, type-1);
        System.out.println("teacher added...");
    }

    private void removeTeacher(int type){
        display.displayHeaderFormat("Remove Teacher");
        int id = in.getId("enter the staff id to delete the staff");
        if(id==0){return;}
        Teacher teacher = teacherDB.getUser(id);
        display.displayRemoveOptions(teacherDB.removeTeacher(teacher, auth),type);
    }

    private void viewTeacherList() {
        display.displayHeaderFormat("Teacher Details");
        display.showDetails(teacherDB.getTeacherList());
    }

    private void viewStudentsList(){
        display.displayHeaderFormat("Student Details");
        display.showDetails(studentDB.getStudentList());
    }

    //SECRETARY OPERATIONS
    private void addPrincipal(int type){
        int id;
        display.displayHeaderFormat("Add Principal");
        if(principalDB.getSize()==5){System.out.println("cannot add principal anymore!!"); return;}
        String name = in.getName("enter the name");
        while (true) {
            id = in.getId("enter the principal id");
            if(id==0){return;}
            if (principalDB.getUser(id) == null) {break;}
            else {System.out.println("id already exists");}
        }
        String block;
        while (true) {
            block = in.getBlock("enter the block");
            if (block.equals("0")) {return;}
            if (principalDB.isBlockPresent(block)) {
                System.out.println("block already assigned");
                continue;}
            break;
        }
        String username = in.getUsername("set the username for the principal", auth);
        String password = in.getPassword("set the password for the principal");
        principalDB.addPrincipal(name, username, password, id, block, auth, type - 1);
        System.out.println("principal added...");
    }

    private void removePrincipal(int type){
        display.displayHeaderFormat("Remove Principal");
        int id = in.getId("enter the principal id to delete");
        if(id==0){return;}
        Principal principal = principalDB.getUser(id);
        display.displayRemoveOptions(principalDB.removePrincipal(principal, auth),type);
    }

    private void viewPrincipalDetails(){
        display.displayHeaderFormat("Principal Details");
        display.showDetails(principalDB.getPrincipalList());
    }

    //STAFF OPERATIONS
    private void applyLeave(Staff staff)  {
        display.displayHeaderFormat("Apply Leave");
        int count =0;
        System.out.println("no.of leave applied:" + staff.getLeaveNum());
        if(staff.getLeaveNum()==7){
            System.out.println("cannot apply leave anymore!!");
            return;
        }
        int leaveNum = in.getLeaveDays("Enter the no.of days to apply leave | enter 0 to exit");
        if (leaveNum == 0) {
            return;
        }
        else if(leaveNum+ staff.getLeaveNum()>7){
            System.out.println("you can only apply leave for "+ (7 - staff.getLeaveNum())+" more days");
            return;
        }
        else if(!valid.isRemainingDaysAvailable(leaveNum)){
            System.out.println("cannot apply leave: there are not enough days in the month");
        }

        while(count < leaveNum && staff.getLeaveNum()<7) {
            Date date1 = in.getDate("enter the Date in dd/mm/yyyy");
            if(valid.isNotValidMonthYear(date1)){System.out.println("can apply leave for only same month in the same year"); continue;}
            else if(valid.isNotValidDate(date1)){System.out.println("can apply leave for only upcoming days!!"); continue;}
            else if(valid.isSunday(date1)){System.out.println("cannot apply leave on sunday!!");continue;}
            boolean flag = staff.getLeaveObj().setLeave(date1);
            if(flag){System.out.println("Leave applied!!");}
            else{System.out.println("leave already applied on the date"); continue;}
            count++;
        }
        System.out.print("leave applied:     ");
        Collections.sort(staff.getLeaveList());
        for (Date d : staff.getLeaveList()) {
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            System.out.print(sd.format(d) + "\n");
        }
        System.out.println();
    }

    private void getAccountDetails(Staff staff){
        if(staff.getAccount()==null) {
            long accNum = in.getAcc("enter the account number");
            String ifsc = in.getIfsc("enter the ifsc code");
            staff.setAcc(accNum, ifsc);
            System.out.println("account added!!");
        }
        else{
            System.out.println("account details already present");
        }
    }

    private void getSalaryStatus(Staff staff){
        display.displayHeaderFormat("Salary Status");
        display.displaySalaryOptions(staff.getSalaryStatus(), staff.getSalary());
    }

    //ADMIN OPERATIONS
    private void viewSchoolDetails(){
        display.displayHeaderFormat("School Details");
        System.out.println("School Name: A-Z Learning Matriculation Higher Secondary School");
        System.out.println("Revenue: "+admin.getRevenue());
        System.out.println("***********************************************************************************************************");
        System.out.println("Secretary: "+secretaryDB.getSize());
        display.showDetails(secretaryDB.getSecretaryList());
        System.out.println("***********************************************************************************************************");
        System.out.println("Principals: "+principalDB.getSize());
        display.showDetails(principalDB.getPrincipalList());
        System.out.println("***********************************************************************************************************");
        System.out.println("Teachers: "+ teacherDB.getSize());
        display.showDetails(teacherDB.getTeacherList());
        System.out.println("***********************************************************************************************************");
        System.out.println("Students: "+studentDB.getSize());
        display.showDetails(studentDB.getStudentList());
    }

    private void viewLeaveDetails(){
        display.displayHeaderFormat("Leave Details");
        System.out.println("-----------------------Secretary----------------------");
        display.showDetails(secretaryDB.getSecretaryLeaveList());
        System.out.println("----------------------Principal--------------------------");
        display.showDetails(principalDB.getPrincipalLeaveList());
        System.out.println("-------------------------Teacher----------------------");
        display.showDetails(teacherDB.getTeacherLeaveList());
    }

    private void creditSalary(){
        Staff obj = null;
        display.displayHeaderFormat("Credit Salary");
        String username = in.getUsername("enter the username to credit salary");
        if(auth.isUniqueUsername(username)){System.out.println("the person is not in the system. so cannot add salary !!!"); return;}
        int type1 = auth.getType(username);
        if(type1==1 || type1==5){System.out.println("can add salary only to staff"); return;}
        switch(type1) {
            case 2 -> obj = teacherDB.getUser(username);
            case 3 -> obj = principalDB.getUser(username);
            case 4 -> obj = secretaryDB.getUser(username);
        }
        if(obj!=null){
            if (valid.isAccount(obj.getAccount())) {
                if (!obj.isSalaryCredited()) {
                    float sal = obj.calculateSalary();
                    System.out.println("Are you sure you want to pay salary of amount " + sal + "rs to " + username + " | type yes to continue ");
                    if (sc.nextLine().equalsIgnoreCase("yes")) {
                        if (admin.giveSalary(sal)) {
                            obj.setSalaryCredited(true);
                            System.out.println("salary credited:" + sal);
                        } else {
                            System.out.println("insufficient funds");
                        }
                    }
                    else{
                        System.out.println("exiting...");
                    }
                } else {
                    System.out.println("salary already credited");
                }
            }
            else{
                System.out.println("account details does not exist in system");
            }
        }

    }

    private void viewFees(){
        display.displayHeaderFormat("view Fees Details");
        HashMap<String, Float> transaction =admin.getTransaction();
        for(Map.Entry<String, Float> m: transaction.entrySet()){
            System.out.println("name: "+m.getKey()+"    "+"amount: "+m.getValue());
        }
        if(transaction.isEmpty()){System.out.println("no fees to display!!");}
    }

    private void register(){
        //int id;
        display.displayHeaderFormat("Register");
        int type = in.getTypeOfUser("enter the type of user you want to register\n1. student\n2. teacher\n3. principal\n4. Secretary");
        if(type==3 && principalDB.getSize()==5){System.out.println("cannot add principal anymore!!"); return;}
        else if(type==4 && secretaryDB.getSize()==1){System.out.println("cannot add secretary anymore!!"); return;}
        String name = in.getName("enter the name");
        String username = in.getUsername("enter the username", auth);
        String password = in.getStrongPassword("enter the password");
        in.getConfirmPassword(password);
        Map<Object, Choice> choiceExecutableMap = new HashMap<>();
        choiceExecutableMap.put(1, new Choice("1",()->registerOptions(type, name, username, password, 0)));
        choiceExecutableMap.put(2, new Choice("2", ()->{
                while (true) {
                    int id = in.getId("enter your unique id");
                    if (id == 0) {
                        return;
                    }
                    if (teacherDB.getUser(id) == null) {
                        registerOptions(type, name, username, password, id);
                        break;
                    } else System.out.println("id already exists");
                }
            }
        ));
        choiceExecutableMap.put(3, new Choice("3",()->{
                while (true) {
                    int id = in.getId("enter your unique id");
                    if (id == 0) {
                        return;
                    }
                    if (principalDB.getUser(id) == null) {
                        registerOptions(type, name, username, password, id);
                        break;
                    } else System.out.println("id already exists");
                }
            }
        ));
        choiceExecutableMap.put(4,new Choice("4",()->{
                while (true) {
                    int id = in.getId("enter your unique id");
                    if (id == 0) {
                        return;
                    }
                    if (secretaryDB.getUser(id) == null) {
                        registerOptions(type, name, username, password, id);
                        break;
                    } else System.out.println("id already exists");
                }
            }
        ));
        choiceExecutableMap.get(type).getChoiceExecutable().execute();
    }

    //REGISTER
    private void registerOptions(int choice, String name, String username, String password, int id){
        Map<Object, Choice> choiceExecutableMap = new HashMap<>();
        choiceExecutableMap.put(1, new Choice("Student Register", ()->registerStudent(choice, name, username, password)));
        choiceExecutableMap.put(2, new Choice("Teacher Register", ()->registerTeacher(choice, name, username,password, id)));
        choiceExecutableMap.put(3, new Choice("Principal Register", ()->registerPrincipal(choice, name, username,password, id)));
        choiceExecutableMap.put(4, new Choice("Secretary Register", ()->registerSecretary(choice, name, username,password, id)));
        choiceExecutableMap.get(choice).getChoiceExecutable().execute();
    }

    private void registerStudent(int type, String name, String username, String password){
        int cls = in.getCls("enter the class");
        int id;
        while(true) {
            id = in.getId("enter the roll no");
            if(id==0){return;}
            if(!studentDB.isStudentIdPresent(id, cls)){
                break;
            }
            System.out.println("roll no already available in class "+cls);
        }

        //id = concat(cls,id);
        studentDB.addStudent(name, id, cls, username, password, auth, type);
        System.out.println("student added!!");
    }

    private void registerTeacher(int type, String name, String username, String password, int id){
        int cls = in.getCls("enter the class being handled");
        String subjectHandled = in.getSubjectHandled("enter the subject handled", cls);
        if (subjectHandled.equals("0")) {
            return;
        }
        if(teacherDB.isNotValidTeacher(subjectHandled, cls)){
            System.out.println("already teacher available for the same class and subject!!");
            System.out.println("teacher not added!!");
            return;
        }
        teacherDB.addTeacher(name, username, password, id, subjectHandled,cls, auth, type);
        System.out.println("teacher added!!");
    }

    private void registerPrincipal(int type, String name, String username, String password, int id){
        while(true) {
            String block = in.getBlock("enter the block");
            if (block.equals("0")) {
                return;
            }
            if (principalDB.isBlockPresent(block)) {
                System.out.println("block already assigned");
                continue;
            }
            principalDB.addPrincipal(name, username, password, id, block, auth, type);
            System.out.println("principal added!!");
            break;
        }
    }

    private void registerSecretary(int type, String name, String username, String password, int id){
        secretaryDB.addSecretary(name, username, password, id, auth, type);
        System.out.println("secretary added!!");
    }

    //CHOICE OPERATIONS
    private boolean executeChoice(Map<Object, Choice> choiceMap) {
        StringBuilder options = new StringBuilder();
        for (Map.Entry<Object, Choice> m : choiceMap.entrySet()) {
            options.append(m.getKey()).append(". ").append(m.getValue().toString()).append("\n");
        }
        String choice = in.getChoice(options.toString(), "[1-"+choiceMap.size()+"]");
        choiceMap.get(choice).getChoiceExecutable().execute();
        return !choice.equals("" + choiceMap.size());
    }

}