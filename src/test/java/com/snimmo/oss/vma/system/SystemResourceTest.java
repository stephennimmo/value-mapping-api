package com.snimmo.oss.vma.system;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class SystemResourceTest {

    @InjectMock
    SystemRepository systemRepository;

    @Test
    public void getAll() {
        Mockito.when(systemRepository.listAll())
                .thenReturn(List.of(new SystemEntity(1, "TEST")));
        System[] systems = given()
                .when()
                .get("/systems")
                .then()
                .statusCode(OK.getStatusCode())
                .extract()
                .as(System[].class);
        assertThat(systems)
                .hasSize(1)
                .contains(new System(1, "TEST"));
    }

    @Test
    public void findById() {
        Mockito.when(systemRepository.findByIdOptional(1))
                .thenReturn(Optional.of(new SystemEntity(1, "TEST")));
        System system = given()
                .when()
                .get("/systems/1")
                .then()
                .statusCode(OK.getStatusCode())
                .extract()
                .as(System.class);
        assertThat(system)
                .isNotNull()
                .isEqualTo(new System(1, "TEST"));
    }

    @Test
    public void findByIdNotFound() {
        Mockito.when(systemRepository.findByIdOptional(1))
                .thenReturn(Optional.empty());
        given()
                .when()
                .get("/systems/1")
                .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    public void post() {
        Mockito.doAnswer(new SystemRepositoryAnswer()).when(systemRepository).persist(any(SystemEntity.class));
        System system = given()
                .when()
                .contentType(ContentType.JSON)
                .body(new System(null, "TEST"))
                .post("/systems")
                .then()
                .statusCode(CREATED.getStatusCode())
                .extract()
                .as(System.class);
        assertThat(system)
                .isNotNull()
                .isEqualTo(new System(1, "TEST"));
    }

    public static class SystemRepositoryAnswer implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
            SystemEntity entity = invocationOnMock.getArgument(0);
            entity.setSystemId(1);
            return null;
        }
    }

}