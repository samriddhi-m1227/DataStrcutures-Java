import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Assignment for the Implementing Lists module.
 * @author Samriddhi Matharu
 * 
 * 
 * 
 * * @param <T> the type of elements stored in the list
 */
public class MyDoubleLinkedList<T> implements List<T> {
    /**
     * Each node in the linked list is represented by this class. It contains a forward and backward pointer
     * to allow traversal in both directions
     * 
     * @param <T> the type of data stored in the node
     */
    static class Node<T> {
        T data;
        Node<T> prev, next;

        /**
         * Creates a new node and set its prev/next pointers. 
         * NOTE: This does NOT adjust the pointers of the prev and next nodes by design.
         * 
         * @param t the data to store in the node
         * @param prev the reference to the previous node
         * @param next the reference to the next node
         */
        public Node(T t, Node<T> prev, Node<T> next) {
        	this.data = t;
        	this.prev = prev;
            this.next = next;
        }
    };

    /**
     * The head of the list.
     */ 
    Node<T> head;
    /**
     * The tail of the list.
     */
    Node<T> tail;

    /**
     * The number of elements in the list.
     */
     int numElements;

    /**
     * This class represents the list iterator implementation for the double linked list.
     * It provides methods to iterate over the elements in the list bidirectionally.
     * 
     * @param <T> the type of data stored in the iterator
     */
    static class DoubleLinkedListIterator<T> implements ListIterator<T> {
        Node<T> current;
        int index;
        
        /**
         * Creates a new iterator starting at the given node.
         * @param current the starting node for iteration
         */
        public DoubleLinkedListIterator(Node<T> current) {
            this.current = current;
            this.index = 0;
        }
           

        @Override
        public boolean hasNext() {
        	 return current != null && current.next != null;
        }

        @Override
        public T next() {
        	  if (!hasNext()) {
                  throw new UnsupportedOperationException("No next element");
              }
              T data = current.data;
              current = current.next;
              index++;
              return data;
          }

        @Override
        public boolean hasPrevious() {
        	  return current != null && current.prev != null;
        }

        @Override
        public T previous() {
        	if (!hasPrevious()) {
                throw new UnsupportedOperationException("No previous element");
            }
            current = current.prev;
            index--;
            return current.data;

        }

        
        //Unimplemented Methods 
        
        @Override
        public int nextIndex() {
            // No need to implement this method.
            throw new UnsupportedOperationException("Unimplemented method 'nextIndex'");
        }

        @Override
        public int previousIndex() {
            // No need to implement this method.
            throw new UnsupportedOperationException("Unimplemented method 'previousIndex'");
        }

        @Override
        public void remove() {
            // No need to implement this method.
            throw new UnsupportedOperationException("Unimplemented method 'remove'");
        }

        @Override
        public void set(T e) {
            // No need to implement this method.
            throw new UnsupportedOperationException("Unimplemented method 'set'");
        }

        @Override
        public void add(T e) {
            // No need to implement this method.
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
    	return numElements;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
    	 return numElements == 0;
    }
    
    /**
     * Returns true if this list contains the specified element.
     * @param o the object to be checked for containment in this list
     * @return true if this list contains the specified element, false otherwise
     */
    @Override
    public boolean contains(Object o) {
    	 Node<T> current = head;
         while (current != null) {
             if (current.data.equals(o)) {
                 return true;
             }
             current = current.next;
         }
         return false;
     }

    /**
     * Returns an iterator over the elements in this list.
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
    	  return new DoubleLinkedListIterator<>(head);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * @return an array containing all of the elements in this list in proper sequence
     */
    @Override
    public Object[] toArray() {
    	  Object[] array = new Object[numElements];
          int index = 0;
          Node<T> current = head;
          while (current != null) {
              array[index++] = current.data;
              current = current.next;
          }
          return array;
      }
    

