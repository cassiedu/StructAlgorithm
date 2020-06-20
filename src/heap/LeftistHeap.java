package heap;

// LeftistHeap class
//
// CONSTRUCTION: with a negative infinity sentinel
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void merge( rhs )      --> Absorb rhs into this heap
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.nio.BufferUnderflowException;


/**
 * Implements a leftist heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class LeftistHeap<Integer extends Comparable<? super Integer>> {
    public static void main(String[] args) {
        int numItems = 8;
        LeftistHeap h = new LeftistHeap();
        LeftistHeap h1 = new LeftistHeap();
        for (int i = 37; i != 0; i = (i + 37) % numItems)
            if ((i % 2 == 0))
                h1.insert(i);
            else
                h.insert(i);
        h.merge(h1);
        for(int i = 1; i < numItems; i++ )
            if( h.deleteMin1() != i )
                System.out.println( "Oops! " + i );
    }

    private LeftistNode root;
    public LeftistHeap(){
        root = null;
    }

    public void merge(LeftistHeap rhs){
        if (this == rhs)
            return;
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    public void insert(int val){
        root = merge(new LeftistNode(val), root);
    }

    private LeftistNode merge(LeftistNode h1, LeftistNode h2){
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        if (h1.value < h2.value)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);
    }

    private LeftistNode merge1(LeftistNode h1, LeftistNode h2){
        if (h1.left == null)
            h1.left = h2;
        else {
            h1.right = merge(h1.right, h2);
            if (h1.left.npl < h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private static void swapChildren(LeftistNode t){
        LeftistNode tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }


    public int findMin(){
        if (isEmpty())
            throw new BufferUnderflowException();
        return root.value;
    }

    private boolean isEmpty(){
        return root == null;
    }

    public LeftistNode deleteMin(){
        if (isEmpty())
            throw new BufferUnderflowException();
        LeftistNode minItem = root;
        root = merge(root.left, root.right);
        return minItem;
    }

    public int deleteMin1(){
        if (isEmpty())
            throw new BufferUnderflowException();
        int minItem = root.value;
        root = merge(root.left, root.right);
        return minItem;
    }

    public void makeEmpty(){
        root = null;
    }

}

class LeftistNode implements Comparable<LeftistNode>{
    int value;
    LeftistNode left;
    LeftistNode right;
    int npl;
    LeftistNode(int val){
        this(val, null, null);
    }

    LeftistNode(int val, LeftistNode lt, LeftistNode rt){
        this.value = val;
        this.right = rt;
        this.left = lt;
        npl = 0;
    }

    @Override
    public String toString() {
        return "value = " + value;
    }

    @Override
    public int compareTo(LeftistNode leftistNode) {
        return this.value - leftistNode.value;
    }

//    @Override
//    public int compareTo(LeftistNode o) {
//        return this.value - o.value;
//    }

}