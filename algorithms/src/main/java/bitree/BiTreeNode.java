package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/9/30 10:27 下午
 */
public class BiTreeNode extends BiTree{
    private BiTreeNode parent;

    public BiTreeNode getParent() {
        return parent;
    }

    public void setParent(BiTreeNode parent) {
        this.parent = parent;
    }
}
