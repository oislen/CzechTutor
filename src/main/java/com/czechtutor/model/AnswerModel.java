package com.czechtutor.model;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <p>
 * The answer model class defines the schema / structure of an answer</p>
 *
 * @author oislen
 */
@Table("ANSWERS")
public class AnswerModel {

    @Id
    private Integer answerId;
    private Integer lessonId;
    private Integer questionId;
    private String answer;
    private Boolean correct;
    private LocalDateTime dateTime;

    /**
     * <p>
     * Sets the attributes of an answer model using a hashmap of attribute
     * values</p>
     *
     * @param questionPayload a hashmap of attributes to set the answer model
     * with
     */
    public void set(HashMap<String, Object> questionPayload) {
        // set class objects
        //this.answerId = (Integer) questionPayload.get("questionId");
        this.lessonId = (Integer) questionPayload.get("lessonId");
        this.questionId = (Integer) questionPayload.get("questionId");
        this.answer = (String) questionPayload.get("answer");
        // determine if answer was correct for the question
        this.correct = (Boolean) questionPayload.get("answer").toString().toLowerCase().trim().equals(questionPayload.get("solution").toString().toLowerCase().trim());
        this.dateTime = (LocalDateTime) questionPayload.get("dateTime");
    }

    /**
     * <p>
     * Gets the attributes of an answer model as a hashmap of attribute
     * values</p>
     *
     * @return a hashmap of the answer model attributes
     */
    public HashMap<String, Object> getAnswerPayload() {
        // create answers payload and update results
        HashMap<String, Object> answerPayload = new HashMap<>();
        answerPayload.put("answerId", answerId);
        answerPayload.put("lessonId", lessonId);
        answerPayload.put("questionId", questionId);
        answerPayload.put("answer", answer);
        answerPayload.put("correct", correct);
        answerPayload.put("dateTime", dateTime);
        return answerPayload;
    }

    /**
     * <p>
     * Gets the answer id attribute of an answer model</p>
     *
     * @return the answer id attribute
     */
    public Integer getAnswerId() {
        return answerId;
    }

    /**
     * <p>
     * Sets the answer id attribute of an answer model</p>
     *
     * @param answerId the answer id attribute
     */
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    /**
     * <p>
     * Gets the lesson id attribute of an answer model</p>
     *
     * @return the lesson id attribute
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * <p>
     * Sets the lesson id attribute of an answer model</p>
     *
     * @param lessonId the lesson id attribute
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * <p>
     * Gets the question id attribute of an answer model</p>
     *
     * @return the question id attribute
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * <p>
     * Sets the question id attribute of an answer model</p>
     *
     * @param questionId the question id attribute
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * <p>
     * Gets the answer attribute of an answer model</p>
     *
     * @return the answer attribute
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * <p>
     * Sets the answer attribute of an answer model</p>
     *
     * @param answer the answer attribute
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * <p>
     * Gets the correct attribute of an answer model</p>
     *
     * @return the correct attribute
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * <p>
     * Sets the correct attribute of an answer model</p>
     *
     * @param correct the correct attribute
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    /**
     * <p>
     * Gets the date time attribute of an answer model</p>
     *
     * @return the date time attribute
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * <p>
     * Sets the date time attribute of an answer model</p>
     *
     * @param dateTime the date time attribute
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
