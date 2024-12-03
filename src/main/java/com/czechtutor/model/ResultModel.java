package com.czechtutor.model;

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
        return resultsPayload;
    }

    /**
     * <p>
     * Gets the result id attribute of a result model</p>
     *
     * @returns the result id attribute
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
     * @returns the result lesson id attribute
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
     * @returns the result n correct attribute
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
     * @returns the result score attribute
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

}
