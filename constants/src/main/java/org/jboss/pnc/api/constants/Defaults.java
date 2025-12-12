package org.jboss.pnc.api.constants;

/**
 * Default values for various purposes.
 *
 * @author Jan Michalov &lt;jmichalo@redhat.com&gt;
 */
public class Defaults {

    /**
     * Represents the key for Alignment Config with global dependency scope. Meaning the Alignment config is applied to
     * all dependencies unless overridden by more specific configuration. It is a default value if a user doesn't
     * specify a dependency override.
     */
    public static final String GLOBAL_SCOPE = "GLOBAL_SCOPE";

    /**
     * Delimiter used for separation of Alignment Ranks for PME/GME CLI and in DB.
     */
    public static final char RANK_DELIMITER = ';';
}
