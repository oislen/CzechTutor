package com.czechtutor;

import java.util.ArrayList;
import java.util.HashMap;

public class Evaluate {
    public static Integer countTotalCorrect(ArrayList<HashMap<String,Object>> results){
        int totalCorrect = 0;
        for (HashMap<String,Object> hashMapObject : results) {
            Boolean isCorrect = (Boolean) hashMapObject.get("correct");
            if (isCorrect) {
                totalCorrect=totalCorrect+1;
            }
        }
        return totalCorrect;
    }
}
