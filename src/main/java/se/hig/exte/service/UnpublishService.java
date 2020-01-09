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
	public UnpublishService(ExamRepository examRepo, CourseRepository courseRepo, SubjectRepository subjectRepo, AcademyRepository academyRepo) {
		this.examRepo = examRepo;
		this.courseRepo = courseRepo;
		this.subjectRepo = subjectRepo;
		this.academyRepo = academyRepo;
	}

	/**
	 * Sets the boolean value of unpublished on {@link Exam}
	 * @param exam The {@link Exam} object to be modified
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	public ResponseEntity<String> editExamUnpublished(Exam exam) {
		examRepo.save(exam);

		return new ResponseEntity<String>(HttpStatus.OK);
	}


	/**
	 * Sets the boolean value of unpublished on {@link Exam}
	 * @param exam The {@link Exam} object to be modified
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	public ResponseEntity<String> toggleExamUnpublished(Exam exam) {
		exam.setUnpublished(!exam.getUnpublished());
		examRepo.save(exam);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	/**
	 * Sets the boolean value of unpublished on {@link Course}
	 * @param course The {@link Course} object to be modified
	 * @param unpublish The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	public ResponseEntity<String> isCourseUnpublished(Course course) {
		boolean unpublished = course.isUnpublished();
		List<Exam> exams = examRepo.findByCourseId(course.getId());
		for (Exam exam : exams) {
			exam.setUnpublished(unpublished);
			editExamUnpublished(exam);
		}
		course.setUnpublished(unpublished);
		courseRepo.save(course);

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * Sets the boolean value of unpublished on {@link Subject}
	 * @param subject The {@link Subject} object to be modified
	 * @param unpublish The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	public ResponseEntity<String> isSubjectUnpublished(Subject subject) {
		boolean unpublished = subject.isUnpublished();
		List<Course> courses = courseRepo.findBySubjectId(subject.getId());
		for (Course course : courses) {
			course.setUnpublished(unpublished);
			isCourseUnpublished(course);
		}
		subject.setUnpublished(unpublished);
		subjectRepo.save(subject);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	public ResponseEntity<String> isSubjectsUnpublished(List<Subject> subjects, boolean unpublished) {
		for (Subject subject : subjects) {
			isSubjectUnpublished(subject, unpublished);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	/**
	 * Sets the unpublished value of {@link Exam} to true on all exams in the list.
	 */
	public void unpublishExpiredExams() {
		List<Exam> exams = examRepo.findByUnpublishDateLessThanAndUnpublishedFalse(LocalDate.now());
		for (Exam exam : exams) {
			exam.setUnpublished(true);
			editExamUnpublished(exam);
			System.out.println("Unpublisher unpublished " + exam.toString());
		}
		examRepo.flush();
		System.out.println(">>>> Unpublisher finished. <<<<");
	}

	public ResponseEntity<String> isAcademyUnpublished(Academy academy) {
		boolean unpublished = academy.getUnpublished();
		List<Subject> subjects = subjectRepo.findByAcademyId(academy.getId());
		for (Subject subject : subjects) {
			subject.setUnpublished(unpublished);
			isSubjectUnpublished(subject);
		}
		academy.setUnpublished(unpublished);
		academyRepo.save(academy);

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	public ResponseEntity<String> isAcademiesUnpublished(List<Academy> academies, boolean unpublished){
		for (Academy academy : academies) {
			academy.setUnpublished(unpublished);
			isAcademyUnpublished(academy);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
