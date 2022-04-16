package jleserman.hw3;
import edu.princeton.cs.algs4.In;
import jleserman.hw3.sort.*;

/**
 * Complete this class into your USERID.hw3 file and change it as necessary.
 */

public class SortTrials {
	static int maxHS = 0;
	static int maxMS = 0;
	static int maxIS = 0;
	static String isStr;
	static int maxQS = 0;
	static int maxSS = 0;


	public static int sumA(Exchangeable[] A){
		int counter = 0;
		for(int i = 0; i<A.length; i++){
			counter = counter + A[i].getExchangeCount();
		}
		return counter/2;
	}

	public static void runHS(Exchangeable[] A){
		HeapSort.sort(A);
		int counts = sumA(A);
		if(counts > maxHS){
			maxHS = counts;
		}
	}
	public static void runMS(Exchangeable[] A){
		Merge.sort(A);
		int counts = sumA(A);
		if(counts > maxMS){
			maxMS = counts;
		}
	}
	public static void runIS(int N){
		if(N <= 2048) {
			Exchangeable[] A = Exchangeable.create(N);
			Insertion.sort(A);
			int counts = sumA(A);
			if (counts > maxIS) {
				maxIS = counts;
				isStr = Integer.toString(maxIS);
			}
		}else{
			isStr = "*";
		}
	}

	public static void runQS(Exchangeable[] A){
		Quick.sort(A);
		int counts = sumA(A);
		if(counts > maxQS){
			maxQS = counts;
		}
	}
	public static void runSS(Exchangeable[] A){
		Selection.sort(A);
		int counts = sumA(A);
		if(counts > maxSS){
			maxSS = counts;
		}
	}


	public static void printData(int N){
		System.out.println(N+"   "+maxSS+"   "+maxQS+"   "+maxHS+"    " +maxMS+"   "+isStr);
		//System.out.println(N+"\t"+maxHS+"\t"+maxMS+"\t"+maxIS+"\t"+maxQS+"\t"+maxSS);
	}

	public static void main(String[] args) {
		System.out.println("N\tSelect\tQuick\tHeap\tMerge\tInsert");
		for (int N = 128; N <= 16384; N *= 2) {
			for (int i = 0; i < 100; i++) {
				Exchangeable[] HS = Exchangeable.create(N);
				Exchangeable[] MS = Exchangeable.create(N);
				Exchangeable[] QS = Exchangeable.create(N);
				Exchangeable[] SS = Exchangeable.create(N);
				runHS(HS);
				runMS(MS);
				runIS(N);
				runQS(QS);
				runSS(SS);
			}
			printData(N);
		}
	}
}
