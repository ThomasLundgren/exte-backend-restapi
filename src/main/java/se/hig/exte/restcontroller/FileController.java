package se.hig.exte.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.ExamService;
import se.hig.exte.service.FileService;

/**
 * A {@code RestController} class responsible for mapping HTTP requests to the
 * /files path. This class calls services that store and fetches file resources.
 */
@RestController
@RequestMapping("/files")
public class FileController /* implements HandlerExceptionResolver */ {

	private final FileService fileService;
	private final ExamService examService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates a {@code FileController}.
	 * 
	 * @param fileService   The {@link FileService} to use.
	 * @param examService   The {@link ExamService} to use.
	 * @param cookieHandler object responsible for handling authentication.
	 */
	@Autowired
	public FileController(FileService fileService, ExamService examService, CookieHandler cookieHandler) {
		this.fileService = fileService;
		this.examService = examService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Fetches a file stored on the server. This method is run when a HTTP GET
	 * request is made to the end-point /download/{filename}. The specified file
	 * name must contain the file extension (e.g. ".pdf"). Returns an empty response
	 * and a status code of "404 - not found" if the file is not found.
	 * 
	 * @param filename The name of the file on the server to fetch.
	 * @return A {@code ResponseEntity} containing a byte array containing the file.
	 */
	@GetMapping(value = "/download/{filename}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> handleFileDownload(@PathVariable String filename) {

		ResponseEntity<byte[]> response = null;
		try {
			File pdf = fileService.fetchFile(filename + ".pdf");
			System.out.println("handleFileDownload" + pdf.getName());
			byte[] contents = Files.readAllBytes(pdf.toPath());
			System.out.println("After contents");
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
	 * @param file The {@code MultiPartFile} to upload to the server.
	 * @param request To see if the user and cookie are valid.
	 * @return A {@code ResponseEntity} containing a message and status code 200 if
	 *         successful, else 417.
	 */
	@PostMapping(value = "/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if (!this.cookieHandler.isValidAdminSession(request.getCookies())) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}

		String message = "";

		System.out.println("filesize:" + file.getSize());

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

	private boolean isPDF(MultipartFile file) {
		return file.getContentType().equals("application/pdf")
				&& file.getOriginalFilename().split("\\.")[1].equals("pdf");
	}

	private boolean examExists(String filename) {
		return examService.findAll().stream().anyMatch(e -> e.getFilename().equalsIgnoreCase(filename));
	}
}