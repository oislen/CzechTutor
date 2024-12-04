package com.czechtutor.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.czechtutor.model.CesModel;

public class CesModel_Test {
    
    final Integer expId = 1;
    final String expEnglish = "Hi.";
    final String expCzech = "Ahoj!";
    final String expLevel = "Beginner";
    final String expReference = "CC-BY 2.0 (France) Attribution: tatoeba.org #538123 (CM) & #373338 (lipao)";
    CesModel cesModel = new CesModel();
    
    {
        cesModel.setId(expId);
        cesModel.setEnglish(expEnglish);
        cesModel.setCzech(expCzech);
        cesModel.setLevel(expLevel);
        cesModel.setReference(expReference);
    }

    @Test
    void Id_Test() {
        Integer obsId = cesModel.getId();
        Assertions.assertEquals(expId, obsId);
    }

    @Test
    void English_Test() {
        String obsEnglish = cesModel.getEnglish();
        Assertions.assertEquals(expEnglish, obsEnglish);
    }

    @Test
    void Czech_Test() {
        String obsCzech = cesModel.getCzech();
        Assertions.assertEquals(expCzech, obsCzech);
    }
    
    @Test
    void Level_Test() {
        String obsLevel = cesModel.getLevel();
        Assertions.assertEquals(expLevel, obsLevel);
    }
    
    @Test
    void Reference_Test() {
        String obsReference = cesModel.getReference();
        Assertions.assertEquals(expReference, obsReference);
    }

}