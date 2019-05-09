package com.kylerobertlevy;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) throws IOException {

        //Create HashMap of students from excel sheet where <studentID, StudentObj>
        HashMap<String, Student> studentHashMap = ExcelReader.createStudentHashMap();

        //Fill in the student's original scores and retake scores if they exist
        ExcelReader.fillTestScores(studentHashMap);

        //Calculate the average
        double classAverage = ExcelReader.calculateClassAverage(studentHashMap);

        //Round the average
        BigDecimal roundingAverage = new BigDecimal(classAverage);
        roundingAverage = roundingAverage.setScale(0, RoundingMode.HALF_EVEN);
        int nearestWholeClassAverage = roundingAverage.intValue();

        //Get a list of strings of all female comp sci students
        ArrayList<String> femaleCompSciIDs = ExcelReader.getListOfFemaleCompSciStudents(studentHashMap);

        //Sort them
        Collections.sort(femaleCompSciIDs);

        System.out.println(nearestWholeClassAverage);
        System.out.println(femaleCompSciIDs);
    }
}
