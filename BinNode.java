
/**
 * Node for a binary tree of strings.
 *
 * @author (lj)
 * @version (2023)
 */
public class BinNode
{
    private String data;
    private BinNode left;
    private BinNode right;
    
    public BinNode(){
        data = "";
        left = null;
        right = null;
    }
    
    public BinNode(String d){
        data = d;
        left = null;
        right = null;
    }
    
    public void setData(String d){
        this.data = d;
    }
    public String getData(){
        return this.data;
    }
    public void setLeft(BinNode l){
        this.left = l;
    }
    public BinNode getLeft(){
        return this.left;
    }
    public void setRight(BinNode r){
        this.right = r;
    }
    public BinNode getRight(){
        return this.right;
    }
}
