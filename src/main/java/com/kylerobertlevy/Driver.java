package com.kylerobertlevy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) throws IOException {

        //Create HashMap of students from excel sheet where <studentID, StudentObj>
        HashMap<String, Student> studentHashMap = ExcelReader.createStudentHashMap();

        //Fill in the student's original scores and retake scores if they exist
        ExcelReader.fillTestScores(studentHashMap);

        //Calculate the average
        int classAverage = ExcelReader.calculateClassAverage(studentHashMap);


        //Get a list of strings of all female comp sci students
        ArrayList<String> femaleCompSciIDs = ExcelReader.getListOfFemaleCompSciStudents(studentHashMap);


        System.out.println(classAverage);
        System.out.println(femaleCompSciIDs);
    }
}
