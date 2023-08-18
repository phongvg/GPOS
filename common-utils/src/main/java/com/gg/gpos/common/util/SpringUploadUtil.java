package com.gg.gpos.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Support upload file and save file to local
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
public class SpringUploadUtil {
	public static final String FILE_SEP = "/";
	
	/**
	 * Upload list of files.
	 *
	 * @param uploadedFiles the uploaded files
	 * @throws IOException 
	 */
	public static void uploadFiles(List<UploadedFile> uploadedFiles) throws IOException {
		if (uploadedFiles != null && !uploadedFiles.isEmpty()) {
			for (UploadedFile uploadedFile : uploadedFiles) {
				SpringUploadUtil.uploadFile(uploadedFile);
			}
		}
	}

	/**
	 * Upload file.
	 *
	 * @param uploadedFile the uploaded file               
	 * @throws IOException 
	 */
	public static void uploadFile(UploadedFile uploadedFile) throws IOException {
		if (uploadedFile != null) {
			Path uploadedFolder = Paths.get(uploadedFile.getUploadedPath());
			if (!uploadedFolder.toFile().exists()) {
				Files.createDirectories(uploadedFolder);
			}
			
			Path file = Paths.get(uploadedFile.getUploadedPath() + FILE_SEP + uploadedFile.getUploadedFileName());
			if (file.toFile().exists()) {
				Files.delete(file);
			}
			
			Files.write(file, uploadedFile.getUploadedFileContent());
		}
	}
}
