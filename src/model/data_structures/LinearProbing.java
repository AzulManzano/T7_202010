package model.data_structures;

import java.util.Iterator;

public class LinearProbing<K extends Comparable<K>, V> implements ILinearProbing<K,V>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Atributo que represneta el tamano actual.
	 */
	private int n;

	/**
	 * Atributo que representa la capacidad.
	 */
	private int m;

	private K[] llaves;

	private V[] valores;
	
	private int numeroRehashes = 0;


	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	public LinearProbing()
	{
		m = 5;
		n = 0;
		llaves = (K[])   new Comparable[m];
		valores = (V[]) new Object[m];
	}

	public LinearProbing(int capacidad)
	{
		m = capacidad;
		n = 0;
		llaves = (K[])   new Comparable[m];
		valores = (V[]) new Object[m];
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	public int darNumeroRehashes()
	{
		return numeroRehashes;
	}
	public V[] darValores()
	{
		return valores;
	}

	public K[] darLlaves()
	{
		return llaves;
	}

	public int darCapacidad()
	{
		return m;
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

			if (n >= (m*0.75))
			{
				int tamanoNuevo = darPrimoSiguiente(m);
				resize(tamanoNuevo);
			}

			int i;
			for (i = hash(llave); llaves[i] != null; i = (i + 1) % m) 
			{
				if (llaves[i].equals(llave)) 
				{
					valores[i] = valor;
					return;
				}
			}
			llaves[i] = llave;
			valores[i] = valor;
			n++;
		}
	}

	public V get(K llave) 
	{
		if(llave != null)
		{
			for (int i = hash(llave); llaves[i] != null; i = (i + 1) % m)
			{
				if (llaves[i].equals(llave))
				{
					return valores[i];
				}
			}
		}
		return null;
	}

	public V delete(K llave) 
	{
		V valorFinal = null;
		if(llave != null)
		{
			if(contains(llave) == false)
			{
				return null;
			}

			int i = hash(llave);
			while (!llave.equals(llaves[i])) 
			{
				i = (i + 1) % m;
			}
			
			valorFinal = valores[i];
			llaves[i] = null;
	        valores[i] = null;
	        
	        i = (i + 1) % m;
	        while (llaves[i] != null) 
	        {
	            K   keyToRehash = llaves[i];
	            V valToRehash = valores[i];
	            llaves[i] = null;
	            valores[i] = null;
	            n--;
	            put(keyToRehash, valToRehash);
	            i = (i + 1) % m;
	        }

	        n--;
	        
	        if (n > 0 && n <= m/8) 
	        {
	        	resize((int)m/4);
	        }
	        
	        assert check();
		}

		return valorFinal;
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
		if(llave != null)
		{
			return get(llave) != null;
		}
		return false;
	}

	private int hash(K llave) 
	{
		return (llave.hashCode() & 0x7fffffff) % m;
	}

	public void resize(int capacidad)
	{
		LinearProbing<K, V> temp = new LinearProbing<K, V>(capacidad);
		for (int i = 0; i < m; i++) 
		{
			if (llaves[i] != null) 
			{
				temp.put(llaves[i], valores[i]);
			}
		}
		llaves = temp.darLlaves();
		valores = temp.darValores();
		m    = temp.darCapacidad();
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
	
	private boolean check() 
	{
        if (n > (m*0.75)) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        for (int i = 0; i < m; i++) {
            if (llaves[i] == null) continue;
            else if (get(llaves[i]) != valores[i]) {
                System.err.println("get[" + llaves[i] + "] = " + get(llaves[i]) + "; vals[i] = " + valores[i]);
                return false;
            }
        }
        return true;
    }
	
	public Queue<K> llaves() 
	{
		 Queue<K> queue = new  Queue<K>();
		for (int i = 0; i < m; i++)
			if (llaves[i] != null) queue.enqueue(llaves[i]);
		return queue;
	}

	public Iterator<K> iterator() 
	{
		 Queue<K> queue = new  Queue<K>();
		for (int i = 0; i < m; i++)
			if (llaves[i] != null) queue.enqueue(llaves[i]);
		return queue.iterator();
	}
}
