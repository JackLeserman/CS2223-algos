package algs.days.day11;

import java.util.Arrays;

import algs.days.day10.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StopwatchCPU;

public class CompareResults {

	public static void stats(SeparateChainingHashST<String,String> table) {
		// number of empty bins
		// largest chain length
		// number of size 1
		int numEmpty = 0;
		int maxChain = 0;  
		int numSingle = 0;
		int maxLength = 30; // more than enough if hash function is good...
		int[] distribution = new int[maxLength];
		int numExcessive = 0;  // how many that exceeded maxLength
		for (int i = 0; i < table.M; i++) {
			if (table.st[i].isEmpty()) {
				numEmpty++;
				continue;
			}

			if (table.st[i].size() > maxChain) {
				maxChain = table.st[i].size();
			}
			if (table.st[i].size() == 1) {
				numSingle++;
			}

			if (table.st[i].size() < maxLength) {
				distribution[table.st[i].size()]++;
			} else {
				numExcessive++;
			}
		}
		distribution[0] = numEmpty;

		double percent = 100*(1-(numEmpty*1.0)/table.M);
		StdOut.println("Table has " + table.M + " indices.");
		StdOut.println("  there are " + numEmpty + " empty indices " + percent + "%");
		StdOut.println("  maximum chain is " + maxChain);
		StdOut.println("  number of single is " + numSingle);
		StdOut.println("  size distribution:" + Arrays.toString(distribution));
		StdOut.println("  number of excessive:" + numExcessive);
	}

	public static void stats(LinearProbingHashST<String,String> table) {
		// number of empty bins
		// largest chain length
		// number of size 1
		int numEmpty = 0;
		int maxChain = 0;  
		int numSingle = 0;
		int maxLength = 30; // more than enough if hash function is good...
		int[] distribution = new int[maxLength];
		int numExcessive = 0;  // how many that exceeded maxLength
		Object keys[] = table.keys;
		for (int i = 0; i < table.M; i++) {
			
			if (keys[i] == null) {
				numEmpty++;
			} else {
				int j = i;
				int num = 0;  
				while (keys[j] != null) {
					num++;
					j = (j+1) % table.M;
				}
				if (num > maxChain) {
					maxChain = num;
				}
				if (num == 1) {
					numSingle++;
				}
	
				if (num < maxLength) {
					distribution[num]++;
				} else {
					numExcessive++;
				}
			}
		}

		distribution[0] = numEmpty;

		double percent = 100*(1-(numEmpty*1.0)/table.M);
		StdOut.println("Table has " + table.M + " indices.");
		StdOut.println("  there are " + numEmpty + " empty indices " + percent + "%");
		StdOut.println("  maximum chain is " + maxChain);
		StdOut.println("  number of single is " + numSingle);
		StdOut.println("  size distribution:" + Arrays.toString(distribution));
		StdOut.println("  number of excessive:" + numExcessive);
	}
	
	public static void main(String[] args) {
		In in = new In ("words.english.txt");
		StopwatchCPU create = new StopwatchCPU();

		SeparateChainingHashST<String,String> linkedListST     = new SeparateChainingHashST<>(17);
		LinearProbingHashST<String,String>    openAddressingST = new LinearProbingHashST<>(17);
		int numWords = 0;
		while (!in.isEmpty()) {
			String word = in.readString();
			numWords++;
			linkedListST.put(word, word);          // You can use value as the key
			openAddressingST.put(word, word);      // You can use value as the key
		}
		in.close();
		StdOut.printf("Elapsed time %.2f seconds\n", create.elapsedTime());
		System.out.println(numWords + " words.");
		
		stats(linkedListST);
		System.out.println("---");
		stats(openAddressingST);
	}
}
