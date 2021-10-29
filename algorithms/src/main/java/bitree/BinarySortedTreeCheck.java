package bitree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/29 11:17 下午
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 *     节点的左子树只包含 小于 当前节点的数。
 *     节点的右子树只包含 大于 当前节点的数。
 *     所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinarySortedTreeCheck {

    /**
     * 思路一：采用先序遍历，则遍历的结果一定是有序的，所以对于遍历后的结果，如果不是有序的，那么就一定不是合法的二叉搜索树
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param root
     * @return
     */
    public boolean isValidBST_1(BiTree root) {

        if(root == null || (root.left == null && root.right == null)) {
            return true;
        }
        LinkedList<Integer> list = new LinkedList<>();
        preFirstTrace(list, root);
        Iterator<Integer> iterator = list.iterator();
        int last = Integer.MIN_VALUE;
        boolean first = true;
        while (iterator.hasNext()) {
            Integer tmp = iterator.next();
            if (last >= tmp && !first) {
                return false;
            }
            first = false;
            last = tmp;
        }
        return true;
    }


    /**
     * 思路二：思路一是将先序遍历和判断分开来进行，所以空间复杂度是O(n)，思路二是将两者合并起来：根据二叉搜索树的特点：root为左子树的最大值，右子树的最小值，
     * 所以在递归的过程中，用两个值来记录即可。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(BiTree root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(BiTree node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }



    public boolean preFirstTraceWithMaxVal(BiTree root) {
        boolean flag = true;
        if(root == null) {
            return flag;
        }
        if (root.left != null) {
            if(root.left.val >= root.val) {
                return false;
            }
            flag = isValidBST(root.left);
        }
        if(!flag) {
            return false;
        }

        if(root.right != null) {
            if(root.right.val <= root.val) {
                return false;
            }
            flag = isValidBST(root.right);
        }
        if(!flag) {
            return false;
        }
        return flag;
    }


    /**
     * description:  先序遍历
     *
     * @param
     * @return
     */
    public void preFirstTrace(LinkedList<Integer> list, BiTree root) {
        if(root == null) {
            return;
        }
        preFirstTrace(list, root.left);
        list.add(root.val);
        preFirstTrace(list, root.right);
    }
}
