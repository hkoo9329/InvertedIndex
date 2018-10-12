import java.util.Iterator;
import java.util.LinkedList;

public class PostingsList {

    private int tokenCounter = 1;

    private LinkedList<PostingsNode> postingsList;
    public PostingsList(){
        postingsList = new LinkedList<PostingsNode>();
    }
    public void add(PostingsNode node){
        postingsList.add(node);
    }

    public void tokenCount(){
        tokenCounter++;
    }
    public int getTokenCounter(){
        return this.tokenCounter;
    }

    public boolean duplicateCheck(PostingsNode node){
        boolean checker = true;
        PostingsNode checkNode;
        Iterator iterator = postingsList.listIterator();
        while (iterator.hasNext()){
            checkNode = (PostingsNode) iterator.next();
            if(checkNode.getDocId() == node.getDocId()){
                checker = false;
                break;
            }
        }
        return checker;
    }


    public void printText(String docId){
        int count =0;
        while (count<postingsList.size()){
            if(postingsList.get(count).getDocId().contentEquals(docId)){
                System.out.println(postingsList.get(count).getText());
                break;
            }
            count++;

        }
    }
    public void printDocIdAll(){
        int count =0;
        while (count<postingsList.size()){
            System.out.println(postingsList.get(count++).getDocId());
        }
    }
}
