package com.czechtutor.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * <p>
 * The ces model class defines the schema / structure of a ces phrase</p>
 *
 * @author oislen
 */
@Table("CES")
public class CesModel {

    @Id
    private Integer id;
    private String english;
    private String czech;
    private String level;
    private String reference;

    /**
     * <p>
     * Sets the attributes of a ces model using a hashmap of attribute
     * values</p>
     *
     * @param payload a hashmap of attributes to set the ces model with
     */
    public void set(HashMap<String, Object> payload) {
        // set the class objects
        //this.id = (Integer) payload.get("id");
        this.english = (String) payload.get("english");
        this.czech = (String) payload.get("czech");
        this.level = (String) payload.get("level");
        this.reference = (String) payload.get("reference");
    }

    /**
     * <p>
     * Gets the attributes of a ces model as a hashmap of attribute values</p>
     *
     * @return a hashmap of the ces model attributes
     */
    public HashMap<String, Object> getCesPayload() {
        // construct questionPayload
        HashMap<String, Object> questionPayload = new HashMap<>();
        questionPayload.put("id", id);
        questionPayload.put("english", english);
        questionPayload.put("czech", czech);
        questionPayload.put("level", level);
        questionPayload.put("reference", reference);
        return questionPayload;
    }

    /**
     * <p>
     * Gets the ces id attribute of a ces model</p>
     *
     * @returns the ces id attribute
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>
     * Sets the ces id attribute of a ces model</p>
     *
     * @param id the ces id attribute
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <p>
     * Gets the ces english attribute of a ces model</p>
     *
     * @returns the ces english attribute
     */
    public String getEnglish() {
        return english;
    }

    /**
     * <p>
     * Sets the ces english attribute of a ces model</p>
     *
     * @param english the ces english attribute
     */
    public void setEnglish(String english) {
        this.english = english;
    }

    /**
     * <p>
     * Gets the ces czech attribute of a ces model</p>
     *
     * @returns the ces czech attribute
     */
    public String getCzech() {
        return czech;
    }

    /**
     * <p>
     * Sets the ces czech attribute of a ces model</p>
     *
     * @param czech the ces czech attribute
     */
    public void setCzech(String czech) {
        this.czech = czech;
    }

    /**
     * <p>
     * Gets the ces level attribute of a ces model</p>
     *
     * @returns the ces level attribute
     */
    public String getLevel() {
        return level;
    }

    /**
     * <p>
     * Sets the ces level attribute of a ces model</p>
     *
     * @param level the ces level attribute
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * <p>
     * Gets the ces reference attribute of a ces model</p>
     *
     * @returns the ces reference attribute
     */
    public String getReference() {
        return reference;
    }

    /**
     * <p>
     * Sets the ces reference attribute of a ces model</p>
     *
     * @param reference the ces reference attribute
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}
