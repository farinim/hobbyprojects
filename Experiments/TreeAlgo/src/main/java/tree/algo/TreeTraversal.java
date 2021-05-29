package tree.algo;

import tree.model.Node;

import java.util.Stack;

public class TreeTraversal {
    public static void inorder( Node root){
        if(root == null){
            return;
        }
        inorder(root.getLeft());
        System.out.println(root.getData());
        inorder(root.getRight());
    }

    private static void push(Stack<Node> stack, Node startNode){
        while(startNode != null){
            stack.push(startNode);
            startNode = startNode.getLeft();
        }
    }
    public static void inorderiterative(Node root){
        Stack<Node> stack = new Stack<>();
        Node temp = null;

        push(stack,root);
        while(!stack.empty()){
            temp = stack.pop();
            System.out.println(temp.getData());
            if(temp.getRight() != null){
                push(stack,temp.getRight());
            }
        }
    }
}
