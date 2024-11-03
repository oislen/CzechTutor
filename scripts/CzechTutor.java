import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

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
    public static void main(String[] args) {
        // load czech / english language phrases from disk
        ArrayList<Map<String, String>> recordSet = CzechTutor.LoadData("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
        // randomly select from loaded phrases
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(recordSet.size());
        System.out.println(recordSet.get(index));
    }
}    