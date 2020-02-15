package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Tag;
import se.hig.exte.repository.TagRepository;

/**
 * A Service used for performing CRUD operations on {@link Tag} objects.
 */
@Service
public class TagService implements CrudService<Tag> {

	private final TagRepository tagRepository;

	/**
	 * Creates a {@code SibjectService} object.
	 * 
	 * @param tagRepo The {@link TagRepository} to use for CRUD operations.
	 */
	@Autowired
	public TagService(TagRepository tagRepo) {
		this.tagRepository = tagRepo;
	}

	/**
	 * Saves a {@link Tag} object to the database.
	 * 
	 * @param tag The {@link Tag} object to save.
	 */
	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	/**
	 * Fetches the {@link Tag} object with the corresponding ID from the
	 * database.
	 * 
	 * @param id The ID of the {@link Tag} object to fetch.
	 */
	@Override
	public Tag findById(int id) {
		return tagRepository.findById(id);
	}

	/**
	 * Fetches all {@link Subject} objects from the database.
	 */
	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}


	/**
	 * Deletes the {@link Tag} object with the corresponding ID from the
	 * database.
	 * 
	 * @param id The ID of the {@link Tag} object to delete.
	 */
	@Override
	public void deleteById(int id) {
		tagRepository.deleteById(id);
	}

	public void deleteAll(List<Tag> tags) {
		tagRepository.deleteAll(tags);
	}

}
