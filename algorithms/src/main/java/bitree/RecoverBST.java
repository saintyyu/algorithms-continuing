package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/30 12:15 上午
 *
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecoverBST {

    /**
     * 思路：
     * 我们需要考虑两个节点被错误地交换后对原二叉搜索树造成了什么影响。对于二叉搜索树，我们知道如果对其进行中序遍历，得到的值序列是递增有序的，而如果我们错误地交换了两个节点，等价于在这个值序列中交换了两个值，破坏了值序列的递增性。
     *
     * 我们来看下如果在一个递增的序列中交换两个值会造成什么影响。假设有一个递增序列 a=[1,2,3,4,5,6,7]a=[1,2,3,4,5,6,7]a=[1,2,3,4,5,6,7]。如果我们交换两个不相邻的数字，例如
     * 222 和 666，原序列变成了 a=[1,6,3,4,5,2,7]a=[1,6,3,4,5,2,7]a=[1,6,3,4,5,2,7]，那么显然序列中有两个位置不满足 ai<ai+1a_i<a_{i+1}ai​
     * <ai+1​，在这个序列中体现为 6>36>36>3，5>25>25>2，因此只要我们找到这两个位置，即可找到被错误交换的两个节点。如果我们交换两个相邻的数字，例如 222 和 333，此时交换后的序列只有一个位置不满足 ai<ai+1a_i<a_{i+1}ai​<ai+1​。因此整个值序列中不满足条件的位置或者有两个，或者有一个。
     *
     * 至此，解题方法已经呼之欲出了：
     *
     */
    //用两个变量x，y来记录需要交换的节点
    private BiTree x = null;
    private BiTree y = null;
    private BiTree pre = null;
    public void recoverTree(BiTree root) {
        dfs(root);
        //如果x和y都不为空，说明二叉搜索树出现错误的节点，将其交换
        if(x!=null && y!=null) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    //中序遍历二叉树，并比较上一个节点(pre)和当前节点的值，如果pre的值大于当前节点值，则记录下这两个节点
    private void dfs(BiTree node) {
        if(node==null) {
            return;
        }
        dfs(node.left);
        if (pre != null) {
            if (pre.val > node.val) {
                y = node;
                if (x == null) {
                    x = pre;
                }
            }
        }
        pre = node;
        dfs(node.right);
    }

}
