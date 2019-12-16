package se.hig.exte.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import se.hig.exte.repository.ExamRepository;

@Service
public class UnpublishService {
	
	private final ExamRepository examRepository;
	
	@Autowired
	public UnpublishService(ExamRepository examRepository) {
		this.examRepository = examRepository;
	}
	
	/**
	 * This method is run automatically by Spring Boot at 04:00 every day.
	 */
	@Scheduled(cron = "0 0 4 * * *")
	public void unpublish() {
		examRepository.findAll().forEach(exam -> {
			System.out.println("Unpublisher looking at exam: " + exam.toString());
			if (!exam.isUnpublished() && exam.getUnpublishDate().isAfter(LocalDate.now())) {
				exam.setUnpublished(true);
				System.out.println("Unpublisher unpublished " + exam.toString());
				examRepository.save(exam);
			}
			examRepository.flush();
		});
	}
	
	

}
