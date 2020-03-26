package model.data_structures;

public interface ILinearProbing<K, V> extends Iterable<K> 
{
	/**
	 * Agregar una dupla (K, V) a la tabla. Si la llave K existe, 
	 * se reemplaza su valor V asociado. V no puede ser null.
	 * En este caso una llave K solo tiene asociado un valor V.
	 * @param llave Valor de la llave asociado al valor.
	 * @param valor Valor del valor asociado  la llave.
	 */
	public void put(K llave,V valor);

	/**
	 * Obtener el valor V asociado a la llave K. Se obtiene null
	 * solo si la llave K no existe. Funciona solo en el caso que 
	 * se agreguen duplas con put( … ).
	 * @param llave Clave asociado al valor. 
	 * @return El valor asociado a la llave.
	 */
	public V get(K llave);

	/**
	 * Borrar la dupla asociada a la llave K. Se obtiene el valor
	 * V asociado a la llave K. Se obtiene null solo si la llave K
	 * no existe. Funciona solo en el caso que se agreguen
	 * duplas con put( … ).
	 * @param llave Clave asociado al valor. 
	 * @return El valor asociado a la llave.
	 */
	public V delete(K llave);

}
