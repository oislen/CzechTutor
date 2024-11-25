package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("RESULTS")
public class ResultModel {
 
	@Id
	private Integer resultId;
	
	//@NotEmpty
	private Integer lessonId;
	
	//@NotEmpty
	private Integer nCorrect;
	
	//@NotEmpty
	private Float score;

  public void set(HashMap<String,Object> payload) {
		// set class objects
		//this.resultId = (Integer) payload.get("resultId");
		this.lessonId = (Integer) payload.get("lessonId");
		this.nCorrect = (Integer) payload.get("nCorrect");
		this.score = (Float) payload.get("score");
  }
 
  public HashMap<String,Object> getResultPayload() {
    // create results payload
    HashMap<String,Object> resultsPayload = new HashMap<>();
    resultsPayload.put("resultId", resultId);
    resultsPayload.put("lessonId", lessonId);
    resultsPayload.put("nCorrect", nCorrect);
    resultsPayload.put("score", score);
    return resultsPayload;
  }

  public Integer getLessonId(){
    return lessonId;
  }
    
  public void setLessonId(Integer lessonId){
    this.lessonId = lessonId;
  }
  
  public Integer getNCorrect(){
    return nCorrect;
  }
    
  public void setNCorrect(Integer nCorrect){
    this.nCorrect = nCorrect;
  }
  
  public Float getScore(){
    return score;
  }
    
  public void setScore(Float score){
    this.score = score;
  }

}
