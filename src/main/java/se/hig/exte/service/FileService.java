package se.hig.exte.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private static final String FILE_DIRECTORY = "uploads";

	/**
	 * Uploads a file to the server.
	 * 
	 * @param file The {@code MultiPartFile} to upload to the server.
	 * @throws IOException if an I/O error occurs when writing.
	 */
	public void storeFile(MultipartFile file) throws IOException {
		Path filePath = Paths.get(FILE_DIRECTORY + "/" + file.getOriginalFilename());
		System.out.println("filePath: " + filePath);
		System.out.println("File saved: " + file.getOriginalFilename());
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Fetches a file stored on the server. The specified file name must contain the
	 * file extension (e.g. ".pdf").
	 * 
	 * @param filename The name of the file on the server to fetch.
	 * @return A {@code File} with the specified name.
	 */
	public File fetchFile(String filename) {
		return new File(FILE_DIRECTORY + "/" + filename);
	}

	/**
	 * Fetches a file stored on the server by using its current name, and renaming
	 * it with a new name. Also appending the .pdf extension if necessary.
	 * 
	 * @param oldFilename The name current name of the file on the server.
	 * @param newFilename The new name of the file on the server.
	 */
	public void editFile(String oldFilename, String newFilename) {
		File file = this.fetchFile(oldFilename);
		file.renameTo(new File(FILE_DIRECTORY + "/" + newFilename));
	}
}
