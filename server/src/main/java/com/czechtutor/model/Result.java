package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("RESULT")
public class Result {
 
	@Id
	private Integer resultId;
	
	//@NotEmpty
	private Integer lessonId;
	
	//@NotEmpty
	private Integer result;
	
    public void set(HashMap<String,Object> payload) {
		// set class objects
		//this.resultId = (Integer) payload.get("resultId");
		this.lessonId = (Integer) payload.get("lessonId");
		this.result = (Integer) payload.get("result");
    }
 
    public HashMap<String,Object> getResultPayload() {
        // create results payload
        HashMap<String,Object> resultsPayload = new HashMap<>();
        resultsPayload.put("resultId", resultId);
        resultsPayload.put("lessonId", lessonId);
        resultsPayload.put("result", result);
        return resultsPayload;
    }

}