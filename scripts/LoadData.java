import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoadData {
    public static void main(String[] args) {
        try{
            FileInputStream fis = new FileInputStream("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader buf = new BufferedReader(isr);
            ArrayList<Map<String, String>> recordSet = new ArrayList<>();
            String lineJustFetched = null;
            

            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    String[] arrayKeys = {"EN", "CZ", "REF"}; 
                    String[] arrayValues;
                    arrayValues = lineJustFetched.split("\t");
                    Map<String, String> mapObject = IntStream.range(0, arrayKeys.length).boxed().collect(Collectors.toMap(i -> arrayKeys[i], i -> arrayValues[i]));
                    recordSet.add(mapObject);
                }
            }


            buf.close();

            System.out.println(recordSet);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}    