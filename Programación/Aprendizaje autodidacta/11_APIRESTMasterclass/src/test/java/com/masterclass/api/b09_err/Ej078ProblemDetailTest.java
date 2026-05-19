package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;
import static org.junit.jupiter.api.Assertions.*;

class Ej078ProblemDetailTest {

    @Test
    void construye404() {
        ProblemDetail pd = Ej078ProblemDetail.build(404, "Usuario 7 no existe", "/api/users/7");
        assertEquals(404, pd.getStatus());
        assertEquals("Usuario 7 no existe", pd.getDetail());
        assertEquals("Not Found", pd.getTitle());
        assertNotNull(pd.getProperties());
        assertTrue(pd.getProperties().containsKey("timestamp"));
    }

    @Test
    void statusNoErrorFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej078ProblemDetail.build(200, "ok", "/x"));
    }
}
