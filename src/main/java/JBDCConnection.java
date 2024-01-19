import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JBDCConnection {
        private List<Tree> listTrees = new ArrayList<Tree>();
        private List<TreeNode> listTreeNodes = new ArrayList<TreeNode>();
    public void getConnection(Connection connection){
        try {
            String[] idAndParentId = new String[2];
            String currentLine = "";
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"TREES\"");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                idAndParentId[0] = rs.getString(1);
                idAndParentId[1] = rs.getString(2);
                this.listTreeNodes.add(new TreeNode());
                listTreeNodes.get(listTreeNodes.size() - 1).setId(Integer.parseInt(idAndParentId[0]));
                listTreeNodes.get(listTreeNodes.size() - 1).setParentNode(Integer.parseInt(idAndParentId[1]));
                if (listTreeNodes.get(listTreeNodes.size() - 1).isRoot()) {
                    listTrees.add(new Tree());
                    listTrees.get(listTrees.size() - 1).setRoot(Integer.parseInt(idAndParentId[0]));
                    }
                }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public List<Tree> getListTrees(){
        return listTrees;
    }
    public List<TreeNode> getListTreeNodes(){
        return listTreeNodes;
    }
}

