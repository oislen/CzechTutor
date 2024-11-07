package com.czechtutor;

import java.util.ArrayList;
import java.util.Random;

public class Choose {
    public static ArrayList<Integer> randomIndices(Integer nIndices, Integer upperIndexBound) {
        // randomly select nIndices between 0 and upperIndexBound
        Random indexGenerator = new Random();
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i = 0; i<nIndices; i++) {
            Integer index = indexGenerator.nextInt(upperIndexBound);
            indexArray.add(index);
        }
        return indexArray;
    }
}
