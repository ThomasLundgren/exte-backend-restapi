package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	Course findById(int id);

	List<Course> findByCourseCode(String courseCode);

	List<Course> findBySubjectId(int subjectId);
}