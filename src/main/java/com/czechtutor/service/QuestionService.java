package com.czechtutor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        ArrayList<CesModel> cesModelLevelArray = cesCrudRepository.findByLevel(lessonModel.getLevel());
        Integer upperIndexBound = (int) cesModelLevelArray.size();
        // create random generator and set seed if required
        Random randomGenerator = new Random();
        if (randomSeed != null) {
            randomGenerator.setSeed(randomSeed);
        }
        // randomly select nOptions to extract from the record set (between 0 and upperIndexBound)
        Boolean findRandomIndices = true;
        ArrayList<Integer> indexArray = new ArrayList<>();
        while (findRandomIndices) {
            Integer index = randomGenerator.nextInt(upperIndexBound);
            if (!indexArray.contains(index)) {
                indexArray.add(index);
                if (indexArray.size() == lessonModel.getNOptions()) {
                    findRandomIndices = false;
                }
            }
        }
        // select the records corresponding to the random indices 
        ArrayList<CesModel> cesModelArray = new ArrayList<>();
        for (Integer index : indexArray) {
                CesModel cesModel = cesModelLevelArray.get(index);
                cesModelArray.add(cesModel);
        }
        // randomly determine the phase, answer and options
        Integer phaseIndex = randomGenerator.nextInt(lessonModel.getNOptions());
        ArrayList<String> optionsArray = new ArrayList<>();
        for (CesModel cesModel : cesModelArray) {
            optionsArray.add((String) cesModel.getCesPayload().get(lessonModel.getToLanguage()));
        }
        // create a question
        QuestionModel questionModel = new QuestionModel();
        questionModel.setLessonId(lessonModel.getLessonId());
        questionModel.setPhrase((String) cesModelArray.get(phaseIndex).getCesPayload().get(lessonModel.getFromLanguage()));
        questionModel.setOption1(optionsArray.get(0));
        questionModel.setOption2(optionsArray.get(1));
        questionModel.setOption3(optionsArray.get(2));
        questionModel.setOption4(optionsArray.get(3));
        questionModel.setSolution((String) cesModelArray.get(phaseIndex).getCesPayload().get(lessonModel.getToLanguage()));
        questionModel.setDateTime(LocalDateTime.now());
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
