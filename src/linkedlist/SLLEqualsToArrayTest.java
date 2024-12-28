package linkedlist;

import java.util.Arrays;

import student.TestCase;

/**
 * 
 * Tests the equals and toArray methods of a singly linked list.
 * 
 * @author Navanit Krishna Satish Kumar
 * @version 10.25.2024
 *
 */
public class SLLEqualsToArrayTest extends TestCase {

    private SinglyLinkedList<String> emptyListA;
    private SinglyLinkedList<String> emptyListB;
    private SinglyLinkedList<String> smallListA;
    private SinglyLinkedList<String> smallListB;
    private SinglyLinkedList<String> bigListA;
    private SinglyLinkedList<String> bigListB;
    private SinglyLinkedList<String> repeating;
    private String nullObject;

    /**
     * Initializes 2 empty lists, 2 lists with a small number
     * of items, and 2 lists with a large number of items
     * 
     */
    public void setUp() {
        emptyListA = new SinglyLinkedList<String>();
        emptyListB = new SinglyLinkedList<String>();

        smallListA = new SinglyLinkedList<String>();
        smallListB = new SinglyLinkedList<String>();
        
        repeating = new SinglyLinkedList<String>();

        repeating.add("test");
        repeating.add("test");
        repeating.add("test1");

        smallListA.add("soccer");
        smallListA.add("swimming");
        smallListA.add("gymnastics");

        smallListB.add("soccer");
        smallListB.add("swimming");
        smallListB.add("gymnastics");

        bigListA = new SinglyLinkedList<String>();

        for (int i = 0; i < 100; i++) {
            bigListA.add("sport" + i);
        }

        bigListB = new SinglyLinkedList<String>();
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }
        nullObject = null;
    }


    /**
     * Tests first add method 
     */
    public void testAddIndex()
    {
        Exception exception = null;
        try
        {
            emptyListA.add(0, nullObject);
        }
        catch (Exception e)
        {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IllegalArgumentException);
        
        exception = null;
        Exception except2 = null;
        try
        {
            emptyListA.add(-1, "not in index range");
        }
        catch (Exception e)
        {
            exception = e;
        }
        
        try
        {
            emptyListA.add(200, "above index range");
        }
        catch (Exception e)
        {
            except2 = e;
        }
        assertNotNull(exception);
        assertNotNull(except2);
        assertTrue(exception instanceof IndexOutOfBoundsException);
        assertTrue(except2 instanceof IndexOutOfBoundsException);

        assertEquals(emptyListA.size(), 0, 0.01);
        emptyListA.add(0, "first");
        assertEquals(emptyListA.size(), 1, 0.01);
        assertEquals(emptyListA.toString(), "{first}");

        smallListA.add(0, "beginning");
        assertEquals(smallListA.size(), 4, 0.01);
        assertEquals(smallListA.toString(), "{beginning, soccer, "
                + "swimming, gymnastics}");

        smallListA.add(2, "middle");
        assertEquals(smallListA.size(), 5, 0.01);
        assertEquals(smallListA.toString(), "{beginning, "
                + "soccer, middle, swimming, gymnastics}");
    } 
    /**
     * Tests second add method
     */
    public void testAddWithoutIndex() {
        Exception exone = null;
        try {
            emptyListA.add(nullObject);
        }
        catch (Exception e) {
            exone = e;
        }
        assertNotNull(exone);
        assertTrue(exone instanceof IllegalArgumentException);
    }
    
    /**
     * Tests the equals method on an empty list
     */
    public void testEqualsEmptyList() {
        assertEquals(emptyListA, emptyListA);
        assertEquals(emptyListA, emptyListB);
        assertFalse(emptyListA.equals(nullObject));
        assertFalse(emptyListA.equals("soccer"));
        assertFalse(emptyListA.equals(smallListA));
        assertFalse(smallListA.equals(emptyListA));
        emptyListB.add("jump roping");
        assertFalse(emptyListA.equals(emptyListB));
        smallListA.clear();
        assertEquals(emptyListA, smallListA);
    }
    
    /**
     * Tests first remove method
     */
    public void testRemoveNoIndex()
    {
        assertEquals(emptyListA.size(), 0, 0.01);
        emptyListA.add("test");
        assertEquals(emptyListA.size(), 1, 0.01);
        assertTrue(emptyListA.remove("test"));
        assertEquals(emptyListA.size(), 0, 0.01);
        
        assertFalse(emptyListA.remove("tet"));
        
        assertEquals(smallListA.size(), 3, 0.01);
        assertTrue(smallListA.remove("swimming"));
        assertEquals(smallListA.size(), 2, 0.01);
        
        emptyListB.add("test");
        assertFalse(emptyListB.remove("wow"));
        assertTrue(emptyListB.remove("test"));
        
        assertFalse(smallListA.remove("swimming"));
    }
    
    /**
     * Tests second remove method
     */
    public void testRemoveIndex() {
        Exception exception = null;
        try {
            emptyListA.remove(1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);
        
        exception = null;
        try {
            smallListA.remove(-1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);
        
        exception = null;
        try {
            smallListA.remove(200);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);

        assertEquals(smallListA.toString(), "{soccer, swimming, gymnastics}");
        assertTrue(smallListA.remove(1));
        assertEquals(smallListA.size(), 2);
        assertEquals(smallListA.toString(), "{soccer, gymnastics}");
        assertTrue(smallListA.remove(0));
        assertEquals(smallListA.size(), 1);
        assertEquals(smallListA.toString(), "{gymnastics}");    
    }
    
    /**
     * Tests get method
     */
    public void testGet() {
        assertEquals(smallListA.get(0), "soccer");
        assertEquals(smallListA.get(1), "swimming");
        assertEquals(smallListA.get(2), "gymnastics");
        Exception exception = null;
        try {
            smallListA.get(-1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }
    
    /**
     * Tests contains method
     */
    public void testContains() {
        assertTrue(smallListA.contains("gymnastics"));
        assertFalse(smallListA.contains("test"));
    }

    /**
     * Tests the equals method on a list with a small number of items in it
     */
    public void testEqualsSmallList() {
        assertEquals(smallListA, smallListA);
        assertEquals(smallListA, smallListB);
        assertFalse(smallListA.equals(nullObject));
        assertFalse(smallListA.equals("soccer"));
        assertFalse(smallListA.equals(bigListA));
        assertFalse(smallListA.equals(emptyListA));
        smallListB.add("jump roping");
        assertFalse(smallListA.equals(smallListB));
        smallListA.add("rope jumping");
        assertFalse(smallListA.equals(smallListB));
        smallListA.remove(3);
        smallListA.add("jump roping");
        assertEquals(smallListA, smallListB);
    }

    /**
     * Tests the equals method on a list with a large number of items in it
     */
    public void testEqualsBigList() {
        assertEquals(bigListA, bigListA);
        assertEquals(bigListA, bigListB);
        assertFalse(bigListA.equals(nullObject));
        assertFalse(bigListA.equals("soccer"));
        assertFalse(bigListA.equals(smallListA));
        assertFalse(bigListA.equals(emptyListA));
        bigListB.add("jump roping");
        assertFalse(bigListA.equals(bigListB));
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 100; i  > 0; i--) {
            bigListB.add("sport" + i);
        }
        assertFalse(bigListA.equals(bigListB));
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 0; i < 50; i++) {
            bigListB.add("sport" + i);
        }
        for (int i = 0; i < 50; i++) {
            bigListB.add("sport" + i);
        }
        assertFalse(bigListA.equals(bigListB));
        bigListB.clear();
        assertFalse(bigListA.equals(bigListB));
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }
        assertEquals(bigListA, bigListB);
    }
    
    /**
     * Tests lastIndexOf method
     */
    public void testLastIndexOf() {
        assertEquals(repeating.lastIndexOf("test1"), 2, 0.01);
        assertEquals(repeating.lastIndexOf("test"), 1, 0.01);
        assertEquals(repeating.lastIndexOf("wow"), -1, 0.01);
    }
    
    /**
     * Tests clear method
     */
    public void testClear() {
        assertEquals(emptyListA.size(), 0, 0.01);
        emptyListA.clear();
        assertEquals(emptyListA.size(), 0, 0.01);
        assertEquals(smallListA.size(), 3, 0.01);
        smallListA.clear();
        assertEquals(smallListA.size(), 0, 0.01);
    }

    /**
     * Tests the toArray method on an empty list
     */
    public void testToArrayEmpty() {
        Object[] emptyArray = {};
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(emptyListA.toArray(), smallListB.toArray()));
        Object[] oneItemArray = { "one thing" };
        emptyListA.add("one thing");
        assertTrue(Arrays.equals(emptyListA.toArray(), oneItemArray));
    }

    /**
     * Tests the toArray method on a list with items in it
     */
    public void testToArrayContents() {
        Object[] origArray = { "soccer", "swimming", "gymnastics" };
        assertTrue(Arrays.equals(smallListA.toArray(), origArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(smallListA.toArray(), bigListB.toArray()));
    }
}

