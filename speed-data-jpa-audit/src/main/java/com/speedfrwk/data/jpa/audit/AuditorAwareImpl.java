package com.speedfrwk.data.jpa.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
//    	log.info("Entering getCurrentAuditor()...");
    	
        //return Optional.of("jpa_auditing");
        // Can use Spring Security to return currently logged in user
    	try {
    		return Optional.of(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		} catch (Exception e) {
		    return Optional.of("sync_queue");
		}
        
    }
}