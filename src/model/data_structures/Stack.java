package model.data_structures;

import java.util.Iterator;

/**
 * Clase que representa un pila.
 */
public class Stack<T> implements IStack<T>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Represneta el primer nodo de la pila.
	 */
	private Nodo<T> primero;

	private int tamano;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Contructor de la clase.
	 * @param pPrimero El primer nodo que ingresa en la pila.
	 */
	public Stack()
	{
		tamano = 0;
		primero = null;		
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Inserta un nuevo elemento en el tope de la pila.
	 * @param dato el nuevo nodo para insertar.
	 */
	public void push(T dato) 
	{
		Nodo<T> nuevo = new Nodo<T>(dato);
		if(primero!=null)
		{
			nuevo.asignarSiguiente(primero);
			primero = nuevo; 
		}
		else
		{
			primero = nuevo;
		}
		tamano++;
	}

	/**
	 * Saca/elimina el elemento tope de la pila (elemento más reciente) y lo retorna.
	 * @return El elemento mas reciente.
	 */
	public T pop() 
	{
		if(primero!=null)
		{
			Nodo<T> cambio = primero;
			primero = primero.darSiguiente();
			tamano--;
			return cambio.darElemento();	
		}

		return null;
	}

	/**
	 * Consultar el tamaño (número de elementos) de la pil.
	 * @return Numero de nodos en la pila.
	 */
	public Integer getSize() 
	{
		return tamano;
	}

	/**
	 * Consultar si la pila está vacía.
	 * @return True si la lista esta vacia, false en caso contrario.
	 */
	public boolean isEmpty() 
	{
		boolean respuesta = false;
		if (primero == null)
		{
			respuesta = true;
		}
		return respuesta;
	}

	/**
	 * Consultar el elemento tope de la pila sin eliminarlo.
	 * @return El elemento mas reciente en la pila.
	 */
	public T getElement()
	{
		if(primero!=null)
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
