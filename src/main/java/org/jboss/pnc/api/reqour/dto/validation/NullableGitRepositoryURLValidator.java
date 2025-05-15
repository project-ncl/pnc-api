package org.jboss.pnc.api.reqour.dto.validation;

import static org.jboss.pnc.api.reqour.dto.validation.GitRepositoryURLValidator.filledButNotMatchingParsed;
import static org.jboss.pnc.api.reqour.dto.validation.GitRepositoryURLValidator.parseURL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jboss.pnc.api.dto.validation.DomainNameUtil;

/**
 * Same as {@link GitRepositoryURLValidator}, but allows the annotated string to be nullable.
 */
public class NullableGitRepositoryURLValidator implements ConstraintValidator<NullableGitRepositoryURL, String> {

    private String protocol;
    private String user;
    private String host;
    private int port;
    private String organization;
    private String repository;

    @Override
    public void initialize(NullableGitRepositoryURL annotation) {
        this.protocol = annotation.protocol();
        this.user = annotation.user();
        this.host = annotation.host();
        this.port = annotation.port();
        this.organization = annotation.organization();
        this.repository = annotation.repository();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.isEmpty()) {
            return false;
        }

        GitRepositoryURLValidator.ParsedURL url = parseURL(value);
        if (url == null || filledButNotMatchingParsed(protocol, url.getProtocol())
                || filledButNotMatchingParsed(user, url.getUser()) || filledButNotMatchingParsed(host, url.getHost())
                || !DomainNameUtil.isValidDomainAddress(url.getHost(), url.getProtocol())
                || (port != -1 && port != url.getPort())
                || filledButNotMatchingParsed(organization, url.getOrganization())
                || filledButNotMatchingParsed(repository, url.getRepository())) {
            return false;
        }

        return true;
    }
}
