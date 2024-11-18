package com.czechtutor.arch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Data {

    public static ArrayList<HashMap<String, String>> load(String dataFilePath) {
        ArrayList<HashMap<String, String>> recordSet = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(dataFilePath), "UTF-8"))) {
            String lineJustFetched;
            String[] strKeys = {"EN", "CZ", "REF"};
            ArrayList<String> arrayKeys = new ArrayList<>(Arrays.asList(strKeys)); 
            // iterate over file lines and transform each line into a record set
            while(true){
                lineJustFetched = buf.readLine();
                if (lineJustFetched == null) {
                    break; 
                } else {
                    String[] strSplit = lineJustFetched.split("\t");
                    ArrayList<String> arrayValues = new ArrayList<>( Arrays.asList(strSplit)); 
                    HashMap<String, String> hashMapObject = new HashMap<>();
                    for (int i = 0; i<3; i++) {
                        hashMapObject.put(arrayKeys.get(i), arrayValues.get(i));
                    }
                    recordSet.add(hashMapObject);
                }
            }
        }catch(Exception e){  
            e.printStackTrace();
        }
        return recordSet;
    }
}
