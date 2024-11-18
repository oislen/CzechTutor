package com.czechtutor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.czechtutor.model.Lesson;
import com.czechtutor.model.Question;
import com.czechtutor.repository.CesRepository;
import com.czechtutor.repository.LessonRepository;
import com.czechtutor.repository.QuestionRepository;

@Service
public class QuizService {

    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;
    private final CesRepository cesRepository;

    public QuizService(LessonRepository lessonRepository, QuestionRepository questionRepository, CesRepository cesRepository) {
        this.lessonRepository = lessonRepository;
        this.questionRepository = questionRepository;
        this.cesRepository = cesRepository;
    }

    public ArrayList<Question> create(HashMap<String, Object> payload) {
        Lesson lesson = new Lesson();
        lesson.set(payload);
        lessonRepository.save(lesson);
        // create an array for holding the questions
        ArrayList<Question> quiz = new ArrayList<>();
        // set the initial questionId
        Integer questionId = 1;
        // load czech / english language phrases from disk
        ArrayList<HashMap<String, String>> recordSet = DataService.load("E:\\GitHub\\CzechTutor\\server\\src\\main\\resources\\data\\ces_bkp.txt");
        for (int questionSubId = 1; questionSubId<=lesson.getNQuestions(); questionSubId++) {
            // set question
            final Integer upperIndexBound = recordSet.size();
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
            ArrayList<HashMap<String, String>> filteredRecordSet = new ArrayList<>();
            for (int i = 0; i<recordSet.size(); i++) {
                if (indexArray.contains(i)) {
                    filteredRecordSet.add(recordSet.get(i));
                }
            }
            // randomly determine the phase, answer and options
            Integer phaseIndex = randomGenerator.nextInt(lesson.getNOptions() - 1);
            ArrayList<String> optionsArray = new ArrayList<>();
            for (HashMap<String, String> hashMapObject : filteredRecordSet) {
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
            quiz.add(question);
            // increment questionId
            questionId++;
        }
        return quiz;
    }
}
