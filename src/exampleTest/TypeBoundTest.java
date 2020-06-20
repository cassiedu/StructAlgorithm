package exampleTest;

public class TypeBoundTest {

    public static void main(String[] args) {
        Integer[] array = {1, 3, 5, 7, 9};
        FindMax(array);
    }

    public static <Integer extends Comparable<? super Integer>>
    Integer FindMax(Integer[] arr){
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i].compareTo(arr[maxIndex]) > 0)
                maxIndex = i;
            return arr[maxIndex];
    }

//    public static <AnyType extends Comparable<? super AnyType>>
//    AnyType FindMax(AnyType[] arr){
//        int maxIndex = 0;
//        for (int i = 0; i < arr.length; i++)
//            if (arr[i].compareTo(arr[maxIndex]) > 0)
//                maxIndex = i;
//        return arr[maxIndex];
//    }
}
