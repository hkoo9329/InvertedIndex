import java.util.*;

public class HashDictionary {

    private HashMap<String, PostingsList> hashMap;

    public HashDictionary() {
        hashMap = new HashMap<String, PostingsList>();
    }

    public void add(String token, PostingsNode node) {
        if (hashMap.containsKey(token)) {
            hashMap.get(token).tokenCount();
            if (hashMap.get(token).duplicateCheck(node)) {
                hashMap.get(token).add(node);
            }
        } else {
            PostingsList postingsList = new PostingsList();
            hashMap.put(token, postingsList);
        }
    }


    public void searchWord(String keyWord) {
        if (hashMap.containsKey(keyWord)) {
            hashMap.get(keyWord).printDocIdAll();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Do you want to search docID?  (yes/no)");
                String insert = sc.nextLine();
                if (insert.contentEquals("yes")) {
                    System.out.print("insert docId : ");
                    String docId = sc.nextLine();
                    hashMap.get(keyWord).printText(docId);
                } else if (insert.contentEquals("no")) {
                    break;
                }
            }
        } else {
            System.out.println("Word is none");
        }
    }


    public int allWordsSum() {
        int sum = 0;
        for (String mapKey : hashMap.keySet()) {
            sum += hashMap.get(mapKey).getTokenCounter();
        }
        return sum;
    }


    public void printMinMaxWords() {
        int min = 0, max = 0;
        String maxKey = null, minKey = null;
        min = hashMap.get(hashMap.keySet().iterator().next()).getTokenCounter();
        max = min;
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).getTokenCounter() > max) {
                maxKey = key;
                max = hashMap.get(key).getTokenCounter();
            }
            if (hashMap.get(key).getTokenCounter() < min) {
                minKey = key;
                min = hashMap.get(key).getTokenCounter();
            }
        }
        System.out.println("MaxWord :" + maxKey + "\tNumber of times :" + max);
        System.out.println("MinWord :" + minKey + "\tNumber of times :" + min);

    }

}
