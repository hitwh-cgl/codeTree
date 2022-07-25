package cn;

//完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
//
// 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。 
//
// 实现 CBTInserter 类: 
//
// 
// CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构； 
// CBTInserter.insert(int v) 向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态
//，并返回插入节点 TreeNode 的父节点的值； 
// CBTInserter.get_root() 将返回树的头节点。 
// 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//
// 
//输入
//["CBTInserter", "insert", "insert", "get_root"]
//[[[1, 2]], [3], [4], []]
//输出
//[null, 1, 2, [1, 2, 3, 4]]
//
//解释
//CBTInserter cBTInserter = new CBTInserter([1, 2]);
//cBTInserter.insert(3);  // 返回 1
//cBTInserter.insert(4);  // 返回 2
//cBTInserter.get_root(); // 返回 [1, 2, 3, 4] 
//
// 
//
// 提示： 
//
// 
// 树中节点数量范围为 [1, 1000] 
// 0 <= Node.val <= 5000 
// root 是完全二叉树 
// 0 <= val <= 5000 
// 每个测试用例最多调用 insert 和 get_root 操作 104 次 
// 
// Related Topics 树 广度优先搜索 设计 二叉树 
// 👍 82 👎 0


import java.util.ArrayDeque;
import java.util.Deque;

public class CompleteBinaryTreeInserter_919 {
    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree_297.Codec codec = new SerializeAndDeserializeBinaryTree_297.Codec();
        TreeNode root = codec.deserialize("1,2,null,null,null");
        CBTInserter cbtInserter = new CBTInserter(root);
        int parent = cbtInserter.insert(2);
        System.out.println(parent);
    }


    static //leetcode submit region begin(Prohibit modification and deletion)
    class CBTInserter {
        // store the node to be filled
        private Deque<TreeNode> deque = new ArrayDeque<>();

        private TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;
            deque.offerLast(root);
            TreeNode cur = root;
            while (cur != null) {
                if (cur.left == null) {
                    break;
                } else {
                    deque.offerLast(cur.left);
                    if (cur.right == null) {
                        break;
                    } else {
                        deque.offerLast(cur.right);
                        deque.pollFirst();
                        cur = deque.peekFirst();
                    }
                }
            }
        }

        public int insert(int val) {
            TreeNode cur = deque.peekFirst();
            if (cur.left == null) {
                cur.left = new TreeNode(val);
                deque.offerLast(cur.left);
                return cur.val;
            } else {
                cur.right = new TreeNode(val);
                deque.offerLast(cur.right);
                deque.pollFirst();
                return cur.val;
            }
        }

        public TreeNode get_root() {
            return this.root;
        }
    }

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
//leetcode submit region end(Prohibit modification and deletion)

}