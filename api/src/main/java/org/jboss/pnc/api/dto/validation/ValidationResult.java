package org.jboss.pnc.api.dto.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationResult {
    public final String validationError;
    public final boolean valid;

    public static ValidationResult valid() {
        return new ValidationResult(null, true);
    }

    public static ValidationResult withError(String error) {
        return new ValidationResult(error, false);
    }

}
