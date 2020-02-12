package model.data_structures;

public interface IStack<T> extends Iterable<T>  
{
	public void push(T dato);

	public T pop();

	public Integer getSize();

	public boolean isEmpty();

	public T getElement();
}
