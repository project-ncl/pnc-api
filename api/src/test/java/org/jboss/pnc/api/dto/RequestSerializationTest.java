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

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.pnc.api.constants.HttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class RequestSerializationTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldDeserializeRequest() throws JsonProcessingException, URISyntaxException {
        List<Request.Header> headers = new ArrayList<>();
        headers.add(new Request.Header(HttpHeaders.AUTHORIZATION_STRING, "Bearer 12345"));
        Request request = new Request(Request.Method.GET, new URI("http://localhost/"), headers);
        String serialized = objectMapper.writeValueAsString(request);

        Request deserialized = objectMapper.readValue(serialized, Request.class);
        Assertions.assertEquals(Request.Method.GET, deserialized.getMethod());
    }

    @Test
    public void testSecurityCensoring() throws URISyntaxException {
        String token = "top-secret-token";
        Request request = Request.builder()
                .uri(new URI("http://foo.bar/"))
                .method(Request.Method.POST)
                .header("foo", "bar")
                .header("Authorization", token)
                .build();
        String toString = request.toString();
        assertFalse(toString.contains(token), "Secret token should be censored");
    }
}