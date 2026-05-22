package com.masterclass.api.b23_ci;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej196DockerBuildPushTest {

    @Test
    void testEsqueleto() {
        assertTrue(true, "Test para Ej196DockerBuildPush");
    }

    @Test
    void testEsJobDependenciaConfigurada() {
        String yml = "docker:\n  needs: build-and-test\n  runs-on: ubuntu-latest";
        assertTrue(Ej196DockerBuildPush.esJobDependenciaConfigurada(yml, "build-and-test"));
        assertFalse(Ej196DockerBuildPush.esJobDependenciaConfigurada(yml, "other-job"));
    }

    @Test
    void testEsSetupBuildxActionValida() {
        assertTrue(Ej196DockerBuildPush.esSetupBuildxActionValida("docker/setup-buildx-action@v3"));
        assertTrue(Ej196DockerBuildPush.esSetupBuildxActionValida("docker/setup-buildx-action@v2"));
        assertFalse(Ej196DockerBuildPush.esSetupBuildxActionValida("docker/other-action"));
    }

    @Test
    void testEsLoginActionValida() {
        assertTrue(Ej196DockerBuildPush.esLoginActionValida("docker/login-action@v3"));
        assertFalse(Ej196DockerBuildPush.esLoginActionValida("other/login"));
    }

    @Test
    void testEsSecretGithubValido() {
        assertTrue(Ej196DockerBuildPush.esSecretGithubValido("${{ secrets.DOCKER_USERNAME }}"));
        assertFalse(Ej196DockerBuildPush.esSecretGithubValido("my-plain-secret"));
    }

    @Test
    void testGenerarMetadatosTags() {
        List<String> tags = Ej196DockerBuildPush.generarMetadatosTags("myorg", "myrepo", "1.0.0");
        assertEquals(2, tags.size());
        assertTrue(tags.contains("myorg/myrepo:1.0.0"));
        assertTrue(tags.contains("myorg/myrepo:latest"));
    }

    @Test
    void testContieneTagLatest() {
        assertTrue(Ej196DockerBuildPush.contieneTagLatest(List.of("myorg/myrepo:1.0.0", "myorg/myrepo:latest")));
        assertFalse(Ej196DockerBuildPush.contieneTagLatest(List.of("myorg/myrepo:1.0.0")));
    }

    @Test
    void testEsRegistryGhcrio() {
        assertTrue(Ej196DockerBuildPush.esRegistryGhcrio("ghcr.io"));
        assertFalse(Ej196DockerBuildPush.esRegistryGhcrio("docker.io"));
    }

    @Test
    void testEsOidcConfigured() {
        assertTrue(Ej196DockerBuildPush.esOidcConfigured("arn:aws:iam::123456789012:role/github-actions-role"));
        assertFalse(Ej196DockerBuildPush.esOidcConfigured("arn:aws:iam:"));
    }

    @Test
    void testEsReleaseTagFormatValido() {
        assertTrue(Ej196DockerBuildPush.esReleaseTagFormatValido("v1.2.3"));
        assertFalse(Ej196DockerBuildPush.esReleaseTagFormatValido("1.2.3"));
        assertFalse(Ej196DockerBuildPush.esReleaseTagFormatValido("v1.0"));
    }

    @Test
    void testGenerarLogDockerPush() {
        String log = Ej196DockerBuildPush.generarLogDockerPush("myorg/myrepo", "v1.0.0", true);
        assertEquals("[DOCKER PUSH] myorg/myrepo:v1.0.0 - SUCCESS", log);
    }
}
