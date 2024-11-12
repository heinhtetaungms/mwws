package org.mw.mwws.utils;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareBean implements AuditorAware<String> {
	//To use in case with security
    /*@Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(ctx -> ctx.getAuthentication())
                .map(auth -> auth.getName())
                .or(() -> Optional.of("System User"));
    }*/
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("System User");
    }

}
