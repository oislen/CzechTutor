package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("ANSWERS")
public class AnswerModel {
 
	@Id
	private Integer answerId;
	
	//@NotEmpty
	private Integer lessonId;
	
  //@NotEmpty
  private String answer; 
	
  //@NotEmpty
  private Boolean correct; 
	
  public void set(HashMap<String,Object> questionPayload) {
		// set class objects
		//this.answerId = (Integer) questionPayload.get("questionId");
		this.lessonId = (Integer) questionPayload.get("lessonId");
    this.answer = (String) questionPayload.get("answer");
    // determine if answer was correct for the question
    this.correct = (Boolean) questionPayload.get("answer").toString().toLowerCase().trim().equals(questionPayload.get("solution").toString().toLowerCase().trim());
  }
 
  public HashMap<String,Object> getAnswerPayload() {
    // create answers payload and update results
    HashMap<String,Object> answerPayload = new HashMap<>();
    answerPayload.put("answerId", answerId);
    answerPayload.put("lessonId", lessonId);
    answerPayload.put("answer", answer);
    answerPayload.put("correct", correct);
    return answerPayload;
  }

  public Integer getAnswerId(){
		return answerId;
	}
	
  public void setAnswerId(Integer answerId){
		this.answerId = answerId;
	}

  public Integer getLessonId(){
		return lessonId;
	}
	
  public void setLessonId(Integer lessonId){
		this.lessonId = lessonId;
	}

  public String getAnswer(){
		return answer;
	}
	
  public void setAnswer(String answer){
		this.answer = answer;
	}

  public Boolean getCorrect(){
		return correct;
	}
	
  public void setCorrect(Boolean correct){
		this.correct = correct;
	}
}
