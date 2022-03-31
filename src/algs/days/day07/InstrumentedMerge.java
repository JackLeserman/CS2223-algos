package algs.days.day07;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Proper MergeSort from Sedewick, 4ed with annotations showing sub-tasks
public class InstrumentedMerge {
    
	static Comparable aux[];
	static int numComparisons = 0;
	
    public static void sort(Comparable[] a) {
    	numComparisons = 0;
    	aux = new Comparable[a.length];
    	sort (a, 0, a.length-1);
    }
    
    // recursive helper function
    static void sort (Comparable[] a, int lo, int hi) {
    	if (hi <= lo) return;
    	
    	int mid = lo + (hi - lo)/2;
    	
    	sort(a, lo, mid);
    	
    	sort(a, mid+1, hi);
    	merge(a, lo, mid, hi);
    }
    
    // merge sorted results a[lo..mid] with a[mid+1..hi] back into a
    static void merge (Comparable[] a, int lo, int mid, int hi) {
    	int left = lo;     // starting index into left sorted sub-array
    	int right = mid+1;  // starting index into right sorted sub-array
    	
    	// copy a[lo..hi] into aux[lo..hi]
    	for (int k = lo; k <= hi; k++) {
    		aux[k] = a[k];
    	}
    	
    	// now comes the merge. Something you might simulate with flashcards
    	// drawn from two stack piles. This is the heart of mergesort. 
    	for (int k = lo; k <= hi; k++) {
    		if       (left > mid)                   { a[k] = aux[right++]; }
    		else if  (right > hi)                   { a[k] = aux[left++];  }
    		else if  (less(aux[right], aux[left]))  { a[k] = aux[right++]; numComparisons++;}
    		else                                    { a[k] = aux[left++];  numComparisons++;}
    	}
    }
    

   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    // print array to standard output a[lo..hi]
    private static void show(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            StdOut.print (a[i] + " ");
        }
    }

    /**
     * Reads in a sequence of strings from standard input; selection sorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
    	System.out.println("N\tBest\tModel\tWorst?\tModel");
        for (int N = 256; N <= 16384; N *= 2) {
        	Integer[] a = new Integer[N];
        	for (int i = 0; i < N; i++) { a[i] = i; }
        	
            InstrumentedMerge.sort(a);
            int numAscending = numComparisons;
            
            // [1, 0, 3, 1, 5, 2, 7, 3, 9, 4.]
            // alternating increasing odd numbers with increasing natural numbers
            for (int i = 0; i < N; i+=2) {
            	a[i] = 2*i+1;
            	a[i+1] = i;
            }
            InstrumentedMerge.sort(a);
            int numAlternating = numComparisons;
            
            double logn = Math.log(N)/Math.log(2);
            int worstModel = (int) (N * logn);
            System.out.println(N + "\t" + numAscending + "\t" + worstModel/2 + "\t" + numAlternating + "\t" + worstModel);
        }
    }
}