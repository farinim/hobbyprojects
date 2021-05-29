package tree.demo;

import tree.algo.TreeTraversal;
import tree.model.Node;

public class TraversalDemo {
    public static void main(String[] args){

        Node<Integer> leaf1 = new Node<>(1,null,null);
        Node<Integer> leaf2 = new Node<>(3,null,null);
        Node<Integer> leaf3 = new Node<>(5,null,null);
        Node<Integer> leaf4 = new Node<>(7,null,null);

        Node<Integer> lchild = new Node<>(2, leaf1, leaf2);
        Node<Integer> rchild = new Node<>(6,leaf3,leaf4);

        Node<Integer> root = new Node<>(4,lchild,rchild);

        TreeTraversal.inorderiterative(root);

    }
}
