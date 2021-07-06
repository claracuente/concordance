import java.io.*;
import java.util.*;

public class concordance { 
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    String key;
    Integer temp_int = 0;

    public concordance() throws IOException{
        BufferedReader s = new BufferedReader(new FileReader("text.txt"));
        String line = s.readLine();
        while (line != null){
            String[] words = line.split(" ");
            for (String a : words){
                if(table.containsKey(a)){
                    temp_int = table.get(a);
                    table.put(a, temp_int+1);
                }else{
                    table.put(a, 1);
                }
            }
            line = s.readLine();
        }
        Enumeration<String> e = table.keys();
        while (e.hasMoreElements()){
            
            key = e.nextElement();
            System.out.println(key + ", " + table.get(key).toString());
        }
        s.close();

    }
    public static void main(String[] args) throws IOException{
    new concordance();
    }
}