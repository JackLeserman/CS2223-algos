package algs.days.day12;

import java.util.Arrays;

/**
 * This is a smaller copy of the actual MaxPQ code in the edu.princeton.cs.algs package. Done
 * this way to make it easier to present in class.
 * 
 * REPEATER Experiment. 
 */
public class MaxPQ<Key> {
	private Key[] pq;                    // store items at indices 1 to N (pq[0] is unused)
	private int N;                       // number of items on priority queue

	public MaxPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}

	public boolean isEmpty() { return N == 0;  }
	public int size() { return N; }

	public void insert(Key x) {
		pq[++N] = x;
		swim(N);
	}
	
	public String toString() {       // for debugging
		return Arrays.toString(pq);
	}

	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		pq[N+1] = null;     // to avoid loitering and help with garbage collection
		sink(1);
		return max;
	}

	/***************************************************************************
	 * Helper functions to restore the heap invariant.
	 ***************************************************************************/
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/***************************************************************************
	 * Helper functions for compares and swaps.
	 ***************************************************************************/
	private boolean less(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..N] a max heap?
	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	// is subtree of pq[1..N] rooted at k a max heap?
	private boolean isMaxHeap(int k) {
		if (k > N) return true;
		int left = 2*k, right = 2*k + 1;
		if (left  <= N && less(k, left))  return false;
		if (right <= N && less(k, right)) return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}
	
	/****************************
	 *  Sample main
	 ****************************/
	public static void main(String[] args) {
		
	}
}
