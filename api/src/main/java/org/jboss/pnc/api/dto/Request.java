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

import static org.jboss.pnc.api.constants.HttpHeaders.AUTHORIZATION_STRING;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.jboss.pnc.api.dto.validation.ValidURI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

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

    private final Object attachment;

    public Request(Method method, URI uri) {
        this(method, uri, new ArrayList<>(), null);
    }

    public Request(Method method, URI uri, List<Header> headers) {
        this(method, uri, headers, null);
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
