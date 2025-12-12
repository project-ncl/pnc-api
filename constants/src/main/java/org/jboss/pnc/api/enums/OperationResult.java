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
 * Status of a completed Operation.
 *
 * The OperationResult class is used by all the Operation sub-classes (e.g. DeliverablesAnalyzerOperation)
 *
 */
public enum OperationResult {

    /**
     * The operation completed successfully.
     */
    SUCCESSFUL,
    /**
     * The operation failed because of reasons unrelated to the systems. For example user provided parameters resulted
     * in unsuccessful outcome.
     */
    FAILED,
    /**
     * Execution of the operation was rejected.
     */
    REJECTED,
    /**
     * The operation was cancelled by user before it was finished.
     */
    CANCELLED,
    /**
     * The operation did not receive response from a system for long time.
     */
    TIMEOUT,
    /**
     * The operation failed because of issue in the systems. For example bug or network error was encountered.
     */
    SYSTEM_ERROR;
}
