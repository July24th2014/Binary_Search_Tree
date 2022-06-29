//import java.util.*;
/**
 * Please do not modify the class/method headers.
 */
public class BinarySearchTree {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * The method for you to implement.
     */
    public boolean isBalancedBinarySearchTree(TreeNode root) {
        // TODO
        int left_height; // left sub-tree
        int right_height; //right sub-tree
        if (root == null) { // Base case
            return true;
        }

        //get height of left and right subtree
        left_height = height(root.left);
        right_height = height(root.right);
        //return the balanced and the bst
        return dfs(root, null,null)
                && Math.abs(right_height - left_height) < 2
                && isBalancedBinarySearchTree(root.right)
                && isBalancedBinarySearchTree(root.left);
    }

    //Get the height of the tree balanced
    public int height(TreeNode root){
        if(root == null){ //Base case
            return 0;
        }
        //return the height of 1 with the LARGER of left-height and right-height
        return 1 + Math.max(height(root.right), height(root.left));
    }

    //Check if the tree is binary search tree
    public boolean dfs(TreeNode root, Integer max, Integer min) {
        if (root == null) { //Base case
            return true;
        }
        if (min != null && root.value <= min || max != null && root.value >= max) {
            return false;
        } else {//Update min and max
            return dfs(root.left, root.value, min) && dfs(root.right, max, root.value);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree solution = new BinarySearchTree();

        /*
         *            3
         *           / \            True
         *          2   4
         */
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(4);

        System.out.println(solution.isBalancedBinarySearchTree(root1)); // Should be true

        /*
         *            3
         *           / \
         *          2   4
         *               \         False since is not balanced
         *                6
         *                 \
         *                  7
         */
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(4);
        root2.right.right = new TreeNode(6);
        root2.right.right.right = new TreeNode(7);

        System.out.println(solution.isBalancedBinarySearchTree(root2)); // Should be false

        /*
         *            3
         *           / \
         *          2   4         False since is not a binary search tree
         *               \
         *                1
         */
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(4);
        root3.right.right = new TreeNode(1);

        System.out.println(solution.isBalancedBinarySearchTree(root3)); // Should be false
    }
}
