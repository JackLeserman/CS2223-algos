package algs.days.day20;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StopwatchCPU;

public class Comparison {

	public static void main(String[] args) {
		System.out.println("N\t\tQSort\tPar(1)\tPar(2)\tPar(10)");
		for (int N = 65536; N <= 1048576*16; N*=2) {
			Integer[] vals1 = new Integer[N];
			Integer[] vals2 = new Integer[N];
			Integer[] vals3 = new Integer[N];
			Integer[] vals10 = new Integer[N];
			for (int i = 0; i < N; i++) { 
				vals1[i] = StdRandom.uniform(Integer.MAX_VALUE); 
				vals2[i] = vals1[i];
				vals3[i] = vals1[i];
				vals10[i] = vals1[i];
			}
			
			QuickSort qs = new QuickSort<Integer>(vals1);
			StopwatchCPU watch = new StopwatchCPU();
			qs.qsortN(0,  N-1);                      // straight
			double timeN = watch.elapsedTime(); 
			
			qs = new QuickSort<Integer>(vals2);
			watch = new StopwatchCPU();
			qs.qsort(0,  N-1);                       // parallel
			double timeP = watch.elapsedTime();
			
			qs = new QuickSort<Integer>(vals3);
			qs.setNumberHelperThreads(2);
			watch = new StopwatchCPU();
			qs.qsort(0,  N-1);                       // parallel with 2 threads
			double timeP2 = watch.elapsedTime();
			
			qs = new QuickSort<Integer>(vals10);
			qs.setNumberHelperThreads(10);
			watch = new StopwatchCPU();
			qs.qsort(0,  N-1);                       // parallel with 2 threads
			double timeP10 = watch.elapsedTime();
			
			System.out.println(String.format("%10d\t%.4f\t%.4f\t%.4f\t%.4f", N, timeN, timeP, timeP2, timeP10));
		}
	}
}

