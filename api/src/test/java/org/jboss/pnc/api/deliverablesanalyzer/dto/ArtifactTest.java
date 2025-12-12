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
package org.jboss.pnc.api.deliverablesanalyzer.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArtifactTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testMilestoneVersionPattern() throws JsonProcessingException {
        MavenArtifact mavenArtifact = MavenArtifact.builder()
                .brewId(123l)
                .buildSystemType(BuildSystemType.BREW)
                .builtFromSource(false)
                .classifier("Foo")
                .filename("foo.bar")
                .groupId("foo")
                .artifactId("bar")
                .version("1.0.0")
                .md5("123")
                .sha1("123abc")
                .sha256("123abcdef")
                .build();

        String mavenArtifactAsString = objectMapper.writeValueAsString(mavenArtifact);
        System.out.println(mavenArtifactAsString);

        Artifact artifactFromString = objectMapper.readValue(mavenArtifactAsString, Artifact.class);

        Assertions.assertEquals(MavenArtifact.class, artifactFromString.getClass());
        Assertions.assertEquals(mavenArtifact, artifactFromString);
    }
}
