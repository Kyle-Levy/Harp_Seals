package com.kylerobertlevy;


import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ExcelReader {


    public static HashMap<String, Student> createStudentHashMap() throws IOException {
        String path = "src/main/topbloc_data/Student Info.xlsx";
        Workbook workbook = WorkbookFactory.create(new File(path));

        Sheet firstSheet = workbook.getSheetAt(0);


        DataFormatter df = new DataFormatter();

        HashMap<String, Student> studentHashMap = new HashMap<>();

        int index = 0;
        for (Row row : firstSheet) {
            if (index != 0) {
                String studentID = df.formatCellValue(row.getCell(0));
                String studentMajor = df.formatCellValue(row.getCell(1));
                String studentGender = df.formatCellValue(row.getCell(2));
                Student currentStudent = new Student(studentID, studentMajor, studentGender);
                studentHashMap.put(studentID, currentStudent);
            }
            index++;
        }
        workbook.close();
        return studentHashMap;
    }

    /*
    Opens up the original test score excel file and sets the student's original test score.
    Then, opens the retake scores, and sets the retake score of the students in that file.
    Returns the new
     */
    public static void fillTestScores(HashMap<String, Student> studentHashMap) throws IOException {
        String original_score_path = "src/main/topbloc_data/Test Scores.xlsx";
        String retake_score_path = "src/main/topbloc_data/Test Retake Scores.xlsx";
        Workbook workbook = WorkbookFactory.create(new File(original_score_path));

        Sheet firstSheet = workbook.getSheetAt(0);


        DataFormatter df = new DataFormatter();

        int index = 0;
        for (Row row : firstSheet) {
            if (index != 0) {
                String studentID = df.formatCellValue(row.getCell(0));
                int testScore = (int) row.getCell(1).getNumericCellValue();
                studentHashMap.get(studentID).setOriginalScore(testScore);
            }
            index++;
        }
        workbook.close();

        workbook = WorkbookFactory.create(new File(retake_score_path));
        firstSheet = workbook.getSheetAt(0);

        index = 0;
        for (Row row : firstSheet) {
            if (index != 0) {
                String studentID = df.formatCellValue(row.getCell(0));
                int retakeTestScore = (int) row.getCell(1).getNumericCellValue();
                studentHashMap.get(studentID).setRetakeScore(retakeTestScore);
            }
            index++;
        }
        workbook.close();

    }


    public static int calculateClassAverage(HashMap<String, Student> studentHashMap){
        double total = 0;

        for(Student currentStudent : studentHashMap.values()){
            total += currentStudent.getFinalScore();
        }

        BigDecimal roundingAverage = new BigDecimal((total/studentHashMap.values().size()));
        roundingAverage = roundingAverage.setScale(0, RoundingMode.HALF_EVEN);


        return roundingAverage.intValue();
    }

    public static ArrayList<String> getListOfFemaleCompSciStudents(HashMap<String, Student> studentHashMap){
        ArrayList<String> femaleCompSciStudents = new ArrayList<>();

        for(Student currentStudent : studentHashMap.values()){
            if (currentStudent.getGender().equals("F") && currentStudent.getMajor().equals("computer science")){
                femaleCompSciStudents.add(currentStudent.getStudentID());
            }
        }

        Collections.sort(femaleCompSciStudents);

        return femaleCompSciStudents;
    }
}
