package cn;

//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 908 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class SerializeAndDeserializeBinaryTree_297 {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(5, new TreeNode(6, null, new TreeNode(9)), new TreeNode(7));
        String str = codec.serialize(root);
        System.out.println(str);
        TreeNode node = codec.deserialize(str);
        System.out.println(node.val);
    }

    static //leetcode submit region begin(Prohibit modification and deletion)

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            List<String> value = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            value.add(String.valueOf(root.val));
            deque.add(root);
            int len = 1;
            for (int i = 1; i <= len; i++) {
                TreeNode cur = deque.poll();
                if (cur.left != null) {
                    value.add(String.valueOf(cur.left.val));
                    deque.offer(cur.left);
                    len++;
                } else {
                    value.add("null");
                }
                if (cur.right != null) {
                    value.add(String.valueOf(cur.right.val));
                    deque.offer(cur.right);
                    len++;
                } else {
                    value.add("null");
                }
            }
            return value.stream().map(Object::toString).collect(Collectors.joining(","));
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("".equals(data)) {
                return null;
            }
            String[] value = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(value[0]));
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);
            int index = 1;
            while (!deque.isEmpty()) {
                TreeNode cur = deque.poll();
                String leftStr = value[index++];
                if ("null".equals(leftStr)) {
                    cur.left = null;
                } else {
                    cur.left = new TreeNode(Integer.parseInt(leftStr));
                    deque.add(cur.left);
                }
                String rightStr = value[index++];
                if ("null".endsWith(rightStr)) {
                    cur.right = null;
                } else {
                    cur.right = new TreeNode(Integer.parseInt(rightStr));
                    deque.add(cur.right);
                }
            }

            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}