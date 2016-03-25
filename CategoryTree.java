import java.util.ArrayList;
import java.util.HashMap;

class CategoryNode {
        private String CategoryName;
        private HashMap<String, CategoryNode> children;
        private ArrayList<String> words;

        public CategoryNode(String name){
                this.CategoryName = name;
        }

        public String getName(){
                return this.CategoryName;
        }

        public void addChild(CategoryNode node){
                if (this.children == null) {
                        children = new HashMap<String, CategoryNode>();
                }
                children.put(node.CategoryName, node);
        }

        public HashMap<String, CategoryNode> getChildren(){
                return this.children;
        }

        public void addWord(String word){
                if (this.words == null) {
                        words = new ArrayList<String>();
                }
                words.add(word);
        }

        public String getWords(){
                if (this.words != null) {
                        StringBuilder wordString = new StringBuilder();
                        for(String word : this.words) {
                                wordString.append(word);
                                wordString.append(", ");
                        }
                        return wordString.substring(0, wordString.length() - 2);
                }
                return "";
        }
        public boolean isLeaf(){
                if (children == null) {
                        return true;
                }
                return false;
        }

        public boolean isRoot(){
                return this.CategoryName.compareToIgnoreCase("root") == 0;
        }

        public CategoryNode hasChild(String name){
                if (children != null) {
                        CategoryNode node = children.get(name);
                        if (node != null) {
                                return node;
                        }
                }
                return null;
        }
}

public class CategoryTree {
        private CategoryNode ROOT;

        public CategoryTree(){
                this.ROOT = new CategoryNode("ROOT");
        }

        public void add(String categoryPattern, String word){
                //split the categoryPattern at ':'
                String[] path = categoryPattern.split(":");
                CategoryNode node = this.ROOT;
                CategoryNode current = this.ROOT;
                for(int i = 0; i < path.length; i++) {
                        current = node.hasChild(path[i]);
                        if (current == null) {
                                for(int j = i; j < path.length; j++) {
                                        CategoryNode child = new CategoryNode(path[i]);
                                        node.addChild(child);
                                        node = child;
                                }
                        }else{
                                node = current;
                        }
                }

                //add words to node
                node.addWord(word);
        }

        public String toString(){
                return serialize(this.ROOT);
        }

        //fix it later
        private String serialize(CategoryNode node){
                if (node == null) {
                        return "";
                }
                if (node.isLeaf()) {
                        return node.getName() + "\n" + node.getWords() + "\n";
                }

                HashMap<String, CategoryNode> children = node.getChildren();
                if (children != null) {
                        for(CategoryNode child : children.values()) {
                                if (node.isRoot()) {
                                        return serialize(child);
                                }else{
                                        return node.getName() + ":" + serialize(child);
                                }
                        }
                }
                return "";
        }
}
