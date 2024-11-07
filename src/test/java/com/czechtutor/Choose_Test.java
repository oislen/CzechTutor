package com.czechtutor;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Choose_Test {
    
    @Test
    void randomIndices_ArrayLength_Test() {
        ArrayList<Integer> indexArray = Choose.randomIndices(2, 5);
        Assertions.assertEquals(2, indexArray.size());
    }

}
