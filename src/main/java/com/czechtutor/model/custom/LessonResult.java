package com.czechtutor.model.custom;

import java.util.HashMap;

/**
 * <p>
 * Custom lesson results class for retrieving lessons, questions and answers</p>
 *
 * @author oislen
 */
public class LessonResult {

    private Integer lessonId;
    private String fromLanguage;
    private String toLanguage;
    private Integer nQuestions;
    private Integer nOptions;
    private String level;
    private Integer resultId;
    private Integer nCorrect;
    private Float score;

    /**
     * <p>
     * Sets the attributes of a custom lessons results model using a hashmap of attribute
     * values</p>
     *
     * @param payload a hashmap of attributes to set the lesson model with
     */
    public void set(HashMap<String, Object> payload) {
        // set the class objects
        this.lessonId = (Integer) payload.get("lessonId");
        this.fromLanguage = (String) payload.get("fromLanguage");
        this.toLanguage = (String) payload.get("toLanguage");
        this.nQuestions = (Integer) payload.get("nQuestions");
        this.nOptions = (Integer) payload.get("nOptions");
        this.level = (String) payload.get("level");
        this.resultId = (Integer) payload.get("resultId");
        this.nCorrect = (Integer) payload.get("nCorrect");
        this.score = (Float) payload.get("score");
    }

    /**
     * <p>
     * Gets the attributes of a custom lessons results model as a hashmap of attribute
     * values</p>
     *
     * @return a hashmap of the lesson model attributes
     */
    public HashMap<String, Object> getLessonResultsPayload() {
        // construct lesson payload
        HashMap<String, Object> lessonResultsPayload = new HashMap<>();
        lessonResultsPayload.put("lessonId", lessonId);
        lessonResultsPayload.put("fromLanguage", fromLanguage);
        lessonResultsPayload.put("toLanguage", toLanguage);
        lessonResultsPayload.put("nQuestions", nQuestions);
        lessonResultsPayload.put("nOptions", nOptions);
        lessonResultsPayload.put("level", level);
        lessonResultsPayload.put("resultId", resultId);
        lessonResultsPayload.put("nCorrect", nCorrect);
        lessonResultsPayload.put("score", score);
        return lessonResultsPayload;
    }

    /**
     * <p>
     * Gets the lesson id attribute of a custom lessons results model</p>
     *
     * @return the lesson id attribute
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * <p>
     * Sets the lesson id attribute of a custom lessons results model</p>
     *
     * @param lessonId the lesson id attribute
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * <p>
     * Gets the lesson from language attribute of a custom lessons results model</p>
     *
     * @return the lesson from language attribute
     */
    public String getFromLanguage() {
        return fromLanguage;
    }

    /**
     * <p>
     * Sets the lesson from language attribute of a custom lessons results model</p>
     *
     * @param fromLanguage the lesson from language attribute
     */
    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    /**
     * <p>
     * Gets the lesson to language attribute of a custom lessons results model</p>
     *
     * @return the lesson to language attribute
     */
    public String getToLanguage() {
        return toLanguage;
    }

    /**
     * <p>
     * Sets the lesson to language attribute of a custom lessons results model</p>
     *
     * @param toLanguage the lesson to language attribute
     */
    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    /**
     * <p>
     * Gets the lesson n questions attribute of a custom lessons results model</p>
     *
     * @return the lesson n questions attribute
     */
    public Integer getNQuestions() {
        return nQuestions;
    }

    /**
     * <p>
     * Sets the lesson n questions attribute of a custom lessons results model</p>
     *
     * @param nQuestions the lesson n questions attribute
     */
    public void setNQuestions(Integer nQuestions) {
        this.nQuestions = nQuestions;
    }

    /**
     * <p>
     * Gets the lesson n options attribute of a custom lessons results model</p>
     *
     * @return the lesson n options attribute
     */
    public Integer getNOptions() {
        return nOptions;
    }

    /**
     * <p>
     * Sets the lesson n options attribute of a custom lessons results model</p>
     *
     * @param nOptions the lesson n options attribute
     */
    public void setNOptions(Integer nOptions) {
        this.nOptions = nOptions;
    }

    /**
     * <p>
     * Gets the lesson level attribute of a custom lessons results model</p>
     *
     * @return the level options attribute
     */
    public String getLevel() {
        return level;
    }

    /**
     * <p>
     * Sets the lesson level attribute of a custom lessons results model</p>
     *
     * @param level the lesson level attribute
     */
    public void setLevel(String level) {
        this.level = level;
    }


    /**
     * <p>
     * Gets the result id attribute of a custom lessons results model</p>
     *
     * @return the result id attribute
     */
    public Integer getResultId() {
        return resultId;
    }

    /**
     * <p>
     * Sets the result id attribute of a custom lessons results model</p>
     *
     * @param resultId the result id attribute
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    /**
     * <p>
     * Gets the result n correct attribute of a custom lessons results model</p>
     *
     * @return the result n correct attribute
     */
    public Integer getNCorrect() {
        return nCorrect;
    }

    /**
     * <p>
     * Sets the result n correct attribute of a custom lessons results model</p>
     *
     * @param nCorrect the result n correct attribute
     */
    public void setNCorrect(Integer nCorrect) {
        this.nCorrect = nCorrect;
    }

    /**
     * <p>
     * Gets the result score attribute of a custom lessons results model</p>
     *
     * @return the result score attribute
     */
    public Float getScore() {
        return score;
    }

    /**
     * <p>
     * Sets the result score attribute of a custom lessons results model</p>
     *
     * @param score the result score attribute
     */
    public void setScore(Float score) {
        this.score = score;
    }

}
