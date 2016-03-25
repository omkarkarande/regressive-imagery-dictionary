import java.io.IOException;
public class Driver {
        public static void main(String[] args){
                RID rid = new RID();
                try{
                        Trie<Category> dictionary = rid.loadDictionaryFromFile(args[0]);
                        Result result = rid.analyzeText("ambrosial", dictionary);
                        System.out.println(result.toString());
                }catch(IOException ex) {
                        ex.printStackTrace();
                }
        }
}
