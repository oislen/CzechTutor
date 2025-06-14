package com.czechtutor.model;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <p>
 * The question model class defines the schema / structure of a question</p>
 *
 * @author oislen
 */
@Table("QUESTIONS")
public class QuestionModel {

    @Id
    private Integer questionId;
    private Integer lessonId;
    private String phrase;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String solution;
    private LocalDateTime dateTime;
    private String dateTimeHash;

    /**
     * <p>
     * Sets the attributes of a question model using a hashmap of attribute
     * values</p>
     *
     * @param payload a hashmap of attributes to set the question model with
     */
    public void set(HashMap<String, Object> payload) {
        // set the class objects
        //this.questionId = (Integer) payload.get("questionId");
        this.lessonId = (Integer) payload.get("lessonId");
        this.phrase = (String) payload.get("phrase");
        this.option1 = (String) payload.get("option1");
        this.option2 = (String) payload.get("option2");
        this.option3 = (String) payload.get("option3");
        this.option4 = (String) payload.get("option4");
        this.solution = (String) payload.get("solution");
        this.dateTime = (LocalDateTime) payload.get("dateTime");
        this.dateTimeHash = (String) payload.get("dateTimeHash");
    }

    /**
     * <p>
     * Gets the attributes of a question model as a hashmap of attribute
     * values</p>
     *
     * @return a hashmap of the question model attributes
     */
    public HashMap<String, Object> getQuestionPayload() {
        // construct questionPayload
        HashMap<String, Object> questionPayload = new HashMap<>();
        questionPayload.put("questionId", questionId);
        questionPayload.put("lessonId", lessonId);
        questionPayload.put("phrase", phrase);
        questionPayload.put("option1", option1);
        questionPayload.put("option2", option2);
        questionPayload.put("option3", option3);
        questionPayload.put("option4", option4);
        questionPayload.put("solution", solution);
        questionPayload.put("dateTime", dateTime);
        questionPayload.put("dateTimeHash", dateTimeHash);
        return questionPayload;
    }

    /**
     * <p>
     * Gets the question id attribute of a question model</p>
     *
     * @return the question id attribute
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * <p>
     * Sets the question id attribute of a question model</p>
     *
     * @param questionId the question id attribute
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * <p>
     * Gets the question lesson id attribute of a question model</p>
     *
     * @return the question lesson id attribute
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * <p>
     * Sets the question lesson id attribute of a question model</p>
     *
     * @param lessonId the question lesson id attribute
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * <p>
     * Gets the question phrase attribute of a question model</p>
     *
     * @return the question phrase attribute
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * <p>
     * Sets the question phrase attribute of a question model</p>
     *
     * @param phrase the question phrase attribute
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    /**
     * <p>
     * Gets the question option 1 attribute of a question model</p>
     *
     * @return the question option 1 attribute
     */
    public String getOption1() {
        return option1;
    }

    /**
     * <p>
     * Sets the question option 1 attribute of a question model</p>
     *
     * @param option1 the question option 1 attribute
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * <p>
     * Gets the question option 2 attribute of a question model</p>
     *
     * @return the question option 2 attribute
     */
    public String getOption2() {
        return option2;
    }

    /**
     * <p>
     * Sets the question option 2 attribute of a question model</p>
     *
     * @param option2 the question option 2 attribute
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * <p>
     * Gets the question option 3 attribute of a question model</p>
     *
     * @return the question option 3 attribute
     */
    public String getOption3() {
        return option3;
    }

    /**
     * <p>
     * Sets the question option 3 attribute of a question model</p>
     *
     * @param option3 the question option 3 attribute
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * <p>
     * Gets the question option 4 attribute of a question model</p>
     *
     * @return the question option 4 attribute
     */
    public String getOption4() {
        return option4;
    }

    /**
     * <p>
     * Sets the question option 4 attribute of a question model</p>
     *
     * @param option4 the question option 4 attribute
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * <p>
     * Gets the question solution attribute of a question model</p>
     *
     * @return the question solution attribute
     */
    public String getSolution() {
        return solution;
    }

    /**
     * <p>
     * Sets the question solution attribute of a question model</p>
     *
     * @param solution the question solution attribute
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * <p>
     * Gets the date time attribute of an question model</p>
     *
     * @return the date time attribute
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * <p>
     * Sets the date time attribute of an question model</p>
     *
     * @param dateTime the date time attribute
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * <p>
     * Gets the date time hash attribute of a lesson model</p>
     *
     * @return the date time hash attribute
     */
    public String getDateTimedHash() {
        return dateTimeHash;
    }

    /**
     * <p>
     * Sets the date time hash attribute of a lesson model</p>
     *
     * @param dateTimeHash the lesson id hash attribute
     */
    public void setDateTimeHash(String dateTimeHash) {
        this.dateTimeHash = dateTimeHash;
    }

}
