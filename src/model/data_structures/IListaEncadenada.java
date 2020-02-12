package model.data_structures;

public interface IListaEncadenada<T> extends Iterable<T> 
{
    public Integer getSize();

    public void add(T dato);
    
    public void addAtEnd(T dato);
    
    public void addAtK (T dato, int k) throws Exception;
    
    public T getElement(int k) throws Exception;
    
    public T getCurrentElement();
    
    public boolean delete(T dato);
    
    public boolean deleteAtK(int k) throws Exception; 
}
