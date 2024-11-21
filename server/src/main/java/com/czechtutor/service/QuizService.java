package com.czechtutor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.czechtutor.model.Ces;
import com.czechtutor.model.Lesson;
import com.czechtutor.model.Question;
import com.czechtutor.repository.AnswerRepository;
import com.czechtutor.repository.CesRepository;
import com.czechtutor.repository.LessonRepository;
import com.czechtutor.repository.QuestionRepository;

@Service
public class QuizService {

    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;
    private final CesRepository cesRepository;
    private final AnswerRepository answerRepository;

    public QuizService(LessonRepository lessonRepository, QuestionRepository questionRepository, CesRepository cesRepository, AnswerRepository answerRepository) {
        this.lessonRepository = lessonRepository;
        this.questionRepository = questionRepository;
        this.cesRepository = cesRepository;
        this.answerRepository = answerRepository;
    }

    public ArrayList<HashMap<String, Object>> create(HashMap<String, Object> payload) {
        Lesson lesson = new Lesson();
        lesson.set(payload);
        lessonRepository.save(lesson);
        // create an array for holding the questions
        ArrayList<HashMap<String, Object>> quiz = new ArrayList<>();
        // set the initial questionId
        Integer questionId = 1;
        // load czech / english language phrases from h2 database
        for (int questionSubId = 1; questionSubId<=lesson.getNQuestions(); questionSubId++) {
            // set question
            Integer upperIndexBound = (int) cesRepository.count();
            // create random generator and set seed if required
            Random randomGenerator = new Random();
            if (payload.containsKey("randomSeed")) {
                randomGenerator.setSeed((Long) payload.get("randomSeed"));
            }
            // randomly select nOptions to extract from the record set (between 0 and upperIndexBound)
            ArrayList<Integer> indexArray = new ArrayList<>();
            for (int i = 0; i<lesson.getNOptions(); i++) {
                Integer index = randomGenerator.nextInt(upperIndexBound);
                indexArray.add(index);
                }
            // select the records corresponding to the random indices 
            ArrayList<HashMap<String, Object>> filteredRecordSet = new ArrayList<>();
            for (int i = 1; i<=upperIndexBound; i++) {
                if (indexArray.contains(i)) {
                    Ces cesPayload = cesRepository.findById(i).orElse(null);
                    HashMap<String, Object> cesRecord = cesPayload.getCesPayload();
                    filteredRecordSet.add(cesRecord);
                }
            }
            // randomly determine the phase, answer and options
            Integer phaseIndex = randomGenerator.nextInt(lesson.getNOptions());
            ArrayList<Object> optionsArray = new ArrayList<>();
            for (HashMap<String, Object> hashMapObject : filteredRecordSet) {
                optionsArray.add(hashMapObject.get(lesson.getToLanguage()));
            }
            // create question payload
            HashMap<String, Object> lessonPayload;
            lessonPayload = lesson.getLessonPayload();
            lessonPayload.put("questionSubId", questionSubId);
            lessonPayload.put("phrase", filteredRecordSet.get(phaseIndex).get(lesson.getFromLanguage()));
            lessonPayload.put("option1", optionsArray.get(0));
            lessonPayload.put("option2", optionsArray.get(1));
            lessonPayload.put("option3",  optionsArray.get(2));
            lessonPayload.put("option4", optionsArray.get(3));
            lessonPayload.put("solution", filteredRecordSet.get(phaseIndex).get(lesson.getToLanguage()));
            // create a question
            Question question = new Question();
            question.set(lessonPayload);
            questionRepository.save(question);
            quiz.add(question.getQuestionPayload());
            // increment questionId
            questionId++;
        }
        return quiz;
    }

    public Integer countTotalCorrect(ArrayList<HashMap<String,Object>> results){
        int totalCorrect = 0;
        for (HashMap<String,Object> hashMapObject : results) {
            Boolean isCorrect = (Boolean) hashMapObject.get("correct");
            if (isCorrect) {
                totalCorrect++;
            }
        }
        return totalCorrect;
    }

    public void clearTables() {
        // clear all records from database
        lessonRepository.deleteAll();
        questionRepository.deleteAll();
        answerRepository.deleteAll();
    }
    
}
