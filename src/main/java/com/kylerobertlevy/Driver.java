package com.kylerobertlevy;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {

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

        Driver.jsonRequest(classAverage, femaleCompSciIDs);
    }

    public static void jsonRequest(int classAverage, ArrayList<String> femaleCompSciIDs){
       try {
           JSONObject submissionJSON = new JSONObject();

           submissionJSON.put("id", "kyle.robert.levy@gmail.com");
           submissionJSON.put("name", "Kyle Levy");
           submissionJSON.put("average", classAverage);
           submissionJSON.put("studentIds", femaleCompSciIDs);

           HttpClient httpClient = HttpClientBuilder.create().build();
           HttpPost request = new HttpPost("http://3.86.140.38:5000/challenge");
           StringEntity submissionDetails = new StringEntity(submissionJSON.toString());
           request.addHeader("content-type", "application/json");
           request.setEntity(submissionDetails);
           HttpResponse response =httpClient.execute(request);

           System.out.println(response);
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }
}
