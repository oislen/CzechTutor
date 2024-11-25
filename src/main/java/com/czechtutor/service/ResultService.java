package com.czechtutor.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.czechtutor.model.AnswerModel;
import com.czechtutor.model.QuestionModel;
import com.czechtutor.model.ResultModel;
import com.czechtutor.repository.crud.ResultCrudRepository;

@Service
public class ResultService {

    private final ResultCrudRepository resultCrudRepository;

    public ResultService(ResultCrudRepository resultCrudRepository) {
        this.resultCrudRepository = resultCrudRepository;
    }   

    public ResultModel get(Integer id) {
        return resultCrudRepository.findById(id).orElse(null);
    }

    public void save(ResultModel resultModel) {
        resultCrudRepository.save(resultModel);
    }
    
    public Integer countTotalCorrect(ArrayList<AnswerModel> lessonAnswers){
        int totalCorrect = 0;
        for (AnswerModel answer : lessonAnswers) {
            if (answer.getCorrect()) {
                totalCorrect++;
            }
        }
        return totalCorrect;
    }

    public ResultModel create(Integer lessonId, Integer nCorrect, Float score){
        // create a result
        ResultModel resultModel = new ResultModel();
        resultModel.setLessonId(lessonId);
        resultModel.setNCorrect(nCorrect);
        resultModel.setScore(score);
        return resultModel;
    }

    public ResultModel  findByLessonId(Integer lessonId) {
        return resultCrudRepository.findByLessonId(String.valueOf(lessonId));
    }

    public ArrayList<HashMap<String, Object>> createLessonSummary(ArrayList<QuestionModel> lessonQuestions, ArrayList<AnswerModel> lessonAnswers, Integer nQuestions) {
        // initialise output object
        ArrayList<HashMap<String, Object>> lessonQuestionsAnswersArray = new ArrayList<>();
        // iterate over each question index
        for (int i = 0; i<nQuestions; i++) {
            // create a temporary object to fill
            HashMap<String, Object> lessonQuestionsAnswers = new HashMap<>();
            // extract out question and answer
            QuestionModel question = lessonQuestions.get(i);
            AnswerModel answer = lessonAnswers.get(i);
            // put relevant data into temporary object
            lessonQuestionsAnswers.put("phrase", question.getPhrase());
            lessonQuestionsAnswers.put("solution", question.getSolution());
            lessonQuestionsAnswers.put("answer", answer.getAnswer());
            lessonQuestionsAnswers.put("correct", answer.getCorrect());
            lessonQuestionsAnswersArray.add(lessonQuestionsAnswers);
        }
        return lessonQuestionsAnswersArray;
    }

}