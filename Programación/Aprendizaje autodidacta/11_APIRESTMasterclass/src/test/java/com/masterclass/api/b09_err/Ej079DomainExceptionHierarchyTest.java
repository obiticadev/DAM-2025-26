package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b09_err.Ej079DomainExceptionHierarchy.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej079DomainExceptionHierarchyTest {

    @Test
    void mapeo() {
        assertEquals(404, Ej079DomainExceptionHierarchy.aStatus(new NoEncontradoException("x")));
        assertEquals(409, Ej079DomainExceptionHierarchy.aStatus(new ConflictoException("x")));
        assertEquals(403, Ej079DomainExceptionHierarchy.aStatus(new NoAutorizadoException("x")));
        assertEquals(400, Ej079DomainExceptionHierarchy.aStatus(new DominioException("x")));
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej079DomainExceptionHierarchy.aStatus(null));
    }
}
