package com.czechtutor;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Choose_Test {
    
    @Test
    void randomIndices_ArrayLength_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(2, 5);
        Assertions.assertEquals(2, indexArray.size());
    }

    @Test
    void randomIndices_ArrayMax_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(2, 5);
        Assertions.assertTrue(Collections.max(indexArray) <= 5);
    }
    
    @Test
    void randomIndices_ArrayMin_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(2, 5);
        Assertions.assertTrue(Collections.min(indexArray) >= 0);
    }

}
