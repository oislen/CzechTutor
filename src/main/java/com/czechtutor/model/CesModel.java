package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("CES")
public class CesModel {
	
	@Id
	private Integer id;
	private String english;
	private String czech;
    private String level;
    private String reference;
    
    public void set(HashMap<String,Object>  payload){
        // set the class objects
		//this.id = (Integer) payload.get("id");
		this.english = (String) payload.get("english");
        this.czech = (String) payload.get("czech");
        this.level = (String) payload.get("level");
        this.reference = (String) payload.get("reference");
    }
	
    public HashMap<String,Object> getCesPayload(){
	    // construct questionPayload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("id", id);
        questionPayload.put("english", english);
        questionPayload.put("czech", czech);
        questionPayload.put("level", level);
        questionPayload.put("reference", reference);
        return questionPayload;
    }
    
    public Integer getId(){
		return id;
	}
	
    public void setId(Integer id){
		this.id = id;
	}
    
    public String getEnglish(){
		return english;
	}
	
    public void setEnglish(String english){
		this.english = english;
	}
    
    public String getCzech(){
		return czech;
	}
	
    public void setCzech(String czech){
		this.czech = czech;
	}
    
    public String getLevel(){
		return level;
	}
	
    public void setLevel(String level){
		this.level = level;
	}
    
    public String getReference(){
		return reference;
	}
	
    public void setRef(String reference){
		this.reference = reference;
	}
}