package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapIterator<T extends Comparable<T>> implements Iterator<T>
{
	private MaxHeapCP<T> copy;

	public HeapIterator(T[] elemenotos) 
	{
		copy = new MaxHeapCP<T>(elemenotos.length);

		for (int i = 1; i <=elemenotos.length ; i++)
		{
			copy.agregar(elemenotos[i]);
		}
	}

	public boolean hasNext()  
	{ 
		return !copy.esVacia() ;                    
	}
	

	public T next() {
		if (!hasNext()) 
		{
			throw new NoSuchElementException();
		}
		return copy.sacarMax();
	}
}

