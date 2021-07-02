import java.io.*;
public class Concordance { 
    public Concordance() throws IOException{
        BufferedReader s = new BufferedReader(new FileReader("text.txt"));
        String line = s.readLine();
        String[] words = line.split(" ");
        System.out.println("");
        for (String a : words){
            System.out.println(a);
        }
        s.close();

    }
    public static void main(String[] args) throws IOException{
    new Concordance();
    }
}