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
package org.jboss.pnc.api.enums;

/**
 * DeliverableAnalyzerReportLabel labels are used to define additional metadata about the DeliverableAnalyzerReport.
 *
 * @author Adam Kridl &lt;akridl@redhat.com&gt;
 */
public enum DeliverableAnalyzerReportLabel {

    /**
     * Mark (i.e. do not actually delete it) the report as deleted.
     * <p>
     * Note: Adding this label discards the RELEASED label.
     * </p>
     */
    DELETED,

    /**
     * Used to denote that the deliverable analysis assigned to this report was run as "scratch", perhaps for testing
     * purposes.
     * <p>
     * Note: Can be marked SCRATCH only when the analysis is executed.
     * </p>
     */
    SCRATCH,

    /**
     * Used to denote that deliverables of the analysis assigned to this report were sent to customer/-s.
     * <p>
     * Note: We cannot mark the analysis report as RELEASED in case report was already marked DELETED or SCRATCH.
     * </p>
     */
    RELEASED
}
