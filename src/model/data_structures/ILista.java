package model.data_structures;

public interface ILista<K> extends Iterable<K> 
{
	public int size();

	public void add(K pArco);
}
