package org.jboss.pnc.api.reqour.dto.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PatternsTest {

    @Test
    void nonScpLike_withoutGitSuffix_isValid() {
        String externalUrl = "https://github.com/repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("https");
        assertThat(url.getUser()).isNull();
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("repo");
    }

    @Test
    void nonScpLike_basicOneWithGitSuffix_isValid() {
        String externalUrl = "git+ssh://github.com/my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("git+ssh");
        assertThat(url.getUser()).isNull();
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo.git");
    }

    @Test
    void nonScpLike_withProtocol_isValid() {
        String externalUrl = "git+ssh://github.com/my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("git+ssh");
        assertThat(url.getUser()).isNull();
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void nonScpLike_withUser_isValid() {
        String externalUrl = "https://user@github.com/my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("https");
        assertThat(url.getUser()).isEqualTo("user");
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void nonScpLike_withPort_isValid() {
        String externalUrl = "ssh://github.com:22/my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("ssh");
        assertThat(url.getUser()).isNull();
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(22);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void nonScpLike_withComplicatedHost_isValid() {
        String externalUrl = "https://gitlab.cee.redhat.com:443/my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("https");
        assertThat(url.getUser()).isNull();
        assertThat(url.getHost()).isEqualTo("gitlab.cee.redhat.com");
        assertThat(url.getPort()).isEqualTo(443);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void nonScpLike_withOrganization_isValid() {
        String externalUrl = "https://gitlab.cee.redhat.com:443/best-org-ever/my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("https");
        assertThat(url.getUser()).isNull();
        assertThat(url.getHost()).isEqualTo("gitlab.cee.redhat.com");
        assertThat(url.getPort()).isEqualTo(443);
        assertThat(url.getOrganization()).isEqualTo("best-org-ever");
        assertThat(url.getRepository()).isEqualTo("my-repo.git");
    }

    @Test
    void nonScpLike_noHost_isInvalid() {
        String externalUrl = "/project";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void nonScpLike_noRepository_isInvalid() {
        String externalUrl = "github.com";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void nonScpLike_withoutProtocol_isInvalid() {
        String externalUrl = "github.com/repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void nonScpLike_isScpLike_isInvalid() {
        String externalUrl = "github.com:project/repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void nonScpLike_hasUser_isInvalid() {
        String externalUrl = "git@github.com:project";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void nonScpLike_hasForgottenPort_isInvalid() {
        String externalUrl = "github.com:/project";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void scpLike_withoutUser_isInvalid() {
        String externalUrl = "github.com:my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void scpLike_withUser_isValid() {
        String externalUrl = "git@github.com:my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isNull();
        assertThat(url.getUser()).isEqualTo("git");
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void scpLike_withPort_isValid() {
        String externalUrl = "git@github.com:22:my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isNull();
        assertThat(url.getUser()).isEqualTo("git");
        assertThat(url.getHost()).isEqualTo("github.com");
        assertThat(url.getPort()).isEqualTo(22);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void scpLike_withComplicatedHost_isValid() {
        String externalUrl = "git@gitlab.cee.redhat.com:my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isNull();
        assertThat(url.getUser()).isEqualTo("git");
        assertThat(url.getHost()).isEqualTo("gitlab.cee.redhat.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void scpLike_withProtocol_isValid() {
        String externalUrl = "scp://git@gitlab.cee.redhat.com:my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("scp");
        assertThat(url.getUser()).isEqualTo("git");
        assertThat(url.getHost()).isEqualTo("gitlab.cee.redhat.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isNull();
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void scpLike_withOrganization_isValid() {
        String externalUrl = "git@gitlab.cee.redhat.com:super-cool-org/my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isNull();
        assertThat(url.getUser()).isEqualTo("git");
        assertThat(url.getHost()).isEqualTo("gitlab.cee.redhat.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isEqualTo("super-cool-org");
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void scpLike_withOrganizationAndProtocol_isValid() {
        String externalUrl = "git+ssh://git@gitlab.cee.redhat.com:super-cool-org/my-repo.git";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("git+ssh");
        assertThat(url.getUser()).isEqualTo("git");
        assertThat(url.getHost()).isEqualTo("gitlab.cee.redhat.com");
        assertThat(url.getPort()).isEqualTo(-1);
        assertThat(url.getOrganization()).isEqualTo("super-cool-org");
        assertThat(url.getRepository()).isEqualTo("my-repo");
    }

    @Test
    void scpLike_forgottenGitSuffix_isInvalid() {
        String externalUrl = "github.com:my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void scpLike_InvalidPort_isInvalid() {
        String externalUrl = "github.com::my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void scpLike_noHost_isInvalid() {
        String externalUrl = ":my-repo";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNull();
    }

    @Test
    void fileLike_validAbsoluteWithoutTrailing_isValid() {
        String externalUrl = "file:///tmp/foo/bar";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("file");
        assertThat(url.getRepository()).isEqualTo("/tmp/foo/bar");
    }

    @Test
    void fileLike_validAbsoluteWithTrailing_isValid() {
        String externalUrl = "file:///tmp/foo/bar/";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("file");
        assertThat(url.getRepository()).isEqualTo("/tmp/foo/bar");
    }

    @Test
    void fileLike_validRelativeWithTrailing_isValid() {
        String externalUrl = "file://../tmp/foo/";

        GitRepositoryURLValidator.ParsedURL url = GitRepositoryURLValidator.parseURL(externalUrl);

        assertThat(url).isNotNull();
        assertThat(url.getProtocol()).isEqualTo("file");
        assertThat(url.getRepository()).isEqualTo("../tmp/foo");
    }
}
