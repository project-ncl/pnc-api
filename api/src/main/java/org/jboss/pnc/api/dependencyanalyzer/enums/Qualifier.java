package org.jboss.pnc.api.dependencyanalyzer.enums;

import static java.text.MessageFormat.format;

import java.util.Arrays;
import java.util.Optional;

import org.jboss.pnc.api.constants.Patterns;
import org.jboss.pnc.api.dto.validation.ValidationResult;

public enum Qualifier {
    PRODUCT_ID(1), PRODUCT(1, PRODUCT_ID) {
        public ValidationResult validate(String... values) {
            ValidationResult result = super.validate(values);
            if (!result.isValid())
                return result;

            if (!values[0].matches(Patterns.PRODUCT_ABBREVIATION))
                return ValidationResult
                        .withError(format("Product abbreviation does not match {0}.", Patterns.PRODUCT_ABBREVIATION));

            return ValidationResult.valid();
        }
    },
    VERSION_ID(1),
    VERSION(2, VERSION_ID) {
        public ValidationResult validate(String... values) {
            ValidationResult result = super.validate(values);
            if (!result.isValid())
                return result;

            result = PRODUCT.validate(values[0]);
            if (!result.isValid())
                return result;

            if (!values[1].matches(Patterns.PRODUCT_STREAM_VERSION))
                return ValidationResult
                        .withError(format("Product Version part does not match {0}.", Patterns.PRODUCT_STREAM_VERSION));

            return ValidationResult.valid();
        }
    },
    MILESTONE_ID(1),
    MILESTONE(2, MILESTONE_ID) {
        public ValidationResult validate(String... values) {
            ValidationResult result = super.validate(values);
            if (!result.isValid())
                return result;

            result = PRODUCT.validate(values[0]);
            if (!result.isValid())
                return result;

            if (!values[1].matches(Patterns.PRODUCT_MILESTONE_VERSION))
                return ValidationResult.withError(
                        format("Product Milestone part does not match {0}.", Patterns.PRODUCT_MILESTONE_VERSION));

            return ValidationResult.valid();
        }
    },
    GROUP_BUILD(1),
    BUILD(1),
    BUILD_CONFIG_ID(1),
    BUILD_CONFIG(1, BUILD_CONFIG_ID),
    GROUP_CONFIG_ID(1),
    GROUP_CONFIG(1, GROUP_CONFIG_ID),
    DEPENDENCY(1),
    QUALITY(1);

    public final int parts;

    private final Qualifier idVersion;

    Qualifier(int parts) {
        this(parts, null);
    }

    Qualifier(int parts, Qualifier idVersion) {
        this.parts = parts;
        this.idVersion = idVersion;
    }

    public Optional<Qualifier> getIdVersion() {
        return Optional.ofNullable(idVersion);
    }

    public ValidationResult validate(String... values) {
        if (values == null)
            return ValidationResult.withError("Input is null");

        if (values.length != parts)
            return ValidationResult.withError(format("Input has {0} parts instead of {1}", values.length, parts));

        if (Arrays.stream(values).anyMatch(val -> val == null || val.isEmpty()))
            return ValidationResult.withError(format("Input has null/empty parts."));

        return ValidationResult.valid();
    }
}
