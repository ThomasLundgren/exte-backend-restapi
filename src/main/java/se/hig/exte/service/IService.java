package se.hig.exte.service;

import java.util.List;

public interface IService<E> {
	
	public E save(E entity);
		
	public E findById(int id);
	
	public void deleteById(int id);
	
	public List<E> findAll();
	
}
