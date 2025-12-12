/**
 * JBoss, Home of Professional Open Source.
 * Copyright 2021 Red Hat, Inc., and individual contributors
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
 * This class contains the set of key constants which are used to access PNC generic settings in PNC DB. These values
 * include settings such as PNC_VERSION or whether the MAINTENANCE_MODE is enabled.
 */
public class GenericSettingsKeys {

    public static final String ANNOUNCEMENT_BANNER = "ANNOUNCEMENT_BANNER";
    public static final String MAINTENANCE_MODE = "MAINTENANCE_MODE";
    public static final String PNC_VERSION = "PNC_VERSION";
    public static final String ANNOUNCEMENT_ETA = "ANNOUNCEMENT_ETA";
}
