package exampleTest;

public class ConstructArrayTest {
    public static void main( String [ ] args )
    {
        int numItems = 10;
        for(int i = 37; i != 0; i = ( i + 37 ) % numItems )
            System.out.print(i + " ");
        System.out.println();
    }
}
