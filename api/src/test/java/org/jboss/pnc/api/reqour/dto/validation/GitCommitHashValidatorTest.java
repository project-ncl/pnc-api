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

class GitCommitHashValidatorTest {

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
    void isValid_validHash_noViolations() {
        ClazzWithFieldAnnotated hash = new ClazzWithFieldAnnotated("4b3b816dc7695b5dce3c6188bb2d874b0508357d");

        Set<ConstraintViolation<ClazzWithFieldAnnotated>> violations = validator.validate(hash);

        assertThat(violations).isEmpty();
    }

    @Test
    void isValid_hashContainingInvalidChars_someViolation() {
        ClazzWithFieldAnnotated hash = new ClazzWithFieldAnnotated("4b3b8-_*c7695b5dce3c6188bb2d874b0508357d");

        Set<ConstraintViolation<ClazzWithFieldAnnotated>> violations = validator.validate(hash);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void isValid_hashOfInvalidLength_someViolation() {
        ClazzWithFieldAnnotated hash = new ClazzWithFieldAnnotated("4b3b8");

        Set<ConstraintViolation<ClazzWithFieldAnnotated>> violations = validator.validate(hash);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void isValid_nullHash_someViolation() {
        ClazzWithFieldAnnotated hash = new ClazzWithFieldAnnotated(null);

        Set<ConstraintViolation<ClazzWithFieldAnnotated>> violations = validator.validate(hash);

        assertThat(violations).isNotEmpty();
    }

    private static class ClazzWithFieldAnnotated {

        @GitCommitHash
        private final String gitCommitHash;

        public ClazzWithFieldAnnotated(String gitCommitHash) {
            this.gitCommitHash = gitCommitHash;
        }
    }
}