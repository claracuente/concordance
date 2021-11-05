//This solution supports up to 1024 different words in a given input file.

import java.io.*;
import java.util.*;

//The concordance class creates a Hashtable to manage its data
//The Hashtable contains words as the keys and lists as values
//The lists hold the lines that contain the specific word
public class concordance {
    Hashtable<String, ArrayList<Integer>> table = new Hashtable<>();

    //Method designed to format output for a single line
    //Takes the bullet point, word and list of lines that contain the word as inputs
    //Example string returned --> a. hello {2: 1, 2}
    public String formatLine(String a, String b, ArrayList<Integer> list){
        int count = list.size();
        String lines = list.toString();
        return a + "   " + b + " {" + count + ":" + lines.substring(1,lines.length()-1) + "}";
    }

    //Method used to write out the results to result.txt
    //Takes the Hashtable as an input
    public void writeOut(Hashtable<String, ArrayList<Integer>> table) throws FileNotFoundException, UnsupportedEncodingException {
        TreeMap<String, ArrayList<Integer>> tmap = new TreeMap<>();
        tmap.putAll(table);
        Set<String> e = tmap.keySet();
        Iterator<String> i = e.iterator();
        PrintWriter writer = new PrintWriter("src/result.txt", "UTF-8");
        char[] alphabet_c = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] alphabet_d = " abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (char d : alphabet_d) {
            for (char c : alphabet_c) {
                if (i.hasNext()){
                    String key = i.next();
                    writer.println(formatLine((d+ "" +c + "."),key, table.get(key)));
                }

            }
        }
        writer.close();
    }


    //This method reads from the input file, text.txt and inserts the proper data into the Hashtable
    public concordance() throws IOException{
        int line_number = 1;
        BufferedReader s = new BufferedReader(new FileReader("src/text.txt"));
        String line = s.readLine();

        while (line != null){

            //Using "\\W" with split will remove any non alpha characters
            String[] words = line.split("\\W");

            for (String a : words){
                if (!table.containsKey(a)) {
                    table.put(a, new ArrayList<>());
                }
                table.get(a).add(line_number);
            }
            line = s.readLine();
            line_number += 1;
        }

        //Remove empty string from table
        table.remove("");

        writeOut(table);
        s.close();

    }


    public static void main(String[] args) throws IOException{
        new concordance();
    }
}
