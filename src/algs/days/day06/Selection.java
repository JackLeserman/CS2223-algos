package algs.days.day06;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Sedgewick, 4ed
public class Selection {

	public static long lessCount;
	public static long exchCount;
	
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        // Truth: UNKNOWN
        for (int i = 0; i < N; i++) {
            int min = i;
			// Truth: A[0 to i] is sorted AND a[min] is candidate for smallest
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) {
                	min = j;
                }
            }
			// Truth: A[min] is smallest of A[i .. n-1]
            exch(a, i, min);
            
            // Truth: A[0 to i] is sorted ascending
        }
      // Truth: ASCENDING order
    }

   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
    	lessCount++;
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
    	exchCount++;
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; selection sorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Selection.sort(a);
        show(a);
        System.out.println("SELECTION SORT: #less = " + lessCount + ", #exch = " + exchCount);
    }
}