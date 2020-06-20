package tree;

public class AVL {

    public static void main(String[] args) {
        String[] s = {"1", "3", "5", "7", "9", "2", "4", "6", "8"};
        AVL avl = new AVL();

        for (String ele: s)
//            avl.insert(ele, new AvlNode(ele));
            avl.insert(ele);
        avl.printTree();
        avl.insert("10");
        avl.printTree();
    }

    private AvlNode root;

    public void insert(String val){
        root = insert(val, root);
    }

    private static class AvlNode{
        String val;
        int height;
        AvlNode left;
        AvlNode right;
        AvlNode (String v, AvlNode l, AvlNode r){
            val = v;
            left = l;
            right = r;
            height = 0;
        }

        AvlNode (String v){
            this(v, null, null);
        }
    }

    public void printTree(){
        if (isEmpty()){
            System.out.println("Empty Tree");
        } else {
            System.out.println("suffixOrder: ");
            suffixOrder(root);
            System.out.println();
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void suffixOrder(AvlNode t){
        if (t != null){
            suffixOrder(t.left);
            System.out.print(t.val + " ");
            suffixOrder(t.right);
        }
    }

    private AvlNode insert(String x, AvlNode t){
        if (t == null)
            return new AvlNode(x, null, null);
        int compareRes = x.compareTo(t.val);
        if (compareRes < 0)
            t.left = insert(x, t.left);
        else if (compareRes > 0)
            t.right  = insert(x, t.right);
        else
            ;
        return balance(t);
    }

    private AvlNode remove(String x, AvlNode t) {
        if (t == null) {
            System.out.println("the node is not existed");
            return null;
        }
        int compareResult = x.compareTo(t.val);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.right != null && t.left != null) {
            t.val = finMin(t.right).val;
            t.right = remove(t.val, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlNode finMin(AvlNode root){
        if (root == null){
            return null;
        } else if (root.left == null){
            return root;
        } else {
            return finMin(root.left);
        }
    }

    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode balance(AvlNode t){
        System.out.println("height: " + t.height);
        if (t == null)
            return t;
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        System.out.println("height: " + t.height);
        return t;
    }

    private int height(AvlNode t){
        return t == null ? -1: t.height;
    }

    private AvlNode rotateWithLeftChild(AvlNode k2){
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode rotateWithRightChild(AvlNode k2){
        AvlNode k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    private AvlNode doubleWithLeftChild(AvlNode k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode doubleWithRightChild(AvlNode k3){
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }
}
