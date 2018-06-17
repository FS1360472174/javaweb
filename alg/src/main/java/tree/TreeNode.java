package tree;

/**
 * @author cnstonefang@gmail.com
 */
public class TreeNode {
    private TreeNode right;

    private TreeNode left;

    private int value;
    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(final TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(final TreeNode left) {
        this.left = left;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }
}
