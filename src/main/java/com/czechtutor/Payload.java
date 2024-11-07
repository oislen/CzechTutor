package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Payload {

    public static HashMap<String,Object> create(String fromLanguage, String toLanguage, Integer questionIndex, ArrayList<HashMap<String, String>> recordSet){
        // randomly generate four indices to extract from the record set
        Integer upperIndexBound = recordSet.size();
        final Integer nIndices = 4;
        ArrayList<Integer> indexArray = Choose.randomIndices(nIndices, upperIndexBound);
        //ArrayList<Map<String, String>> filteredRecordSet = new ArrayList<> (indexArray.stream().map(recordSet::get).collect(Collectors.toList()));
        ArrayList<HashMap<String, String>> filteredRecordSet = new ArrayList<>();
        for (int i = 0; i<recordSet.size(); i++) {
            if (indexArray.contains(i)) {
                filteredRecordSet.add(recordSet.get(i));
            }
        }
        // randomly determine the phase, answer and options
        Random phaseIndexGenerator = new Random();
        Integer phaseIndex = phaseIndexGenerator.nextInt(3);
        String phrase = filteredRecordSet.get(phaseIndex).get(fromLanguage);
        String solution = filteredRecordSet.get(phaseIndex).get(toLanguage);
        ArrayList<String> options = new ArrayList<>();
        for (HashMap<String, String> hashMapObject : filteredRecordSet) {
            options.add(hashMapObject.get(toLanguage));
        }
        // construct question payload
        HashMap<String,Object> questionPayload = new HashMap<>();
        questionPayload.put("question", questionIndex);
        questionPayload.put("phrase", phrase);
        questionPayload.put("options", options);
        questionPayload.put("solution", solution);
        return questionPayload;
    }
}
