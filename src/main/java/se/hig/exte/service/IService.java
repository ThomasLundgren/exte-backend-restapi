package se.hig.exte.service;

public interface IService<E> {
	
	public E add(E entity);
		
	public E findById(int id);
	
	public void deleteById(int id);
	
	public void update(E entity);
	
}
