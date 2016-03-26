
import java.util.*;
import java.util.ArrayList;

public class Result {

        private HashMap<Category, ArrayList<String> > resultTree;

        public Result(){
                this.resultTree = new HashMap<Category, ArrayList<String> >();
        }

        public void addCategory(Category category, String token){
                if (resultTree.get(category) == null) {
                        resultTree.put(category, new ArrayList<String>());
                }
                resultTree.get(category).add(token);
        }

        public String toString(){
                StringBuilder serialized = new StringBuilder("");
                for (Map.Entry<Category, ArrayList<String> > entry : this.resultTree.entrySet()) {
                        serialized.append(entry.getKey().toString() + "---\n" + entry.getValue().toString() + "\n\n");
                }

                return serialized.toString();
        }
}
