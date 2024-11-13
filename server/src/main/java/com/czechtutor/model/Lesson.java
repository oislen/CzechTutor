package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

@Table("Lesson")
public class Lesson {
	
	@Id
	private Integer lessonId;
	
	@NotEmpty
	private String fromLanguage;
	
	@NotEmpty
	private String toLanguage;
	
	@NotEmpty
	private Integer nQuestions;

    public void set(HashMap<String,Object>  payload){
        // set the class objects
		this.lessonId = (Integer) payload.get("lessonId");
		this.fromLanguage = (String) payload.get("fromLanguage");
		this.toLanguage = (String) payload.get("toLanguage");
		this.nQuestions = (Integer) payload.get("nQuestions");
    }
	
    public HashMap<String,Object> getHashMap(){
	    // construct question payload
        HashMap<String,Object> payload = new HashMap<>();
        payload.put("lessonId", lessonId);
        payload.put("fromLanguage", fromLanguage);
        payload.put("toLanguage", toLanguage);
        payload.put("nQuestions", nQuestions);
        return payload;
	}
}
