package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("CES")
public class Ces {
	
	@Id
	private Integer ID;
	
	//@NotEmpty
	private String EN;
	
	//@NotEmpty
	private String CZ;
	
    //@NotEmpty
    private String REF;

    public void set(HashMap<String,Object>  payload){
        // set the class objects
		//this.ID = (Integer) payload.get("ID");
		this.EN = (String) payload.get("EN");
        this.CZ = (String) payload.get("CZ");
        this.REF = (String) payload.get("REF");
    }
	
    public HashMap<String,Object> getQuestionPayload(){
	    // construct questionPayload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("ID", ID);
        questionPayload.put("EN", EN);
        questionPayload.put("CZ", CZ);
        questionPayload.put("REF", REF);
        return questionPayload;
    }
}