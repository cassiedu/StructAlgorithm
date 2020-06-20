package exampleTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareToTest {
    public static void main(String[] args) {
        List<Node> node = new ArrayList<>();
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        for (int val : arr) {
            node.add(new Node(val));
            System.out.printf(val + "\t");
        }
        System.out.println();
        System.out.println("original nodes sequence:");
        System.out.println(node + "\t");

        Collections.sort(node);
        System.out.println("after sorting, nodes sequence: ");
        System.out.println(node + "\t");
    }


    private static class Node implements Comparable<Node> {
        int value;
        Node left;
        Node right;
        int npl;

        Node(int val) {
            this(val, null, null);
        }

        Node(int val, Node lt, Node rt) {
            this.value = val;
            this.right = rt;
            this.left = lt;
            npl = 0;
        }

        @Override
        public String toString() {
            return "vAL = " + value;
        }

        @Override
        public int compareTo(Node node) {
            return this.value - node.value;
        }
    }

}

