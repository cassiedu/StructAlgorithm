package exampleDesign.dynamicProgramming;

public class ComputeFibonacci {
    /**
     * Compute Fibonacci numbers as described in Chapter 1.
     */
    public static int fibRecursive( int n )
    {
        if( n <= 1 )
            return 1;
        else
            return fibRecursive( n - 1 ) + fibRecursive( n - 2 );
    }

    /**
     * Compute Fibonacci numbers as described in Chapter 1.
     */
    public static int fibLinear( int n )
    {
        if( n <= 1 )
            return 1;

        int last = 1;
        int nextToLast = 1;
        int answer = 1;
        for( int i = 2; i <= n; i++ )
        {
            answer = last + nextToLast;
            nextToLast = last;
            last = answer;
        }
        return answer;
    }

    public static double evalRecursive( int n )
    {
        if( n == 0 )
            return 1.0;
        else
        {
            double sum = 0.0;
            for( int i = 0; i < n; i++ )
                sum += evalRecursive( i );
            return 2.0 * sum / n + n;
        }
    }

    public static double eval( int n )
    {
        double [ ] c = new double [ n + 1 ];

        c[ 0 ] = 1.0;
        for( int i = 1; i <= n; i++ )
        {
            double sum = 0.0;
            for( int j = 0; j < i; j++ )
                sum += c[ j ];
            c[ i ] =  2.0 * sum / i + i;
        }

        return c[ n ];
    }

    public static void main( String [ ] args )
    {
        System.out.println( "fib( 7 ) = " + fibRecursive( 7 ) );
        System.out.println( "fibonacci( 7 ) = " + fibLinear( 7 ) );
        System.out.println( "eval( 10 ) = " + evalRecursive( 10 ) );
        System.out.println( "eval( 10 ) = " + eval( 10 ) );
    }
}
