package se.hig.exte.service;

import java.util.List;

/**
 * An interface specifying the methods that a {@code CrudService} - a Service that performs CRUD operations
 * on Entity objects - must implement.
 * @param <E> The type of Entity which the {@code CrudService} operates on.
 */
public interface CrudService<E> {
	
	/**
	 * Saves the entity to the database. Used for adding and updating.
	 * @param entity The entity to save.
	 * @return
	 */
	public E save(E entity);
		
	/**
	 * Fetches the entity with the given ID from the database.
	 * @param id The ID of the entity to fetch.
	 * @return The entity.
	 */
	public E findById(int id);

	/**
	 * Fetches all entities from the database.
	 * @return A {@link List} containing all entities.
	 */
	public List<E> findAll();
	
	/**
	 * Deletes the entity with the specified ID from the database.
	 * @param id The ID of the entity to delete.
	 */
	public void deleteById(int id);
	
}
