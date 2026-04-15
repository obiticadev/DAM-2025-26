package bloque5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej29 - Serializacion con Herencia")
class Ej29_SerializarHerenciaTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("guardarEmpleado + cargarEmpleado: Camarero preservado")
    void camareroIdaVuelta() throws Exception {
        String ruta = dir + "/cam.dat";
        Ej29_SerializarHerencia.Camarero c = new Ej29_SerializarHerencia.Camarero("Ana", 1500, 200);
        Ej29_SerializarHerencia.guardarEmpleado(ruta, c);
        Ej29_SerializarHerencia.Empleado e = Ej29_SerializarHerencia.cargarEmpleado(ruta);
        assertInstanceOf(Ej29_SerializarHerencia.Camarero.class, e);
        assertEquals("Ana", e.getNombre());
    }

    @Test @DisplayName("polimorfismoPreservado: devuelve true")
    void polimorfismo() throws Exception {
        assertTrue(Ej29_SerializarHerencia.polimorfismoPreservado(dir + "/pol.dat"));
    }

    @Test @DisplayName("tiposDeserializados: preserva tipos mixtos")
    void tiposDeserializados() throws Exception {
        String[] tipos = Ej29_SerializarHerencia.tiposDeserializados(dir + "/tipos.dat");
        assertEquals(3, tipos.length);
        assertEquals("Empleado", tipos[0]);
        assertEquals("Camarero", tipos[1]);
        assertEquals("Cocinero", tipos[2]);
    }

    @Test @DisplayName("nominaTotal: suma salarios + propinas")
    void nominaTotal() throws Exception {
        String ruta = dir + "/equipo.dat";
        List<Ej29_SerializarHerencia.Empleado> equipo = new ArrayList<>();
        equipo.add(new Ej29_SerializarHerencia.Empleado("Base", 1000));
        equipo.add(new Ej29_SerializarHerencia.Camarero("Ana", 1500, 200));
        equipo.add(new Ej29_SerializarHerencia.Cocinero("Pedro", 1800, "Paella"));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(equipo);
        }
        // 1000 + (1500+200) + 1800 = 4500
        assertEquals(4500.0, Ej29_SerializarHerencia.nominaTotal(ruta), 0.01);
    }

    @Test @DisplayName("contarPorTipo: cuenta correctamente")
    void contarPorTipo() throws Exception {
        String ruta = dir + "/tipos2.dat";
        List<Ej29_SerializarHerencia.Empleado> equipo = new ArrayList<>();
        equipo.add(new Ej29_SerializarHerencia.Empleado("B", 1000));
        equipo.add(new Ej29_SerializarHerencia.Camarero("A", 1500, 200));
        equipo.add(new Ej29_SerializarHerencia.Camarero("C", 1400, 150));
        equipo.add(new Ej29_SerializarHerencia.Cocinero("P", 1800, "Sushi"));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(equipo);
        }
        int[] c = Ej29_SerializarHerencia.contarPorTipo(ruta);
        assertEquals(1, c[0]); // Empleado base
        assertEquals(2, c[1]); // Camareros
        assertEquals(1, c[2]); // Cocineros
    }

    @Test @DisplayName("subclaseHeredaSerializable: devuelve true")
    void subclaseHeredaSerializable() {
        assertTrue(Ej29_SerializarHerencia.subclaseHeredaSerializable());
    }
}
