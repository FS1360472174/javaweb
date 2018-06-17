package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author cnstonefang@gmail.com
 */
public class SimpleTree {

    public static  void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(5);
        treeNode.setLeft(treeNode1);
        treeNode.setRight(treeNode2);
        treeNode1.setRight(treeNode3);
        System.out.println(binaryTreePaths(treeNode));
    }

    /**
     * 找出树的路径
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
       Stack<TreeNode> stack = new Stack<TreeNode>();
       Stack<Integer> path = new Stack<Integer>();
       while (true) {
           while (root != null) {
               System.out.println(root.getValue());
               path.push(root.getValue());
               stack.push(root);
               root = root.getLeft();
           }
           if (stack.isEmpty()) {
               break;
           }
           root = stack.pop();
           root = root.getRight();
       }
       return new ArrayList<String>();
    }
}
