package model.data_structures;

import java.util.Iterator;
//gorditolopez204

public class SeparateChaining<K extends Comparable<K>,V> implements ISeparateChaining<K,V> 
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	private int n;                                
	private int m;    
	private int totales;
	private SequentialSearch<K, V>[] st;
	private int numeroRehashes = 0;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public SeparateChaining()
	{
		totales =0;
		n = 0;
		m = 5;
		st = (SequentialSearch<K, V>[]) new SequentialSearch[m];
		for (int i = 0; i < m; i++)
		{
			st[i] = new SequentialSearch<K, V>();
		}
	}

	public SeparateChaining(int capacidad)
	{
		n = 0;
		m = capacidad;
		st = (SequentialSearch<K, V>[]) new SequentialSearch[m];
		for (int i = 0; i < m; i++)
		{
			st[i] = new SequentialSearch<K, V>();
		}
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	public int darTotalleidos()
	{
		return totales;
	}
	public int darNumeroRehashes()
	{
		return numeroRehashes;
	}
	
	public int darCapacidad()
	{
		return m;
	}

	public SequentialSearch<K, V>[] darArreglo()
	{
		return st;
	}

	public int size() 
	{
		return n;
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
		if (llave != null)
		{
			if (valor == null) 
			{
				delete(llave);
				return;
			}

			if (n >= (m*5)) 
			{
				int tamanoNuevo = darPrimoSiguiente(m);
				resize(tamanoNuevo);
			}

			int i = hash(llave);
			if (!st[i].contains(llave)) n++;
			st[i].put(llave, valor);
			totales++;
		}
	}

	public V get(K llave) 
	{
		if (llave != null)
		{
			int i = hash(llave);
			return st[i].get(llave);
		}
		return null;
	}

	public V delete(K llave) 
	{
		V valorElim = null;
		if (llave != null)
		{
			int i = hash(llave);
			if (st[i].contains(llave)) n--;
			valorElim = st[i].delete(llave);
			totales--;
			
			if (n > 0 && n <= m/2) 
	        {
	        	resize((int)m/4);
	        }
		}
		return valorElim;
	}

	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
	} 

	private void resize(int capacidad) 
	{
		SeparateChaining<K, V> temp = new SeparateChaining<K, V>(capacidad);
		for (int i = 0; i < m; i++) 
		{
			Queue<K> queue = st[i].llaves();
			for (int j = 0; j<st[i].size(); j++) 
			{
				K llvaTemporal = queue.dequeue();
				temp.put(llvaTemporal, st[i].get(llvaTemporal));
			}
		}
		m  = temp.darCapacidad();
		n  = temp.size();
		st = temp.darArreglo();
		numeroRehashes++;
	}

	private int darPrimoSiguiente(int numero)
	{
		int primo = numero*2;
		boolean encontrado = false;
		boolean noSirve = false;

		for(int i = 0; i<numero*2 && encontrado == false; i++)
		{
			primo++;
			for(int j =2; j<numero && noSirve == false; j++)
			{
				if(primo % j == 0)
				{
					noSirve = true;
				}
				if(j == numero-1)
				{
					encontrado = true;
				}
			}
			noSirve = false;
		}

		return primo;
	}

	public Iterator<K> iterator() 
	{
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < m; i++) {
			for (K key : st[i].llaves())
				queue.enqueue(key);
		}
		return queue.iterator();
	}
}
