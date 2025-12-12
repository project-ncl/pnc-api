/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2022 Red Hat, Inc., and individual contributors
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
package org.jboss.pnc.api.reqour.dto.validation;

import java.util.regex.Pattern;

public class Patterns {

    /**
     * This pattern matches the following format: scheme://[user@]host[:port][/organization]/repository[.git]
     */
    public static class NonScpLike {

        // need the '.' in repository for repositories like 'vert.x'
        public static final Pattern PATTERN = Pattern.compile(
                "^(?<protocol>[\\w+]+)://(?:(?<user>[\\w-]+)@)?(?<host>[\\w.]+)(?::(?<port>\\d+))?(?:/(?<organization>[\\w-]+))*?/(?<repository>[\\w.-]+(?:\\.git)?)$");

        public static final String PROTOCOL_GROUP = "protocol";
        public static final String USER_GROUP = "user";
        public static final String HOST_GROUP = "host";
        public static final String PORT_GROUP = "port";
        public static final String ORGANIZATION_GROUP = "organization";
        public static final String REPOSITORY_GROUP = "repository";
    }

    /**
     * This pattern matches the following format: [scheme://]user@host[:port]:[workspace/][organization/]repository.git
     */
    public static class ScpLike {

        // need the '.' in repository for repositories like 'vert.x'
        public static final Pattern PATTERN = Pattern.compile(
                "^(?:(?<protocol>[\\w+]+)://)?(?<user>[\\w-]+)@(?<host>[\\w.]+)(?::(?<port>\\d+))?:(?:(?<organization>[\\w-]+)/)*?(?<repository>[\\w.-]+)\\.git$");

        public static final String PROTOCOL_GROUP = "protocol";
        public static final String USER_GROUP = "user";
        public static final String HOST_GROUP = "host";
        public static final String PORT_GROUP = "port";
        public static final String ORGANIZATION_GROUP = "organization";
        public static final String REPOSITORY_GROUP = "repository";
    }

    /**
     * This pattern matches the following format: file://path, where path is either relative path or absolute path,
     * ending either with the trailing slash or not.
     */
    public static class FileLike {

        public static final Pattern PATTERN = Pattern.compile("^(?<protocol>file)://(?<repository>(/?[\\w._-]+)+)?/?$");

        public static final String PROTOCOL_GROUP = "protocol";
        public static final String REPOSITORY_GROUP = "repository";
    }
}
