import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static ArrayList<Map<String, String>> loadData(String dataFilePath) {
        ArrayList<Map<String, String>> recordSet = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(dataFilePath), "UTF-8"))) {
            String lineJustFetched;
            String[] arrayKeys = {"EN", "CZ", "REF"};
            // iterate over file lines and transform each line into a record set
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    String[] arrayValues;
                    arrayValues = lineJustFetched.split("\t");
                    Map<String, String> mapObject = IntStream.range(0, arrayKeys.length).boxed().collect(Collectors.toMap(i -> arrayKeys[i], i -> arrayValues[i]));
                    recordSet.add(mapObject);
                }
            }
        }catch(IOException  e){
            System.out.println(e);
        }
        return recordSet;
    }
    
    public static ArrayList<Integer> randomDataIndices(Integer nIndices, Integer upperIndexBound) {
        // randomly select nIndices between 0 and upperIndexBound
        Random randomGenerator = new Random();
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i = 0; i<nIndices; i++)
        {
            Integer pick = randomGenerator.nextInt(upperIndexBound);
            indexArray.add(pick);
        }
        return indexArray;
    }
    
    public static void main(String[] args) {
        // load czech / english language phrases from disk
        ArrayList<Map<String, String>> recordSet = Main.loadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
        // randomly generate four indices from data
        Integer upperIndexBound = recordSet.size();
        Integer nIndices = 4;
        ArrayList<Integer> indexArray = Main.randomDataIndices(nIndices, upperIndexBound);
        // extract randomly select index values from record set
        ArrayList<Map<String, String>> filteredRecordSet = new ArrayList<> (indexArray.stream().map(recordSet::get).collect(Collectors.toList()));
        // construct basic payload
        HashMap<String,Object> payload = new HashMap<>();
        payload.put("question", 1);
        payload.put("phrases", filteredRecordSet);
        payload.put("correct", 2);
        System.out.println(payload);
    }
}    