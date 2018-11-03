public class PostingsNode {

    private String docId;
    private String text;
// dictionary id number
    public PostingsNode (){
        this.docId = null;
        this.text = null;
    }

    public PostingsNode(String docId, String text){
        this.docId = docId;
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
    public String  getDocId(){
        return this.docId;
    }
}
