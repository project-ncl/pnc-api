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

import lombok.Builder;
import lombok.Value;
import org.jboss.pnc.api.dto.validation.DomainNameUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;

/**
 * Validates whether the provided URL is of the format {@link Patterns.NonScpLike} or {@link Patterns.ScpLike}.
 */
public class ExternalURLValidator implements ConstraintValidator<ExternalURL, String> {

    private String protocol;
    private String user;
    private String host;
    private int port;
    private String organization;
    private String repository;

    @Override
    public void initialize(ExternalURL annotation) {
        this.protocol = annotation.protocol();
        this.user = annotation.user();
        this.host = annotation.host();
        this.port = annotation.port();
        this.organization = annotation.organization();
        this.repository = annotation.repository();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        ParsedURL url = parseURL(value);
        if (url == null || filledButNotMatchingParsed(protocol, url.getProtocol())
                || filledButNotMatchingParsed(user, url.getUser()) || filledButNotMatchingParsed(host, url.getHost())
                || !DomainNameUtil.isValidDomainAddress(url.getHost()) || (port != -1 && port != url.getPort())
                || filledButNotMatchingParsed(organization, url.getOrganization())
                || filledButNotMatchingParsed(repository, url.getRepository())) {
            return false;
        }

        return true;
    }

    /**
     * Parses the provided external URL into the representation, from which it's trivial to get URL parts, e.g. user.
     *
     * @param url URL to be parsed
     * @return Parsed representation corresponding to the given URL. In case of invalid URL (=unparsable) was given,
     *         null is returned.
     */
    public static ParsedURL parseURL(String url) {
        final ParsedURL.Builder builder = new ParsedURL.Builder();
        final Matcher nonScpLikeMatcher = Patterns.NonScpLike.PATTERN.matcher(url);
        final Matcher scpLikeMatcher = Patterns.ScpLike.PATTERN.matcher(url);

        if (nonScpLikeMatcher.matches()) {
            return builder.protocol(nonScpLikeMatcher.group(Patterns.NonScpLike.PROTOCOL_GROUP))
                    .user(nonScpLikeMatcher.group(Patterns.NonScpLike.USER_GROUP))
                    .host(nonScpLikeMatcher.group(Patterns.NonScpLike.HOST_GROUP))
                    .port(computePort(nonScpLikeMatcher.group(Patterns.NonScpLike.PORT_GROUP)))
                    .organization(nonScpLikeMatcher.group(Patterns.NonScpLike.ORGANIZATION_GROUP))
                    .repository(nonScpLikeMatcher.group(Patterns.NonScpLike.REPOSITORY_GROUP))
                    .build();
        }

        if (scpLikeMatcher.matches()) {
            return builder.protocol(scpLikeMatcher.group(Patterns.ScpLike.PROTOCOL_GROUP))
                    .user(scpLikeMatcher.group(Patterns.ScpLike.USER_GROUP))
                    .host(scpLikeMatcher.group(Patterns.ScpLike.HOST_GROUP))
                    .port(computePort(scpLikeMatcher.group(Patterns.ScpLike.PORT_GROUP)))
                    .organization(scpLikeMatcher.group(Patterns.ScpLike.ORGANIZATION_GROUP))
                    .repository(scpLikeMatcher.group(Patterns.ScpLike.REPOSITORY_GROUP))
                    .build();
        }

        return null;
    }

    private boolean filledButNotMatchingParsed(String valueFromAnnotation, String parsedValue) {
        return valueFromAnnotation != null && !valueFromAnnotation.isEmpty()
                && !valueFromAnnotation.equals(parsedValue);
    }

    private static int computePort(String portMatch) {
        return (portMatch == null) ? -1 : Integer.parseInt(portMatch);
    }

    @Builder(builderClassName = "Builder")
    @Value
    public static class ParsedURL {

        String protocol;
        String user;
        String host;
        int port;
        String organization;
        String repository;
    }
}
