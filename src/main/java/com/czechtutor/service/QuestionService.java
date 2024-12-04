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

/**
 * <p>
 * The question service class defines the available processes for generating and
 * modifying the question model</p>
 *
 * @author oislen
 */
@Service
public class QuestionService {

    private final QuestionCrudRepository questionCrudRepository;
    private final CesCrudRepository cesCrudRepository;

    public QuestionService(QuestionCrudRepository questionCrudRepository, CesCrudRepository cesCrudRepository) {
        this.questionCrudRepository = questionCrudRepository;
        this.cesCrudRepository = cesCrudRepository;
    }

    /**
     * <p>
     * Finds a question model given a question id</p>
     *
     * @param id the question id to find by
     * @return the question model
     */
    public QuestionModel get(Integer id) {
        return questionCrudRepository.findById(id).orElse(null);
    }

    /**
     * <p>
     * Writes a question model to the database</p>
     *
     * @param questionModel the question model
     */
    public void save(QuestionModel questionModel) {
        questionCrudRepository.save(questionModel);
    }

    /**
     * <p>
     * Creates a question model given a lesson model</p>
     *
     * @param lessonModel the lesson model
     * @return the question model
     */
    public QuestionModel create(LessonModel lessonModel, Long randomSeed) {
        // set question
        ArrayList<CesModel> cesModelArray = cesCrudRepository.findByLevel(lessonModel.getLevel());
        Integer upperIndexBound = (int) cesModelArray.size();
        // create random generator and set seed if required
        Random randomGenerator = new Random();
        if (randomSeed != null) {
            randomGenerator.setSeed(randomSeed);
        }
        // randomly select nOptions to extract from the record set (between 0 and upperIndexBound)
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i = 0; i < lessonModel.getNOptions(); i++) {
            Integer index = randomGenerator.nextInt(upperIndexBound);
            indexArray.add(index);
        }
        // select the records corresponding to the random indices 
        ArrayList<HashMap<String, Object>> filteredRecordSet = new ArrayList<>();
        for (int i = 1; i <= upperIndexBound; i++) {
            if (indexArray.contains(i)) {
                CesModel cesModel = cesModelArray.get(i);
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
        lessonPayload.put("option3", optionsArray.get(2));
        lessonPayload.put("option4", optionsArray.get(3));
        lessonPayload.put("solution", filteredRecordSet.get(phaseIndex).get(lessonModel.getToLanguage()));
        // create a question
        QuestionModel questionModel = new QuestionModel();
        questionModel.set(lessonPayload);
        questionCrudRepository.save(questionModel);
        return questionModel;
    }

    /**
     * <p>
     * Finds all question models with a specified lesson id as an array list</p>
     *
     * @param LessonId the lesson id to find by
     * @return the question models as an array list
     */
    public ArrayList<QuestionModel> findByLessonId(Integer lessonId) {
        return questionCrudRepository.findByLessonId(String.valueOf(lessonId));
    }
}
