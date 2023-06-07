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

package org.jboss.pnc.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.jboss.pnc.api.dto.validation.ValidURI;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.jboss.pnc.api.constants.HttpHeaders.AUTHORIZATION_STRING;

@Data
@AllArgsConstructor
@Jacksonized
@Builder(builderClassName = "Builder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    @NotNull
    private final Method method;

    @ValidURI
    private final URI uri;

    private final List<@Valid Header> headers;

    /**
     * Body of the request
     */
    private final Object attachment;

    /**
     * Annotations are metadata that can influence the behaviour of the request beyond the HTTP method, url, and
     * headers. For example, the code doing the request could apply changes to the attachment or headers based on the
     * annotations.
     * <p/>
     * Annotations are dependent on how the client doing the request interprets those values.
     */
    private final Map<String, List<String>> annotations;

    public Request(Method method, URI uri) {
        this(method, uri, new ArrayList<>(), null, null);
    }

    public Request(Method method, URI uri, List<Header> headers) {
        this(method, uri, headers, null, null);
    }

    public enum Method {
        GET, POST, PUT, DELETE, HEAD, PATCH, OPTIONS
    }

    public static class Builder {
        private List<Header> headers = new ArrayList<>(); // initialize default value for lombok builder

        public Builder header(Header header) {
            headers.add(header);
            return this;
        }

        public Builder header(String name, String value) {
            return header(new Header(name, value));
        }

        public Builder authTokenHeader(String token) {
            return header(AUTHORIZATION_STRING, "Bearer " + token);
        }
    }

    @Value
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE) // To make Jackson happy
    @AllArgsConstructor
    public static class Header {
        @NotBlank(message = "Header name cannot be empty")
        String name;

        @NotNull(message = "Header value cannot be missing")
        String value;

        @Override
        public String toString() {
            if (AUTHORIZATION_STRING.equals(getName())) {
                return getName() + ":***";
            } else {
                return getName() + ":" + getValue();
            }
        }
    }
}
