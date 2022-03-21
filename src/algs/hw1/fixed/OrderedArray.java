package algs.hw1.fixed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * An array of elements of a given type, ordered by some Comparator.
 * 
 * Whenever you access a given element, the number of inspections is increased by one, allowing
 * you to determine how efficiently this structure was used.
 *  
 * @param <T>     A generic parameter, declaring the type of each element, which can be parameterized 
 */
public class OrderedArray<T> {
	/** Total number of inspections since construction. */
	long numInspections = 0;
	
	/** Elements that can be inspected. */
	T[] elements;
	
	/**
	 * Construct an OrderedArray object using the given elements and comparator.
	 * If comparator is null, then the elements remain in their original ordering.
	 */
	@SuppressWarnings("unchecked")
	public OrderedArray(T[] elements, Comparator<T> comparator) {
		this.elements = (T[]) new Object[elements.length];
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = elements[i];
		}
		
		if (comparator != null) {
			Arrays.sort(this.elements, comparator);
		}
	}
	
	/** Requesting an OrderedArray that actually takes these elements as is without rearranging them. */
	public OrderedArray(T[] elements) {
		this(elements, null);
	}
	
	/** 
	 * Retrieve the value at the given index location.
	 * Each such execution increments the number of inspections. 
	 */
	public T get(int idx) {
		numInspections++;
		return elements[idx];
	}
	
	/** Count the number of elements in this ordered array. */
	public int length() { return elements.length; }
	
	/** Return the number of inspections. */
	public long getNumInspections() {
		return numInspections;
	}
}
