package algs.spreadsheet.graph;

import java.util.Iterator;

/**
 * Modified Bag implementation to support simple deletion of a value from the bag.
 * Follows sedgewick standard implementation
 *
 * Use this class "as is" 
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {

	Node first;     // first node in the list (may be null)
	int size;
	
	class Node {
		Item    item;
		Node    next;
	}

	/** Add an item to the Bag, without checking first whether it is contained. */
	public void add (Item item) {
		Node oldfirst = first;

		first = new Node();
		first.item = item;
		first.next = oldfirst;
		size++;
	}
	
	/**
	 * Remove a value from the Bag, should it exist.
	 * 
	 * Return true on success; false otherwise
	 * @param item
	 */
	public boolean remove (Item item) {
		if (first == null) { return false; }
		
		Node prev = null;
		Node n = first;
		while (n != null) {
			if (n.item.equals(item)) {
				if (prev == null) {
					first = n.next;
				} else {
					prev.next = n.next;
				}
				size--;
				return true;
			}
			
			prev = n;
			n = n.next;
		}		
		
		return false;
	}


    /** Returns iterator over items in bag. */
    public Iterator<Item> iterator()  { return new ListIterator(first); }
    public int size() { return size; }
    
    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node first)  { current = first;        }
        public boolean hasNext()         { return current != null; }
        public void remove()             {                         }

        public Item next() {
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
}
