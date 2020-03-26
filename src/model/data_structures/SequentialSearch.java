package model.data_structures;

import java.util.Iterator;

public class SequentialSearch<K extends Comparable<K>,V> implements ISequentialSearch<K,V>
{
	private int n;           
	private NodoTabla<K,V> first;
	private V eliminado;

	public SequentialSearch()
	{
		n = 0;
		first = null;
	}

	public int size()
	{
		return n;
	}

	public V darEliminando()
	{
		return eliminado;
	}
	
	public void cambiarEliminado(V cambio)
	{
		eliminado = cambio;
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

	public void put(K llave, V valor) 
	{
		if(llave != null)
		{
			if (valor == null) 
			{
				delete(llave);
				return;
			}

			for (NodoTabla<K,V> x = first; x != null; x = x.darSiguiente()) 
			{
				if (llave.equals(x.darLLave())) 
				{
					x.cambiarValor(valor); 
					return;
				}
			}
			
			NodoTabla<K,V> nuevo = new NodoTabla(llave, valor, first);
			first = nuevo;
			n++;
		}
	}

	public V get(K llave) 
	{
		if(llave != null)
		{
			for (NodoTabla<K,V> x = first; x != null; x = x.darSiguiente()) 
			{
				if (llave.equals(x.darLLave()))
					return x.darValor();
			}
		}
		return null;
	}

	public V delete(K llave) 
	{

		if(llave != null)
		{
			first = delete(first, llave);
			return darEliminando();
		}
		return null;
	}

	private NodoTabla<K,V> delete(NodoTabla<K,V> x, K llave)
	{
		cambiarEliminado(null);
		if (x == null) 
			return null;
		if (llave.equals(x.darLLave())) 
		{
			n--;
			cambiarEliminado(x.darValor());
			return x.darSiguiente();
		}
		x.asignarSiguiente(delete(x.darSiguiente(), llave));
		return x;
	}

	public Queue<K> llaves()
	{
		Queue<K> queue = new Queue<K>();
		for (NodoTabla<K,V> x = first; x != null; x = x.darSiguiente())
			queue.enqueue(x.darLLave());
		return  queue;
	}
	
	public Iterator<K> iterator() 
	{
		Queue<K> queue = new Queue<K>();
		for (NodoTabla<K,V> x = first; x != null; x = x.darSiguiente())
			queue.enqueue(x.darLLave());
		return (Iterator<K>) queue;
	}
}
