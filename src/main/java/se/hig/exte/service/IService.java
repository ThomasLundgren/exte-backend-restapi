package se.hig.exte.service;

public interface IService<E> {
	
	public E save(E entity);
		
	public E findById(int id);
	
	public boolean deleteById(int id);
	
}
