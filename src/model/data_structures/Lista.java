package model.data_structures;

import java.util.Iterator;

public class Lista<K extends Comparable<K>> implements ILista<K>
{

	private Nodo<K> primero;

	private int tamano;

	public Lista()
	{
		primero = null;
		tamano = 0;
	}


	public int size() 
	{
		return tamano;
	}

	public boolean isEmpty() 
	{
		return size() == 0;
	}

	public boolean contains(K llave) 
	{
		if (llave != null)
		{
			return get(llave) != null;
		}
		return false;
	}

	public void add(K pArco) 
	{
		if(pArco != null)
		{
			if (contains(pArco) == false) 
			{
				Nodo<K> nuevo = new Nodo<K>(pArco);
				nuevo.asignarSiguiente(primero);
				primero = nuevo;

				tamano++;
			}
		}
	}

	public K get(K llave) 
	{
		if(llave != null)
		{
			for (Nodo<K> x = primero; x != null; x = x.darSiguiente()) 
			{
				if (llave.compareTo(x.darElemento()) == 0)
					return x.darElemento();
			}
		}
		return null;
	}

	public Queue<K> darArcos()
	{
		Queue<K> queue = new Queue<K>();
		for (Nodo<K> x = primero; x != null; x = x.darSiguiente())
			queue.enqueue(x.darElemento());
		return  queue;
	}

	public Iterator<K> iterator() 
	{
		Queue<K> queue = new Queue<K>();
		for (Nodo<K> x = primero; x != null; x = x.darSiguiente())
			queue.enqueue(x.darElemento());
		return (Iterator<K>) queue;
	}


}
