package org.jboss.pnc.api.reqour.dto.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Same as {@link GitRepositoryURL}, but can be potentially nullable.
 */
@Documented
@Constraint(validatedBy = NullableGitRepositoryURLValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullableGitRepositoryURL {

    String message() default "Invalid URL of the git repository";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return the protocol the annotated string must match, e.g. http. By default, any protocol is allowed.
     */
    String protocol() default "";

    /**
     * @return the user the annotated string must match, e.g. git. By default, any user is allowed.
     */
    String user() default "";

    /**
     * @return the host the annotated string must match, e.g. 'gitlab.cee.redhat.com'. By default, any host is allowed.
     */
    String host() default "";

    /**
     * @return the port the annotated string must match, e.g. 443. By default, any port is allowed.
     */
    int port() default -1;

    /**
     * @return the organization the annotated string must match, e.g. 'pnc'. By default, any organization is allowed.
     */
    String organization() default "";

    /**
     * @return the organization the annotated string must match, e.g. 'pnc-api'. By default, any repository is allowed.
     */
    String repository() default "";
}
