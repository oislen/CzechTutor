package com.czechtutor;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Choose_Test {
    
    Integer nIndices = 2;
    Integer upperIndexBound = 5;

    @Test
    void randomIndices_ArrayLength_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(nIndices, upperIndexBound);
        Assertions.assertEquals(2, indexArray.size());
    }

    @Test
    void randomIndices_ArrayMax_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(nIndices, upperIndexBound);
        Assertions.assertTrue(Collections.max(indexArray) <= upperIndexBound);
    }
    
    @Test
    void randomIndices_ArrayMin_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(nIndices, upperIndexBound);
        Assertions.assertTrue(Collections.min(indexArray) >= 0);
    }

}
