package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	
	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E> (null);
		tail = new LLNode<E> (null);
		
		head.next = tail;
		tail.prev = head;
	}

	
	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException();
		
		LLNode<E> n = new LLNode<E>(element);
		if(head == null)
		{
			head.next = n;
			n.prev = head;
			n.next = tail;
			tail.prev = n;
		}
		else
		{
			tail.prev.next = n;
			n.next = tail;
			n.prev = tail.prev;
			tail.prev = n;
		}
		size += 1; 
		
		return true;
	}

	
	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index out of bounds.");
		else
		{
			LLNode<E> current = new LLNode<E>(null);
			current = head.next;
			
			for(int counter=0; counter<index; counter++)
				current = current.next;
			
			return current.data;
		}
	}

	
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			throw new NullPointerException();
		
		if(head == null && index != 0)
			throw new IndexOutOfBoundsException();
		
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> n = new LLNode<E>(element);
		if(head == null || head != null && index == 0)
		{
			head.next = n;
			n.prev = head;
			n.next = tail;
			tail.prev = n;
		}
		else if(index == size)
		{
			tail.prev.next = n;
			n.next = tail;
			n.prev = tail.prev;
			tail.prev = n;
		}
		else
		{
			LLNode<E> current = new LLNode<E>(null);
			current = head.next;
			
			for(int counter=0; counter<index; counter++)
			{
				current = current.next;
			}
			
			current.prev.next = n;
			n.next = current;
			n.prev = current.prev;
			current.prev = n;
		}
		
		size += 1;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	
	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		LLNode<E> current = head.next;
		LLNode<E> previous;
		
		if(index < 0 || index >= size || head == null)
			throw new IndexOutOfBoundsException();
		else
		{
			for(int i=0; i<index; i++)
				current = current.next;
			
			previous = current.prev;
			
			previous.next = current.next;
			current.next.prev = previous;
			current.next = current.prev = null;
			
			size -= 1;
		}
		
		return current.data;
	}

	
	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> current = head.next;
		
		if(index < 0 || index >= size || head == null)
			throw new IndexOutOfBoundsException();
		
		if(element == null)
			throw new NullPointerException();
		else
		{
			for(int i=0; i<index; i++)
				current = current.next;
			
			current.data = element;
		}
		
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
