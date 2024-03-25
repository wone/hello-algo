package chapter_tree;

import utils.PrintUtil;
import utils.TreeNode;

import java.util.*;

/**
 * 广度优先遍历
 */
public class zigzag_tree_traversal {
    public static void main(String[] args) {
        /* 初始化二叉树 */
        // 这里借助了一个从数组直接生成二叉树的函数
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,13,14,15));
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(root);

//        /* 层序遍历 */
//        List<Integer> list = levelOrder(root);
        System.out.println("\n层序遍历的节点打印序列  " );

        printTreeByLevelOrder(root);

        System.out.println("\n zigzag 遍历的节点打印序列 " );
        List<List<Integer>> result = zigzagLevelOrder(root);
        for (List<Integer> level : result) {
            for (Integer val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

//        zigzagLevelOrder2(root);
    }
    // 层序遍历 tree
    public static void printTreeByLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            System.out.println(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    
    /**
     * Zigzag遍历（也称之为蛇形遍历）是一种特殊的树遍历方式，它按层遍历树的节点，但在每一层上，节点的访问顺序会发生变化。
     * 在奇数层（从根节点开始计数），我们按从左到右的顺序访问节点；在偶数层，我们按从右到左的顺序访问节点。
     *
     * 给定一个完全二叉树，我们可以使用以下解题思路来实现Zigzag遍历：
     *
     * 1. 使用两个栈（stack1和stack2）来分别存储当前层和下一层的节点。初始时，将根节点压入stack1。
     *
     * 2. 使用一个布尔变量`left_to_right`来表示当前层的遍历顺序。对于奇数层，`left_to_right`为`true`；对于偶数层，`left_to_right`为`false`。初始时，将`left_to_right`设置为`true`。
     *
     * 3. 当stack1不为空时，执行以下步骤：
     *
     *    a. 弹出stack1的顶部节点，并访问该节点。
     *
     *    b. 如果`left_to_right`为`true`，则首先将该节点的左子节点压入stack2，然后将右子节点压入stack2。如果`left_to_right`为`false`，则首先将该节点的右子节点压入stack2，然后将左子节点压入stack2。
     *
     *    c. 如果stack1为空（即已访问完当前层的所有节点），则交换stack1和stack2（即将下一层的节点设置为当前层），并将`left_to_right`的值取反。
     *
     * 4. 重复步骤3，直到stack1为空且stack2为空（即已访问完所有节点）。
     *
     * 这种解题思路的关键在于使用两个栈来分别存储当前层和下一层的节点，并根据`left_to_right`变量来确定在每一层上访问节点的顺序。通过交替地改变遍历顺序，我们可以实现Zigzag遍历。
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 存当前层
        Stack<TreeNode> stack1 = new Stack<>();
        // 存下一层
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        boolean leftToRight = true;

        while (!stack1.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            // 循环遍历当前层，并将当前层的子节点加入到 stack2
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                currentLevel.add(node.val);

                if (leftToRight) {
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                }
            }
            result.add(currentLevel);
            // stack1 为空说明，当前层遍历完毕，需要重置leftToRight
            leftToRight = !leftToRight;
            // 将存储下一层节点的 stack 和上一层 stack 交换下
            Stack<TreeNode> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
        }

        return result;
    }
}
