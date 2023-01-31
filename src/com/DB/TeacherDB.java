package com.DB;

import com.utility.Authenticator;
import com.system.Teacher;

import java.util.ArrayList;
import java.util.Collections;

public class TeacherDB {
    private final ArrayList<Teacher> teacherList = new ArrayList<>();

    public  Teacher getUser(String username){
        for(Teacher teacher: teacherList){
            if(username.equals(teacher.getUserId())){
                return teacher;
            }
        }
        return null;
    }

    public Teacher getUser(int id){
        for(Teacher teacher: teacherList){
            if(id==teacher.getId()){
                return teacher;
            }
        }
        return null;
    }

    public void addTeacher(String name, String username, String password, int id, String sub,int cls, Authenticator auth, int type){
        Teacher t = new Teacher(name, username, password, id, sub, cls);
        auth.register(username, password, type);
        teacherList.add(t);
    }

    public void addTeacher(String name, String username, String password, int id, String sub,int cls, long accNo, String ifsc, Authenticator auth, int type){
            Teacher t = new Teacher(name, username, password, id, sub, cls);
            auth.register(username, password, type);
            t.setAcc(accNo,ifsc);
            teacherList.add(t);
    }

    public int removeTeacher(Teacher teacher, Authenticator auth){
            if(teacher == null){return -1;}
            if(getSize()==0){return 0;}
            auth.remove(teacher.getUserId());
            teacherList.remove(teacher);
            return 1;
    }

    public int getSize(){
        if(teacherList.isEmpty()){return 0;}
        return teacherList.size();
    }

    public String getTeacherList(){
        Collections.sort(teacherList);
        StringBuilder teacherDetails= new StringBuilder();
        for(Teacher teacher: teacherList){
            teacherDetails.append(teacher).append("\n");
            teacherDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return teacherDetails.toString();
    }

    public String getTeacherLeaveList(){
        Collections.sort(teacherList);
        StringBuilder teacherDetails= new StringBuilder();
        for(Teacher teacher: teacherList){
            teacherDetails.append(teacher).append(teacher.leaveString()).append("\n");
            teacherDetails.append("-----------------------------------------------------------------------------------------------------------").append("\n");
        }
        return teacherDetails.toString();
    }

    public boolean isNotValidTeacher(String subjectHandled, int cls){
        for(Teacher teacher: teacherList){
            if(teacher.getSubHandled().equals(subjectHandled) && teacher.getCls()==cls) return true;
        }
        return false;
    }



}
