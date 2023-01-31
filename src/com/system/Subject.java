package com.system;

public class Subject {
    private String subjCode;
    private String subjName;
    private int subjMarks;
    public Subject(String subjCode, String subjName, int subjMarks){
        this.subjCode = subjCode;
        this.subjName = subjName;
        this.subjMarks = subjMarks;
    }

    public int getSubjMarks() {
        return subjMarks;
    }

    public String getSubjName() {
        return subjName;
    }

    public String getSubjCode() {
        return subjCode;
    }
}
