import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;
import org.json.JSONArray

public class CzechTutor {

    public static ArrayList<Map<String, String>> LoadData(String dataFilePath) {
        ArrayList<Map<String, String>> recordSet = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(dataFilePath);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader buf = new BufferedReader(isr);
            String lineJustFetched = null;
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
            buf.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordSet;
    }
    
    public static ArrayList<Integer> RandomDataIndices(Integer nIndices, Integer upperIndexBound) {
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
        ArrayList<Map<String, String>> recordSet = CzechTutor.LoadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
        // randomly generate four indices from data
        Integer upperIndexBound = recordSet.size();
        Integer nIndices = 4;
        ArrayList<Integer> indexArray = CzechTutor.RandomDataIndices(nIndices, upperIndexBound);
        // Expand logic here to construct payload
        // extract randomly select index values from record set
        ArrayList<Map<String, String>> filteredRecordSet = new ArrayList<Map<String, String>> (indexArray.stream().map(recordSet::get).collect(Collectors.toList()));

        String message;
        JSONObject json = new JSONObject();
        json.put("name", "student");
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("information", "test");
        item.put("id", 3);
        item.put("name", "course1");
        array.put(item);
        json.put("course", array);
        message = json.toString();

        System.out.println(filteredRecordSet);
    }
}    