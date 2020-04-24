package model.data_structures;

import java.util.Iterator;

public class MaxHeapCP<T extends Comparable<T>> implements IMaxHeapCP<T> 
{
	//----------------------------------------------------------------
	//Atributos ------------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * Atributo que representa el primer elemento de la cola de prioridad.
	 */
	private T[] elementos;

	/**
	 * Atributo que hace referencia al tamaño.
	 */
	private int tamano;
	

	//----------------------------------------------------------------
	//Constructor ----------------------------------------------------
	//----------------------------------------------------------------

	/**
	 * constructor numero uno, este recibe una capacidad inicial y genera un arreglo vacio. 
	 */
	public MaxHeapCP(int capacidad)
	{

		tamano = 0;
		elementos = (T[]) new Comparable[capacidad + 1];
	}

	/**
	 * Constractor numero dos. Este recibe una  lista y la agraga a la cola de prioridad.
	 */
	public MaxHeapCP(T[] lista) 
	{
		tamano = lista.length;
		elementos = (T[]) new Comparable[lista.length + 1];
		for (int i = 0; i < tamano; i++)
			elementos[i+1] = lista[i];
		for (int k = tamano/2; k >= 1; k--)
			sink(k);
		assert isMaxHeap();
	}
	
	public MaxHeapCP()
	{
		tamano = 0;
		elementos = (T[]) new Comparable[50];
	}
	//----------------------------------------------------------------
	//Metodos --------------------------------------------------------
	//----------------------------------------------------------------


	public int darNumElementos() 
	{
		return tamano;
	}


	public void agregar(T elemento) 
	{        
        if(tamano == elementos.length - 1)
        {
        	resize(2*elementos.length);
        }
        
        elementos[++tamano] = elemento;
        swim(tamano);
        assert isMaxHeap();
	}


	public T sacarMax() 
	{	        
		if (esVacia() == false)
		{
			T maximo = elementos[1];
			exch(1,tamano--);
			sink(1);
			elementos[tamano+1] = null;

			if((tamano > 0) && (tamano ==(elementos.length -1) / 4))
			{
				resize(elementos.length / 2);
			}

			assert isMaxHeap();
			return maximo;
		}

		return null;
	}


	public T darMax() 
	{
		if(esVacia() == false)
		{
			return elementos[1];
		}
		else
		{
			return null;
		}
	}

	public boolean esVacia() 
	{
		return (tamano == 0);
	}

	//----------------------------------------------------------------
	//Metodos de ordenamiento ----------------------------------------
	//----------------------------------------------------------------

	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= tamano) {
			int j = 2*k;
			if (j < tamano && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) 
	{
		return elementos[i].compareTo(elementos[j]) < 0;    
	}

	private void exch(int i, int j) 
	{
		T swap = elementos[i];
		elementos[i] = elementos[j];
		elementos[j] = swap;
	}


	private boolean isMaxHeap() {
		for (int i = 1; i <= tamano; i++) {
			if (elementos[i] == null) return false;
		}
		for (int i = tamano+1; i < elementos.length; i++) {
			if (elementos[i] != null) return false;
		}
		if (elementos[0] != null) return false;
		return isMaxHeapOrdered(1);
	}

	private boolean isMaxHeapOrdered(int k) {
		if (k > tamano) return true;
		int left = 2*k;
		int right = 2*k + 1;
		if (left  <= tamano && less(k, left))  return false;
		if (right <= tamano && less(k, right)) return false;
		return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
	}

	private void resize(int capacidad) 
	{
		assert capacidad > tamano;
		T[] temp = (T[]) new Comparable[capacidad];
		for (int i = 1; i <= tamano; i++) {
			temp[i] = elementos[i];
		}
		elementos = temp;
	}

	//----------------------------------------------------------------
	//Iterador -------------------------------------------------------
	//----------------------------------------------------------------

	public Iterator<T> iterator() 
	{
		return new HeapIterator(elementos);
	}
	
}
