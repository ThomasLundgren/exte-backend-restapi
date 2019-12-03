package se.hig.exte.service;

public interface IService<E> {
	
	public E add(E entity);
		
	public E getById(int id);
	
	public void deleteById(int id);
	
}
