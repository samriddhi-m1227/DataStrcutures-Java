import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Assignment for the Hashing module. Implement a HashMap that uses quadratic probing as a
 * hash collision avoidance method.
 * @author Samriddhi Matharu
 */
public class MyHashMap<K, V> implements Map<K, V> {
	/** The initial size of the hash table. */
    // We start with a table size that is a power of 2. The combination of this table size
    // and the quadratic probing formula guarantees that all slots will be visited.
    public static int INITIAL_TABLE_SIZE = 4;

    // The array to use for the hash table. 
    // NOTE We store Objects in this array as it is not possible to create an array of Type V due
    // to type erasure.
    /** The array to use for the hash table. */
    @SuppressWarnings("rawtypes")
    Map.Entry entries[] = new Map.Entry[INITIAL_TABLE_SIZE];
    

    // The number of elements in the hash table.
    int numElements = 0;

    /**
     * Returns the index to use. This uses linear chaining.
     * @param key
     * @param iteration The iteration of linear probing we are going to use.
     * @return  The index computed using quadratic probing.
     */
    private int getIndex(K key, int iteration) {
        return (key.hashCode() + iteration) % entries.length;
    }

    @Override
    public int size() {
    	return numElements;
    }

    @Override
    public boolean isEmpty() {
    	 return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
    	 if (key == null) return false;

 	    for (int i = 0; i < entries.length; i++) {
 	        int index = getIndex((K) key, i);
 	        if (entries[index] != null && entries[index].getKey().equals(key)) {
 	            return true;
 	        }
 	    }
 	    return false;
 	}


    @Override
    public boolean containsValue(Object value) {
    	 if (value == null) return false;

 	    for (int i = 0; i < entries.length; i++) {
 	        if (entries[i] != null && entries[i].getValue().equals(value)) {
 	            return true;
 	        }
 	    }
 	    return false;
 	}


    @Override
    public V get(Object key) {
    	if (key == null) return null;

	    for (int i = 0; i < entries.length; i++) {
	        int index = getIndex((K) key, i);
	        if (entries[index] != null && entries[index].getKey().equals(key)) {
	            return (V) entries[index].getValue(); // Casting to V
	        }
	    }
	    return null;
	}


    @Override
    public V put(K key, V value) {
    	 if (key == null) return null;

    	    if ((double) numElements / entries.length > 0.5) {
    	        // Resize the array
    	        resize();
    	    }

    	    // Loop through all possible indices to find an empty slot or the slot containing the same key
    	    for (int i = 0; i < entries.length; i++) {
    	        int index = getIndex(key, i);
    	        if (entries[index] != null && entries[index].getKey().equals(key)) {
    	            // If the key already exists, update the value and return the previous value
    	            V oldValue = (V) entries[index].getValue();
    	            entries[index].setValue(value);
    	            return oldValue;
    	        } else if (entries[index] == null) {
    	            // If the slot is empty, add the key-value pair
    	            entries[index] = new MyEntry<>(key, value);
    	            numElements++;
    	            System.out.println("Added entry: " + key + "=" + value + " at index " + index);
    	            return null; // New key added, return null
    	        }
    	    }

    	    return null; // This should not be reached if the hash map is properly resized
    	}

    @Override
    public V remove(Object key) {
    	 if (key == null) return null;

 	    for (int i = 0; i < entries.length; i++) {
 	        int index = getIndex((K) key, i);
 	        if (entries[index] != null && entries[index].getKey().equals(key)) {
 	            V removedValue = (V) entries[index].getValue();
 	            entries[index] = null; // Remove the entry
 	            numElements--;

 	            // Re-hash and re-insert entries that were affected by the removed entry
 	            int j = index + 1;
 	            while (entries[j] != null) {
 	                Map.Entry<K, V> entryToRehash = entries[j];
 	                entries[j] = null; // Remove the entry
 	                numElements--;

 	                // Re-insert the entry to its new position
 	                put(entryToRehash.getKey(), entryToRehash.getValue());

 	                j++;
 	            }
 	            return removedValue;
 	        }
 	    }
 	    return null; // Key not found
 	}
    
    /**
     * Resize the hash table to double its current size.
     */
    private void resize() {
    	int newSize = entries.length * 2;
        Map.Entry<K, V>[] newEntries = new Map.Entry[newSize];

        // Rehash and reinsert entries into the new array
        for (Map.Entry<K, V> entry : entries) {
            if (entry != null) {
                int index = getIndex(entry.getKey(), newSize); // Use the new table size here
                int i = 1;
                while (newEntries[index] != null) {
                    // Handle collisions by linear probing
                    index = (index + i) % newSize;
                    i++;
                }
                newEntries[index] = entry;
            }
        }

        entries = newEntries;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    	for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }


    @Override
    public void clear() {
    	for (int i = 0; i < entries.length; i++) {
            entries[i] = null;
        }
        numElements = 0;
    }


    @Override
    public Set<K> keySet() {
    	Set<K> keySet = new HashSet<>();
        for (Map.Entry<K, V> entry : entries) {
            if (entry != null) {
                keySet.add(entry.getKey());
            }
        }
        return keySet;
    }


    @Override
    public Collection<V> values() {
    	List<V> valuesList = new ArrayList<>();
        for (Map.Entry<K, V> entry : entries) {
            if (entry != null) {
                valuesList.add(entry.getValue());
            }
        }
        return valuesList;
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
    	Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                entrySet.add(entries[i]);
            } else {
            }
        }
        return entrySet;
    }

    /** 
     * Returns the size of the hash table.
     * NOTE: This is the number of slots in your hash table and not the number of entries it contains.
     * @return The size of the hash table.
     */
    public int sizeOfTable() {
       return entries.length;
    	 
    }  
    
    /**
     * Represents a key-value pair in the hash map.
     */
    private static class MyEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        
       
        }

}
