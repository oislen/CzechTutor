package com.czechtutor.model.custom;

import java.util.HashMap;

/**
 * <p>
 * Custom class for retrieving lessons, questions and answers</p>
 *
 * @author oislen
 */
public class LessonQuestionAnswer {

    private Integer lessonId;
    private String fromLanguage;
    private String toLanguage;
    private Integer nQuestions;
    private Integer nOptions;
    private String level;
    private Integer questionId;
    private String phrase;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String solution;
    private Integer answerId;
    private String answer;
    private Boolean correct;

    /**
     * <p>
     * Sets the attributes of a custom lesson questions answers model using a hashmap of attribute
     * values</p>
     *
     * @param payload a hashmap of attributes to set the custom lesson questions answers model with
     */
    public void set(HashMap<String, Object> payload) {
        // set the class objects
        this.lessonId = (Integer) payload.get("lessonId");
        this.fromLanguage = (String) payload.get("fromLanguage");
        this.toLanguage = (String) payload.get("toLanguage");
        this.nQuestions = (Integer) payload.get("nQuestions");
        this.nOptions = (Integer) payload.get("nOptions");
        this.level = (String) payload.get("level");
        this.questionId = (Integer) payload.get("questionId");
        this.phrase = (String) payload.get("phrase");
        this.option1 = (String) payload.get("option1");
        this.option2 = (String) payload.get("option2");
        this.option3 = (String) payload.get("option3");
        this.option4 = (String) payload.get("option4");
        this.solution = (String) payload.get("solution");
        this.answer = (String) payload.get("answer");
        this.correct = (Boolean) payload.get("correct");
    }

    /**
     * <p>
     * Gets the attributes of a custom lesson questions answers model as a hashmap of attribute
     * values</p>
     *
     * @return a hashmap of the custom lesson questions answers model attributes
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
        lessonResultsPayload.put("questionId", questionId);
        lessonResultsPayload.put("phrase", phrase);
        lessonResultsPayload.put("option1", option1);
        lessonResultsPayload.put("option2", option2);
        lessonResultsPayload.put("option3", option3);
        lessonResultsPayload.put("option4", option4);
        lessonResultsPayload.put("solution", solution);
        lessonResultsPayload.put("answer", answer);
        lessonResultsPayload.put("correct", correct);
        return lessonResultsPayload;
    }

    /**
     * <p>
     * Gets the lesson id attribute of a custom lesson questions answers model</p>
     *
     * @return the lesson id attribute
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * <p>
     * Sets the lesson id attribute of a custom lesson questions answers model</p>
     *
     * @param lessonId the lesson id attribute
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * <p>
     * Gets the lesson from language attribute of a custom lesson questions answers model</p>
     *
     * @return the lesson from language attribute
     */
    public String getFromLanguage() {
        return fromLanguage;
    }

    /**
     * <p>
     * Sets the lesson from language attribute of a custom lesson questions answers model</p>
     *
     * @param fromLanguage the lesson from language attribute
     */
    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    /**
     * <p>
     * Gets the lesson to language attribute of a custom lesson questions answers model</p>
     *
     * @return the lesson to language attribute
     */
    public String getToLanguage() {
        return toLanguage;
    }

    /**
     * <p>
     * Sets the lesson to language attribute of a custom lesson questions answers model</p>
     *
     * @param toLanguage the lesson to language attribute
     */
    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    /**
     * <p>
     * Gets the lesson n questions attribute of a custom lesson questions answers model</p>
     *
     * @return the lesson n questions attribute
     */
    public Integer getNQuestions() {
        return nQuestions;
    }

    /**
     * <p>
     * Sets the lesson n questions attribute of a custom lesson questions answers model</p>
     *
     * @param nQuestions the lesson n questions attribute
     */
    public void setNQuestions(Integer nQuestions) {
        this.nQuestions = nQuestions;
    }

