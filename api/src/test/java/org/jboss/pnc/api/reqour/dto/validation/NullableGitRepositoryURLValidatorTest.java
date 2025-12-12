package org.jboss.pnc.api.reqour.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NullableGitRepositoryURLValidatorTest {

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
    public void validate_whenNull_returnsEmptyViolations() {
        UrlRequest request = new UrlRequest(null);

        Set<ConstraintViolation<UrlRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    public void validate_whenValidNonScpLikeExternalURL_returnsEmptyViolations() {
        UrlRequest request = new UrlRequest("https://github.com/project/repo.git");

        Set<ConstraintViolation<UrlRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    private static class UrlRequest {

        @NullableGitRepositoryURL
        private final String annotatedUrl;

        public UrlRequest(String annotatedUrl) {
            this.annotatedUrl = annotatedUrl;
        }
    }
}
