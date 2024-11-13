package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

//@Table("Answer")
public class Answer {
 
	@Id
	private Integer answerId;
	
	@NotEmpty
	private Integer lessonId;
	
	@NotEmpty
	private Integer answerSubId;
	
    @NotEmpty
    private String answer; 
	
    @NotEmpty
    private Boolean correct; 
	
    public void set(HashMap<String,Object> payload) {
        // determine if answer was correct for the question
        Boolean correct = payload.get("answer").toString().toLowerCase().trim().equals(payload.get("solution").toString().toLowerCase().trim());
		// set class objects
		this.answerId = (Integer) payload.get("questionId");
		this.lessonId = (Integer) payload.get("lessonId");
		this.answerSubId = (Integer) payload.get("questionSubId");
        this.answer = (String) payload.get("answer");
        this.correct = correct;
    }
 
    public HashMap<String,Object> getHashMap() {
        // create results payload and update results
        HashMap<String,Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("answer", answer);
        payload.put("correct", correct);
        return payload;
    }

}
