package models;

public class NodeTrie {
    private final NodeTrie[] children;
    private boolean isWord;
    private final LinkedList<Integer> positions;

    public NodeTrie(){
        children = new NodeTrie[26];
        isWord = false;
        positions = new LinkedList<>();
    }

    public NodeTrie[] getChildren(){
        return children;
    } 

    public boolean isAWord(){
        return isWord;
    }
    
    public void setIsWord(boolean isWord){
        this.isWord = isWord;
    }

    public NodeTrie getChild(char c){
        return children[c - 'a'];
    }
    
    public void setChild(char c, NodeTrie child){
        this.children[c - 'a'] = child;
    }

    public LinkedList<Integer> getPositions() {
        return positions;
    }
}
