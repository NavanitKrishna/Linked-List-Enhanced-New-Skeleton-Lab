package linkedlist;

/**
 * This is a basic implementation of a linked list
 *
 * @author Mark Wiggans (mmw125)
 * @version 4/14/2015
 * @author Christina Olk (colk)
 * @version 9.4.15
 * @author Grace Fields
 * @version 10.26.15
 * @author Jamal Ahmad (jamal93)
 * @version 10/15/2016
 * @author Margaret Ellis (maellis1)
 * @version 03/16/2017
 * @author JW Lee (jiayiw6)
 * @version 10/14/2019
 * 
 * @param <E>
 *            This is the type of object that this class will store
 */

public class SinglyLinkedList<E> implements LList<E> {

    /**
     * This represents a node in a singly linked list. This node stores data
     * along with having a pointer to the next node in the list
     *
     * @param <D>
     * This is the type of object that this class will store
     */
    
    public static class Node<D> {

        private D data;
        private Node<D> next;

        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(D d) {
            data = d;
        }
       
        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<D> n) {
            next = n;
        }
        
        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<D> next() {
            return next;
        }
        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public D getData() {
            return data;
        }
    }

    private Node<E> head;
    private int size;
        
    /**
     * Creates a new LinkedList object
     */
    public SinglyLinkedList() {
        head = null;
        size = 0;
    }
    
    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Adds the object to the position in the list
     *
     * @precondition obj cannot be null
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(int index, E obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node<E> current = head;

        if (isEmpty()) {
            head = new Node<E>(obj);
        }

        else if (index == 0) {
            Node<E> newNode = new Node<E>(obj);
            newNode.setNext(head);
            head = newNode;
        } 
        
        else {
            int currentIndex = 0;
            while (current != null) {
                if (currentIndex == index - 1) {
                    Node<E> newNode = new Node<E>(obj);
                    newNode.setNext(current.next);
                    current.setNext(newNode);
                    break;
                }
                current = current.next;
                currentIndex++;
            }
        }
        size++;
    }

    /**
     * Adds the object to the end of the list.
     *
     * @precondition obj cannot be null
     * @param obj
     *            the object to add
     * @throws IllegalArgumentException
     *             if obj is null
     */
    
    @Override
    public void add(E obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }
        Node<E> current = head;
        if (isEmpty()) {
            head = new Node<E>(obj);
        }
        else {
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(new Node<E>(obj));
        }
        size++;
    }

    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Removes the first instance of the given object from the list
     *
     * @param obj
     *            the object to remove
     * @return true if successful
     */

    @Override
    public boolean remove(E obj) {
        Node<E> current = head;
        if (head != null && obj.equals(current.data)) {
            head = head.next;
            size--;
            return true;
        }
        while (current != null && current.next != null) {
            if (obj.equals(current.next.data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes the object at the given position
     *
     * @param index
     *            the position of the object
     * @return true if the removal was successful
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     */

    @Override
    public boolean remove(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            head = head.next;
            size--;
            return true;
        }
        else {
            Node<E> current = head;
            int currentIndex = 0;
            while (current.next != null) {
                if ((currentIndex + 1) == index) {
                    Node<E> newNext = current.next.next;
                    current.setNext(newNext);
                    size--;
                    return true;
                }
                current = current.next;
                currentIndex++;
            } 
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if no node at the given index
     */
     
    @Override
    public E get(int index) {
        Node<E> current = head;
        int currentIndex = 0;
        E data = null;
        while (current != null) {
            if (currentIndex == index) {
                data = current.data;
            }
            currentIndex++;
            current = current.next;
        }
        if (data == null) {
            throw new IndexOutOfBoundsException("Index exceeds the size.");
        }
        return data;
    }
     
    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
 
    @Override
    public boolean contains(E obj) {
        Node<E> current = head;
        while (current != null) {
            if (obj.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    /**
     * Removes all of the elements from the list
     */

    @Override
    public void clear() {
        if (head != null) {
            head.setNext(null);
            head = null;
            size = 0;
        }
    }

    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    
    @Override
    public int lastIndexOf(E obj) {
        int lastIndex = -1;
        Node<E> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (obj.equals(current.data)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;
        }
        return lastIndex;
    }

    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */

    @Override
    public String toString() {
        String result = "{";
        Node<E> current = head;
        while (current != null) {
            result += "" + current.data;
            current = current.next;
            if (current != null) {
                result += ", ";
            }
        }
        result += "}";
        return result;
    }
    
    /**
     * Returns an array representation of the list If a list contains A, B, and
     * C, the following should be returned {A, B, C}, If a list
     * contains A, B, C, and C the following should be returned {A, B, C, C}
     *
     * @return an array representing the list
     */

    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        Node<E> current = head;
        int count = 0;
        while (current != null) {
            array[count] = current.getData();
            current = current.next;
            count++;
        }
        return array;
    }
   
    /**
     * Returns true if both lists have the exact same contents
     * in the exact same order
     *
     * @return a boolean of whether two lists have the same contents,
     *         item per item and in the same order
     */
    @Override
    
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            @SuppressWarnings("unchecked")
            SinglyLinkedList<E> other = ((SinglyLinkedList<E>)obj);
            if (other.size() == this.size()) {
                Node<E> current = head;
                Node<E> otherCurrent = other.head;
                while (current != null) {
                    if (!current.getData().equals(otherCurrent.getData())) {
                        return false;
                    }
                    current = current.next();
                    otherCurrent = otherCurrent.next();
                }
                return true;
            }
        }
        return false;
    }
}
