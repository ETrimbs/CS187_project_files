
/*  Linear Probing Hash Table */
public class Hashtable {
	private int currentSize, maxSize;

	public Pair[] map = null; // stores hash table elements

	// Constructor
	public Hashtable(int capacity) {
		currentSize = 0;
		maxSize = capacity;
		map = new Pair[maxSize];
		for (int i = 0; i < maxSize; i++)
			map[i] = null;
	}

	// Function to check if hash table is empty
	public boolean isEmpty() {
		return getSize() == 0;
	}

	// Function to get size of hash table
	public int getSize() {
		return currentSize;
	}

	// Function to check if hash table is full
	public boolean isFull() {
		return currentSize == maxSize;
	}

	// Function to check if hash table contains a key
	public boolean contains(String key) {
		return get(key) != null;
	}

	// Function to get hash-code/hash-value for a given key
	public int hash(String key) {
		return Math.abs(key.hashCode()) % maxSize;
	}

	// Function to get value for a given key
	public String get(String key) {
		int index = hash(key) % maxSize;

		while (map[index] != null && !map[index].getKey().equals(key)) {
			if (index == maxSize - 1)
				index = 0;
			else
				index++;
		}

		return map[index] == null ? null : map[index].getValue();
	}

	// Function to insert key-value pair
	public void put(String key, String val) {
		if (isFull())
			rehash();

		int index = hash(key) % maxSize;

		while (map[index] != null)
			index++;

		map[index] = new Pair(key, val);
		currentSize++;
	}

	/// Function to rehash when the table is full
	public void rehash() {
		Pair[] newMap = new Pair[maxSize * 2];
		Pair curr;

		int i = 0, count = currentSize;
		while (i < maxSize && count > 0) {
			curr = map[i];
			if (curr != null) {
				putPair(curr, newMap);
				count--;
			}
			i++;
		}

		this.map = newMap;
		maxSize *= 2;
	}

	private void putPair(Pair _pair, Pair[] _map) {
		int index = hash(_pair.getKey()) % _map.length;

		while (_map[index] != null)
			index++;

		_map[index] = _pair;
	}

	// Function to print HashTable
	public void printHashTable() {
		System.out.println("\nHash Table: Key, Value ");
		for (int i = 0; i < maxSize; i++)
			if (map[i] != null)
				System.out.println(map[i].getKey() + ", " + map[i].getValue());
		System.out.println();
	}
}

class Pair {

	private String key;
	private String value;

	public Pair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