    @Override
    public <E> E[] toArray(E[] a) {
        // No need to implement this method.
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    /**
     * Appends the specified element to the end of this list.
     * @param t the element to be appended to this list
     * @return true (as specified by Collection.add(E))
     */
    @Override
    public boolean add(T t) {
    	 if (head == null) {
             head = new Node<>(t, null, null);
             tail = head;
         } else {
             Node<T> newNode = new Node<>(t, tail, null);
             tail.next = newNode;
             tail = newNode;
         }
         numElements++;
         return true;
     }
   
    
    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
    	 Node<T> current = head;
         while (current != null) {
             if (current.data.equals(o)) {
                 if (current == head) {
                     head = head.next;
                     if (head != null) {
                         head.prev = null;
                     }
                 } else if (current == tail) {
                     tail = tail.prev;
                     tail.next = null;
                 } else {
                     current.prev.next = current.next;
                     current.next.prev = current.prev;
                 }
                 numElements--;
                 return true;
             }
             current = current.next;
         }
         return false;
     }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all of the elements of the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
    	  for (Object obj : c) {
              if (!contains(obj)) {
                  return false;
              }
          }
          return true;
      }

    
    /**
     * Adds all of the elements in the specified collection to this list.
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
    	for (T element : c) {
            add(element);
        }
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position.
     * @param index index at which to insert the first element from the specified collection
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
    	 if (index < 0 || index > numElements) {
    	        throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    	    }

    	    boolean modified = false;

    	    for (T element : c) {
    	        add(index++, element);
    	        modified = true;
    	    }

    	    return modified;
    	}

    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * @param c collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean removeAll(Collection<?> c) {
    	 if (c == null) {
    	        throw new NullPointerException("Collection c is null");
    	    }

    	    boolean modified = false;
    	    Iterator<?> iterator = c.iterator();
    	    while (iterator.hasNext()) {
    	        if (remove(iterator.next())) {
    	            modified = true;
    	        }
    	    }
    	    return modified;
    	}
    
    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * @param c collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
    	 if (c == null) {
    	        throw new NullPointerException("Collection c is null");
    	    }

    	    boolean modified = false;
    	    Iterator<T> iterator = iterator();
    	    while (iterator.hasNext()) {
    	        if (!c.contains(iterator.next())) {
    	            iterator.remove();
    	            modified = true;
    	        }
    	    }
    	    return modified;
    	}
    
    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
    	 head = null;
         tail = null;
         numElements = 0;
     }
    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T get(int index) {
    	 if (index < 0 || index >= numElements) {
             throw new IndexOutOfBoundsException("Index out of bounds: " + index);
         }
         Node<T> current = head;
         for (int i = 0; i < index; i++) {
             current = current.next;
         }
         return current.data;
     }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T set(int index, T element) {
    	 if (index < 0 || index >= numElements) {
             throw new IndexOutOfBoundsException("Index out of bounds: " + index);
         }
         Node<T> current = head;
         for (int i = 0; i < index; i++) {
             current = current.next;
         }
         T oldValue = current.data;
         current.data = element;
         return oldValue;
     }
    /**
     * Inserts the specified element at the specified position in this list.
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(int index, T element) {
    	 if (index < 0 || index > numElements) {
             throw new IndexOutOfBoundsException("Index out of bounds: " + index);
         }
         if (index == numElements) {
             add(element);
         } else {
             Node<T> current = head;
             for (int i = 0; i < index; i++) {
                 current = current.next;
             }
             Node<T> newNode = new Node<>(element, current.prev, current);
             if (current.prev != null) {
                 current.prev.next = newNode;
             } else {
                 head = newNode;
             }
             current.prev = newNode;
             numElements++;
         }
     }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T remove(int index) {
    	if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T removedValue = current.data;
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        numElements--;
        return removedValue;
    }
    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object o) {
    	 int index = 0;
         Node<T> current = head;
         while (current != null) {
             if (current.data.equals(o)) {
                 return index;
             }
             current = current.next;
             index++;
         }
         return -1;
     }
    /**
     * Returns the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object o) {
    	 int index = numElements - 1;
         Node<T> current = tail;
         while (current != null) {
             if (current.data.equals(o)) {
                 return index;
             }
             current = current.prev;
             index--;
         }
         return -1;
     }
    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     * @return a list iterator over the elements in this list (in proper sequence)
     */
    @Override
    public ListIterator<T> listIterator() {
    	   return new DoubleLinkedListIterator<>(head);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.
     * @param index  index of the first element to be returned from the list iterator (by a call to next)
     * @return a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    @Override
    public ListIterator<T> listIterator(int index) {
    	if (index < 0 || index > numElements) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return new DoubleLinkedListIterator<>(current);
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws UnsupportedOperationException if the subList operation is not supported
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // No need to implement this method.
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }
}
