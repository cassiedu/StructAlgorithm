package map.hashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class SeparateChainingHashTable {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Employee employee = new Employee(id, name);
                    myLinkedList.insert(employee);
                    break;
                case "list":
                    myLinkedList.list();
                    break;
//                case "find":
//                    System.out.println("请输入要查找的id");
//                    id = scanner.nextInt();
//                    myLinkedList.find(id);
//                    break;
//                case "exit":
//                    scanner.close();
//                    System.exit(0);
                default:
                    break;
            }
        }
    }
}


class MyLinkedList{
    public static final int DEFAULT_TABLE_SIZE = 7;
    public static int currentSize;
    public static List<Employee>[] theLists;
    private Employee head;
    //构造hashTable
    public MyLinkedList(){
        this(DEFAULT_TABLE_SIZE);
    }

    public MyLinkedList(int tableSize){
        theLists = new LinkedList[nextPrime(tableSize)];
        for (int i = 0; i < theLists.length; i++){
            theLists[i] = new LinkedList<>();
        }
    }

    //将hashTable置空
    public void makeEmpty(){
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;
    }

    public boolean contains(Employee employee){
        List<Employee> whichList = theLists[myHash(employee)];
        return whichList.contains(employee);
    }

    //遍历链表的雇员信息
    public void list() {
        for(int i = 0; i < currentSize; i++) {
            List<Employee> curList = theLists[i];
            ListIterator<Employee> iter = curList.listIterator();

            if(iter == null) { //说明链表为空
                System.out.println("第 " + ( i + 1) + " 链表为空");
                return;
            }
            System.out.print("第 " + (i + 1) + " 链表的信息为");
            while (iter.hasNext()){
                Employee cur = iter.next();
                System.out.println(cur.id + cur.name);
            }
            System.out.println();
        }
    }

    /* 返回大于N且不超过MAXTABLESIZE的最小素数 */
    int nextPrime(int n)
    {
        int i;
        int p = (n % 2 != 0)? (n + 2) : (n + 1);/*从大于N的下一个奇数开始 */

        while( p <= DEFAULT_TABLE_SIZE ) {
            for( i=(int)sqrt(p); i>2; i-- )
                if (p % i == 0) break; /* p不是素数 */
            if ( i==2 ) break; /* for正常结束，说明p是素数 */
            else  p += 2; /* 否则试探下一个奇数 */
        }
        return p;
    }

    public void insert(Employee employee){
//        boolean isContain = contains(employee);
        List<Employee> whichList = theLists[myHash(employee)];
        if (!whichList.contains(employee)){
            whichList.add(employee);
            if (++currentSize > theLists.length)
                rehash();
        }
    }

    public void remove(Employee employee){
        List<Employee> whichList = theLists[myHash(employee)];
        if (whichList.contains(employee)){
            whichList.remove(employee);
            currentSize--;
        }
    }

    //扩充数组大小
    private void rehash(){
        List<Employee>[] oldLists = theLists;
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++){
            theLists[j] = new LinkedList<>();
        }
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++)
            for (Employee item: oldLists[i])
                insert(item);
    }

    private int myHash(Employee x){
//        int hashVal = x.hashCode();
//        hashVal %= theLists.length;
//        if (hashVal < 0){
//            hashVal += theLists.length;
//        }
//        return hashVal;
        return x.id % DEFAULT_TABLE_SIZE;
    }

    //散列函数
    public static int hash(String key, int tableSize){
        System.out.println(key.charAt(1));
        int hashVal = 0;
        for (int i = 0; i < key.length(); i++){
            hashVal = 37 * hashVal + key.charAt(i);
        }
        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;
        return hashVal;
    }
}

class Employee{
    public int id;
    public String name;
    public Employee next;


    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id = " + id + ", name = " + name;
    }

    //    private int seniority;
    public boolean equals(Object rhs){
        return rhs instanceof Employee && name.equals(((Employee)rhs).name);
    }
}
