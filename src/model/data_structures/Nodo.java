package model.data_structures;

/**
 * Clase que representa un nodo.
 */
public class Nodo<T>
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Atributo que representa el elemento que se encuentra en el nodo. 
	 */
	private T objeto;

	/**
	 * Atributo que representa el siguiente nodo. 
	 */
	private Nodo<T> siguiente;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 *  Constructor de la clase Nodo. 
	 *  @param pObjeto Objeto con el cual se va inicializar el nodo.
	 */
	public Nodo(T pObjeto)
	{
		objeto = pObjeto;
		siguiente = null;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Retornar el elemento que esta en el nodo.
	 * @return el objeto.
	 */
	public T darElemento()
	{
		return objeto;
	}

	/**
	 * Retornar el siguiente nodo.
	 * @return Siguinete nodo.
	 */
	public Nodo<T> darSiguiente()
	{
		return siguiente;
	}

	/**
	 * Asigna al nodo actual un nodo siguiente que entra por parametro. 
	 * @param pNodo el nodo a asignar como siguiente.
	 */
	public void asignarSiguiente(Nodo<T> pNodo)
	{
		siguiente = pNodo;
	}
}
