package tree;

/**
 * Created by fangsheng on 2017/9/3.
 *
 * @cnstonefang@gmail.com
 */
public class Item {
    private Integer id;
    private Integer parentId;
    private String name;
    public Item(Integer id, Integer parentId,String name) {
    	this.id = id;
    	this.parentId = parentId;
    	this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
