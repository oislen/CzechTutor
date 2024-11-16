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

    public void set(HashMap<String,Object>  lessonPayload, ArrayList<HashMap<String, String>> recordSet){
		final String fromLanguage = (String) lessonPayload.get("fromLanguage");
		final String toLanguage = (String) lessonPayload.get("toLanguage");
        final Integer nOptions = (Integer) lessonPayload.get("nOptions");
        final Integer upperIndexBound = recordSet.size();
        // create random generator and set seed if required
		Random randomGenerator = new Random();
        if (lessonPayload.containsKey("randomSeed")) {
            randomGenerator.setSeed((Long) lessonPayload.get("randomSeed"));
        }
        // randomly select nOptions to extract from the record set (between 0 and upperIndexBound)
		ArrayList<Integer> indexArray = new ArrayList<>();
		for (int i = 0; i<nOptions; i++) {
			Integer index = randomGenerator.nextInt(upperIndexBound);
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
        Integer phaseIndex = randomGenerator.nextInt(nOptions - 1);
        ArrayList<String> optionsArray = new ArrayList<>();
        for (HashMap<String, String> hashMapObject : filteredRecordSet) {
            optionsArray.add(hashMapObject.get(toLanguage));
        }
        // set the class objects
		this.questionId = (Integer) lessonPayload.get("questionId");
		this.lessonId = (Integer) lessonPayload.get("lessonId");
        this.questionSubId = (Integer) lessonPayload.get("questionSubId");
        this.phrase = filteredRecordSet.get(phaseIndex).get(fromLanguage);
        this.options = optionsArray;
        this.solution = filteredRecordSet.get(phaseIndex).get(toLanguage);
    }
	
    public HashMap<String,Object> getQuestionPayload(){
	    // construct questionPayload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("questionId", questionId);
        questionPayload.put("lessonId", lessonId);
        questionPayload.put("questionSubId", questionSubId);
        questionPayload.put("phrase", phrase);
        questionPayload.put("options", options);
        questionPayload.put("solution", solution);
        return questionPayload;
    }
}
