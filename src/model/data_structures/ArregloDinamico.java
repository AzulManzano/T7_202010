package model.data_structures;

import java.util.Iterator;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T[] elementos;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}



	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T[] darArreglo()
	{
		return elementos;
	}


	@Override
	public void agregar(T dato) 
	{
		if(tamanoAct == tamanoMax)
		{
			T[] copia = (T[]) new Comparable[tamanoMax*2];
			tamanoMax = tamanoMax*2;

			for(int i=0; i<tamanoAct; i++ )
			{
				copia[i] = elementos[i];
			}

			elementos = copia;
		}
		elementos[tamanoAct++] = dato;
		tamanoAct ++;
	}

	@Override
	public T buscar(T dato) 
	{
		T bucado = null;
		boolean f = false;
		for(int i=0; i<tamanoAct && f == false; i++ )
		{
			if(elementos[i] == dato)
			{
				bucado = elementos[i];
				f = true;
			}

		}
		return bucado;
	}

	@Override
	public T eliminar(T dato) 
	{
		boolean f = false;
		if(buscar(dato)!=null)
		{
			if(tamanoAct==1)
			{
				elementos[0] = null;
			}
			else if(tamanoAct>1)
			{
				for(int i=0; i<tamanoAct && f == false; i++ )
				{
					if(elementos[i] == dato)
					{
						f = true;
						for(int a = i; a<tamanoAct; a++)
						{
							if(a==tamanoAct-1)
							{
								elementos[a]= null;
							}
							else
							{
								elementos[a]=elementos[a++];
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public T darElemento(int i) 
	{
		if(i<tamanoAct)
		{
			return elementos[i];
		}
		return null;
	}

	@Override
	public Iterator<T> iterator() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void shellSort(T[] datos)
	{
		
	}

	
}
