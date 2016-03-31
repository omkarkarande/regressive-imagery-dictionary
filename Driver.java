import java.io.IOException;
public class Driver {
        public static void main(String[] args){
                RID rid = new RID();
                try{
                        Trie<Category> dictionary = rid.loadDictionaryFromFile(args[0]);
                        Result result = rid.analyzeText("I hope all my days Will be lit by your face I hope all the years Will hold tight our promises I don't wanna be old and sleep alone An empty house is not a home I don't wanna be old and feel afraid I don't wanna be old and sleep alone An empty house is not a home I don't wanna be old and feel afraid And if I need anything at all I need a place that's hidden in the deep Where lonely angels sing you to your sleep The modern world is broken I need a place where I can make my bed A lover's lap where I can lay my head Cause now the room is spinning The day's beginning", dictionary);
                        System.out.println(result.toString());
                }catch(IOException ex) {
                        ex.printStackTrace();
                }
        }
}
