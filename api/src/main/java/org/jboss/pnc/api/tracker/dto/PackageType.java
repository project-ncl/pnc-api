package org.jboss.pnc.api.tracker.dto;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the supported package types for repositories within the system.
 * Each type is associated with a specific short string code used in configurations
 * and identifiers.
 */
public enum PackageType {

    /** Apache Maven package type represented by the code "mvn". */
    MAVEN("mvn"),

    /** Node Package Manager package type represented by the code "npm". */
    NPM("npm"),

    /** Generic artifact package type represented by the code "generic". */
    GENERIC("generic");

    /** The unique string code representing the package type. */
    private final String code;

    /** Internal read-only cache for efficient, O(1) case-insensitive lookups by code. */
    private static final Map<String, PackageType> BY_CODE = Stream.of(values())
            .collect(Collectors.toMap(PackageType::getCode, Function.identity()));

    /**
     * Internal constructor to initialize the package type with its corresponding code.
     *
     * @param code the string representation of the package type
     */
    PackageType(String code) {
        this.code = code;
    }

    /**
     * Returns the string code associated with this package type.
     *
     * @return the string code (e.g., "mvn", "npm")
     */
    public String getCode() {
        return code;
    }

    /**
     * Resolves a {@code PackageType} from its string code.
     * The lookup operation is case-insensitive and optimized for performance.
     *
     * @param code the string code to look up (must not be null)
     * @return the corresponding {@code PackageType} instance
     * @throws NullPointerException if the provided code is null
     * @throws IllegalArgumentException if no matching {@code PackageType} is found for the given code
     */
    public static PackageType fromCode(String code) {
        Objects.requireNonNull(code, "Code for PackageType must not be null");

        PackageType result = BY_CODE.get(code.toLowerCase());
        if (result == null) {
            throw new IllegalArgumentException("Unknown code for PackageType: " + code);
        }
        return result;
    }
}
