/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2021 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.api.constants;

/**
 * Keys for build configuration parameters.
 *
 * @author pkocandr
 */
public enum BuildConfigurationParameterKeys {
    ALIGNMENT_PARAMETERS(
            "Additional parameters, which will be passed to the PME CLI executable during the alignment before the "
                    + "build. The format is as you would enter them on a command line, and each MUST start with a "
                    + "dash."),
    BREW_BUILD_NAME(
            "Specify the Brew build name of the build configuration. This is used to override the default value, and "
                    + "can be useful for builds that disable PME. For Maven builds the format should be "
                    + "'<groupid>:<artifactid>'."),
    BUILD_CATEGORY(
            "Specify the category of the build. It can be either SERVICE for managed service builds or STANDARD "
                    + "(default if not present) for on-premise builds. Empty value is not allowed."),
    EXTRA_REPOSITORIES(
            "Allows to specify any public repositories, which will be used to proxy build dependencies. Format is a "
                    + "single URL per line."),
    BUILDER_POD_MEMORY(
            "Specify the amount of memory the builder pod should have available. Enter number of GiB, for example '4'"
                    + " = 4 GiB, '5.5' = 5632 MiB. Use this responsibly and only when needed!"),
    ALIGNMENT_POD_MEMORY(
            "Same as 'BUILDER_POD_MEMORY', but for the alignment pod."),
            ;

    /** The description to be shown to a user. */
    private String desc;

    private BuildConfigurationParameterKeys(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the description to be shown to a user.
     *
     * @return the value description
     */
    public String getDesc() {
        return desc;
    }
}
