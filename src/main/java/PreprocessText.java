import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PreprocessText {

    private ArrayList<PostingsNode> textArray;
    private HashDictionary hashDictionary;
    private String fileParhString = "D:/github/InvertedIndex/Dataset";
    public PreprocessText() throws IOException {
        textArray = new ArrayList<PostingsNode>();
        hashDictionary = new HashDictionary();
        initText();
    }

    public void initText() throws IOException {
        Files.walk(Paths.get(fileParhString)).forEach(filePath -> {
            if(Files.isRegularFile(filePath)){
                File file = new File(filePath.toString());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String inputData = null;
                    String text = "";
                    int counter = 1;
                    String docId =removeTag(br.readLine())+" ";
                    while ((inputData = br.readLine()) != null){
                        if(inputData.contentEquals("<DOC>")){
                            while (true) {
                                inputData = br.readLine();
                                if (!inputData.contentEquals("</DOC>")) {
                                    text = text + inputData + "\n";
                                    continue;
                                }
                                else {
                                    addDictionary(docId+String.valueOf(counter++),text);
                                    text = "";
                                    break;
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addDictionary(String docId, String text) throws Exception {
        String token = null;
        text = removeTag(text);
        PostingsNode node = new PostingsNode(docId, text);
        textArray.add(node);

        StringTokenizer tokenizer = new StringTokenizer(text, ".,/ !?()\"\n");
        while (tokenizer.hasMoreTokens()) {
            token= tokenizer.nextToken();
            if(!token.contentEquals("\n")) {
                hashDictionary.add(token, node);
            }

        }

    }

    public String removeTag(String text) throws Exception {
        text = text.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        text = text.toLowerCase();
        return text;
    }

    public HashDictionary getDictionary(){
        return hashDictionary;
    }

    public int getDocSize(){
        return textArray.size();
    }

    public float getDocAvg(){
        float avg = 0.0f;
        avg = (float) hashDictionary.allWordsSum()/(float) getDocSize();
        return avg;
    }
}
