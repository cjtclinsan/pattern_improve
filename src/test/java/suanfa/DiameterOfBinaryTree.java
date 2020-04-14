package suanfa;

/**
 * @author taosh
 * @create 2020-03-10 10:19
 */
public class DiameterOfBinaryTree {
    static int res = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private static int dfs(TreeNode root) {
        if ( root == null ){
            return 0;
        }

        System.out.println(root.left+"   "+root.right);

        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        res = Math.max(res, leftDepth+rightDepth);
        int d = Math.max(leftDepth, rightDepth);
        return d+1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
