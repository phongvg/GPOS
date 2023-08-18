package com.gg.gpos.common.util;

public class UploadedFile {
	private String uploadedPath;
	private String uploadedFileName;
	private byte[] uploadedFileContent;
	
	public String getUploadedPath() {
		return uploadedPath;
	}
	
	public void setUploadedPath(String uploadedPath) {
		this.uploadedPath = uploadedPath;
	}
	
	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public byte[] getUploadedFileContent() {
		return uploadedFileContent;
	}
	
	public void setUploadedFileContent(byte[] uploadedFileContent) {
		this.uploadedFileContent = uploadedFileContent;
	}
	
	
}
