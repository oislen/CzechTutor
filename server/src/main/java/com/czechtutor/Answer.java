package com.czechtutor;

import java.util.HashMap;

public class Answer {
 
    public static HashMap<String,Object> create(HashMap<String,Object> questionPayload, String answer) {

        // create results payload and update results
        HashMap<String,Object> answerPayload = new HashMap<>(questionPayload);
        Boolean correct = answer.toLowerCase().trim().equals(questionPayload.get("solution").toString().toLowerCase().trim());
        answerPayload.put("answer", answer);
        answerPayload.put("correct", correct);
        answerPayload.remove("options");
        return answerPayload;
    }

}
