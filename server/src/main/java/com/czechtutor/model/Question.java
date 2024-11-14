package com.czechtutor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import javax.validation.constraints.NotEmpty;

@Table("Question")
public class Question {
	
	@Id
	private Integer questionId;
	
	//@NotEmpty
	private Integer lessonId;
	
	//@NotEmpty
	private Integer questionSubId;
	
    //@NotEmpty
    private String phrase;
	
    //@NotEmpty
    private ArrayList<String> options;
	
    //@NotEmpty
    private String solution; 

    public void set(HashMap<String,Object>  payload, ArrayList<HashMap<String, String>> recordSet){
		final String fromLanguage = (String) payload.get("fromLanguage");
		final String toLanguage = (String) payload.get("toLanguage");
        final Integer nIndices = (Integer) payload.get("nQuestions");
        final Integer upperIndexBound = recordSet.size();
        // randomly select nIndices to extract from the record set (between 0 and upperIndexBound)
		Random indexGenerator = new Random();
		ArrayList<Integer> indexArray = new ArrayList<>();
		for (int i = 0; i<nIndices; i++) {
			Integer index = indexGenerator.nextInt(upperIndexBound);
			indexArray.add(index);
            }
		// select the records corresponding to the random indices 
        ArrayList<HashMap<String, String>> filteredRecordSet = new ArrayList<>();
        for (int i = 0; i<recordSet.size(); i++) {
            if (indexArray.contains(i)) {
                filteredRecordSet.add(recordSet.get(i));
            }
        }
        // randomly determine the phase, answer and options
        Random phaseIndexGenerator = new Random();
        Integer phaseIndex = phaseIndexGenerator.nextInt(3);
        ArrayList<String> optionsArray = new ArrayList<>();
        for (HashMap<String, String> hashMapObject : filteredRecordSet) {
            optionsArray.add(hashMapObject.get(toLanguage));
        }
        // set the class objects
		this.questionId = (Integer) payload.get("questionId");
		this.lessonId = (Integer) payload.get("lessonId");
        this.questionSubId = (Integer) payload.get("questionSubId");
        this.phrase = filteredRecordSet.get(phaseIndex).get(fromLanguage);
        this.options = optionsArray;
        this.solution = filteredRecordSet.get(phaseIndex).get(toLanguage);
    }
	
    public HashMap<String,Object> getHashMap(){
	    // construct question payload
        HashMap<String,Object> payload = new HashMap<>();
        payload.put("questionId", questionId);
        payload.put("lessonId", lessonId);
        payload.put("questionSubId", questionSubId);
        payload.put("phrase", phrase);
        payload.put("options", options);
        payload.put("solution", solution);
        return payload;
    }
}
