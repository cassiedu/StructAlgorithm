package heap;

import tree.SortTreeADT;

import java.nio.BufferUnderflowException;

public class BinaryHeapTest {
    public BinaryHeapTest(){
        this(DEFAULT_CAPACITY);

    }

    public BinaryHeapTest(int size){
        currentSize = 0;
        array = new int[size+1];
    }

    public BinaryHeapTest(int[] items){
        this.array = items;
        currentSize = items.length;
        array = new int[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (int item: items)
            array[i++] = item;
        buildHeap();
    }

    public void insert(int x)
    {
        if(currentSize == array.length - 1)
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
        int hole = ++currentSize;
        for( array[ 0 ] = x; x < array[ hole / 2 ]; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }

    private void enlargeArray(int newSize)
    {
        int[] old = array;
        array = new int[newSize];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    private void buildHeap(){
        for (int i = currentSize/2; i > 0; i--)
            percolateDown(i);
    }

    public int findMin( )
    {
        if( isEmpty( ) )
            throw new BufferUnderflowException();
        return array[ 1 ];
    }

    private boolean isEmpty(){
        return currentSize == 0;
    }

    public int deleteMin(){
        if (isEmpty())
            throw new BufferUnderflowException();
        int minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;

    }

    public void makeEmpty( )
    {
        currentSize = 0;
    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private static int[] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        int tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ] < array[ child ])
                child++;
            if( array[ child ] < tmp)
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    public static void print(int[] arr){
        for (int item: arr)
            System.out.printf(item + " ");
        System.out.println();
    }

    // Test program
    public static void main( String [ ] args )
    {
        int numItems = 100;
        BinaryHeapTest h = new BinaryHeapTest();
        int i;
        int j = 1;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems ){
            h.insert( i );
            j++;
            if (j > 10)
                break;
        }
        print(array);

//        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
//            h.insert( i );
//        print(array);

        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }
}


