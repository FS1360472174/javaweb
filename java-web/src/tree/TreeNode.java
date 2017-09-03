package tree;

import java.util.List;

/**
 * Created by fangsheng on 2017/9/3.
 *
 * @cnstonefang@gmail.com
 */
public class TreeNode {
    private Item item;
    private List<TreeNode> children;
    public TreeNode(Item it,List<TreeNode> child){
        item = it;
        children = child;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
