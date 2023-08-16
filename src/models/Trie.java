package models;

public class Trie {
    private NodeTrie root;
    private String fileName;

    public Trie(String filename){
        root = new NodeTrie();
        this.fileName = filename;
    }

    public void insert(String word, int position){
        NodeTrie aux = root;
        for(char c : word.toCharArray() ){
            if(aux.getChild(c) == null){
                aux.setChild(c, new NodeTrie());
            }
            aux = aux.getChild(c);
        }
        aux.setIsWord(true);
        aux.getPositions().insert(position);
    }

    public String getFileName() {
        return fileName;
    }

    public boolean search(String word){
        NodeTrie aux = root;
        // recorrer segun los caracteres de word
        for(char c : word.toCharArray()){
            aux = aux.getChild(c);
            if(aux == null)
                return false;
        }
        // si no una palabras
        return aux.isAWord();
    }
    
    public NodeTrie found(String word){
        NodeTrie aux = root;
        // recorrer segun los caracteres de word
        for(char c : word.toCharArray()){
            aux = aux.getChild(c);
            if(aux == null)
                return null;
        }
        // si no una palabras
        return aux.isAWord()?aux:null;
    }

    @Override
    public String toString() {
        return this.fileName;
    }
}
