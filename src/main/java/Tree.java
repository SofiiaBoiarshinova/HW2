import java.util.ArrayList;
import java.util.List;

public class Tree extends TreeNode{
    private int root;
    private List<Integer> nodeList = new ArrayList<>();
    private List<Integer> leavesList = new ArrayList<>();
    public void setRoot(int rootId){
        root = rootId;
    }
    public int getRoot(){return root;}
    public void addLeaf(int leaf){ leavesList.add(leaf);}
    public List<Integer> getLeavesList(){ return leavesList;}
    public void addNode(int node){ nodeList.add(node);}
    public List<Integer> getNodeList(){ return nodeList;}
}
