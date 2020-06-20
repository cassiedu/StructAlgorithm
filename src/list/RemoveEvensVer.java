package list;

import java.util.Iterator;
import java.util.LinkedList;


public class RemoveEvensVer {
    public static void main(String[] args) {
        LinkedList<Integer> lst = new LinkedList<>();
        int[] arr = {1,2,3,4,5,6,7,8};
        for (int ele: arr){
            lst.add(ele);
        }
        print(lst);
        System.out.println();
        removeEvensVer(lst);
        print(lst);
    }

    public static void removeEvensVer(LinkedList<Integer> lst){
        Iterator<Integer> itr = lst.iterator();
        while (itr.hasNext())
            if (itr.next() % 2 == 0)
                itr.remove();
    }

    public static void print(LinkedList<Integer> lst){
        Iterator<Integer> itr = lst.iterator();
        while (itr.hasNext()){
            Integer item = itr.next();
            System.out.print(item + " ");
        }
    }
}

//class MyLinkedList<Integer> {
//    public Node head;
//
//    public void add(Node node) {
//        Node temp = head;
//        while (true) {
//            if (temp.next == null) {
//                break;
//            }
//            temp = temp.next;
//        }
//        temp.next = node;
//    }
//}
//
//class Node{
//    int num;
//
//    public Node head;
//    public Node next;
//
//    public Node(int num) {
//        this.num = num;
//    }
//
//}