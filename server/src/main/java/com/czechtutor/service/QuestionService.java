package com.czechtutor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.czechtutor.model.CesModel;
import com.czechtutor.model.LessonModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.repository.crud.CesCrudRepository;
import com.czechtutor.repository.crud.QuestionCrudRepository;

@Service
public class QuestionService {
    
    private final QuestionCrudRepository questionCrudRepository;
    private final CesCrudRepository cesCrudRepository;

    public QuestionService(QuestionCrudRepository questionCrudRepository, CesCrudRepository cesCrudRepository) {
        this.questionCrudRepository = questionCrudRepository;
        this.cesCrudRepository = cesCrudRepository;
    }   

    public QuestionModel get(Integer id) {
        return questionCrudRepository.findById(id).orElse(null);
    }

    public void save(QuestionModel question) {
        questionCrudRepository.save(question);
    }

    public QuestionModel create(LessonModel lessonModel) {
        // set question
        Integer upperIndexBound = (int) cesCrudRepository.count();
        // create random generator and set seed if required
        Random randomGenerator = new Random();
        //if (payload.containsKey("randomSeed")) {
        //    randomGenerator.setSeed((Long) payload.get("randomSeed"));
        //}
        // randomly select nOptions to extract from the record set (between 0 and upperIndexBound)
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i = 0; i<lessonModel.getNOptions(); i++) {
            Integer index = randomGenerator.nextInt(upperIndexBound);
            indexArray.add(index);
            }
        // select the records corresponding to the random indices 
        ArrayList<HashMap<String, Object>> filteredRecordSet = new ArrayList<>();
        for (int i = 1; i<=upperIndexBound; i++) {
            if (indexArray.contains(i)) {
                CesModel cesModel = cesCrudRepository.findById(i).orElse(null);
                HashMap<String, Object> cesPayload = cesModel.getCesPayload();
                filteredRecordSet.add(cesPayload);
            }
        }
        // randomly determine the phase, answer and options
        Integer phaseIndex = randomGenerator.nextInt(lessonModel.getNOptions());
        ArrayList<Object> optionsArray = new ArrayList<>();
        for (HashMap<String, Object> hashMapObject : filteredRecordSet) {
            optionsArray.add(hashMapObject.get(lessonModel.getToLanguage()));
        }
        // create question payload
        HashMap<String, Object> lessonPayload;
        lessonPayload = lessonModel.getLessonPayload();
        lessonPayload.put("phrase", filteredRecordSet.get(phaseIndex).get(lessonModel.getFromLanguage()));
        lessonPayload.put("option1", optionsArray.get(0));
        lessonPayload.put("option2", optionsArray.get(1));
        lessonPayload.put("option3",  optionsArray.get(2));
        lessonPayload.put("option4", optionsArray.get(3));
        lessonPayload.put("solution", filteredRecordSet.get(phaseIndex).get(lessonModel.getToLanguage()));
        // create a question
        QuestionModel questionModel = new QuestionModel();
        questionModel.set(lessonPayload);
        questionCrudRepository.save(questionModel);
        return questionModel;
    }

    public ArrayList<QuestionModel>  findByLessonId(Integer lessonId) {
        return questionCrudRepository.findByLessonId(String.valueOf(lessonId));
    }
}
