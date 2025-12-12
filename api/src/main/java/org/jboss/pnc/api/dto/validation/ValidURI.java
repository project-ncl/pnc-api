package org.jboss.pnc.api.dto.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = URIConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURI {
    String message() default "Invalid URI";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return the protocol (scheme) the annotated string must match, e.g. ftp or http. Per default any protocol is
     *         allowed
     */
    String protocol() default "";

    /**
     * @return the host the annotated string must match, e.g. localhost. Per default any host is allowed
     */
    String host() default "";

    /**
     * @return the port the annotated string must match, e.g. 80. Per default any port is allowed
     */
    int port() default -1;
}
