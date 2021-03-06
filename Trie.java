import java.util.HashMap;

class TrieNode<T> {
        private char data;
        private HashMap<Character, TrieNode<T> > children;
        private boolean terminator = false;
        private T endpoint;

        //constructor to initialize root node
        public TrieNode() {
        }

        //constructor to initialize a data node
        public TrieNode(char letter) {
                this.data = letter;
        }

        //adds a child node to the current node
        public void addChild(TrieNode<T> child) {
                if (this.children == null) {
                        this.children = new HashMap<Character, TrieNode<T> >();
                }
                this.children.put(child.data, child);
        }

        //checks if the node is a leaf node
        public boolean isLeaf() {
                if (this.children == null) {
                        return true;
                }
                return false;
        }

        //get the Endpoint
        public T getEndpoint() {
                return this.endpoint;
        }

        //check if the node is a terminator for the path
        public boolean isTerminator() {
                return this.terminator;
        }

        //sets the node as a terminator
        public void setTerminator(T endpoint) {
                this.terminator = true;
                this.endpoint = endpoint;
        }

        //check if the node is Root of the Trie
        public boolean isRoot() {
                if ((int) this.data == 0) {
                        return true;
                }
                return false;
        }

        //getter for the data item of the node
        public char getLetter() {
                return this.data;
        }

        //returns a map of all the children of the node
        public HashMap<Character, TrieNode<T> > getChildren() {
                return this.children;
        }

        //checks if the node has a particular child
        public TrieNode<T> hasChild(char letter) {
                if (this.children != null) {
                        return this.children.get(letter);
                }
                return null;
        }

}

public class Trie<T> {

        //stores the ROOT of the trie
        private TrieNode<T> ROOT;

        //creates a new trie with a root element
        public Trie() {
                this.ROOT = new TrieNode<T>();
        }

        //adds an item to the trie
        //adds the T item to the leaf at the terminator
        public void add(String pattern, T category) {

                TrieNode<T> parent = ROOT;
                TrieNode<T> current = ROOT;

                //loop through all the letters and find the common parent
                boolean createBranch = false;
                for (int i = 0; i < pattern.length(); i++) {
                        current = parent.hasChild(pattern.charAt(i));
                        if (current == null) {
                                createBranch = true;
                                for (int j = i; j < pattern.length(); j++) {
                                        TrieNode<T> node = new TrieNode<T>(pattern.charAt(j));
                                        parent.addChild(node);
                                        parent = node;
                                }
                                parent.setTerminator(category);
                                break;
                        } else {
                                parent = current;
                        }
                }

                if (!createBranch) {
                        if (!parent.isTerminator() || !parent.isLeaf()) {
                                parent.setTerminator(category);
                        }
                }
        }

        //Search for a pattern in the Trie
        public T search(String pattern) {
                TrieNode<T> node = this.ROOT;
                for (int i = 0; i < pattern.length(); i++) {

                        if (node.hasChild('*') != null){
                            return node.hasChild('*').getEndpoint();
                        }

                        //check if letter in nodes children
                        if(node.hasChild(pattern.charAt(i)) != null) {
                                node = node.hasChild(pattern.charAt(i));
                        }else{
                                return null;
                        }
                }

                //check if terminating node
                if (node.isTerminator() || node.isLeaf()) {
                        return node.getEndpoint();
                } else {
                        if (node.hasChild('*') != null) {
                                return node.hasChild('*').getEndpoint();
                        } else {
                                return null;
                        }
                }
        }

        public void printTrie() {
                printTrie(this.ROOT, "");
        }

        private void printTrie(TrieNode<T> node, String word) {
                //DFS traversal of the trie printing all words
                if (node != null) {
                        if (!node.isRoot()) {
                                word += node.getLetter();
                        }

                        //print word if terminator
                        if (node.isTerminator() || node.isLeaf()) {
                                System.out.println(word + " - " + node.getEndpoint().toString());
                        }

                        HashMap<Character, TrieNode<T> > children = node.getChildren();
                        if (children != null) {
                                for (TrieNode<T> child : children.values()) {
                                        printTrie(child, word);
                                }
                        }
                }
        }
}
