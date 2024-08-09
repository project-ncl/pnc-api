/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2024-2024 Red Hat, Inc., and individual contributors
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

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ExternalURLValidatorTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void afterAll() {
        validatorFactory.close();
    }

    @Test
    public void validate_whenValidNonScpLikeExternalURL_returnsEmptyViolations() {
        URLRequest request = new URLRequest("https://github.com/project/repo.git");

        Set<ConstraintViolation<URLRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validate_whenValidScpLikeExternalURL_returnsEmptyViolations() {
        URLRequest request = new URLRequest("git@github.com:project/repo.git");

        Set<ConstraintViolation<URLRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validate_whenInvalidExternalURL_returnsNonEmptyViolations() {
        URLRequest request = new URLRequest("git@github.com/project/repo.git");

        Set<ConstraintViolation<URLRequest>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    private static class URLRequest {

        @ExternalURL
        private final String externalURL;

        public URLRequest(String externalURL) {
            this.externalURL = externalURL;
        }
    }
}
