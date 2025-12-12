/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2022 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.api.reqour.dto.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Constraint annotation validating repository URL, which should meet one of the following:<br/>
 * - {@link Patterns.ScpLike#PATTERN}<br/>
 * - {@link Patterns.NonScpLike#PATTERN}<br/>
 * - {@link Patterns.FileLike#PATTERN}<br/>
 */
@Documented
@Constraint(validatedBy = GitRepositoryURLValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface GitRepositoryURL {

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
