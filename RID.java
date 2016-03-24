import java.io.*;

public class RID{
    public RID(){

    }

    public void loadDictionaryFromFile(String filename) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line = input.readLine()) != null){
            if(tabCount(line) == 0){
            }
        }
    }

    private int tabCount(String line){
        int count = 0;
        for(char ch : line.toCharArray()){
            if(ch == '\t'){
                count++;
            }else{
                break;
            }
        }
        return count;
    }
}
