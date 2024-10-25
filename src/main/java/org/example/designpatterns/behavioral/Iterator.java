package org.example.designpatterns.behavioral;

// provides a method to access elements sequentially for a collection of objects

import java.util.Stack;

interface IteratorI{
    boolean hasNext();
    TreeNode next();
}
interface TreeIterator {
    IteratorI createInorderTreeIterator();
}
class TreeNode{
    int val;
    TreeNode left, right;
    TreeNode(int val){
        this.val = val;
        left = null;
        right = null;
    }
}

class BSTTree implements TreeIterator{
    TreeNode root ;
    public BSTTree(){
        root = null;
    }

    public void add(int val){
        root = addRecursively(root,val);
    }
    private TreeNode addRecursively(TreeNode root, int val){
        if(root == null)
            return new TreeNode(val);
        else if(root.val < val){
            root.right = addRecursively(root.right, val);
        }else {
            root.left = addRecursively(root.left, val);
        }
        return  root;
    }

    public void PrintInorder(TreeNode root){
        if(root == null)
            return;
        PrintInorder(root.left);
        System.out.println(root.val);
        PrintInorder(root.right);
    }

    private class InorderIterator implements IteratorI{
        private Stack<TreeNode> stack;
        InorderIterator(TreeNode root){
            stack = new Stack<>();
            moveLeft(root);
        }

        private void moveLeft(TreeNode current)
        {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            if(!hasNext())
                throw  new RuntimeException();
            TreeNode node = stack.pop();
            moveLeft(node.right);
            return node;
        }
    }

    @Override
    public IteratorI createInorderTreeIterator() {
        return new InorderIterator(root);
    }
}

public class Iterator {
    public static void main(String[] args) {
        BSTTree tree = new BSTTree();
        tree.add(5);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.add(1);
        tree.add(8);
        tree.add(6);
        tree.PrintInorder(tree.root);
        System.out.println("//");
        IteratorI itr = tree.createInorderTreeIterator();
        while (itr.hasNext()){
            System.out.println(itr.next().val);
        }
    }
}
