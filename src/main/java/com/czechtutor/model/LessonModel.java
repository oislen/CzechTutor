package com.czechtutor.model;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <p>
 * The lesson model class defines the schema / structure of a lesson</p>
 *
 * @author oislen
 */
@Table("LESSONS")
public class LessonModel {

    @Id
    private Integer lessonId;
    private String fromLanguage;
    private String toLanguage;
    private Integer nQuestions;
    private Integer nOptions;
    private String level;
    private LocalDateTime dateTime;

    /**
     * <p>
     * Sets the attributes of a lesson model using a hashmap of attribute
     * values</p>
     *
     * @param payload a hashmap of attributes to set the lesson model with
     */
    public void set(HashMap<String, Object> payload) {
        // set the class objects
        //this.lessonId = (Integer) payload.get("lessonId");
        this.fromLanguage = (String) payload.get("fromLanguage");
        this.toLanguage = (String) payload.get("toLanguage");
        this.nQuestions = (Integer) payload.get("nQuestions");
        this.nOptions = (Integer) payload.get("nOptions");
        this.level = (String) payload.get("level");
        this.dateTime = (LocalDateTime) payload.get("dateTime");
    }

    /**
     * <p>
     * Gets the attributes of a lesson model as a hashmap of attribute
     * values</p>
     *
     * @return a hashmap of the lesson model attributes
     */
    public HashMap<String, Object> getLessonPayload() {
        // construct lesson payload
        HashMap<String, Object> lessonPayload = new HashMap<>();
        lessonPayload.put("lessonId", lessonId);
        lessonPayload.put("fromLanguage", fromLanguage);
        lessonPayload.put("toLanguage", toLanguage);
        lessonPayload.put("nQuestions", nQuestions);
        lessonPayload.put("nOptions", nOptions);
        lessonPayload.put("level", level);
        lessonPayload.put("dateTime", dateTime);
        return lessonPayload;
    }

    /**
     * <p>
     * Gets the lesson id attribute of a lesson model</p>
     *
     * @return the lesson id attribute
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * <p>
     * Sets the lesson id attribute of a lesson model</p>
     *
     * @param lessonId the lesson id attribute
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * <p>
     * Gets the lesson from language attribute of a lesson model</p>
     *
     * @return the lesson from language attribute
     */
    public String getFromLanguage() {
        return fromLanguage;
    }

    /**
     * <p>
     * Sets the lesson from language attribute of a lesson model</p>
     *
     * @param fromLanguage the lesson from language attribute
     */
    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    /**
     * <p>
     * Gets the lesson to language attribute of a lesson model</p>
     *
     * @return the lesson to language attribute
     */
    public String getToLanguage() {
        return toLanguage;
    }

    /**
     * <p>
     * Sets the lesson to language attribute of a lesson model</p>
     *
     * @param toLanguage the lesson to language attribute
     */
    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    /**
     * <p>
     * Gets the lesson n questions attribute of a lesson model</p>
     *
     * @return the lesson n questions attribute
     */
    public Integer getNQuestions() {
        return nQuestions;
    }

    /**
     * <p>
     * Sets the lesson n questions attribute of a lesson model</p>
     *
     * @param nQuestions the lesson n questions attribute
     */
    public void setNQuestions(Integer nQuestions) {
        this.nQuestions = nQuestions;
    }

    /**
     * <p>
     * Gets the lesson n options attribute of a lesson model</p>
     *
     * @return the lesson n options attribute
     */
    public Integer getNOptions() {
        return nOptions;
    }

    /**
     * <p>
     * Sets the lesson n options attribute of a lesson model</p>
     *
     * @param nOptions the lesson n options attribute
     */
    public void setNOptions(Integer nOptions) {
        this.nOptions = nOptions;
    }

    /**
     * <p>
     * Gets the lesson level attribute of a lesson model</p>
     *
     * @return the level options attribute
     */
    public String getLevel() {
        return level;
    }

    /**
     * <p>
     * Sets the lesson level attribute of a lesson model</p>
     *
     * @param level the lesson level attribute
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * <p>
     * Gets the date time attribute of an lesson model</p>
     *
     * @return the date time attribute
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * <p>
     * Sets the date time attribute of an lesson model</p>
     *
     * @param dateTime the date time attribute
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
