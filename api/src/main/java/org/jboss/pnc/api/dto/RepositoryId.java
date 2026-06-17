package org.jboss.pnc.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Repository identification in Artifactory.
 */
@Builder
@Value
@Jacksonized
public class RepositoryId implements Comparable<RepositoryId> {

    private String project;

    private String name;

    /**
     * Generates path element in Artifactory for artifacts retrieval.
     *
     * @return generated path element
     */
    public String getPath() {
        return project + "-" + name;
    }

    /**
     * Converts the repository identifier to a canonical string format "project:name".
     * This format is intended for API communication and storage.
     *
     * @return The formatted string "project:name".
     */
    public String getIdentifier() {
        return project + ":" + name;
    }

    /**
     * Parses a string representation in the format "project:name" back into a {@link RepositoryId} object.
     *
     * @param repositoryId The string to parse.
     * @return A new {@link RepositoryId} instance.
     * @throws IllegalArgumentException if the format is invalid.
     */
    public static RepositoryId parse(String repositoryId) {
        if (repositoryId == null || !repositoryId.contains(":")) {
            throw new IllegalArgumentException("Invalid RepositoryId format. Expected 'project:name'.");
        }

        String[] parts = repositoryId.split(":", 2);
        return new RepositoryId(parts[0], parts[1]);
    }

    @Override
    public int compareTo(RepositoryId o) {
        return getPath().compareTo(o.getPath());
    }

}