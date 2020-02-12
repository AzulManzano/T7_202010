package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que permite iterar una estructura de datos.
 */
public class Iterador<T>  implements Iterator<T>
{

	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Atributo que representa el siguiente. 
	 */
	private Nodo<T> siguiente;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 *  Constructor de la clase iterador. 
	 *  @param pNodo siguiente nodo.
	 */
	public Iterador (Nodo<T> pNodo)
	{
		siguiente = pNodo;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Retornar si hay o no siguiente.
	 * @return True si hay siguiente. False de lo contrario.
	 */
	public boolean hasNext()
	{
		return siguiente!=null;
	}

	/**
	 * Retornar el siguiente.
	 * @return Siguinete.
	 */
	public T next() throws NoSuchElementException 
	{
		if(siguiente==null)
		{
			throw new NoSuchElementException("No hay siguiente");
		}
		T objeto = siguiente.darElemento();
		siguiente = siguiente.darSiguiente();
		return objeto;
	}
}
