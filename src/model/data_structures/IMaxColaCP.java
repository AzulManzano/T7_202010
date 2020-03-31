package model.data_structures;

public interface IMaxColaCP<T> extends Iterable<T> 
{
	/*
	 * Retorna número de elementos presentes en la cola de prioridad.
	 */
	public int darNumElementos();

	/*
	 * Agrega un elemento a la cola de prioridad. Para comparar la prioridad de
	 * dos elementos T se debe usar el comparador "natural" de la clase T.
	 */
	public void agregar(T elemento);

	/*
	 * Saca/atiende el elemento máximo en la cola y lo retorna; null en caso de 
	 * cola vacía.
	 */
	public T sacarMax();

	/*
	 * Obtener el elemento máximo (sin sacarlo de la Cola); null en caso de cola 
	 * vacía.
	 */
	public T darMax();

	/*
	 * Retorna si la cola está vacío o no.
	 */
	public boolean esVacia();
}
