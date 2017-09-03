package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangsheng on 2017/9/3.
 *
 * @cnstonefang@gmail.com
 */
public class ListToTree {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1,0,"level-1"));
        items.add(new Item(2,0,"level-1-2"));
        items.add(new Item(11,1,"level2-1"));
        items.add(new Item(111,1,"level2-1-1"));
        items.add(new Item(110,11,"level2-1-1"));
        items.add(new Item(22,2,"level-2-2"));
        List<TreeNode> tree =build(items);
        System.out.println(tree);
    }

    private static List<TreeNode> build(List<Item> items) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (Item item : items) {
            TreeNode treeNode = new TreeNode(item,null);
            if (item.getParentId().intValue() == 0) {
                trees.add(treeNode);
            }

            for (Item it : items) {
                if (it.getParentId() == item.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(new TreeNode(it,null));
                }
            }
        }
        return trees;
    }
}
