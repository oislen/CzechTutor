package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("ANSWER")
public class Answer {
 
	@Id
	private Integer answerId;
	
	//@NotEmpty
	private Integer lessonId;
	
    //@NotEmpty
    private String answerText; 
	
    //@NotEmpty
    private Boolean correct; 
	
    public void set(HashMap<String,Object> questionPayload) {
		// set class objects
		//this.answerId = (Integer) questionPayload.get("questionId");
		this.lessonId = (Integer) questionPayload.get("lessonId");
        this.answerText = (String) questionPayload.get("answerText");
        // determine if answer was correct for the question
        this.correct = (Boolean) questionPayload.get("answerText").toString().toLowerCase().trim().equals(questionPayload.get("solution").toString().toLowerCase().trim());
    }
 
    public HashMap<String,Object> getAnswerPayload() {
        // create answers payload and update results
        HashMap<String,Object> answerPayload = new HashMap<>();
        answerPayload.put("answerId", answerId);
        answerPayload.put("lessonId", lessonId);
        answerPayload.put("answerText", answerText);
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

    public String getAnswerText(){
		return answerText;
	}
	
    public void setAnswerText(String answerText){
		this.answerText = answerText;
	}

    public Boolean getCorrect(){
		return correct;
	}
	
    public void setCorrect(Boolean correct){
		this.correct = correct;
	}
}
