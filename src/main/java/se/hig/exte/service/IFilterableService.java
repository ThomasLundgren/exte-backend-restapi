package se.hig.exte.service;

import java.util.List;

public interface IFilterableService<E> extends IService<E> {

	public List<E> findAllByParentId(int id);
	
}
