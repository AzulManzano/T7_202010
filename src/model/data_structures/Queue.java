package model.data_structures;

import java.util.Iterator;

public class Queue<T> implements IQueue<T> 
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Represneta el primer nodo .
	 */
	private Nodo<T> primero;

	private int tamano;

	/**
	 * Represneta el primer nodo de la pila.
	 */
	private Nodo<T> ultimo;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Contructor de la clase.
	 * @param pPrimero El primer nodo que ingresa en la pila.
	 * @param pUltimo El ultimo nodo que ingresa en la pila.
	 */
	public Queue()
	{
		tamano = 0;
		primero = null;
		ultimo = primero;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * inserta un nuevo elemento al "final" de la cola (elemento más reciente)
	 * @param dato El nodo que ingresa a la cola.
	 */
	public void enqueue(T dato) 
	{
		Nodo<T> nuevo = new Nodo<T>(dato);
		if(isEmpty() == false)
		{
			ultimo.asignarSiguiente(nuevo);
			ultimo = nuevo;	
		}
		else 
		{
			primero = nuevo;
			ultimo = primero;
		}
		tamano++;
	}

	/**
	 * Saca/elimina el elemento del "principio" de la cola (elemento menos reciente) y lo retorna. 
	 * @return El elemento mas antiguo.
	 */
	public T dequeue() 
	{
		if(isEmpty() == false)
		{
			Nodo<T> retorna = primero;
			if(primero != ultimo)
			{
				primero = primero.darSiguiente();
			}
			else 
			{
				primero = null;
				ultimo = null;
			}
			tamano--;
			return retorna.darElemento();
		}
		return null;
	}

	/**
	 * Consultar el tamaño (número de elementos) de la cola.
	 * @return La cantidad de nodos de la cola.
	 */
	public Integer getSize() 
	{
		return tamano;
	}

	/**
	 * Consultar si la cola está vacía.
	 * @return True si la cola esta vacia, false en caso contrario.
	 */
	public boolean isEmpty() 
	{
		boolean respuesta = false;
		if (primero == null && ultimo == null)
		{
			respuesta = true;
		}
		return respuesta;
	}

	/**
	 * Consultar el elemento del "principio" de la cola sin eliminarlo
	 * @return El elemento mas rviejo en la cola.
	 */
	public T getElement()  
	{
		if(primero != null)
		{
			return primero.darElemento();
		}
		return null;
	}

	public Iterator<T> iterator() 
	{
		return new Iterador<T>(primero);
	}

}
