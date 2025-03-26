package com.v.loans.audit;

import java.util.Optional;

import org.hibernate.annotations.Comment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.of("LOANS MS");
	}

}
