package se.hig.exte.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import se.hig.exte.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController implements HandlerExceptionResolver {

	private final FileService fileService;

	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

		String message = "";

		if (isPDF(file)) {
			try {
				fileService.storeFile(file);
				message = "Successfully uploaded: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
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

	public boolean isPDF(MultipartFile file) {
		return file.getContentType().equals("application/pdf")
				&& file.getOriginalFilename().split("\\.")[1].equals("pdf");
	}
}