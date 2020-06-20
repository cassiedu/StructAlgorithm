package list;

import java.util.*;
import java.util.Iterator;

public class MyLinkedList implements Iterable {
    private int theSize;
    private int modCount = 0;
    public static Node beginMarker;
    public static Node endMarker;

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        List<Node> nodes = new LinkedList();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        ListIterator<Node> iter = nodes.listIterator();
        while (iter.hasNext()){
            System.out.println(iter.next().data);
        }
    }

    public void list() {
        //判断链表是否为空
        if(beginMarker.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        Node temp = beginMarker.next;
        while(true) {
            //判断是否到链表最后
            if(temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移， 一定小心
            temp = temp.next;
        }
    }

    private static class Node{
        public int data;
        public Node prev;
        public Node next;
        public Node(int d, Node p, Node n){
            data = d;
            prev = p;
            next = n;
        }

        @Override
        public String toString() {
            return "data" + data;
        }

        public Node(int d){
            data = d;
        }
    }

    private void clear(){
        beginMarker = new Node(0, null, null);
        endMarker = new Node(0, beginMarker, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void add(int idx, int x){
        addBefore(getNode(idx, 0, size()), x);
    }

    private void addBefore(Node p, int x){
        Node newNode = new Node(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private int remove(int idx){
        return remove(getNode(idx));
    }

    private int remove (Node p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }


    private Node getNode(int idx){
        return getNode(idx, 0, size() - 1);
    }

    private Node getNode(int idx, int lower, int upper){
        Node p;
        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        if (idx < size()/2){
            p = beginMarker.next;
            for (int i = 0; i < idx; i++){
                p = p.next;
            }
        }
        else {
            p = endMarker;
            for (int i = size(); i > idx; i--){
                p = p.prev;
            }
        }
        return p;
    }

    @Override
    public Iterator iterator() {
         return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator{
        private Node current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public Object next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();

            int nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }
}
