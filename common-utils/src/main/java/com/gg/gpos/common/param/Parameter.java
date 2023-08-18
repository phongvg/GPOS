package com.gg.gpos.common.param;

public class Parameter {
	private String attachmentPath;
	private String attachmentContextPath;
	private String applicationUrl;
	private String restPath;

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentContextPath() {
		return attachmentContextPath;
	}

	public void setAttachmentContextPath(String attachmentContextPath) {
		this.attachmentContextPath = attachmentContextPath;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getRestPath() {
		return restPath;
	}

	public void setRestPath(String restPath) {
		this.restPath = restPath;
	}
}
