package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/4/18 11:00 下午
 */
public class BiTree {
    int val;
    BiTree left;
    BiTree right;

    public BiTree(int val) {
        this.val = val;
    }

    public BiTree getLeft() {
        return left;
    }

    public void setLeft(BiTree left) {
        this.left = left;
    }

    public BiTree getRight() {
        return right;
    }

    public void setRight(BiTree right) {
        this.right = right;
    }
}
