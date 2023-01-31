package com.system;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Student  extends User implements Comparable<Student>{
    private float feesRequired;
    private boolean isFeesPaid = false;
    //private boolean isFeesReceived = false;
    private ArrayList<Subject> subList;
    private int total;
    private int avg;
    private int rank;
    private int cls;
    public Student(String name, int rollNo, int cls, String emailID, String pwd, ArrayList<Subject> subList){
        super(emailID,pwd,1);
        setName(name);
        setId(rollNo);
        this.subList = subList;
        this.cls = cls;
        this.feesRequired=getFeesAmt();
    }

    public float getFeesRequired() {
        return feesRequired;
    }

    public ArrayList<Subject> getSubList() {
        return subList;
    }

    public int getAvg() {
        return avg;
    }

    public int getCls() {
        return cls;
    }

    private int getSize(){
        if(subList == null){return 0;}
        return this.subList.size();
    }

    private float getFeesAmt(){
        float fees = 0;
        if(cls > 0 && cls < 3){fees =10000; }
        else if(cls > 2 && cls < 8){fees =25000; }
        else if(cls > 7 && cls < 11){fees =40000;}
        else if(cls == 11 || cls == 12){fees =50000; }
        return fees;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSubject(ArrayList<Subject> subList){
        this.subList= subList;
        this.total= calculateTotal(subList);
        this.avg= total/subList.size();
    }

    public boolean isFeesPaid() {
        return isFeesPaid;
    }

    public float payFees(float amount){
        this.feesRequired -= amount;
        if(feesRequired==0)
            isFeesPaid=true;
        return this.feesRequired;
    }

    private int calculateTotal(ArrayList<Subject> subList){
        int total = 0;
        for(Subject sub: subList){
            total +=sub.getSubjMarks();
        }
        return total;
    }

    public String ObtainMarkSheet(){
        //String markSheet="";
        StringBuilder markSheet = new StringBuilder();
        markSheet.append("******------------------------Marksheet-----------------------------******\n");
        markSheet.append("Student name: ").append(getName()).append("\n");
        markSheet.append("Student roll no: ").append(getId()).append("\n");
        markSheet.append("Student class: ").append(this.cls).append("\n");
        if(this.subList!=null){
            markSheet.append("---------------------------------------------------------------------------"+"\n");
            markSheet.append("Subject Code     \t\t Subject Name   \t\t Subject mark"+"\n");
            for(Subject sub : this.subList){
                markSheet.append(sub.getSubjCode()).append(" \t\t\t\t\t ").append(formatter(sub.getSubjName())).append(formatter(sub.getSubjMarks())).append("\n");
            }
            markSheet.append("              \t\t\t Total Marks  \t\t\t\t\t").append(this.total).append("\n");
            markSheet.append("----------------------------------------" + "Rank:").append(this.rank).append("----------------------------------------");
        }
        else{
            markSheet.append("---------------please wait! mark is being processed!!--------------------");
        }
        return markSheet.toString();
    }


    public String toString(){
        StringBuilder details = new StringBuilder();
        details.append("rollno: ").append(getId()).append(" | ").append("name: ").append(getName()).append(" | ").append("class: ").append(this.cls).append(" | ");
        for(int i = 0; i < this.getSize(); i++){
        details.append(this.subList.get(i).getSubjCode()).append(" | ").append(this.subList.get(i).getSubjName()).append(" | ").append(this.subList.get(i).getSubjMarks()).append(" | ");
        }
        if(this.getSize()!=0)
        details.append("total: ").append(this.total).append(" | ").append("average: ").append(this.avg).append(" | ").append("rank: ").append(this.rank);
        return details.toString();
    }



    private String formatter(String word){
        word = word.trim();
        int n =30-word.length();
        word = word + " ".repeat(Math.max(0, n));
        word = word.replace("_"," ");
        return word;
    }

    private String formatter(int num){
        if(num>9 && num<100){return " "+num;}
        else if(num<9){return "  "+num;}
        return ""+num;
    }



    @Override
    public int compareTo(@NotNull Student st) {
        return (this.cls - st.cls)==0? getId()-st.getId() : this.cls - st.cls;
    }
}
