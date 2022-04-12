package algs.days.day03;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Implementation solution by Adam Kalayjian. Some extra functionality by Heineman.
 * 
 * Note: The trick in the pop of the final sign bit allows for a total of 63 bits.
 */
public class BinaryStack {

    private long storage = 1L;

    public String toString() {
        return Long.toBinaryString(storage);
    }

    public void push (int bit) {
    	if (isFull()) { throw new RuntimeException("BinaryStack is full."); }
    	
        // shift left by 1, then add bit (make sure either a 0 or a 1).
        storage = storage << 1;
        storage += bit % 2;
    }
 
    public int pop () {
    	if (isEmpty()) { throw new RuntimeException("BinaryStack is empty."); }
    	
    	if (isFull()) {
    		// have to take special care because the ">>" operator in java will keep the negative
    		storage = (storage ^ -9223372036854775808L);  // strip away the sign bit #63
    		int bit = (int) (1L & storage);
            storage = storage >> 1;                       
            storage = storage | 4611686018427387904L;    // add back in #62
            return bit;
    	}
    	
        // isolate first bit
        int bit = (int) (1L & storage);
        storage = storage >> 1;
        return bit;
    }

    public int size () {// need to find where the largest bit is efficiently.
        // So... find log base 2 of storage and then round down.
        return (int) (Math.log(storage) / Math.log(2));
    }

    public boolean isEmpty() {
        // if storage is 1 then only the helper bit is left.
        return storage == 1L;
    }
    
    public boolean isFull() {
        // Once you turn negative, then the 64th bit is set
    	return storage < 0;
    }
    
    // sample driver
    public static void main(String[] args) {
    	BinaryStack bs = new BinaryStack();
    	
    	// generate random bits until BinaryStack is full and push them onto stack.
    	// keep track so we can check later. 
    	String order = "";
    	while (!bs.isFull()) {
    		int randomBit = StdRandom.uniform(2);
    		order += randomBit;
    		bs.push(randomBit);
    	}
    	System.out.println("Push order is: " + order);
    	System.out.println("A total of " + order.length() + " bits are stored.");
    	
    	order = "";
    	while (!bs.isEmpty()) {
    		int bit = bs.pop();
    		order += bit;
    	}
    	System.out.println("Pop order is:  " + order);
    }

}