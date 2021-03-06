import java.io.*;
public class RID {
        public RID(){
        }

        //Load dictionary from a file
        public Trie<Category> loadDictionaryFromFile(String filename) throws IOException {
                Trie<Category> trie = new Trie<Category>();
                BufferedReader input = new BufferedReader(new FileReader(filename));
                String line = null;
                int prev_tab_count = 0;
                Category category = new Category();
                while((line = input.readLine()) != null) {
                        int tabCount = tabCount(line);

                        if(tabCount == 0) {
                                category = new Category(line.trim());
                                prev_tab_count = tabCount;
                        }

                        if(tabCount == 1) {
                                //check if it came back from ahead
                                if (prev_tab_count > tabCount) {
                                        // create a new category
                                        category = new Category(category.getPrimary(), line.trim());
                                }else{
                                        category.setSecondary(line.trim());
                                }
                                prev_tab_count = tabCount;
                        }

                        if (tabCount == 2) {
                                //could be a category or a pattern
                                if(line.contains("(")) {
                                        // add an item to trie
                                        String pattern = line.trim().split(" ")[0].toLowerCase();
                                        trie.add(pattern, category);
                                        prev_tab_count = tabCount;
                                }else{
                                        //check if it came back from ahead
                                        if (prev_tab_count > tabCount) {
                                                // create a new category
                                                category = new Category(category.getPrimary(), category.getSecondary(), line.trim());
                                        }else{
                                                category.setTertiary(line.trim());
                                        }
                                        prev_tab_count = tabCount;
                                }
                        }

                        if (tabCount == 3) {
                                // add an item to trie
                                String pattern = line.trim().split(" ")[0].toLowerCase();
                                trie.add(pattern, category);
                                prev_tab_count = tabCount;
                        }
                }
                return trie;
        }
        //analyze text
        public Result analyzeText(String buffer, Trie<Category> dictionary){
            //tokenize text based on whitespaces.
            String[] tokens = buffer.trim().split(" ");
            Result result = new Result();
            for(String token: tokens){
                Category category = dictionary.search(token.toLowerCase());
                if (category != null){
                    result.addCategory(category, token);
                }
            }
            return result;
        }
        /*utility functions*/
        private int tabCount(String line){
                int count = 0;
                for(char ch : line.toCharArray()) {
                        if(ch == '\t') {
                                count++;
                        }else{
                                break;
                        }
                }
                return count;
        }
}
