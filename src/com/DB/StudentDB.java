package com.DB;

import com.utility.Authenticator;
import com.system.Student;
import java.util.ArrayList;
import java.util.Collections;

public class StudentDB {
    private final ArrayList<Student> studList = new ArrayList<>();

    public  Student getUser(String username){
        for(Student student: studList){
            if(username.equals(student.getUserId())){
                return student;
            }
        }
        return null;
    }

    public Student getStudentByCls(int rollNo,int cls){
        for(Student student: studList){
            if(student.getCls()==cls && student.getId() == rollNo){
                return student;
            }
        }
        return null;
    }

    public void addStudent(String name, int id, int cls, String username, String password, Authenticator auth, int type){
             auth.register(username, password, type);
             studList.add(new Student(name, id, cls, username, password, null));
                for(Student s: studList){
                    calculateRank(s);
                }
    }

    public int removeStudent(Student student, Authenticator auth) {
        if (student == null) {
            return -1;
        }
        if (getSize() == 0) {
            return 0;
        }
        auth.remove(student.getUserId());
        studList.remove(student);
        return 1;
    }


    public int getSize(){
        if(studList.isEmpty()){return 0;}
        return studList.size();
    }

    public String getStudentList(){
        Collections.sort(studList);
        StringBuilder studentDetails= new StringBuilder();
        for(Student student: studList){
            studentDetails.append(student).append("\n");
            studentDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return studentDetails.toString();
    }

    public String getClassStudentsList(int cls){
        Collections.sort(studList);
        StringBuilder studentDetails= new StringBuilder();
        for(Student student: studList){
            if (student.getCls()==cls){
            studentDetails.append(student).append("\n");
            studentDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");}
        }
        return studentDetails.toString();
    }


    public void calculateRank(Student s){
        int rank = 1;
        for (Student student : studList) {
            if (student.getId() == s.getId()) {
                continue;
            }
            if (student.getAvg() > s.getAvg()) {
                rank++;
            }
        }
        s.setRank(rank);
    }

    public boolean isStudentIdPresent(int id, int cls){
        for(Student student: studList){
            if(cls==student.getCls()) {
                if (id == student.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

}
