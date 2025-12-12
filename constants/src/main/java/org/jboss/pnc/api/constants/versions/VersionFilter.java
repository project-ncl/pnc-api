package org.jboss.pnc.api.constants.versions;

/**
 * Specifies what part of a version string should be used when filtering versions by similarity. Version parts are:
 * MAJOR.MINOR.MICRO.QUALIFIER-SUFFIX
 */
public enum VersionFilter {
    /**
     * Ignores all parts, effectively allowing all versions.
     */
    ALL,
    /**
     * Filters by major part, meaning the MAJOR of the versions should be the same.
     */
    MAJOR,
    /**
     * Filters by major and minor parts, meaning the MAJOR.MINOR of the versions should be the same.
     */
    MAJOR_MINOR,
    /**
     * Filters by major, minor and micro parts, meaning the MAJOR.MINOR.MICRO of the versions should be the same.
     */
    MAJOR_MINOR_MICRO,
    /**
     * Filters by major, minor, micro and qualifier parts meaning the MAJOR.MINOR.MICRO.QUALIFIER of the versions should
     * be the same.
     */
    MAJOR_MINOR_MICRO_QUALIFIER
}
