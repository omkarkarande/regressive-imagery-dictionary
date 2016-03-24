import java.util.HashMap;

class TrieNode<T>{
    private char data;
    private HashMap<Character,TrieNode<T>> children;
    private boolean terminator = false;
    private T endpoint;
    //constructor to initialize root node
    public TrieNode(){
    }

    //constructor to initialize a data node
    public TrieNode(char letter){
        this.data = letter;
    }

    //adds a child node to the current node
    public void addChild(TrieNode<T> child){
        if (this.children == null){
            this.children = new HashMap<Character, TrieNode<T>>();
        }
        this.children.put(child.data, child);
    }

    //checks if the node is a leaf node
    public boolean isLeaf(){
        if (this.children == null){
            return true;
        }
        return false;
    }

    //
    public void setTerminator(boolean value){
        this.terminator = value;
    }

    public boolean isTerminator(){
        return this.terminator;
    }

    public boolean isRoot(){
        if ((int)this.data == 0){
            return true;
        }
        return false;
    }

    public char getLetter(){
        return this.data;
    }

    public HashMap<Character, TrieNode<T>> getChildren(){
        return this.children;
    }

    public TrieNode<T> hasChild(char letter){
        if (this.children != null){
            return this.children.get(letter);
        }
        return null;
    }

}

public class Trie<T>{

    private TrieNode<T> ROOT;

    public Trie(){
        this.ROOT = new TrieNode<T>();
    }

    public void add(String pattern){
        TrieNode<T> parent = ROOT;
        TrieNode<T> current = ROOT;
        //loop through all the letter and find the common parent
        for (int i = 0; i < pattern.length(); i++){
            current = parent.hasChild(pattern.charAt(i));
            if (current == null){
                for (int j = i; j < pattern.length(); j++){
                    TrieNode<T> node = new TrieNode<T>(pattern.charAt(j));
                    parent.addChild(node);
                    parent = node;
                }
                parent.setTerminator(true);
                break;
            }else{
                parent = current;
            }
        }
    }

    public boolean search(String pattern){
        TrieNode<T> parent = this.ROOT;
        for (int i = 0; i<pattern.length(); i++){
            if (parent.hasChild(pattern.charAt(i)) == null){
                return false;
            }
            parent = parent.hasChild(pattern.charAt(i));
        }
        return true;
    }

    public void printTrie(){
        printTrie(this.ROOT, "");
    }

    private void printTrie(TrieNode<T> node, String word){
        //DFS traversal of the trie printing all words
        if (node != null){
            if (!node.isRoot()){
                word += node.getLetter();
            }

            //print word if terminator
            if (node.isTerminator() || node.isLeaf()){
                System.out.println(word);
            }

            HashMap<Character, TrieNode<T>> children = node.getChildren();
            if (children != null){
                for(TrieNode<T> child: children.values()){
                    printTrie(child, word);
                }
            }
        }
    }
}
