/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			list1.remove(-3);
			fail("Check: remove element from -ve index");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.remove(20);
			fail("Check: remove element from index >= size");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.remove(0);
			fail("Check: remove element where head == null i.e. list is empty");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		
		//adding null element to the list, add should throw exception
		try {
			list1.add(null);
			fail("Check null pointer exception.");
		}
		catch(NullPointerException e) {
			
		}
		
		//adding element to an empty list
		emptyList.add(11);
		assertEquals("Add: check element is added to an empty list", (Integer)11, emptyList.get(0));
		
		//adding element to a non-empty list
		shortList.add("C");
		assertEquals("Add: check last element added is C", "C", shortList.get(2));
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		
		//adding null element
		try {
			list1.add(1, null);
			fail("adding null element");
		}
		catch(NullPointerException e) {
			
		}
		
		emptyList.add(0, 1);
		assertEquals("Add: at index 0 in empty list", (Integer)1, emptyList.get(0));
		
		//adding element to empty list, index != 0
		try {
			emptyList.add(2, 2);
			fail("adding element to empty list, index != 0");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		//adding element at index < 0, index > size+1
		try {
			list1.add(-2,  11);
			fail("adding element at index < 0");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		
		try {
			list1.add(10, 20);
			fail("adding element at index > size+1");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		//adding at index 0
		emptyList.add(0, 13);
		assertEquals("Add: index 0", (Integer)13, emptyList.get(0));
		
		//adding in between
		list1.add(2, 14);
		assertEquals("Add: index in between 0 and size", (Integer)14, list1.get(2));
		
		//adding at index = size
		shortList.add("C");
		assertEquals("Add: index = size", "C", shortList.get(2));
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    list1.set(1, 11);
	    assertEquals("Set: check replaced element", (Integer)11, list1.get(1));
	    
	    try {
	    	list1.set(-1, 99);
	    	fail("index < 0");
	    }
	    catch(IndexOutOfBoundsException e) {
	    	
	    }
	    
	    try {
	    	list1.set(10, 98);
	    	fail("index >= size");
	    }
	    catch(IndexOutOfBoundsException e) {
	    	
	    }
	    
	    try {
	    	emptyList.set(0, 97);
	    	fail("head == null");
	    }
	    catch(IndexOutOfBoundsException e) {
	    	
	    }
	    
	    try {
	    	list1.set(1, null);
	    	fail("element = null");
	    }
	    catch(NullPointerException e) {
	    	
	    }
	}
	
	
	// TODO: Optionally add more test methods.
	
}
