package model.data_structures;

import java.util.Iterator;

public class MaxColaCP<T extends Comparable<T>> implements IMaxColaCP<T> 
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Atributo que representa el primer elemento de la cola de prioridad.
	 */
	private Nodo<T> primero;

	/**
	 * Atributo que representa el ultimo elemento de la cola de prioridad. 
	 */
	private Nodo<T> ultimo;

	/**
	 * Atributo que hace referencia al tamaño.
	 */
	private int tamano;

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Constructor.
	 **/
	public MaxColaCP()
	{
		tamano = 0;
		primero = null;
		ultimo = null;
	}

	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Retorna número de elementos presentes en la cola de prioridad.
	 **/
	public int darNumElementos() 
	{
		return tamano;
	}

	/**
	 * Agrega un elemento a la cola de prioridad. Para comparar la prioridad de
	 * dos elementos T se debe usar el comparador "natural" de la clase T.
	 */
	public void agregar(T elemento) 
	{
		Nodo<T> nuevo = new Nodo<T>(elemento);

		if(esVacia() == false)
		{
			if(primero.darElemento().compareTo(elemento) <= 0)
			{
				nuevo.asignarSiguiente(primero);
				primero = nuevo;
			}
			else if(ultimo.darElemento().compareTo(elemento) >= 0)
			{
				ultimo.asignarSiguiente(nuevo);
				ultimo = nuevo;
			}
			else 
			{
				Nodo<T> corredor = primero.darSiguiente();
				Nodo<T> cambiador = primero;
				boolean termine = false;
				while(corredor != null && termine == false)
				{
					if(corredor.darElemento().compareTo(elemento) >= 1 )
					{
						corredor = corredor.darSiguiente();
						cambiador = cambiador.darSiguiente();
					}
					else
					{
						nuevo.asignarSiguiente(corredor);
						cambiador.asignarSiguiente(nuevo);
						termine = true;
					}

				}
			}
		}
		else 
		{
			primero = nuevo;
			ultimo = nuevo;
		}
		tamano++;
	}


	/**
	 * Saca/atiende el elemento máximo en la cola y lo retorna; null en caso de 
	 * cola vacía.
	 */
	public T sacarMax() 
	{
		if(esVacia() == false)
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
	 * Obtener el elemento máximo (sin sacarlo de la Cola); null en caso de cola 
	 * vacía.
	 */
	public T darMax() 
	{
		return primero.darElemento();
	}


	/**
	 *  Retorna si la cola está vacío o no.
	 */
	public boolean esVacia() 
	{
		return (tamano == 0);
	}


	/**
	 * Metodo iterador.
	 */
	public Iterator<T> iterator() 
	{
		return new Iterador<T>(primero);
	}

}
