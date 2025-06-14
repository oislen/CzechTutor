package com.czechtutor.model;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <p>
 * The result model class defines the schema / structure of a result</p>
 *
 * @author oislen
 */
@Table("RESULTS")
public class ResultModel {

    @Id
    private Integer resultId;
    private Integer lessonId;
    private Integer nCorrect;
    private Float score;
    private LocalDateTime dateTime;
    private String dateTimeHash;

    /**
     * <p>
     * Sets the attributes of a result model using a hashmap of attribute
     * values</p>
     *
     * @param payload a hashmap of attributes to set the result model with
     */
    public void set(HashMap<String, Object> payload) {
        // set class objects
        //this.resultId = (Integer) payload.get("resultId");
        this.lessonId = (Integer) payload.get("lessonId");
        this.nCorrect = (Integer) payload.get("nCorrect");
        this.score = (Float) payload.get("score");
        this.dateTime = (LocalDateTime) payload.get("dateTime");
        this.dateTimeHash = (String) payload.get("dateTimeHash");
    }

    /**
     * <p>
     * Gets the attributes of a result model as a hashmap of attribute
     * values</p>
     *
     * @return a hashmap of the result model attributes
     */
    public HashMap<String, Object> getResultPayload() {
        // create results payload
        HashMap<String, Object> resultsPayload = new HashMap<>();
        resultsPayload.put("resultId", resultId);
        resultsPayload.put("lessonId", lessonId);
        resultsPayload.put("nCorrect", nCorrect);
        resultsPayload.put("score", score);
        resultsPayload.put("dateTime", dateTime);
        resultsPayload.put("dateTimeHash", dateTimeHash);
        return resultsPayload;
    }

    /**
     * <p>
     * Gets the result id attribute of a result model</p>
     *
     * @return the result id attribute
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * <p>
     * Sets the result id attribute of a result model</p>
     *
     * @param resultId the result id attribute
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * <p>
     * Gets the result lesson id attribute of a result model</p>
     *
     * @return the result lesson id attribute
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * <p>
     * Sets the result lesson id attribute of a result model</p>
     *
     * @param lessonId the result lesson id attribute
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * <p>
     * Gets the result n correct attribute of a result model</p>
     *
     * @return the result n correct attribute
     */
    public Integer getNCorrect() {
        return nCorrect;
    }

    /**
     * <p>
     * Sets the result n correct attribute of a result model</p>
     *
     * @param nCorrect the result n correct attribute
     */
    public void setNCorrect(Integer nCorrect) {
        this.nCorrect = nCorrect;
    }

    /**
     * <p>
     * Gets the result score attribute of a result model</p>
     *
     * @return the result score attribute
     */
    public Float getScore() {
        return score;
    }

    /**
     * <p>
     * Sets the result score attribute of a result model</p>
     *
     * @param score the result score attribute
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * <p>
     * Gets the date time attribute of an result model</p>
     *
     * @return the date time attribute
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * <p>
     * Sets the date time attribute of an result model</p>
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
