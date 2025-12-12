package org.jboss.pnc.api.constants;

public class OperationParameters {

    /**
     * Parameter key for org.jboss.pnc.dto.BuildPushOperation representing whether build should be reimported if it has
     * been already pushed.
     */
    public static final String BUILD_PUSH_REIMPORT = "reimport";

    /**
     * Parameter key for org.jboss.pnc.dto.BuildPushOperation representing Brew tag prefix.
     */
    public static final String BUILD_PUSH_TAG_PREFIX = "tagPrefix";

    /**
     * Parameter key for org.jboss.pnc.dto.BuildPushOperation representing id of milestone to be closed after push
     * succeeds.
     */
    public static final String BUILD_PUSH_MILESTONE_CLOSE = "milestoneClose";
}
