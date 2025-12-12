/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2014-2020 Red Hat, Inc., and individual contributors
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
package org.jboss.pnc.api.enums;

/**
 * Enum describing job progress in notifications.
 *
 * @deprecated Use {@link ProgressStatus}
 * @author Honza Brázdil &lt;jbrazdil@redhat.com&gt;
 */
@Deprecated
public enum JobNotificationProgress {
    /**
     * The job is waiting. For example build waiting for dependencies.
     */
    PENDING,
    /**
     * The job is running. For example build is building.
     */
    IN_PROGRESS,
    /**
     * The job has finished. For example build failed.
     */
    FINISHED
}