    /**
     * <p>
     * Gets the lesson n options attribute of a custom lesson questions answers model</p>
     *
     * @return the lesson n options attribute
     */
    public Integer getNOptions() {
        return nOptions;
    }

    /**
     * <p>
     * Sets the lesson n options attribute of a custom lesson questions answers model</p>
     *
     * @param nOptions the lesson n options attribute
     */
    public void setNOptions(Integer nOptions) {
        this.nOptions = nOptions;
    }

    /**
     * <p>
     * Gets the lesson level attribute of a custom lesson questions answers model</p>
     *
     * @return the level options attribute
     */
    public String getLevel() {
        return level;
    }

    /**
     * <p>
     * Sets the lesson level attribute of a custom lesson questions answers model</p>
     *
     * @param level the lesson level attribute
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * <p>
     * Gets the question id attribute of a custom lesson questions answers model</p>
     *
     * @return the question id attribute
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * <p>
     * Sets the question id attribute of a custom lesson questions answers model</p>
     *
     * @param questionId the question id attribute
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * <p>
     * Gets the question phrase attribute of a custom lesson questions answers model</p>
     *
     * @return the question phrase attribute
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * <p>
     * Sets the question phrase attribute of a custom lesson questions answers model</p>
     *
     * @param phrase the question phrase attribute
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    /**
     * <p>
     * Gets the question option 1 attribute of a custom lesson questions answers model</p>
     *
     * @return the question option 1 attribute
     */
    public String getOption1() {
        return option1;
    }

    /**
     * <p>
     * Sets the question option 1 attribute of a custom lesson questions answers model</p>
     *
     * @param option1 the question option 1 attribute
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * <p>
     * Gets the question option 2 attribute of a custom lesson questions answers model</p>
     *
     * @return the question option 2 attribute
     */
    public String getOption2() {
        return option2;
    }

    /**
     * <p>
     * Sets the question option 2 attribute of a custom lesson questions answers model</p>
     *
     * @param option2 the question option 2 attribute
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * <p>
     * Gets the question option 3 attribute of a custom lesson questions answers model</p>
     *
     * @return the question option 3 attribute
     */
    public String getOption3() {
        return option3;
    }

    /**
     * <p>
     * Sets the question option 3 attribute of a custom lesson questions answers model</p>
     *
     * @param option3 the question option 3 attribute
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * <p>
     * Gets the question option 4 attribute of a custom lesson questions answers model</p>
     *
     * @return the question option 4 attribute
     */
    public String getOption4() {
        return option4;
    }

    /**
     * <p>
     * Sets the question option 4 attribute of a custom lesson questions answers model</p>
     *
     * @param option4 the question option 4 attribute
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * <p>
     * Gets the question solution attribute of a custom lesson questions answers model</p>
     *
     * @return the question solution attribute
     */
    public String getSolution() {
        return solution;
    }

    /**
     * <p>
     * Sets the question solution attribute of a custom lesson questions answers model</p>
     *
     * @param solution the question solution attribute
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * <p>
     * Gets the answer id attribute of a custom lesson questions answers model</p>
     *
     * @return the answer id attribute
     */
    public Integer getAnswerId() {
        return answerId;
    }

    /**
     * <p>
     * Sets the answer id attribute of a custom lesson questions answers model</p>
     *
     * @param answerId the answer id attribute
     */
    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    /**
     * <p>
     * Gets the answer attribute of a custom lesson questions answers model</p>
     *
     * @return the answer attribute
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * <p>
     * Sets the answer attribute of a custom lesson questions answers model</p>
     *
     * @param answer the answer attribute
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * <p>
     * Gets the correct attribute of a custom lesson questions answers model</p>
     *
     * @return the correct attribute
     */
    public Boolean getCorrect() {
        return correct;
    }

    /**
     * <p>
     * Sets the correct attribute of a custom lesson questions answers model</p>
     *
     * @param correct the correct attribute
     */
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}
