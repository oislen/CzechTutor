package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("LESSON")
public class Lesson {
	
	@Id
	private Integer lessonId;
	
	//@NotEmpty
	private String fromLanguage;

	//@NotEmpty
	private String toLanguage;
	
	//@NotEmpty
	private Integer nQuestions;
	
	//@NotEmpty
	private Integer nOptions;

    public void set(HashMap<String,Object>  payload){
        // set the class objects
		this.lessonId = (Integer) payload.get("lessonId");
		this.fromLanguage = (String) payload.get("fromLanguage");
		this.toLanguage = (String) payload.get("toLanguage");
		this.nQuestions = (Integer) payload.get("nQuestions");
		this.nOptions = (Integer) payload.get("nOptions");
    }

    public HashMap<String,Object> getLessonPayload(){
	    // construct lesson payload
        HashMap<String,Object> lessonPayload = new HashMap<>();
        lessonPayload.put("lessonId", lessonId);
        lessonPayload.put("fromLanguage", fromLanguage);
        lessonPayload.put("toLanguage", toLanguage);
        lessonPayload.put("nQuestions", nQuestions);
        lessonPayload.put("nOptions", nOptions);
        return lessonPayload;
	}
		
    public Integer getNQuestions(){
		return nQuestions;
	}
}
