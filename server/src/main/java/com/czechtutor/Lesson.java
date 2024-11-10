package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;

public class Lesson {

    public static ArrayList<HashMap<String,Object>> create(String fromLanguage, String toLanguage, Integer nQuestions) {
        ArrayList<HashMap<String,Object>> lesson = new ArrayList<>();
        // load czech / english language phrases from disk
        //ArrayList<HashMap<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
        ArrayList<HashMap<String, String>> recordSet = Data.load("E:\\GitHub\\CzechTutor\\server\\src\\main\\resources\\ces_bkp.txt");
        for (int questionIndex = 0; questionIndex<nQuestions; questionIndex++) {
            // create question payload
            HashMap<String,Object> questionPayload = Question.create(fromLanguage, toLanguage, questionIndex, recordSet);
            lesson.add(questionPayload);
        }
        return lesson;

    }
}