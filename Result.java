public class Result{

    private CategoryTree resultTree;

    public Result(){
        this.resultTree = new CategoryTree();
    }

    public void addCategory(Category category, String token){
        this.resultTree.add(category.toString(), token);
    }

    public String toString(){
        return this.resultTree.toString();
    }
}
