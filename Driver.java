import java.io.IOException;
public class Driver {
        public static void main(String[] args){
                RID rid = new RID();
                try{
                        Trie<Category> trie = rid.loadDictionaryFromFile(args[0]);
                        
                }catch(IOException ex) {
                        ex.printStackTrace();
                }
        }
}
