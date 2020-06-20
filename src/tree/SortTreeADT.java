package tree;

import java.nio.BufferUnderflowException;

public class SortTreeADT {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 0, 2, 4, 6, 8};
        SortTreeADT sortTreeAdt = new SortTreeADT();
        for (int ele: arr)
            sortTreeAdt.insert(ele);
        sortTreeAdt.printTree();
        sortTreeAdt.insert(10);
        sortTreeAdt.printTree();
        sortTreeAdt.remove(8);
        sortTreeAdt.printTree();

    }

    private static class Node{
        int val;
        Node left;
        Node right;

//        Node(int val) {
//            this.val = val;
//        }

        Node(int val){
            this(val, null, null);
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "val = " + val;
        }
    }


    private Node root;

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(int val){
        return contains(val, root);
    }

    public void insert(int val){
        root = insert(val, root);
    }

    public void remove(int val){
        root = remove(val, root);
    }

    public void printTree(){
        if (isEmpty()){
            System.out.println("Empty Tree");
        } else {
            System.out.println("prefixOrder: ");
            prefixOrder(root);
            System.out.println();
            System.out.println("infixOrder: ");
            infixOrder(root);
            System.out.println();
            System.out.println("suffixOrder: ");
            suffixOrder(root);
            System.out.println();
        }
    }

    public int findMin(){
        if (isEmpty())
            throw new BufferUnderflowException();
        return finMin(root).val;
    }

    public int findMax(){
        if (isEmpty())
            throw new BufferUnderflowException();
        return finMax(root).val;
    }

//        private boolean contains(int val, Node t){
//            if (t == null)
//                return false;
//            int compRes = val.compareTo(t.val);
//            if (compRes < 0)
//                return contains(val, t.left);
//            else if (compRes > 0){
//                return contains(val, t.right);
//            } else
//                return true;
//        }

//        private Node contains(int val){
//            if (val == this.val){
//                return this;
//            } else if (val < this.val){
//                if (this.left == null){
//                    System.out.println("the node is not existed");
//                    return null;
//                } else {
//                    return this.left.contains(val);
//                }
//            } else {
//                if (this.right == null){
//                    System.out.println("the node is not existed");
//                    return null;
//                } else{
//                    return this.right.contains(val);
//                }
//            }
//        }

//        public void insert(Node node){
//            if (node == null){
//                return;
//            }
//            if (node.val < this.val){
//                if (this.left == null){
//                    this.left =node;
//                } else {
//                    this.left.insert(node);
//                }
//            } else {
//                if (this.right == null) {
//                    this.right = node;
//                } else {
//                    this.right.insert(node);
//                }
//            }
//        }

    private boolean contains(int val, Node t){
        if (t == null)
            return false;
        if (val < t.val)
            return contains(val, t.left);
        else if (val > t.val){
            return contains(val, t.right);
        } else
            return true;
    }

    private Node insert (int val, Node t){
        if (t == null){
            return new Node(val, null, null);
        }
        if (val < t.val){
            t.left = insert(val, t.left);
        } else if (val > t.val) {
            t.right = insert(val, t.right);
        } else {
            ;
        }
        return t;
    }

    private Node remove(int val, Node t) {
//            Node node = contains(val);
        if (t == null) {
            System.out.println("the node is not existed");
            return null;
        } else if (t.right != null && t.left != null) {
            t.val = finMin(t.right).val;
            t.right = remove(val, t.right);
        } else if (val < t.val){
            t.right = remove(val, t.left);
        } else if (val > t.val) {
            t.left = remove(val, t.right);
        } else {
            t = t.left != null ? t.left : t.right;
        }
        return t;
    }

    private Node finMin(Node root){
        if (root == null){
            return null;
        } else if (root.left == null){
            return root;
        } else {
            return finMin(root.left);
        }
    }

    private Node finMax(Node root){
        if (root != null){
            while (root.right != null){
                root = root.right;
            }
        }
        return root;
    }

    //用深度标记每一个结点（输出目录）
//    public void prefixOrder(){
//        if (this.left != null){
//            this.left.prefixOrder();
//        }
//        System.out.println(this);
//        if (this.right != null){
//            this.right.prefixOrder();
//        }
//    }

    public void prefixOrder(Node t){
        if (t != null){
            prefixOrder(t.left);
            System.out.print(t.val + " ");
            prefixOrder(t.right);
        }
    }

    //顺序输出（查找元素）
//    public void infixOrder(){
//        if (this.left != null){
//            this.left.infixOrder();
//        }
//        System.out.println(this);
//        if (this.right != null){
//            this.right.infixOrder();
//        }
//    }

    public void infixOrder(Node t){
        if (t != null){
            infixOrder(t.left);
            System.out.print(t.val + " ");
            infixOrder(t.right);
        }
    }

    //计算结点高度
//    public void suffixOrder(){
//        if (this.left != null){
//            this.left.suffixOrder();
//        }
//        if (this.right != null){
//            this.right.suffixOrder();
//        }
//        System.out.println(this);
//    }

    public void suffixOrder(Node t){
        if (t != null){
            suffixOrder(t.left);
            System.out.print(t.val + " ");
            suffixOrder(t.right);
        }
    }

}
