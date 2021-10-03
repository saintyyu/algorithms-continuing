package bitree;

/**
 * @description:
 * @author: saintyyu
 * @date: 2021/10/3 4:19 下午
 */
public class PreFirstBinaryTreeValidation {

    /**
     * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     *  *
     *  *      _9_
     *  *     /   \
     *  *    3     2
     *  *   / \   / \
     *  *  4   1 #  6
     *  * /\  /\  / \
     *  *# # # #  # #
     *  * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
     *  *
     *  * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
     *  *
     *  * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
     *  *
     *  * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
     *  *
     *  * 示例 1:
     *  *
     *  * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
     *  * 输出: true
     *  * 示例 2:
     *  *
     *  * 输入: "1,#"
     *  * 输出: false
     *  * 示例 3:
     *  *
     *  * 输入: "9,#,#,1"
     *  * 输出: false
     *  *
     *  * 来源：力扣（LeetCode）
     *  * 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
     *  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *  分析：对于本题目，二叉树非法的序列就是#还有左右子树的场景。所以二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
     *
     *     如果遇到了空节点，则要消耗一个槽位；
     *
     *     如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
     *
     */
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                slots--;
                i++;
            } else { // 读一个数字
                i += 2;
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }
}
