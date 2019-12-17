package se.hig.exte.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import se.hig.exte.service.ExamService;
import se.hig.exte.service.FileService;

/**
 * A {@code RestController} class responsible for mapping HTTP requests to the
 * /files path. This class calls services that store and fetches file resources.
 */
@RestController
@RequestMapping("/files")
public class FileController implements HandlerExceptionResolver {

	private final FileService fileService;
	private final ExamService examService;

	/**
	 * Creates a {@code FileController}.
	 * 
	 * @param fileService The {@link FileService} to use.
	 * @param examService The {@link ExamService} to use.
	 */
	@Autowired
	public FileController(FileService fileService, ExamService examService) {
		this.fileService = fileService;
		this.examService = examService;
	}

	/**
	 * Gets a file stored on the server. This method is run when a HTTP GET request
	 * is made to the end-point /download/{fileName}. The specified file name must
	 * contain the file extension (e.g. ".pdf"). Returns an empty response and a
	 * status code of "404 - not found" if the file is not found.
	 * 
	 * @param fileName The name of the file on the server to fetch.
	 * @return A {@code ResponseEntity} containing a byte array containing the file.
	 */
	@GetMapping(value = "/download/{fileName}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> handleFileDownload(@PathVariable String fileName) {
		ResponseEntity<byte[]> response;
		try {
			File pdf = fileService.fetchFile(fileName);
			byte[] contents = Files.readAllBytes(pdf.toPath());
			response = new ResponseEntity<byte[]>(contents, HttpStatus.OK);
		} catch (IOException ioe) {
			response = new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	/**
	 * Uploads a file to the server. This method is run when a HTTP POST request is
	 * made to the end-point files/upload/ and expects a {@code MultipartFile}.
	 * Returns a status code of "417 - expectation failed" if a file with the
	 * specified name already exists or if the file is not a PDF file.
	 * 
	 * @param fileName The name of the file to upload to the server.
	 * @return A {@code ResponseEntity} containing a message and status code 200 if
	 *         successful, else 417.
	 */
	@PostMapping(value = "/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

		String message = "";

		if (isPDF(file)) {
			try {
				if (!examExists(file.getOriginalFilename().split("\\.")[0])) {
					fileService.storeFile(file);
					message = "Successfully uploaded: " + file.getOriginalFilename();
					return ResponseEntity.status(HttpStatus.OK).body(message);
				} else {
					message = "Duplicate exam filename, exam with the filename already exists.";
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
				}
			} catch (Exception e) {
				message = "Failed to upload: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		} else {
			message = "Invalid filetype, supported filetype is PDF.";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}

	}

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {

		if (e instanceof MaxUploadSizeExceededException) {
			ModelAndView modelAndView = new ModelAndView("inline error");
			modelAndView.addObject("error", "Error: File size exceeded the maximum limit. Maximum limit set to 5MB");
			return modelAndView;
		}

		if (e instanceof MultipartException) {
			ModelAndView modelAndView = new ModelAndView("inline error");
			modelAndView.addObject("error", "MultipartException");
			return modelAndView;
		}

		e.printStackTrace();
		return new ModelAndView("500");
	}

	private boolean isPDF(MultipartFile file) {
		return file.getContentType().equals("application/pdf")
				&& file.getOriginalFilename().split("\\.")[1].equals("pdf");
	}

	private boolean examExists(String fileName) {
		return examService.findAll().stream().anyMatch(e -> e.getFileName().equalsIgnoreCase(fileName));
	}
}