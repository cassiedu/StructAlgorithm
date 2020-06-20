package list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<Integer> implements Iterable<Integer>{
    private static final int DEFAULT_capacity = 10;
    private int theSize;
    private int[] theItems;

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public boolean isEmpty(){
        return size()== 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public int get(int idx){
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public int set(int idx, int newVal){
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        int old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public boolean add(int x){
        add(size(), x);
        return true;
    }

    public void add(int idx, int x){
        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > idx; i--){
            theItems[i] = theItems[i - 1];
        }
        theSize++;
    }

    public int remove(int idx){
        int removedItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++){
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;
    }

    public Iterator<Integer> iterator(){
        return (Iterator<Integer>) new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<java.lang.Integer> {
        private int current = 0;
        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public java.lang.Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    public void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_capacity);
    }

    //capacity expand
    public void ensureCapacity(int newCapacity){
        if (newCapacity < theSize)
            return;
        int[] old = theItems;
        theItems = new int[newCapacity];
        for(int i = 0; i < size(); i++){
            theItems[i] = old[i];
        }
    }

    public int size(){
        return theSize;
    }


}
