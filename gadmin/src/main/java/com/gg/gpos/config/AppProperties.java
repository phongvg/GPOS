package com.gg.gpos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("app")
public class AppProperties {
	private int defaultPage;
	private int defaultPageSize;
	private String applicationUrl;
	private String attachmentPath;
	private String attachmentContextPath;
	private String restPath;
	private boolean sendEmailEnabled;
	
	@Value( "${scheduled.time.difference}" )
	private Long timeDifference;
}
