package algs.days.day11;

public class LinearProbingHashST<Key, Value> {   // Simplified from Book...
	int N;           // number of key-value pairs in the symbol table
	int M;           // size of linear probing table
	Key[] keys;      // the keys
	Value[] vals;    // the values

	/** Initializes an empty symbol table with the specified initial capacity. */
	public LinearProbingHashST(int capacity) {
		M = capacity;
		N = 0;
		keys = (Key[])   new Object[M];
		vals = (Value[]) new Object[M];
	}

	public int size()        { return N; }
	public boolean isEmpty() { return size() == 0; }

	/** Useful to just check that ST contains key (CANNOT be NULL). */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/** Hash function for keys - returns value between 0 and M-1. */
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	/** Insert (key, value) or replace existing value with new val. */
	public void put(Key key, Value val) {
		if (N >= M/2) resize(2*M);          // double table size if 50% full

		int i = hash(key);
		while (keys[i] != null) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
			i = (i + 1) % M;
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	/** Return key or null if doesn't exist. */
	public Value get(Key key) {
		int i = hash(key); 
		while (keys[i] != null) {
			if (keys[i].equals(key)) {
				return vals[i];
			}
			i = (i + 1) % M;
		}
		return null;
	}

	/** Remove key (and associated value) from ST. */
	public void delete(Key key) {
		if (!contains(key)) return;

		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % M;
		}

		// delete key and associated value
		keys[i] = null;
		vals[i] = null;

		// rehash all keys IN SAME CHAIN
		i = (i + 1) % M;
		while (keys[i] != null) {
			// delete keys[i] an vals[i] and reinsert
			Key   keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;                             // temporarily drop by one...
			put(keyToRehash, valToRehash);   // since this will add it back in
			i = (i + 1) % M;
		}

		N--;  // don't forget we are asked to delete key that was known to exist
	}

	// resizes the hash table to the given capacity by re-hashing all of the keys
	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		M    = temp.M;
	}
}