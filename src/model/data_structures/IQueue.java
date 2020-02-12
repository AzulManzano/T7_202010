package model.data_structures;

public interface IQueue<T> extends Iterable<T> 
{
	public void enqueue(T dato);

	public T dequeue();

	public Integer getSize();

	public boolean isEmpty();

	public T getElement();
}
