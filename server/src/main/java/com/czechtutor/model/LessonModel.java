package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("LESSONS")
public class LessonModel {
	
	@Id
	private Integer lessonId;
	
	//@NonNull
	private String fromLanguage;

	//@NonNull
	private String toLanguage;
	
	//@Nullable
	private Integer nQuestions;
	
	//@Nullable
	private Integer nOptions;

    public void set(HashMap<String,Object>  payload){
        // set the class objects
		//this.lessonId = (Integer) payload.get("lessonId");
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

    public Integer getLessonId(){
		return lessonId;
	}
	
    public void setLessonId(Integer lessonId){
		this.lessonId = lessonId;
	}

    public String getFromLanguage(){
		return fromLanguage;
	}
	
    public void setFromLanguage(String fromLanguage){
		this.fromLanguage = fromLanguage;
	}

    public String getToLanguage(){
		return toLanguage;
	}
	
    public void setToLanguage(String toLanguage){
		this.toLanguage = toLanguage;
	}

    public Integer getNQuestions(){
		return nQuestions;
	}
	
    public void setNQuestions(Integer nQuestions){
		this.nQuestions = nQuestions;
	}

    public Integer getNOptions(){
		return nOptions;
	}
	
    public void setNOptions(Integer nOptions){
		this.nOptions = nOptions;
	}

}
