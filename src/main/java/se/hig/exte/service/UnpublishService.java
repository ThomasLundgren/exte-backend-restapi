package se.hig.exte.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Course;
import se.hig.exte.model.Exam;
import se.hig.exte.model.Subject;
import se.hig.exte.repository.AcademyRepository;
import se.hig.exte.repository.CourseRepository;
import se.hig.exte.repository.ExamRepository;
import se.hig.exte.repository.SubjectRepository;

@Service
public class UnpublishService {
	private final AcademyRepository academyRepo;
	private final ExamRepository examRepo;
	private final CourseRepository courseRepo;
	private final SubjectRepository subjectRepo;

	@Autowired
	public UnpublishService(ExamRepository examRepo, CourseRepository courseRepo, SubjectRepository subjectRepo,
			AcademyRepository academyRepo) {
		this.examRepo = examRepo;
		this.courseRepo = courseRepo;
		this.subjectRepo = subjectRepo;
		this.academyRepo = academyRepo;
	}

	/**
	 * Sets the boolean value of unpublished on {@link Exam}
	 *
	 * @param exam The {@link Exam} object to be modified
	 * @return The ResponseEntity string of the http status.
	 */
	public Exam setExamUnpublished(Exam exam) {
		return examRepo.save(exam);
	}

	/**
	 * Sets the boolean value of unpublished on {@link Exam}s
	 *
	 * @param exams The {@link Exam}s object to be modified
	 * @return The ResponseEntity string of the http status.
	 */
	public List<Exam> setExamsUnpublished(List<Exam> exams) {
		return examRepo.saveAll(exams);
	}

	/**
	 * Sets the boolean value of unpublished on {@link Exam}
	 *
	 * @param exam The {@link Exam} object to be modified
	 * @return The ResponseEntity string of the http status.
	 */
	public Exam toggleExamUnpublished(Exam exam) {
		exam.setUnpublished(!exam.getUnpublished());
		return examRepo.save(exam);
	}

	/**
	 * Sets the boolean value of unpublished on {@link Course}
	 *
	 * @param course The {@link Course} object to be modified
	 * @return The ResponseEntity string of the http status.
	 */
	public Course setCourseUnpublished(Course course) {
		boolean unpublished = course.isUnpublished();
		List<Exam> exams = examRepo.findByCourseId(course.getId());
		for (Exam exam : exams) {
			exam.setUnpublished(unpublished);
			setExamUnpublished(exam);
		}
		course.setUnpublished(unpublished);

		return courseRepo.save(course);
	}

	/**
	 * Sets the boolean value of unpublished on {@link Course}s
	 *
	 * @param courses The {@link Course}s object to be modified
	 * @return The ResponseEntity string of the http status.
	 */
	public List<Course> setCoursesUnpublished(List<Course> courses) {
		for (Course course : courses) {
			setCourseUnpublished(course);
		}

		return courses;
	}

	/**
	 * Sets the boolean value of unpublished on {@link Subject}
	 *
	 * @param subject The {@link Subject} object to be modified
	 * @return The ResponseEntity string of the http status.
	 */
	public Subject setSubjectUnpublished(Subject subject) {
		boolean unpublished = subject.isUnpublished();
		List<Course> courses = courseRepo.findBySubjectId(subject.getId());
		for (Course course : courses) {
			course.setUnpublished(unpublished);
			setCourseUnpublished(course);
		}
		subject.setUnpublished(unpublished);

		return subjectRepo.save(subject);
	}

	public List<Subject> setSubjectsUnpublished(List<Subject> subjects) {
		for (Subject subject : subjects) {
			setSubjectUnpublished(subject);
		}
		return subjects;
	}

	public ResponseEntity<String> SetSubjectsUnpublished(List<Subject> subjects, boolean unpublished) {
		for (Subject subject : subjects) {
			setSubjectUnpublished(subject);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	public Academy setAcademyUnpublished(Academy academy) {
		boolean unpublished = academy.getUnpublished();
		List<Subject> subjects = subjectRepo.findByAcademyId(academy.getId());
		for (Subject subject : subjects) {
			subject.setUnpublished(unpublished);
			setSubjectUnpublished(subject);
		}
		academy.setUnpublished(unpublished);

		return academyRepo.save(academy);
	}

	public List<Academy> setAcademiesUnpublished(List<Academy> academies) {
		for (Academy academy : academies) {
			setAcademyUnpublished(academy);
		}
		return academies;
	}

	/**
	 * Sets the unpublished value of {@link Exam} to true on all exams in the list.
	 */
	public void unpublishExpiredExams() {
		List<Exam> exams = examRepo.findByUnpublishDateLessThanAndUnpublishedFalse(LocalDate.now());
		for (Exam exam : exams) {
			exam.setUnpublished(true);
			setExamUnpublished(exam);
		}
	}

	public void unpublishEmptyCourses() {
		List<Course> courses = courseRepo.findByUnpublishedFalse();
		for (Course course : courses) {
			List<Exam> exams = examRepo.findByCourseIdAndUnpublishedFalse(course.getId());
			if (exams.isEmpty()) {
				course.setUnpublished(true);
				courseRepo.save(course);
			}
		}
	}

	public void unpublishEmptySubjects() {
		List<Subject> subjects = subjectRepo.findByUnpublishedFalse();
		for (Subject subject : subjects) {
			List<Course> courses = courseRepo.findBySubjectIdAndUnpublishedFalse(subject.getId());
			if (courses.isEmpty()) {
				subject.setUnpublished(true);
				subjectRepo.save(subject);
			}
		}
	}

	public void unpublishEmptyAcademies() {
		List<Academy> academies = academyRepo.findByUnpublishedFalse();
		for (Academy academy : academies) {
			List<Subject> subjects = subjectRepo.findByAcademyIdAndUnpublishedFalse(academy.getId());
			if (subjects.isEmpty()) {
				academy.setUnpublished(true);
				academyRepo.save(academy);
			}
		}
	}

}
