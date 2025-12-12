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
package org.jboss.pnc.api.constants;

/**
 * This class provides keys for Mapped Diagnostic Context (MDC) in logging.
 *
 */
public class MDCKeys {
    /**
     * Identifier of the original request context.
     */
    public static final String REQUEST_CONTEXT_KEY = "requestContext";
    /**
     * Identifier of a running process.
     */
    public static final String PROCESS_CONTEXT_KEY = "processContext";

    /**
     * Sub-identifier of a running process, eg. operation retry attempt.
     */
    public static final String PROCESS_CONTEXT_VARIANT_KEY = "processContextVariant";

    /**
     * Event identifier used to calculate the duration
     */
    public static final String EVENT_NAME_KEY = "process_stage_name";

    /**
     * Event BEGIN or END used to calculate the duration
     */
    public static final String EVENT_TYPE_KEY = "process_stage_step";

    /**
     * Identifier of user who initiated the operation.
     */
    public static final String USER_ID_KEY = "userId";

    /**
     * Username who initiated the operation.
     */
    public static final String USER_NAME_KEY = "user";

    /**
     * Indicator if the context belongs to temporary build.
     *
     * <p>
     * Value: "true" or "false"
     * </p>
     */
    public static final String TMP_KEY = "tmp";
    /**
     * When the log can expire and may be deleted.
     *
     * <p>
     * Value: DateTimeFormatter.ISO_INSTANT.format(timeOfExpiration)
     * </p>
     */
    public static final String EXP_KEY = "exp";
    /**
     * Identifier of the build the operation is working with.
     */
    public static final String BUILD_ID_KEY = "buildId";

    public static final String REQUEST_TOOK = "request.took";
    public static final String RESPONSE_STATUS = "response.status";

    /**
     * The traceparent represents the incoming request in a tracing system in a common format, understood by all
     * vendors. see https://www.w3.org/TR/trace-context-1/#trace-context-http-headers-format
     * <p>
     * Value: {1-byte}-{16-byte array}-{8-byte array}-{8-bit field} Example:
     * 00-0af7651916cd43dd8448eb211c80319c-b9c7c989f97918e1-01
     * </p>
     */
    public static final String TRACEPARENT_KEY = "traceparent";

    /**
     * The tracestate field value is a list of list-members separated by commas (,). A list-member is a key/value pair
     * separated by an equals sign (=). Spaces and horizontal tabs surrounding list-members are ignored. There can be a
     * maximum of 32 list-members in a list.
     * <p>
     * Value: list of key/value pair (separated by an equals sign (=)), separated by commas (,) Example:
     * rojo=00f067aa0ba902b7,congo=t61rcWkgMzE
     * </p>
     */
    public static final String TRACESTATE_KEY = "tracestate";

    /**
     * This is the ID of the whole trace forest and is used to uniquely identify a distributed trace through a system.
     * <p>
     * Value: 16-byte array
     * </p>
     */
    public static final String TRACE_ID_KEY = "traceId";

    /**
     * This is the ID of the whole trace forest and is used to uniquely identify a distributed trace through a system,
     * in SLF4J format.
     * <p>
     * Value: 16-byte array
     * </p>
     */
    public static final String SLF4J_TRACE_ID_KEY = "trace_id";

    /**
     * This is the ID of this request as known by the caller (in some tracing systems, this is known as the span-id,
     * where a span is the execution of a client request).
     * <p>
     * Value: 8-byte array
     * </p>
     */
    public static final String SPAN_ID_KEY = "spanId";

    /**
     * This is the ID of this request as known by the caller (in some tracing systems, this is known as the span-id,
     * where a span is the execution of a client request), in SLF4J format.
     * <p>
     * Value: 8-byte array
     * </p>
     */
    public static final String SLF4J_SPAN_ID_KEY = "span_id";

    /**
     * This is the ID of this request as known by the caller (in some tracing systems, this is known as the span-id,
     * where a span is the execution of a client request).
     * <p>
     * Value: 8-byte array
     * </p>
     */
    public static final String PARENT_ID_KEY = "parentId";

    /**
     * This is the ID of this request as known by the caller (in some tracing systems, this is known as the span-id,
     * where a span is the execution of a client request), in SLF4J format.
     * <p>
     * Value: 8-byte array
     * </p>
     */
    public static final String SLF4J_PARENT_ID_KEY = "parent_id";

    /**
     * An 8-bit field that controls tracing flags such as sampling, trace level, etc.
     * <p>
     * Value: 8-bit
     * </p>
     */
    public static final String TRACE_FLAGS_KEY = "traceFlags";

    /**
     * An boolean value indicating whether the trace is sampled or not
     * <p>
     * Value: "true" or "false"
     * </p>
     */
    public static final String TRACE_SAMPLED_KEY = "sampled";

    /**
     * An 8-bit field that controls tracing flags such as sampling, trace level, etc, in SLF4J format.
     * <p>
     * Value: 8-bit
     * </p>
     */
    public static final String SLF4J_TRACE_FLAGS_KEY = "trace_flags";

    /**
     * The main purpose of the tracestate HTTP header is to provide additional vendor-specific trace identification
     * information across different distributed tracing systemsThe main purpose of the tracestate HTTP header is to
     * provide additional vendor-specific trace identification information across different distributed tracing systems.
     */
    public static final String TRACE_STATE_KEY = "traceState";

    /**
     * The main purpose of the tracestate HTTP header is to provide additional vendor-specific trace identification
     * information across different distributed tracing systemsThe main purpose of the tracestate HTTP header is to
     * provide additional vendor-specific trace identification information across different distributed tracing systems,
     * in SLF4J format.
     */
    public static final String SLF4J_TRACE_STATE_KEY = "trace_state";

    /**
     * Internet Protocol (IP) address of the client or last proxy that sent the request
     */
    public static final String SRC_IP_KEY = "srcIp";

    /**
     * The X-Forwarded-For request header is a de-facto standard header for identifying the originating IP address of a
     * client connecting to a web server through a proxy server.
     */
    public static final String X_FORWARDED_FOR_KEY = "xForwardedFor";

}
