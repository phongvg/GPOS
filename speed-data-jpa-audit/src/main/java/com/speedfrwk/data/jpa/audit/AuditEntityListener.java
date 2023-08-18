package com.speedfrwk.data.jpa.audit;

import static javax.transaction.Transactional.TxType.MANDATORY;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditEntityListener {
	
    @PrePersist
    public void prePersist(Object target) {
    	perform(target, Action.INSERTED);
    }

    @PreUpdate
    public void preUpdate(Object target) {
        perform(target, Action.UPDATED);
    }

    @PreRemove
    public void preRemove(Object target) {
        perform(target, Action.DELETED);
    }

    @Transactional(MANDATORY)
    private void perform(Object target, Action action) {
    	try {
            EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
    		entityManager.persist(new AuditLog(action, target.toString()));
    	} catch (Exception e) {
    		log.error("ERROR AUDIT LISTENER: {}", e);
    	}
    }

}
