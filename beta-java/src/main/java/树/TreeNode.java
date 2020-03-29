package 树;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){}

    public TreeNode(int value){
        this.value = value;
    }

    public void store(int value){
        if(value < this.value){
            if(left == null){
                left = new TreeNode();
                left.value = value;
            }else {
                left.store(value);
            }
        }else if (value > this.value){
            if (right == null){
                right = new TreeNode();
                right.value = value;
            }else {
                right.store(value);
            }
        }
    }

    public boolean find(int value){
        if (value == this.value) {
            return true;
        }else if (value < this.value){
            if (left == null) return false;
            return left.find(value);
        }else {
            if (right == null) return false;
            return right.find(value);
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        result.add(root.value);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        result.addAll(inorderTraversal(root.left));
        result.add(root.value);
        result.addAll(inorderTraversal(root.right));
        return result;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.value);
        return result;
    }

    public void preTraverse(){
        System.out.print(this.value + ",");
        if (left != null) left.preTraverse();
        if (right != null) right.preTraverse();
    }

    public void middleTraverse(){
        if (left != null) left.middleTraverse();
        System.out.print(this.value + ",");
        if (right != null) right.middleTraverse();
    }

    public void backTraverse(){
        if (left != null) left.backTraverse();
        if (right != null) right.backTraverse();
        System.out.print(this.value + ",");
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for (int i = 0; i < data.length; i++) {
            data[i] = i+1;
            System.out.print(data[i] + ",");
        }
        System.out.println();
        TreeNode root = new TreeNode();
        root.value = data[0];
        for (int i = 1; i < data.length; i++) {
            root.store(data[i]);
        }

        System.out.println("前序：");
        System.out.println(preorderTraversal(root));
        System.out.println("中序：");
        System.out.println(inorderTraversal(root));
        System.out.println("后序：");
        System.out.println(postorderTraversal(root));
        System.out.println("-----------------------------------");
        root.find(data[19]);
        System.out.println("前序遍历");
        root.preTraverse();
        System.out.println();
        System.out.println("中序遍历");
        root.middleTraverse();
        System.out.println();
        System.out.println("后序遍历");
        root.backTraverse();
    }
}
