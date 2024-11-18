package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("QUESTION")
public class Question {
	
	@Id
	private Integer questionId;
	
	//@NotEmpty
	private Integer lessonId;
	
	//@NotEmpty
	private Integer questionSubId;
	
    //@NotEmpty
    private String phrase;
	
    //@NotEmpty
    private String option1;
	
    //@NotEmpty
    private String option2;
    
    //@NotEmpty
    private String option3;

    //@NotEmpty
    private String option4;

    //@NotEmpty
    private String solution; 

    public void set(HashMap<String,Object>  payload){
        // set the class objects
		//this.questionId = (Integer) payload.get("questionId");
		this.lessonId = (Integer) payload.get("lessonId");
        this.questionSubId = (Integer) payload.get("questionSubId");
        this.phrase = (String) payload.get("phrase");
        this.option1 = (String) payload.get("option1");
        this.option2 = (String) payload.get("option2");
        this.option3 = (String) payload.get("option3");
        this.option4 = (String) payload.get("option4");
        this.solution = (String) payload.get("solution");
    }
	
    public HashMap<String,Object> getQuestionPayload(){
	    // construct questionPayload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("questionId", questionId);
        questionPayload.put("lessonId", lessonId);
        questionPayload.put("questionSubId", questionSubId);
        questionPayload.put("phrase", phrase);
        questionPayload.put("option1", option1);
        questionPayload.put("option2", option2);
        questionPayload.put("option3", option3);
        questionPayload.put("option4", option4);
        questionPayload.put("solution", solution);
        return questionPayload;
    }
}
