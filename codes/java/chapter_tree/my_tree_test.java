package chapter_tree;

import utils.PrintUtil;
import utils.TreeNode;

import java.util.*;

/**
 * 广度优先遍历
 */
public class my_tree_test {
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

//    public static void printTreeByZigzagOrder(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        while (!queue.isEmpty()) {
//            TreeNode node = (TreeNode) queue.poll();
//            System.out.println(node.val);
//
//            if (node.left != null) {
//                queue.add(node.left);
//            }
//
//            if (node.right != null) {
//                queue.add(node.right);
//            }
//        }
//    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        boolean leftToRight = true;

        while (!stack1.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
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
            leftToRight = !leftToRight;
            Stack<TreeNode> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
        }

        return result;
    }
}
