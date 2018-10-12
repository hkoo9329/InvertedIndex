import java.io.*;
import java.util.Scanner;

public class mainProject {

    public static void main(String[] args) throws IOException {
        PreprocessText preText = new PreprocessText();
        HashDictionary dictionary = preText.getDictionary();

        System.out.println("Number of documents :"+preText.getDocSize());
        System.out.println("Average of documents :"+preText.getDocAvg());
        dictionary.printMinMaxWords();


        Scanner sc = new Scanner(System.in);
        while (true){

            System.out.println("=============== exit : q  ================");
            System.out.print("Insert serch words : ");
            String insert = sc.next();
            if(insert.contentEquals("q"))
                break;
            else{
                dictionary.searchWord(insert);
            }
        }
        sc.close();



    }
}
