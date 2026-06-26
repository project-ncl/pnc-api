package org.jboss.pnc.api.dto;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.pnc.api.tracker.dto.PackageType;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Repository identification value object representing a unique identifier for a repository in Artifactory.
 * Comprises a project name and the specific repository name. Also includes a package type included even though it's not
 * needed for identifying the repository.
 */
@Builder
@Value
@Jacksonized
public class RepositoryId implements Comparable<RepositoryId> {

    /** The name of the project this repository belongs to. */
    private String project;

    /** The type of packages managed by this repository. */
    private PackageType packageType;

    /** The specific name of the repository. */
    private String name;

    private static final Pattern REPOSITORY_PATTERN = Pattern
            .compile("(?<project>[^:]+):(?<packageType>[^:]+):(?<name>[^:]+)");

    /**
     * Generates path element in Artifactory for artifacts retrieval.
     *
     * @return generated path element
     */
    public String getPath() {
        return project + "-" + name;
    }

    /**
     * Converts the repository identifier to a canonical string format "project:packageType:name".
     * This format is intended for API communication and storage.
     *
     * @return The formatted string "project:packageType:name".
     */
    public String getIdentifier() {
        return project + ":" + packageType + ":" + name;
    }

    /**
     * Factory method to parse a {@code RepositoryId} from a composite string format.
     * <p>
     * The expected format is {@code "project:packageType:name"} (e.g., {@code "pnc:mvn:imports"}).
     *
     * @param input the raw string representation to parse (must not be null)
     * @return a fully constructed and validated {@code RepositoryId} instance
     * @throws NullPointerException if the input string is null
     * @throws IllegalArgumentException if the format is invalid.
     */
    public static RepositoryId parse(String repositoryId) {
        Objects.requireNonNull(repositoryId, "The input must not be null");

        Matcher matcher = REPOSITORY_PATTERN.matcher(repositoryId);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Invalid RepositoryId format. Expected 'project:packageType:name', but was: " + repositoryId);
        }

        String packageTypeStr = matcher.group("packageType");
        PackageType packageType;
        try {
            packageType = PackageType.valueOf(packageTypeStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown packageType: " + packageTypeStr, e);
        }
        return RepositoryId.builder()
                .project(matcher.group("project"))
                .packageType(packageType)
                .name(matcher.group("name"))
                .build();
    }

    @Override
    public int compareTo(RepositoryId o) {
        // Compares paths of the 2 repositories because it is composed of project and name and there can't be 2 repos
        // in Artifactory with the same path but different packageType.
        return getPath().compareTo(o.getPath());
    }

}
