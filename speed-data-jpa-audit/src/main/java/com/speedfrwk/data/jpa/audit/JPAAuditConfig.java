package com.speedfrwk.data.jpa.audit;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.speedfrwk.data.jpa.audit")
@EntityScan("com.speedfrwk.data.jpa.audit")
public class JPAAuditConfig {

}
