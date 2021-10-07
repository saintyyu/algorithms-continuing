package bitree;

import java.util.Arrays;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/3 10:45 上午
 *
 * 已知先序遍历和中序遍历，重建二叉树
 */
public class RebuildBinaryTreeWithPreAndMiddle {

    /**
     * 递归思路: 先序遍历的每一个值在中序遍历中都会被二分成两半，所以每次根据先序遍历的pre[0]，将中序遍历的数组二分。
     */
    public BiTree reconstructBinaryTree(int[] pre, int[] mid) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        BiTree root = new BiTree(pre[0]);
        for (int i = 0; i< mid.length; i++) {
            if (mid[i] == pre[0]) {
                root.left = reconstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(mid, 0, i));
                root.right = reconstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(mid, i + 1, mid.length));
                break;
            }
        }
        return root;
    }

    /**
     * 方法一需要不断copy数组，这里通过计数的方式，来避免copy数组
     * @param pre
     * @param mid
     * @return
     */
    public static BiTree reconstructBinaryTree_2(int[] pre, int[] mid) {
        return rebuild(pre, 0, pre.length-1, mid, 0, mid.length-1);
    }

    public static BiTree rebuild(int[] pre, int preStart, int preEnd, int[] mid, int midStart, int midEnd) {
        if (preStart > preEnd) {
            return null;
        }

        BiTree root = new BiTree(pre[preStart]);
        for (int i = midStart; i <= midEnd; i++) {
            if (mid[i] == root.val) {
                root.left = rebuild(pre, preStart+1, preStart+i-midStart, mid, midStart, i-1);
                root.right = rebuild(pre, preStart+i-midStart+1, preEnd, mid, i+1, midEnd);
                break;
            }
        }
        return root;
    }

}
