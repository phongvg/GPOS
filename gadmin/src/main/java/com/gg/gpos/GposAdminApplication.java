package com.gg.gpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.speedfrwk.data.jpa.audit.JPAAuditConfig;
import com.speedfrwk.security.jwt.JWTConfig;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
@Import({JWTConfig.class, JPAAuditConfig.class})
public class GposAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(GposAdminApplication.class, args);
	}

}
