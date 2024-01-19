import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private int id;
    private List<Integer> childrenList = new ArrayList<>();
    private int parentNode;

    public void setId(int idNode){
        id = idNode;
    }
    public int getId(){
        return id;
    }
    public void setParentNode(int idParentNode){
        parentNode = idParentNode;
    }
    public int getParentNode(){
        return parentNode;
    }
    public Boolean isLeaf(){
        return (childrenList.isEmpty());
    }
    public Boolean isRoot(){
        return (id == parentNode);
    }
    public void addChild(int idChild){
        childrenList.add(idChild);
    }
    public List<Integer> getChildrenList(){
        return childrenList;
    }
}
