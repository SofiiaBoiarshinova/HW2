import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Tree{
    static final String DB_URL_POSTGRESQL = "jdbc:postgresql://localhost/treeDB";
    static final String DB_URL_H2 = "jdbc:h2:~/treeDB";
    static final String USER = "userTree";
    static final String PASS = "pass";
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        List<Tree> listTrees;
        List<TreeNode> listTreeNodes;
        System.out.println("Введите 1 для PostgreSQL, 2 для H2");
        Scanner in = new Scanner(System.in);
        int chooseDB = in.nextInt();
        switch (chooseDB){
            case 1:
                jdbc_postgresql treeDB = new jdbc_postgresql(DB_URL_POSTGRESQL, USER, PASS);
                JBDCConnection jdbcPostgreSQL = new JBDCConnection();
                jdbcPostgreSQL.getConnection(treeDB.connection);
                listTrees = jdbcPostgreSQL.getListTrees();
                listTreeNodes = jdbcPostgreSQL.getListTreeNodes();
                break;
            case 2:
                jdbc_h2 treeDBB = new jdbc_h2(DB_URL_H2, USER, PASS);
                JBDCConnection jdbcH2 = new JBDCConnection();
                jdbcH2.getConnection(treeDBB.connection);
                listTrees = jdbcH2.getListTrees();
                listTreeNodes = jdbcH2.getListTreeNodes();
                break;
            default:
               throw new RuntimeException("Нет допустимого варианта БД");
        }

        //Находим дочерние узлы каждого узла
        for (int i = 0; i < listTreeNodes.size(); i++){
            for(int j = 0; j < listTreeNodes.size(); j++){
                if(i != j){
                    if(listTreeNodes.get(i).getId() == listTreeNodes.get(j).getParentNode()){
                        listTreeNodes.get(i).addChild(listTreeNodes.get(j).getId());
                    }
                }
            }
        }
        int currentRoot;
        List<Integer> currentListOfNodes;
        List<Integer> usedNodes = new ArrayList<>();
        usedNodes.add(-1);
   //    Создание деревьев
        for(Tree tree : listTrees){
            currentRoot = tree.getRoot();
            for(TreeNode node : listTreeNodes){
                if (currentRoot == node.getId()){
                    for(int elemNode : node.getChildrenList()){
                        tree.addNode(elemNode);
                    }
                    break;
                }
            }
            currentListOfNodes = tree.getNodeList();
            int node = -1;
            boolean flag = true;
                while (flag){
                    flag = false;
                    for(int node0: currentListOfNodes){
                        if (usedNodes.contains(node0)){
                            continue;
                        }
                        else{
                            node = node0;
                            usedNodes.add(node);
                            flag = true;
                            break;
                        }

                    }
                    if(!flag){
                        break;
                    }

                    for(TreeNode currentNode : listTreeNodes){
                        if (currentNode.getId() == node){
                            if (currentNode.getChildrenList().isEmpty()){
                                tree.addLeaf(node);
                            }else{
                                for (int newNode : currentNode.getChildrenList()){
                                    tree.addNode(newNode);
                                }
                            }
                            break;
                        }
                    }
                    currentListOfNodes = tree.getNodeList();
                }
        }
        //Нахождение количества листьев и вывод в файл
        int countLeaves = 0;
        FileOutputStream fos = new FileOutputStream("src/main/java/output.csv");
        for (Tree tree : listTrees){
                countLeaves += tree.getLeavesList().size();
        }
        fos.write((countLeaves+"").getBytes());
        fos.flush();
        fos.close();
    }

}