package com.kylerobertlevy;

public class Student {
    private String studentID;
    private String major;
    private String gender;
    private double originalScore;
    private double retakeScore;

    public Student(String studentID, String major, String gender) {
        this.studentID = studentID;
        this.major = major;
        this.gender = gender;

        this.originalScore = 0;
        this.retakeScore = 0;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getOriginalScore() {
        return originalScore;
    }

    public void setOriginalScore(int originalScore) {
        this.originalScore = originalScore;
    }

    public double getRetakeScore() {
        return retakeScore;
    }

    public void setRetakeScore(int retakeScore) {
        this.retakeScore = retakeScore;
    }

    public double getFinalScore(){
        if(this.retakeScore > this.originalScore){
            return this.retakeScore;
        } else {
            return this.originalScore;
        }
    }

    public String toString(){
        return this.studentID + ": " + this.major + " " + this.gender + "\nOriginal Score: " + this.originalScore + "\nRetake Score: " + this.retakeScore;
    }
}
