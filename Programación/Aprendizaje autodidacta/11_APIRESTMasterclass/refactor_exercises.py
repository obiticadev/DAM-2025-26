# -*- coding: utf-8 -*-
import os
import re

base_path = r"C:\Users\obitica\Java\DAM-2025-26\Programación\Aprendizaje autodidacta\11_APIRESTMasterclass"

exercises = {
    # ==================== BLOCK 13: RELATIONS ====================
    "Ej115OneToOne": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Valida si el usuario tiene un perfil asociado.
     */
    public static boolean validarPerfilAsociado(Usuario115 u) {
        return u != null && u.getPerfil() != null;
    }

    /**
     * Reto Extra 2: Obtiene la biografia del perfil del usuario, o un valor por defecto.
     */
    public static String obtenerBioDelUsuario(Usuario115 u, String porDefecto) {
        if (u == null || u.getPerfil() == null || u.getPerfil().getBio() == null) {
            return porDefecto;
        }
        return u.getPerfil().getBio();
    }

    /**
     * Reto Extra 3: Comprueba si la biografia tiene una longitud suficiente.
     */
    public static boolean esBioSuficiente(Usuario115 u, int minLongitud) {
        if (u == null || u.getPerfil() == null || u.getPerfil().getBio() == null) {
            return false;
        }
        return u.getPerfil().getBio().length() >= minLongitud;
    }

    /**
     * Reto Extra 4: Comprueba si el usuario tiene el mismo nombre que su bio.
     */
    public static boolean tieneMismoNombreYBio(Usuario115 u) {
        if (u == null || u.getPerfil() == null || u.getPerfil().getBio() == null) {
            return false;
        }
        try {
            var fNombre = u.getClass().getDeclaredField("nombre");
            fNombre.setAccessible(true);
            String nombre = (String) fNombre.get(u);
            return nombre != null && nombre.equalsIgnoreCase(u.getPerfil().getBio());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Reto Extra 5: Comprueba si la biografia contiene una palabra clave.
     */
    public static boolean bioContienePalabra(Usuario115 u, String palabra) {
        if (u == null || u.getPerfil() == null || u.getPerfil().getBio() == null || palabra == null) {
            return false;
        }
        return u.getPerfil().getBio().toLowerCase().contains(palabra.toLowerCase());
    }

    /**
     * Reto Extra 6: Crea una instancia de Perfil115 con bio saneada.
     */
    public static Perfil115 crearPerfilSaneado(String bio) {
        if (bio == null) {
            return new Perfil115("");
        }
        return new Perfil115(bio.trim());
    }

    /**
     * Reto Extra 7: Genera una representacion formateada del perfil.
     */
    public static String formatearPerfil(Usuario115 u) {
        if (u == null) return "SIN_USUARIO";
        if (u.getPerfil() == null) return "SIN_PERFIL";
        return "Perfil[Id=" + u.getPerfil().getId() + ", Bio=" + u.getPerfil().getBio() + "]";
    }

    /**
     * Reto Extra 8: Comprueba si el ID del perfil es par.
     */
    public static boolean perfilTieneIdPar(Usuario115 u) {
        if (u == null || u.getPerfil() == null || u.getPerfil().getId() == null) {
            return false;
        }
        return u.getPerfil().getId() % 2 == 0;
    }

    /**
     * Reto Extra 9: Comprueba si el usuario tiene id asignado.
     */
    public static boolean usuarioTieneId(Usuario115 u) {
        return u != null && u.getId() != null;
    }

    /**
     * Reto Extra 10: Clona superficialmente el usuario.
     */
    public static Usuario115 clonarUsuarioSencillo(Usuario115 u) {
        if (u == null) return null;
        try {
            var fNombre = u.getClass().getDeclaredField("nombre");
            fNombre.setAccessible(true);
            String nombre = (String) fNombre.get(u);
            return new Usuario115(nombre, u.getPerfil());
        } catch (Exception e) {
            return null;
        }
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertTrue(Ej115OneToOne.validarPerfilAsociado(u));
        assertFalse(Ej115OneToOne.validarPerfilAsociado(null));
    }

    @Test
    void testRetoExtra02() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertEquals("hola", Ej115OneToOne.obtenerBioDelUsuario(u, "def"));
        assertEquals("def", Ej115OneToOne.obtenerBioDelUsuario(null, "def"));
    }

    @Test
    void testRetoExtra03() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertTrue(Ej115OneToOne.esBioSuficiente(u, 3));
        assertFalse(Ej115OneToOne.esBioSuficiente(u, 10));
    }

    @Test
    void testRetoExtra04() {
        var u = new Usuario115("Ana", new Perfil115("Ana"));
        assertTrue(Ej115OneToOne.tieneMismoNombreYBio(u));
    }

    @Test
    void testRetoExtra05() {
        var u = new Usuario115("Ana", new Perfil115("hola mundo"));
        assertTrue(Ej115OneToOne.bioContienePalabra(u, "mundo"));
        assertFalse(Ej115OneToOne.bioContienePalabra(u, "casa"));
    }

    @Test
    void testRetoExtra06() {
        var p = Ej115OneToOne.crearPerfilSaneado("  hola  ");
        assertEquals("hola", p.getBio());
    }

    @Test
    void testRetoExtra07() {
        var u = new Usuario115("Ana", null);
        assertEquals("SIN_PERFIL", Ej115OneToOne.formatearPerfil(u));
    }

    @Test
    void testRetoExtra08() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        assertFalse(Ej115OneToOne.perfilTieneIdPar(u));
    }

    @Test
    void testRetoExtra09() {
        var u = new Usuario115("Ana", null);
        assertFalse(Ej115OneToOne.usuarioTieneId(u));
    }

    @Test
    void testRetoExtra10() {
        var u = new Usuario115("Ana", new Perfil115("hola"));
        var c = Ej115OneToOne.clonarUsuarioSencillo(u);
        assertNotNull(c);
        assertEquals(u.getPerfil(), c.getPerfil());
    }
"""
    },
    "Ej116OneToManyManyToOne": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta el numero de lineas de un pedido.
     */
    public static int contarLineas(Pedido116 p) {
        return p == null || p.getLineas() == null ? 0 : p.getLineas().size();
    }

    /**
     * Reto Extra 2: Comprueba si el pedido tiene al menos una linea.
     */
    public static boolean tieneLineas(Pedido116 p) {
        return p != null && p.getLineas() != null && !p.getLineas().isEmpty();
    }

    /**
     * Reto Extra 3: Verifica si una linea tiene asociado el pedido correcto.
     */
    public static boolean esLineaSincronizada(Pedido116 p, Linea116 l) {
        return p != null && l != null && l.getPedido() == p;
    }

    /**
     * Reto Extra 4: Comprueba si el pedido contiene un producto por nombre.
     */
    public static boolean contieneProducto(Pedido116 p, String producto) {
        if (p == null || p.getLineas() == null || producto == null) return false;
        for (Linea116 l : p.getLineas()) {
            try {
                var f = l.getClass().getDeclaredField("producto");
                f.setAccessible(true);
                String val = (String) f.get(l);
                if (producto.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta cuantos productos tienen un nombre mas largo que un valor.
     */
    public static int contarProductosConNombreLargo(Pedido116 p, int len) {
        if (p == null || p.getLineas() == null) return 0;
        int count = 0;
        for (Linea116 l : p.getLineas()) {
            try {
                var f = l.getClass().getDeclaredField("producto");
                f.setAccessible(true);
                String val = (String) f.get(l);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea una linea de pedido.
     */
    public static Linea116 crearLinea(String producto) {
        return new Linea116(producto);
    }

    /**
     * Reto Extra 7: Obtiene el primer producto del pedido o null.
     */
    public static String obtenerPrimerProducto(Pedido116 p) {
        if (p == null || p.getLineas() == null || p.getLineas().isEmpty()) return null;
        try {
            var l = p.getLineas().get(0);
            var f = l.getClass().getDeclaredField("producto");
            f.setAccessible(true);
            return (String) f.get(l);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 8: Comprueba si todos los productos no son nulos ni vacios.
     */
    public static boolean todosProductosValidos(Pedido116 p) {
        if (p == null || p.getLineas() == null || p.getLineas().isEmpty()) return false;
        for (Linea116 l : p.getLineas()) {
            try {
                var f = l.getClass().getDeclaredField("producto");
                f.setAccessible(true);
                String val = (String) f.get(l);
                if (val == null || val.trim().isEmpty()) return false;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reto Extra 9: Sincroniza bidireccionalmente un pedido y una linea.
     */
    public static void vincularLinea(Pedido116 p, Linea116 l) {
        if (p != null && l != null) {
            p.addLinea(l);
        }
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto del pedido.
     */
    public static String formatearPedido(Pedido116 p) {
        if (p == null) return "SIN_PEDIDO";
        return "Pedido[Id=" + p.getId() + ", Lineas=" + contarLineas(p) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var p = new Pedido116();
        assertEquals(0, Ej116OneToManyManyToOne.contarLineas(p));
        p.addLinea(new Linea116("A"));
        assertEquals(1, Ej116OneToManyManyToOne.contarLineas(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Pedido116();
        assertFalse(Ej116OneToManyManyToOne.tieneLineas(p));
        p.addLinea(new Linea116("A"));
        assertTrue(Ej116OneToManyManyToOne.tieneLineas(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Pedido116();
        var l = new Linea116("A");
        p.addLinea(l);
        assertTrue(Ej116OneToManyManyToOne.esLineaSincronizada(p, l));
    }

    @Test
    void testRetoExtra04() {
        var p = new Pedido116();
        var l = new Linea116("Laptop");
        p.addLinea(l);
        assertTrue(Ej116OneToManyManyToOne.contieneProducto(p, "Laptop"));
        assertFalse(Ej116OneToManyManyToOne.contieneProducto(p, "PC"));
    }

    @Test
    void testRetoExtra05() {
        var p = new Pedido116();
        p.addLinea(new Linea116("Mouse"));
        p.addLinea(new Linea116("Monitor"));
        assertEquals(1, Ej116OneToManyManyToOne.contarProductosConNombreLargo(p, 6));
    }

    @Test
    void testRetoExtra06() {
        var l = Ej116OneToManyManyToOne.crearLinea("Teclado");
        assertNotNull(l);
    }

    @Test
    void testRetoExtra07() {
        var p = new Pedido116();
        p.addLinea(new Linea116("Mouse"));
        assertEquals("Mouse", Ej116OneToManyManyToOne.obtenerPrimerProducto(p));
    }

    @Test
    void testRetoExtra08() {
        var p = new Pedido116();
        p.addLinea(new Linea116("A"));
        assertTrue(Ej116OneToManyManyToOne.todosProductosValidos(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Pedido116();
        var l = new Linea116("A");
        Ej116OneToManyManyToOne.vincularLinea(p, l);
        assertEquals(p, l.getPedido());
    }

    @Test
    void testRetoExtra10() {
        var p = new Pedido116();
        assertEquals("Pedido[Id=null, Lineas=0]", Ej116OneToManyManyToOne.formatearPedido(p));
    }
"""
    },
    "Ej117ManyToManyJoinTable": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta el numero de cursos de un alumno.
     */
    public static int contarCursos(Alumno117 a) {
        return a == null || a.getCursos() == null ? 0 : a.getCursos().size();
    }

    /**
     * Reto Extra 2: Comprueba si el alumno tiene al menos un curso.
     */
    public static boolean tieneCursos(Alumno117 a) {
        return a != null && a.getCursos() != null && !a.getCursos().isEmpty();
    }

    /**
     * Reto Extra 3: Verifica si un alumno esta matriculado en un curso concreto.
     */
    public static boolean estaMatriculado(Alumno117 a, Curso117 c) {
        return a != null && a.getCursos() != null && c != null && a.getCursos().contains(c);
    }

    /**
     * Reto Extra 4: Comprueba si el alumno tiene un curso por nombre.
     */
    public static boolean tieneCursoPorNombre(Alumno117 a, String nombreCurso) {
        if (a == null || a.getCursos() == null || nombreCurso == null) return false;
        for (Curso117 c : a.getCursos()) {
            try {
                var f = c.getClass().getDeclaredField("nombre");
                f.setAccessible(true);
                String val = (String) f.get(c);
                if (nombreCurso.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta cursos con nombre mas largo que len.
     */
    public static int contarCursosNombreLargo(Alumno117 a, int len) {
        if (a == null || a.getCursos() == null) return 0;
        int count = 0;
        for (Curso117 c : a.getCursos()) {
            try {
                var f = c.getClass().getDeclaredField("nombre");
                f.setAccessible(true);
                String val = (String) f.get(c);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea un nuevo curso.
     */
    public static Curso117 crearCurso(String nombre) {
        return new Curso117(nombre);
    }

    /**
     * Reto Extra 7: Desmatricula a un alumno de un curso.
     */
    public static boolean desmatricular(Alumno117 a, Curso117 c) {
        if (a == null || a.getCursos() == null || c == null) return false;
        return a.getCursos().remove(c);
    }

    /**
     * Reto Extra 8: Comprueba si dos alumnos comparten al menos un curso.
     */
    public static boolean compartenCurso(Alumno117 a1, Alumno117 a2) {
        if (a1 == null || a2 == null || a1.getCursos() == null || a2.getCursos() == null) return false;
        for (Curso117 c : a1.getCursos()) {
            if (a2.getCursos().contains(c)) return true;
        }
        return false;
    }

    /**
     * Reto Extra 9: Matricula a un alumno en multiples cursos.
     */
    public static void matricularEnLote(Alumno117 a, java.util.List<Curso117> lista) {
        if (a != null && lista != null) {
            for (Curso117 c : lista) {
                a.matricular(c);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto del alumno.
     */
    public static String formatearAlumno(Alumno117 a) {
        if (a == null) return "SIN_ALUMNO";
        return "Alumno[Id=" + a.getId() + ", Cursos=" + contarCursos(a) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var a = new Alumno117("Ana");
        assertEquals(0, Ej117ManyToManyJoinTable.contarCursos(a));
        a.matricular(new Curso117("Mates"));
        assertEquals(1, Ej117ManyToManyJoinTable.contarCursos(a));
    }

    @Test
    void testRetoExtra02() {
        var a = new Alumno117("Ana");
        assertFalse(Ej117ManyToManyJoinTable.tieneCursos(a));
        a.matricular(new Curso117("Mates"));
        assertTrue(Ej117ManyToManyJoinTable.tieneCursos(a));
    }

    @Test
    void testRetoExtra03() {
        var a = new Alumno117("Ana");
        var c = new Curso117("Mates");
        a.matricular(c);
        assertTrue(Ej117ManyToManyJoinTable.estaMatriculado(a, c));
    }

    @Test
    void testRetoExtra04() {
        var a = new Alumno117("Ana");
        a.matricular(new Curso117("Historia"));
        assertTrue(Ej117ManyToManyJoinTable.tieneCursoPorNombre(a, "Historia"));
        assertFalse(Ej117ManyToManyJoinTable.tieneCursoPorNombre(a, "Lengua"));
    }

    @Test
    void testRetoExtra05() {
        var a = new Alumno117("Ana");
        a.matricular(new Curso117("Geografia"));
        assertEquals(1, Ej117ManyToManyJoinTable.contarCursosNombreLargo(a, 5));
    }

    @Test
    void testRetoExtra06() {
        var c = Ej117ManyToManyJoinTable.crearCurso("Mates");
        assertNotNull(c);
    }

    @Test
    void testRetoExtra07() {
        var a = new Alumno117("Ana");
        var c = new Curso117("Mates");
        a.matricular(c);
        assertTrue(Ej117ManyToManyJoinTable.desmatricular(a, c));
        assertEquals(0, a.getCursos().size());
    }

    @Test
    void testRetoExtra08() {
        var a1 = new Alumno117("Ana");
        var a2 = new Alumno117("Eva");
        var c = new Curso117("Mates");
        a1.matricular(c);
        a2.matricular(c);
        assertTrue(Ej117ManyToManyJoinTable.compartenCurso(a1, a2));
    }

    @Test
    void testRetoExtra09() {
        var a = new Alumno117("Ana");
        var c1 = new Curso117("Mates");
        var c2 = new Curso117("Historia");
        Ej117ManyToManyJoinTable.matricularEnLote(a, java.util.List.of(c1, c2));
        assertEquals(2, a.getCursos().size());
    }

    @Test
    void testRetoExtra10() {
        var a = new Alumno117("Ana");
        assertEquals("Alumno[Id=null, Cursos=0]", Ej117ManyToManyJoinTable.formatearAlumno(a));
    }
"""
    },
    "Ej118BidirectionalSync": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta libros de un autor.
     */
    public static int contarLibros(Autor118 a) {
        return a == null || a.getLibros() == null ? 0 : a.getLibros().size();
    }

    /**
     * Reto Extra 2: Comprueba si el autor tiene libros.
     */
    public static boolean tieneLibros(Autor118 a) {
        return a != null && a.getLibros() != null && !a.getLibros().isEmpty();
    }

    /**
     * Reto Extra 3: Verifica si un libro esta sincronizado con el autor.
     */
    public static boolean esLibroSincronizado(Autor118 a, Libro118 l) {
        return a != null && l != null && l.getAutor() == a && a.getLibros().contains(l);
    }

    /**
     * Reto Extra 4: Comprueba si el autor tiene un libro con determinado titulo.
     */
    public static boolean tieneLibroConTitulo(Autor118 a, String titulo) {
        if (a == null || a.getLibros() == null || titulo == null) return false;
        for (Libro118 l : a.getLibros()) {
            try {
                var f = l.getClass().getDeclaredField("titulo");
                f.setAccessible(true);
                String val = (String) f.get(l);
                if (titulo.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta libros con titulo largo.
     */
    public static int contarLibrosTituloLargo(Autor118 a, int len) {
        if (a == null || a.getLibros() == null) return 0;
        int count = 0;
        for (Libro118 l : a.getLibros()) {
            try {
                var f = l.getClass().getDeclaredField("titulo");
                f.setAccessible(true);
                String val = (String) f.get(l);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea un nuevo libro.
     */
    public static Libro118 crearLibro(String titulo) {
        return new Libro118(titulo);
    }

    /**
     * Reto Extra 7: Desvincula de forma sincronizada un libro de su autor.
     */
    public static void desvincularLibro(Autor118 a, Libro118 l) {
        if (a != null && l != null) {
            a.removeLibro(l);
        }
    }

    /**
     * Reto Extra 8: Comprueba si un libro tiene autor.
     */
    public static boolean tieneAutor(Libro118 l) {
        return l != null && l.getAutor() != null;
    }

    /**
     * Reto Extra 9: Sincroniza en lote multiples libros.
     */
    public static void vincularEnLote(Autor118 a, java.util.List<Libro118> lista) {
        if (a != null && lista != null) {
            for (Libro118 l : lista) {
                a.addLibro(l);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna texto del autor.
     */
    public static String formatearAutor(Autor118 a) {
        if (a == null) return "SIN_AUTOR";
        return "Autor[Libros=" + contarLibros(a) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var a = new Autor118("Cervantes");
        assertEquals(0, Ej118BidirectionalSync.contarLibros(a));
        a.addLibro(new Libro118("Quijote"));
        assertEquals(1, Ej118BidirectionalSync.contarLibros(a));
    }

    @Test
    void testRetoExtra02() {
        var a = new Autor118("Cervantes");
        assertFalse(Ej118BidirectionalSync.tieneLibros(a));
        a.addLibro(new Libro118("Quijote"));
        assertTrue(Ej118BidirectionalSync.tieneLibros(a));
    }

    @Test
    void testRetoExtra03() {
        var a = new Autor118("Cervantes");
        var l = new Libro118("Quijote");
        a.addLibro(l);
        assertTrue(Ej118BidirectionalSync.esLibroSincronizado(a, l));
    }

    @Test
    void testRetoExtra04() {
        var a = new Autor118("Cervantes");
        a.addLibro(new Libro118("Quijote"));
        assertTrue(Ej118BidirectionalSync.tieneLibroConTitulo(a, "Quijote"));
        assertFalse(Ej118BidirectionalSync.tieneLibroConTitulo(a, "Novela"));
    }

    @Test
    void testRetoExtra05() {
        var a = new Autor118("Cervantes");
        a.addLibro(new Libro118("El Quijote"));
        assertEquals(1, Ej118BidirectionalSync.contarLibrosTituloLargo(a, 8));
    }

    @Test
    void testRetoExtra06() {
        var l = Ej118BidirectionalSync.crearLibro("La Galatea");
        assertNotNull(l);
    }

    @Test
    void testRetoExtra07() {
        var a = new Autor118("Cervantes");
        var l = new Libro118("Quijote");
        a.addLibro(l);
        Ej118BidirectionalSync.desvincularLibro(a, l);
        assertFalse(a.getLibros().contains(l));
        assertNull(l.getAutor());
    }

    @Test
    void testRetoExtra08() {
        var l = new Libro118("Quijote");
        assertFalse(Ej118BidirectionalSync.tieneAutor(l));
    }

    @Test
    void testRetoExtra09() {
        var a = new Autor118("Cervantes");
        var l1 = new Libro118("Q1");
        var l2 = new Libro118("Q2");
        Ej118BidirectionalSync.vincularEnLote(a, java.util.List.of(l1, l2));
        assertEquals(2, a.getLibros().size());
    }

    @Test
    void testRetoExtra10() {
        var a = new Autor118("Cervantes");
        assertEquals("Autor[Libros=0]", Ej118BidirectionalSync.formatearAutor(a));
    }
"""
    },
    "Ej119CascadeTypes": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta los conceptos de una factura.
     */
    public static int contarConceptos(Factura119 f) {
        return f == null || f.getConceptos() == null ? 0 : f.getConceptos().size();
    }

    /**
     * Reto Extra 2: Comprueba si la factura tiene conceptos.
     */
    public static boolean tieneConceptos(Factura119 f) {
        return f != null && f.getConceptos() != null && !f.getConceptos().isEmpty();
    }

    /**
     * Reto Extra 3: Comprueba si un concepto esta en la factura.
     */
    public static boolean contieneConcepto(Factura119 f, Concepto119 c) {
        return f != null && f.getConceptos() != null && c != null && f.getConceptos().contains(c);
    }

    /**
     * Reto Extra 4: Comprueba si algun concepto tiene una descripcion.
     */
    public static boolean tieneDescripcion(Factura119 f, String desc) {
        if (f == null || f.getConceptos() == null || desc == null) return false;
        for (Concepto119 c : f.getConceptos()) {
            try {
                var field = c.getClass().getDeclaredField("descripcion");
                field.setAccessible(true);
                String val = (String) field.get(c);
                if (desc.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta descripciones largas.
     */
    public static int contarConceptosDescLarga(Factura119 f, int len) {
        if (f == null || f.getConceptos() == null) return 0;
        int count = 0;
        for (Concepto119 c : f.getConceptos()) {
            try {
                var field = c.getClass().getDeclaredField("descripcion");
                field.setAccessible(true);
                String val = (String) field.get(c);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea un concepto.
     */
    public static Concepto119 crearConcepto(String descripcion) {
        return new Concepto119(descripcion);
    }

    /**
     * Reto Extra 7: Remueve el primer concepto.
     */
    public static boolean removerPrimerConcepto(Factura119 f) {
        if (f == null || f.getConceptos() == null || f.getConceptos().isEmpty()) return false;
        f.getConceptos().remove(0);
        return true;
    }

    /**
     * Reto Extra 8: Valida factura no nula.
     */
    public static boolean esValida(Factura119 f) {
        return f != null;
    }

    /**
     * Reto Extra 9: Vincula conceptos en lote.
     */
    public static void vincularConceptos(Factura119 f, java.util.List<Concepto119> lista) {
        if (f != null && lista != null) {
            for (Concepto119 c : lista) {
                f.add(c);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna formato de factura.
     */
    public static String formatearFactura(Factura119 f) {
        if (f == null) return "SIN_FACTURA";
        return "Factura[Id=" + f.getId() + ", Conceptos=" + contarConceptos(f) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var f = new Factura119();
        assertEquals(0, Ej119CascadeTypes.contarConceptos(f));
        f.add(new Concepto119("Servicio"));
        assertEquals(1, Ej119CascadeTypes.contarConceptos(f));
    }

    @Test
    void testRetoExtra02() {
        var f = new Factura119();
        assertFalse(Ej119CascadeTypes.tieneConceptos(f));
        f.add(new Concepto119("Servicio"));
        assertTrue(Ej119CascadeTypes.tieneConceptos(f));
    }

    @Test
    void testRetoExtra03() {
        var f = new Factura119();
        var c = new Concepto119("Servicio");
        f.add(c);
        assertTrue(Ej119CascadeTypes.contieneConcepto(f, c));
    }

    @Test
    void testRetoExtra04() {
        var f = new Factura119();
        f.add(new Concepto119("Premium"));
        assertTrue(Ej119CascadeTypes.tieneDescripcion(f, "Premium"));
        assertFalse(Ej119CascadeTypes.tieneDescripcion(f, "Normal"));
    }

    @Test
    void testRetoExtra05() {
        var f = new Factura119();
        f.add(new Concepto119("Mantenimiento"));
        assertEquals(1, Ej119CascadeTypes.contarConceptosDescLarga(f, 8));
    }

    @Test
    void testRetoExtra06() {
        var c = Ej119CascadeTypes.crearConcepto("Soporte");
        assertNotNull(c);
    }

    @Test
    void testRetoExtra07() {
        var f = new Factura119();
        f.add(new Concepto119("A"));
        assertTrue(Ej119CascadeTypes.removerPrimerConcepto(f));
        assertEquals(0, f.getConceptos().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej119CascadeTypes.esValida(new Factura119()));
        assertFalse(Ej119CascadeTypes.esValida(null));
    }

    @Test
    void testRetoExtra09() {
        var f = new Factura119();
        var c1 = new Concepto119("A");
        var c2 = new Concepto119("B");
        Ej119CascadeTypes.vincularConceptos(f, java.util.List.of(c1, c2));
        assertEquals(2, f.getConceptos().size());
    }

    @Test
    void testRetoExtra10() {
        var f = new Factura119();
        assertEquals("Factura[Id=null, Conceptos=0]", Ej119CascadeTypes.formatearFactura(f));
    }
"""
    },
    "Ej120FetchLazyEager": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta libros de una biblioteca.
     */
    public static int contarLibros(Biblioteca120 b) {
        return b == null || b.getLibros() == null ? 0 : b.getLibros().size();
    }

    /**
     * Reto Extra 2: Comprueba si tiene libros.
     */
    public static boolean tieneLibros(Biblioteca120 b) {
        return b != null && b.getLibros() != null && !b.getLibros().isEmpty();
    }

    /**
     * Reto Extra 3: Comprueba si un libro esta en biblioteca.
     */
    public static boolean contieneLibro(Biblioteca120 b, LibroLazy120 l) {
        return b != null && b.getLibros() != null && l != null && b.getLibros().contains(l);
    }

    /**
     * Reto Extra 4: Comprueba si algun libro tiene un titulo.
     */
    public static boolean tieneTitulo(Biblioteca120 b, String titulo) {
        if (b == null || b.getLibros() == null || titulo == null) return false;
        for (LibroLazy120 l : b.getLibros()) {
            try {
                var field = l.getClass().getDeclaredField("titulo");
                field.setAccessible(true);
                String val = (String) field.get(l);
                if (titulo.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta titulos largos.
     */
    public static int contarLibrosTituloLargo(Biblioteca120 b, int len) {
        if (b == null || b.getLibros() == null) return 0;
        int count = 0;
        for (LibroLazy120 l : b.getLibros()) {
            try {
                var field = l.getClass().getDeclaredField("titulo");
                field.setAccessible(true);
                String val = (String) field.get(l);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea un libro lazy.
     */
    public static LibroLazy120 crearLibro(String titulo) {
        return new LibroLazy120(titulo);
    }

    /**
     * Reto Extra 7: Remueve el primer libro.
     */
    public static boolean removerPrimerLibro(Biblioteca120 b) {
        if (b == null || b.getLibros() == null || b.getLibros().isEmpty()) return false;
        b.getLibros().remove(0);
        return true;
    }

    /**
     * Reto Extra 8: Valida biblioteca no nula.
     */
    public static boolean esValida(Biblioteca120 b) {
        return b != null;
    }

    /**
     * Reto Extra 9: Vincula libros en lote.
     */
    public static void vincularLibros(Biblioteca120 b, java.util.List<LibroLazy120> lista) {
        if (b != null && lista != null) {
            for (LibroLazy120 l : lista) {
                b.add(l);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna formato de biblioteca.
     */
    public static String formatearBiblioteca(Biblioteca120 b) {
        if (b == null) return "SIN_BIBLIOTECA";
        return "Biblioteca[Id=" + b.getId() + ", Libros=" + contarLibros(b) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var b = new Biblioteca120("Central");
        assertEquals(0, Ej120FetchLazyEager.contarLibros(b));
        b.add(new LibroLazy120("A"));
        assertEquals(1, Ej120FetchLazyEager.contarLibros(b));
    }

    @Test
    void testRetoExtra02() {
        var b = new Biblioteca120("Central");
        assertFalse(Ej120FetchLazyEager.tieneLibros(b));
        b.add(new LibroLazy120("A"));
        assertTrue(Ej120FetchLazyEager.tieneLibros(b));
    }

    @Test
    void testRetoExtra03() {
        var b = new Biblioteca120("Central");
        var l = new LibroLazy120("A");
        b.add(l);
        assertTrue(Ej120FetchLazyEager.contieneLibro(b, l));
    }

    @Test
    void testRetoExtra04() {
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("Quijote"));
        assertTrue(Ej120FetchLazyEager.tieneTitulo(b, "Quijote"));
        assertFalse(Ej120FetchLazyEager.tieneTitulo(b, "Celestina"));
    }

    @Test
    void testRetoExtra05() {
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("Quijote"));
        assertEquals(1, Ej120FetchLazyEager.contarLibrosTituloLargo(b, 5));
    }

    @Test
    void testRetoExtra06() {
        var l = Ej120FetchLazyEager.crearLibro("A");
        assertNotNull(l);
    }

    @Test
    void testRetoExtra07() {
        var b = new Biblioteca120("Central");
        b.add(new LibroLazy120("A"));
        assertTrue(Ej120FetchLazyEager.removerPrimerLibro(b));
        assertEquals(0, b.getLibros().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej120FetchLazyEager.esValida(new Biblioteca120("A")));
        assertFalse(Ej120FetchLazyEager.esValida(null));
    }

    @Test
    void testRetoExtra09() {
        var b = new Biblioteca120("Central");
        var l1 = new LibroLazy120("A");
        var l2 = new LibroLazy120("B");
        Ej120FetchLazyEager.vincularLibros(b, java.util.List.of(l1, l2));
        assertEquals(2, b.getLibros().size());
    }

    @Test
    void testRetoExtra10() {
        var b = new Biblioteca120("Central");
        assertEquals("Biblioteca[Id=null, Libros=0]", Ej120FetchLazyEager.formatearBiblioteca(b));
    }
"""
    },
    "Ej121NPlusOneProblem": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta posts de un blog.
     */
    public static int contarPosts(Blog121 b) {
        return b == null || b.getPosts() == null ? 0 : b.getPosts().size();
    }

    /**
     * Reto Extra 2: Comprueba si tiene posts.
     */
    public static boolean tienePosts(Blog121 b) {
        return b != null && b.getPosts() != null && !b.getPosts().isEmpty();
    }

    /**
     * Reto Extra 3: Comprueba si un post esta en blog.
     */
    public static boolean contienePost(Blog121 b, Post121 p) {
        return b != null && b.getPosts() != null && p != null && b.getPosts().contains(p);
    }

    /**
     * Reto Extra 4: Comprueba si algun post tiene un titulo.
     */
    public static boolean tieneTitulo(Blog121 b, String titulo) {
        if (b == null || b.getPosts() == null || titulo == null) return false;
        for (Post121 p : b.getPosts()) {
            try {
                var field = p.getClass().getDeclaredField("titulo");
                field.setAccessible(true);
                String val = (String) field.get(p);
                if (titulo.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta titulos largos.
     */
    public static int contarPostsTituloLargo(Blog121 b, int len) {
        if (b == null || b.getPosts() == null) return 0;
        int count = 0;
        for (Post121 p : b.getPosts()) {
            try {
                var field = p.getClass().getDeclaredField("titulo");
                field.setAccessible(true);
                String val = (String) field.get(p);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea un post.
     */
    public static Post121 crearPost(String titulo) {
        return new Post121(titulo);
    }

    /**
     * Reto Extra 7: Remueve el primer post.
     */
    public static boolean removerPrimerPost(Blog121 b) {
        if (b == null || b.getPosts() == null || b.getPosts().isEmpty()) return false;
        b.getPosts().remove(0);
        return true;
    }

    /**
     * Reto Extra 8: Valida blog no nulo.
     */
    public static boolean esValido(Blog121 b) {
        return b != null;
    }

    /**
     * Reto Extra 9: Vincula posts en lote.
     */
    public static void vincularPosts(Blog121 b, java.util.List<Post121> lista) {
        if (b != null && lista != null) {
            for (Post121 p : lista) {
                b.add(p);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna formato de blog.
     */
    public static String formatearBlog(Blog121 b) {
        if (b == null) return "SIN_BLOG";
        return "Blog[Posts=" + contarPosts(b) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var b = new Blog121("Tech");
        assertEquals(0, Ej121NPlusOneProblem.contarPosts(b));
        b.add(new Post121("JPA"));
        assertEquals(1, Ej121NPlusOneProblem.contarPosts(b));
    }

    @Test
    void testRetoExtra02() {
        var b = new Blog121("Tech");
        assertFalse(Ej121NPlusOneProblem.tienePosts(b));
        b.add(new Post121("JPA"));
        assertTrue(Ej121NPlusOneProblem.tienePosts(b));
    }

    @Test
    void testRetoExtra03() {
        var b = new Blog121("Tech");
        var p = new Post121("JPA");
        b.add(p);
        assertTrue(Ej121NPlusOneProblem.contienePost(b, p));
    }

    @Test
    void testRetoExtra04() {
        var b = new Blog121("Tech");
        b.add(new Post121("JPA"));
        assertTrue(Ej121NPlusOneProblem.tieneTitulo(b, "JPA"));
        assertFalse(Ej121NPlusOneProblem.tieneTitulo(b, "Spring"));
    }

    @Test
    void testRetoExtra05() {
        var b = new Blog121("Tech");
        b.add(new Post121("Hibernate"));
        assertEquals(1, Ej121NPlusOneProblem.contarPostsTituloLargo(b, 5));
    }

    @Test
    void testRetoExtra06() {
        var p = Ej121NPlusOneProblem.crearPost("A");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra07() {
        var b = new Blog121("Tech");
        b.add(new Post121("A"));
        assertTrue(Ej121NPlusOneProblem.removerPrimerPost(b));
        assertEquals(0, b.getPosts().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej121NPlusOneProblem.esValido(new Blog121("A")));
        assertFalse(Ej121NPlusOneProblem.esValido(null));
    }

    @Test
    void testRetoExtra09() {
        var b = new Blog121("Tech");
        var p1 = new Post121("A");
        var p2 = new Post121("B");
        Ej121NPlusOneProblem.vincularPosts(b, java.util.List.of(p1, p2));
        assertEquals(2, b.getPosts().size());
    }

    @Test
    void testRetoExtra10() {
        var b = new Blog121("Tech");
        assertEquals("Blog[Posts=0]", Ej121NPlusOneProblem.formatearBlog(b));
    }
"""
    },
    "Ej122JoinFetchAndEntityGraph": {
        "package": "b13_rel",
        "methods": """
    /**
     * Reto Extra 1: Cuenta tareas de un proyecto.
     */
    public static int contarTareas(Proyecto122 p) {
        return p == null || p.getTareas() == null ? 0 : p.getTareas().size();
    }

    /**
     * Reto Extra 2: Comprueba si tiene tareas.
     */
    public static boolean tieneTareas(Proyecto122 p) {
        return p != null && p.getTareas() != null && !p.getTareas().isEmpty();
    }

    /**
     * Reto Extra 3: Comprueba si una tarea esta en proyecto.
     */
    public static boolean contieneTarea(Proyecto122 p, Tarea122 t) {
        return p != null && p.getTareas() != null && t != null && p.getTareas().contains(t);
    }

    /**
     * Reto Extra 4: Comprueba si alguna tarea tiene un titulo.
     */
    public static boolean tieneTitulo(Proyecto122 p, String titulo) {
        if (p == null || p.getTareas() == null || titulo == null) return false;
        for (Tarea122 t : p.getTareas()) {
            try {
                var field = t.getClass().getDeclaredField("titulo");
                field.setAccessible(true);
                String val = (String) field.get(t);
                if (titulo.equalsIgnoreCase(val)) return true;
            } catch (Exception e) {}
        }
        return false;
    }

    /**
     * Reto Extra 5: Cuenta titulos largos.
     */
    public static int contarTareasTituloLargo(Proyecto122 p, int len) {
        if (p == null || p.getTareas() == null) return 0;
        int count = 0;
        for (Tarea122 t : p.getTareas()) {
            try {
                var field = t.getClass().getDeclaredField("titulo");
                field.setAccessible(true);
                String val = (String) field.get(t);
                if (val != null && val.length() > len) count++;
            } catch (Exception e) {}
        }
        return count;
    }

    /**
     * Reto Extra 6: Crea una tarea.
     */
    public static Tarea122 crearTarea(String titulo) {
        return new Tarea122(titulo);
    }

    /**
     * Reto Extra 7: Remueve la primera tarea.
     */
    public static boolean removerPrimeraTarea(Proyecto122 p) {
        if (p == null || p.getTareas() == null || p.getTareas().isEmpty()) return false;
        p.getTareas().remove(0);
        return true;
    }

    /**
     * Reto Extra 8: Valida proyecto no nulo.
     */
    public static boolean esValido(Proyecto122 p) {
        return p != null;
    }

    /**
     * Reto Extra 9: Vincula tareas en lote.
     */
    public static void vincularTareas(Proyecto122 p, java.util.List<Tarea122> lista) {
        if (p != null && lista != null) {
            for (Tarea122 t : lista) {
                p.add(t);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna formato de proyecto.
     */
    public static String formatearProyecto(Proyecto122 p) {
        if (p == null) return "SIN_PROYECTO";
        return "Proyecto[Id=" + p.getId() + ", Tareas=" + contarTareas(p) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var p = new Proyecto122("Web");
        assertEquals(0, Ej122JoinFetchAndEntityGraph.contarTareas(p));
        p.add(new Tarea122("UI"));
        assertEquals(1, Ej122JoinFetchAndEntityGraph.contarTareas(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Proyecto122("Web");
        assertFalse(Ej122JoinFetchAndEntityGraph.tieneTareas(p));
        p.add(new Tarea122("UI"));
        assertTrue(Ej122JoinFetchAndEntityGraph.tieneTareas(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Proyecto122("Web");
        var t = new Tarea122("UI");
        p.add(t);
        assertTrue(Ej122JoinFetchAndEntityGraph.contieneTarea(p, t));
    }

    @Test
    void testRetoExtra04() {
        var p = new Proyecto122("Web");
        p.add(new Tarea122("UI"));
        assertTrue(Ej122JoinFetchAndEntityGraph.tieneTitulo(p, "UI"));
        assertFalse(Ej122JoinFetchAndEntityGraph.tieneTitulo(p, "DB"));
    }

    @Test
    void testRetoExtra05() {
        var p = new Proyecto122("Web");
        p.add(new Tarea122("Backend"));
        assertEquals(1, Ej122JoinFetchAndEntityGraph.contarTareasTituloLargo(p, 5));
    }

    @Test
    void testRetoExtra06() {
        var t = Ej122JoinFetchAndEntityGraph.crearTarea("A");
        assertNotNull(t);
    }

    @Test
    void testRetoExtra07() {
        var p = new Proyecto122("Web");
        p.add(new Tarea122("A"));
        assertTrue(Ej122JoinFetchAndEntityGraph.removerPrimeraTarea(p));
        assertEquals(0, p.getTareas().size());
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej122JoinFetchAndEntityGraph.esValido(new Proyecto122("A")));
        assertFalse(Ej122JoinFetchAndEntityGraph.esValido(null));
    }

    @Test
    void testRetoExtra09() {
        var p = new Proyecto122("Web");
        var t1 = new Tarea122("A");
        var t2 = new Tarea122("B");
        Ej122JoinFetchAndEntityGraph.vincularTareas(p, java.util.List.of(t1, t2));
        assertEquals(2, p.getTareas().size());
    }

    @Test
    void testRetoExtra10() {
        var p = new Proyecto122("Web");
        assertEquals("Proyecto[Id=null, Tareas=0]", Ej122JoinFetchAndEntityGraph.formatearProyecto(p));
    }
"""
    },

    # ==================== BLOCK 14: JPA ADVANCED ====================
    "Ej123TransactionPropagation": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si prop requiere una nueva transaccion siempre.
     */
    public static boolean requiereNueva(Propagacion prop) {
        return prop == Propagacion.REQUIRES_NEW;
    }

    /**
     * Reto Extra 2: Comprueba si prop exige una transaccion activa obligatoriamente.
     */
    public static boolean exigeTransaccion(Propagacion prop) {
        return prop == Propagacion.MANDATORY;
    }

    /**
     * Reto Extra 3: Comprueba si prop prohibe tener transaccion activa.
     */
    public static boolean prohibeTransaccion(Propagacion prop) {
        return prop == Propagacion.NEVER;
    }

    /**
     * Reto Extra 4: Comprueba si prop soporta transaccion activa si existe.
     */
    public static boolean soportaTransaccion(Propagacion prop) {
        return prop == Propagacion.SUPPORTS;
    }

    /**
     * Reto Extra 5: Comprueba si prop requiere una transaccion (creandola si no existe).
     */
    public static boolean esRequerida(Propagacion prop) {
        return prop == Propagacion.REQUIRED;
    }

    /**
     * Reto Extra 6: Valida el estado transaccional.
     */
    public static boolean validarEstado(int txActiva, Propagacion prop) {
        if (prop == Propagacion.MANDATORY && txActiva == 0) return false;
        if (prop == Propagacion.NEVER && txActiva != 0) return false;
        return true;
    }

    /**
     * Reto Extra 7: Obtiene el nombre del tipo de propagacion.
     */
    public static String obtenerNombre(Propagacion prop) {
        return prop == null ? "NULL" : prop.name();
    }

    /**
     * Reto Extra 8: Simula si habra rollback en caso de fallo.
     */
    public static boolean provocaraRollback(Propagacion prop) {
        return prop == Propagacion.REQUIRED || prop == Propagacion.REQUIRES_NEW || prop == Propagacion.MANDATORY;
    }

    /**
     * Reto Extra 9: Comprueba si es una propagacion segura.
     */
    public static boolean esSegura(Propagacion prop) {
        return prop != null;
    }

    /**
     * Reto Extra 10: Retorna un identificador simulado.
     */
    public static String obtenerIdSimulado(Propagacion prop) {
        return "PROP_" + obtenerNombre(prop);
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej123TransactionPropagation.requiereNueva(Propagacion.REQUIRES_NEW));
        assertFalse(Ej123TransactionPropagation.requiereNueva(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej123TransactionPropagation.exigeTransaccion(Propagacion.MANDATORY));
        assertFalse(Ej123TransactionPropagation.exigeTransaccion(Propagacion.NEVER));
    }

    @Test
    void testRetoExtra03() {
        assertTrue(Ej123TransactionPropagation.prohibeTransaccion(Propagacion.NEVER));
        assertFalse(Ej123TransactionPropagation.prohibeTransaccion(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej123TransactionPropagation.soportaTransaccion(Propagacion.SUPPORTS));
        assertFalse(Ej123TransactionPropagation.soportaTransaccion(Propagacion.REQUIRES_NEW));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej123TransactionPropagation.esRequerida(Propagacion.REQUIRED));
        assertFalse(Ej123TransactionPropagation.esRequerida(Propagacion.SUPPORTS));
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej123TransactionPropagation.validarEstado(1, Propagacion.REQUIRED));
        assertFalse(Ej123TransactionPropagation.validarEstado(0, Propagacion.MANDATORY));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("REQUIRED", Ej123TransactionPropagation.obtenerNombre(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej123TransactionPropagation.provocaraRollback(Propagacion.REQUIRED));
        assertFalse(Ej123TransactionPropagation.provocaraRollback(Propagacion.NEVER));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej123TransactionPropagation.esSegura(Propagacion.REQUIRED));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("PROP_REQUIRED", Ej123TransactionPropagation.obtenerIdSimulado(Propagacion.REQUIRED));
    }
"""
    },
    "Ej124IsolationLevels": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Verifica si el nivel previene dirty reads.
     */
    public static boolean previeneDirtyRead(Nivel n) {
        return n != Nivel.READ_UNCOMMITTED;
    }

    /**
     * Reto Extra 2: Verifica si el nivel previene non-repeatable reads.
     */
    public static boolean previeneNonRepeatableRead(Nivel n) {
        return n == Nivel.REPEATABLE_READ || n == Nivel.SERIALIZABLE;
    }

    /**
     * Reto Extra 3: Verifica si el nivel previene phantom reads.
     */
    public static boolean previenePhantomRead(Nivel n) {
        return n == Nivel.SERIALIZABLE;
    }

    /**
     * Reto Extra 4: Comprueba si el nivel previene todos los fenomenos.
     */
    public static boolean previeneTodo(Nivel n) {
        return n == Nivel.SERIALIZABLE;
    }

    /**
     * Reto Extra 5: Comprueba si a es mas estricto que b.
     */
    public static boolean esMasEstricto(Nivel a, Nivel b) {
        if (a == null || b == null) return false;
        return a.ordinal() > b.ordinal();
    }

    /**
     * Reto Extra 6: Retorna el nombre en texto.
     */
    public static String obtenerNombreNivel(Nivel n) {
        return n == null ? "NULL" : n.name();
    }

    /**
     * Reto Extra 7: Retorna el nombre en texto del fenomeno.
     */
    public static String obtenerNombreFenomeno(Fenomeno f) {
        return f == null ? "NULL" : f.name();
    }

    /**
     * Reto Extra 8: Comprueba si un nivel es el mas bajo disponible.
     */
    public static boolean esElMasBajo(Nivel n) {
        return n == Nivel.READ_UNCOMMITTED;
    }

    /**
     * Reto Extra 9: Comprueba si un nivel es el mas estricto disponible.
     */
    public static boolean esElMasAlto(Nivel n) {
        return n == Nivel.SERIALIZABLE;
    }

    /**
     * Reto Extra 10: Retorna un identificador estructurado del nivel.
     */
    public static String obtenerIdNivel(Nivel n) {
        return "ISOLATION_" + obtenerNombreNivel(n);
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej124IsolationLevels.previeneDirtyRead(Nivel.READ_COMMITTED));
        assertFalse(Ej124IsolationLevels.previeneDirtyRead(Nivel.READ_UNCOMMITTED));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej124IsolationLevels.previeneNonRepeatableRead(Nivel.REPEATABLE_READ));
        assertFalse(Ej124IsolationLevels.previeneNonRepeatableRead(Nivel.READ_COMMITTED));
    }

    @Test
    void testRetoExtra03() {
        assertTrue(Ej124IsolationLevels.previenePhantomRead(Nivel.SERIALIZABLE));
        assertFalse(Ej124IsolationLevels.previenePhantomRead(Nivel.REPEATABLE_READ));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej124IsolationLevels.previeneTodo(Nivel.SERIALIZABLE));
        assertFalse(Ej124IsolationLevels.previeneTodo(Nivel.REPEATABLE_READ));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej124IsolationLevels.esMasEstricto(Nivel.SERIALIZABLE, Nivel.REPEATABLE_READ));
        assertFalse(Ej124IsolationLevels.esMasEstricto(Nivel.READ_COMMITTED, Nivel.SERIALIZABLE));
    }

    @Test
    void testRetoExtra06() {
        assertEquals("READ_COMMITTED", Ej124IsolationLevels.obtenerNombreNivel(Nivel.READ_COMMITTED));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("DIRTY_READ", Ej124IsolationLevels.obtenerNombreFenomeno(Fenomeno.DIRTY_READ));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej124IsolationLevels.esElMasBajo(Nivel.READ_UNCOMMITTED));
        assertFalse(Ej124IsolationLevels.esElMasBajo(Nivel.READ_COMMITTED));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej124IsolationLevels.esElMasAlto(Nivel.SERIALIZABLE));
        assertFalse(Ej124IsolationLevels.esElMasAlto(Nivel.REPEATABLE_READ));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("ISOLATION_SERIALIZABLE", Ej124IsolationLevels.obtenerIdNivel(Nivel.SERIALIZABLE));
    }
"""
    },
    "Ej125OptimisticLocking": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el precio de un producto.
     */
    public static double obtenerPrecio(ProdVer125 p) {
        return p == null ? 0.0 : p.getPrecio();
    }

    /**
     * Reto Extra 2: Obtiene la version de un producto.
     */
    public static long obtenerVersion(ProdVer125 p) {
        if (p == null) return -1;
        try {
            var f = p.getClass().getDeclaredField("version");
            f.setAccessible(true);
            return (long) f.get(p);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Reto Extra 3: Comprueba si el precio es mayor que un limite.
     */
    public static boolean precioEsMayor(ProdVer125 p, double limite) {
        return p != null && p.getPrecio() > limite;
    }

    /**
     * Reto Extra 4: Comprueba si la version es inicial (0).
     */
    public static boolean esVersionInicial(ProdVer125 p) {
        return obtenerVersion(p) == 0;
    }

    /**
     * Reto Extra 5: Incrementa el precio en base a un porcentaje.
     */
    public static void incrementarPrecioPorcentaje(ProdVer125 p, double pct) {
        if (p != null && pct > 0) {
            p.setPrecio(p.getPrecio() * (1 + pct / 100.0));
        }
    }

    /**
     * Reto Extra 6: Crea un nuevo producto.
     */
    public static ProdVer125 crearProducto(double precio) {
        return new ProdVer125(precio);
    }

    /**
     * Reto Extra 7: Descuenta un valor del precio.
     */
    public static void aplicarDescuento(ProdVer125 p, double desc) {
        if (p != null && desc > 0 && p.getPrecio() >= desc) {
            p.setPrecio(p.getPrecio() - desc);
        }
    }

    /**
     * Reto Extra 8: Comprueba si el producto tiene ID.
     */
    public static boolean tieneId(ProdVer125 p) {
        return p != null && p.getId() != null;
    }

    /**
     * Reto Extra 9: Valida si el precio es positivo.
     */
    public static boolean precioEsValido(ProdVer125 p) {
        return p != null && p.getPrecio() > 0;
    }

    /**
     * Reto Extra 10: Retorna formato de texto del producto.
     */
    public static String formatearProducto(ProdVer125 p) {
        if (p == null) return "SIN_PRODUCTO";
        return "Prod[Precio=" + p.getPrecio() + ", Ver=" + obtenerVersion(p) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var p = new ProdVer125(100);
        assertEquals(100.0, Ej125OptimisticLocking.obtenerPrecio(p), 0.001);
    }

    @Test
    void testRetoExtra02() {
        var p = new ProdVer125(100);
        assertEquals(0, Ej125OptimisticLocking.obtenerVersion(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new ProdVer125(100);
        assertTrue(Ej125OptimisticLocking.precioEsMayor(p, 50));
        assertFalse(Ej125OptimisticLocking.precioEsMayor(p, 150));
    }

    @Test
    void testRetoExtra04() {
        var p = new ProdVer125(100);
        assertTrue(Ej125OptimisticLocking.esVersionInicial(p));
    }

    @Test
    void testRetoExtra05() {
        var p = new ProdVer125(100);
        Ej125OptimisticLocking.incrementarPrecioPorcentaje(p, 10);
        assertEquals(110.0, p.getPrecio(), 0.001);
    }

    @Test
    void testRetoExtra06() {
        var p = Ej125OptimisticLocking.crearProducto(80);
        assertNotNull(p);
    }

    @Test
    void testRetoExtra07() {
        var p = new ProdVer125(100);
        Ej125OptimisticLocking.aplicarDescuento(p, 20);
        assertEquals(80.0, p.getPrecio(), 0.001);
    }

    @Test
    void testRetoExtra08() {
        var p = new ProdVer125(100);
        assertFalse(Ej125OptimisticLocking.tieneId(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new ProdVer125(100);
        assertTrue(Ej125OptimisticLocking.precioEsValido(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new ProdVer125(100);
        assertEquals("Prod[Precio=100.0, Ver=0]", Ej125OptimisticLocking.formatearProducto(p));
    }
"""
    },
    "Ej126PessimisticLocking": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el stock de un articulo.
     */
    public static int obtenerStock(ArtStock126 a) {
        return a == null ? 0 : a.getStock();
    }

    /**
     * Reto Extra 2: Comprueba si hay stock disponible (mayor que 0).
     */
    public static boolean tieneStockDisponible(ArtStock126 a) {
        return a != null && a.getStock() > 0;
    }

    /**
     * Reto Extra 3: Comprueba si hay suficiente stock para cubrir una cantidad.
     */
    public static boolean stockSuficiente(ArtStock126 a, int cant) {
        return a != null && a.getStock() >= cant;
    }

    /**
     * Reto Extra 4: Crea un nuevo articulo con stock inicial.
     */
    public static ArtStock126 crearArticulo(Long id, int stock) {
        return new ArtStock126(id, stock);
    }

    /**
     * Reto Extra 5: Incrementa el stock de un articulo.
     */
    public static void incrementarStock(ArtStock126 a, int cant) {
        if (a != null && cant > 0) {
            a.setStock(a.getStock() + cant);
        }
    }

    /**
     * Reto Extra 6: Decrementa el stock si hay suficiente.
     */
    public static boolean decrementarStock(ArtStock126 a, int cant) {
        if (a != null && cant > 0 && a.getStock() >= cant) {
            a.setStock(a.getStock() - cant);
            return true;
        }
        return false;
    }

    /**
     * Reto Extra 7: Obtiene el ID del articulo.
     */
    public static Long obtenerId(ArtStock126 a) {
        if (a == null) return null;
        try {
            var f = a.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return (Long) f.get(a);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 8: Comprueba si el stock es cero.
     */
    public static boolean stockEsCero(ArtStock126 a) {
        return a != null && a.getStock() == 0;
    }

    /**
     * Reto Extra 9: Comprueba si el stock es par.
     */
    public static boolean stockEsPar(ArtStock126 a) {
        return a != null && a.getStock() % 2 == 0;
    }

    /**
     * Reto Extra 10: Retorna formato de texto del stock.
     */
    public static String formatearStock(ArtStock126 a) {
        if (a == null) return "SIN_ARTICULO";
        return "Art[Id=" + obtenerId(a) + ", Stock=" + a.getStock() + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var a = new ArtStock126(1L, 10);
        assertEquals(10, Ej126PessimisticLocking.obtenerStock(a));
    }

    @Test
    void testRetoExtra02() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.tieneStockDisponible(a));
    }

    @Test
    void testRetoExtra03() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.stockSuficiente(a, 5));
        assertFalse(Ej126PessimisticLocking.stockSuficiente(a, 15));
    }

    @Test
    void testRetoExtra04() {
        var a = Ej126PessimisticLocking.crearArticulo(2L, 20);
        assertNotNull(a);
    }

    @Test
    void testRetoExtra05() {
        var a = new ArtStock126(1L, 10);
        Ej126PessimisticLocking.incrementarStock(a, 5);
        assertEquals(15, a.getStock());
    }

    @Test
    void testRetoExtra06() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.decrementarStock(a, 3));
        assertEquals(7, a.getStock());
    }

    @Test
    void testRetoExtra07() {
        var a = new ArtStock126(99L, 10);
        assertEquals(99L, Ej126PessimisticLocking.obtenerId(a));
    }

    @Test
    void testRetoExtra08() {
        var a = new ArtStock126(1L, 0);
        assertTrue(Ej126PessimisticLocking.stockEsCero(a));
    }

    @Test
    void testRetoExtra09() {
        var a = new ArtStock126(1L, 10);
        assertTrue(Ej126PessimisticLocking.stockEsPar(a));
    }

    @Test
    void testRetoExtra10() {
        var a = new ArtStock126(1L, 10);
        assertEquals("Art[Id=1, Stock=10]", Ej126PessimisticLocking.formatearStock(a));
    }
"""
    },
    "Ej127SecondLevelCache": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el numero de hits de forma segura.
     */
    public int obtenerHits() {
        return hits();
    }

    /**
     * Reto Extra 2: Obtiene el numero de misses de forma segura.
     */
    public int obtenerMisses() {
        return misses();
    }

    /**
     * Reto Extra 3: Limpia completamente la cache.
     */
    public void limpiarCache() {
        cache.clear();
    }

    /**
     * Reto Extra 4: Comprueba si la cache esta vacia.
     */
    public boolean cacheEstaVacia() {
        return cache.isEmpty();
    }

    /**
     * Reto Extra 5: Comprueba el tamano actual de la cache.
     */
    public int tamanoCache() {
        return cache.size();
    }

    /**
     * Reto Extra 6: Comprueba si una clave existe en la cache fisica.
     */
    public boolean contieneClave(K key) {
        return cache.containsKey(key);
    }

    /**
     * Reto Extra 7: Precalienta la cache con un mapa de entradas.
     */
    public void precalentar(Map<K, V> entradas) {
        if (entradas != null) {
            cache.putAll(entradas);
        }
    }

    /**
     * Reto Extra 8: Obtiene un valor sin contar como hit ni miss.
     */
    public V obtenerSilencioso(K key) {
        return cache.get(key);
    }

    /**
     * Reto Extra 9: Invalida multiples claves a la vez.
     */
    public void invalidarLote(Iterable<K> keys) {
        if (keys != null) {
            for (K key : keys) {
                invalidate(key);
            }
        }
    }

    /**
     * Reto Extra 10: Retorna ratio de hits como porcentaje [0, 100].
     */
    public double hitRatioPorcentaje() {
        return hitRatio() * 100.0;
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0, c.obtenerHits());
    }

    @Test
    void testRetoExtra02() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0, c.obtenerMisses());
    }

    @Test
    void testRetoExtra03() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        c.limpiarCache();
        assertTrue(c.cacheEstaVacia());
    }

    @Test
    void testRetoExtra04() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertTrue(c.cacheEstaVacia());
    }

    @Test
    void testRetoExtra05() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0, c.tamanoCache());
    }

    @Test
    void testRetoExtra06() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        assertTrue(c.contieneClave(1));
    }

    @Test
    void testRetoExtra07() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.precalentar(java.util.Map.of(1, "v1"));
        assertEquals("v1", c.obtenerSilencioso(1));
    }

    @Test
    void testRetoExtra08() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertNull(c.obtenerSilencioso(1));
    }

    @Test
    void testRetoExtra09() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        c.get(1);
        c.get(2);
        c.invalidarLote(java.util.List.of(1, 2));
        assertTrue(c.cacheEstaVacia());
    }

    @Test
    void testRetoExtra10() {
        var c = new Ej127SecondLevelCache<Integer, String>(k -> "v" + k);
        assertEquals(0.0, c.hitRatioPorcentaje(), 0.001);
    }
"""
    },
    "Ej128Auditing": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el texto de un documento de forma segura.
     */
    public static String obtenerTexto(DocAud128 d) {
        return d == null ? "" : d.getTexto();
    }

    /**
     * Reto Extra 2: Comprueba si tiene fecha de creacion asignada.
     */
    public static boolean tieneCreadoEn(DocAud128 d) {
        return d != null && d.getCreadoEn() != null;
    }

    /**
     * Reto Extra 3: Comprueba si tiene fecha de actualizacion asignada.
     */
    public static boolean tieneActualizadoEn(DocAud128 d) {
        return d != null && d.getActualizadoEn() != null;
    }

    /**
     * Reto Extra 4: Crea un nuevo documento auditable.
     */
    public static DocAud128 crearDocumento(String texto) {
        return new DocAud128(texto);
    }

    /**
     * Reto Extra 5: Comprueba si el documento ha sido editado (creadoEn != actualizadoEn).
     */
    public static boolean haSidoEditado(DocAud128 d) {
        if (d == null || d.getCreadoEn() == null || d.getActualizadoEn() == null) return false;
        return !d.getCreadoEn().equals(d.getActualizadoEn());
    }

    /**
     * Reto Extra 6: Actualiza el texto de un documento de forma manual.
     */
    public static void actualizarTexto(DocAud128 d, String nuevoTexto) {
        if (d != null) {
            d.setTexto(nuevoTexto);
        }
    }

    /**
     * Reto Extra 7: Obtiene el ID del documento.
     */
    public static Long obtenerId(DocAud128 d) {
        return d == null ? null : d.getId();
    }

    /**
     * Reto Extra 8: Comprueba si el texto contiene una palabra.
     */
    public static boolean contienePalabra(DocAud128 d, String palabra) {
        return d != null && d.getTexto() != null && palabra != null && d.getTexto().contains(palabra);
    }

    /**
     * Reto Extra 9: Comprueba si el documento es nuevo (ID nulo).
     */
    public static boolean esNuevo(DocAud128 d) {
        return d != null && d.getId() == null;
    }

    /**
     * Reto Extra 10: Retorna formato de auditoria del documento.
     */
    public static String formatearAuditoria(DocAud128 d) {
        if (d == null) return "SIN_DOCUMENTO";
        return "Doc[Id=" + d.getId() + ", Creado=" + d.getCreadoEn() + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var d = new DocAud128("hola");
        assertEquals("hola", Ej128Auditing.obtenerTexto(d));
    }

    @Test
    void testRetoExtra02() {
        var d = new DocAud128("hola");
        assertFalse(Ej128Auditing.tieneCreadoEn(d));
    }

    @Test
    void testRetoExtra03() {
        var d = new DocAud128("hola");
        assertFalse(Ej128Auditing.tieneActualizadoEn(d));
    }

    @Test
    void testRetoExtra04() {
        var d = Ej128Auditing.crearDocumento("nuevo");
        assertNotNull(d);
    }

    @Test
    void testRetoExtra05() {
        var d = new DocAud128("hola");
        assertFalse(Ej128Auditing.haSidoEditado(d));
    }

    @Test
    void testRetoExtra06() {
        var d = new DocAud128("hola");
        Ej128Auditing.actualizarTexto(d, "mundo");
        assertEquals("mundo", d.getTexto());
    }

    @Test
    void testRetoExtra07() {
        var d = new DocAud128("hola");
        assertNull(Ej128Auditing.obtenerId(d));
    }

    @Test
    void testRetoExtra08() {
        var d = new DocAud128("hola mundo");
        assertTrue(Ej128Auditing.contienePalabra(d, "mundo"));
        assertFalse(Ej128Auditing.contienePalabra(d, "casa"));
    }

    @Test
    void testRetoExtra09() {
        var d = new DocAud128("hola");
        assertTrue(Ej128Auditing.esNuevo(d));
    }

    @Test
    void testRetoExtra10() {
        var d = new DocAud128("hola");
        assertEquals("Doc[Id=null, Creado=null]", Ej128Auditing.formatearAuditoria(d));
    }
"""
    },
    "Ej129SoftDelete": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el nombre del cliente de forma segura.
     */
    public static String obtenerNombre(ClienteSD129 c) {
        return c == null ? "" : c.getNombre();
    }

    /**
     * Reto Extra 2: Comprueba si el cliente esta marcado como borrado.
     */
    public static boolean estaBorrado(ClienteSD129 c) {
        return c != null && c.isBorrado();
    }

    /**
     * Reto Extra 3: Crea un nuevo cliente activo.
     */
    public static ClienteSD129 crearCliente(String nombre) {
        return new ClienteSD129(nombre);
    }

    /**
     * Reto Extra 4: Marca un cliente como borrado (soft delete manual).
     */
    public static void borrarClienteManual(ClienteSD129 c) {
        if (c != null) {
            c.setBorrado(true);
        }
    }

    /**
     * Reto Extra 5: Restaura un cliente borrado logicamente.
     */
    public static void restaurarCliente(ClienteSD129 c) {
        if (c != null) {
            c.setBorrado(false);
        }
    }

    /**
     * Reto Extra 6: Obtiene el ID del cliente de forma segura.
     */
    public static Long obtenerId(ClienteSD129 c) {
        return c == null ? null : c.getId();
    }

    /**
     * Reto Extra 7: Comprueba si el cliente es nuevo (ID nulo).
     */
    public static boolean esNuevo(ClienteSD129 c) {
        return c != null && c.getId() == null;
    }

    /**
     * Reto Extra 8: Comprueba si el nombre del cliente contiene una palabra.
     */
    public static boolean nombreContiene(ClienteSD129 c, String palabra) {
        return c != null && c.getNombre() != null && palabra != null && c.getNombre().contains(palabra);
    }

    /**
     * Reto Extra 9: Comprueba si el cliente esta activo (no borrado).
     */
    public static boolean estaActivo(ClienteSD129 c) {
        return c != null && !c.isBorrado();
    }

    /**
     * Reto Extra 10: Retorna formato del cliente.
     */
    public static String formatearCliente(ClienteSD129 c) {
        if (c == null) return "SIN_CLIENTE";
        return "Cliente[Id=" + c.getId() + ", Activo=" + estaActivo(c) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var c = new ClienteSD129("Ana");
        assertEquals("Ana", Ej129SoftDelete.obtenerNombre(c));
    }

    @Test
    void testRetoExtra02() {
        var c = new ClienteSD129("Ana");
        assertFalse(Ej129SoftDelete.estaBorrado(c));
    }

    @Test
    void testRetoExtra03() {
        var c = Ej129SoftDelete.crearCliente("Eva");
        assertNotNull(c);
    }

    @Test
    void testRetoExtra04() {
        var c = new ClienteSD129("Ana");
        Ej129SoftDelete.borrarClienteManual(c);
        assertTrue(c.isBorrado());
    }

    @Test
    void testRetoExtra05() {
        var c = new ClienteSD129("Ana");
        c.setBorrado(true);
        Ej129SoftDelete.restaurarCliente(c);
        assertFalse(c.isBorrado());
    }

    @Test
    void testRetoExtra06() {
        var c = new ClienteSD129("Ana");
        assertNull(Ej129SoftDelete.obtenerId(c));
    }

    @Test
    void testRetoExtra07() {
        var c = new ClienteSD129("Ana");
        assertTrue(Ej129SoftDelete.esNuevo(c));
    }

    @Test
    void testRetoExtra08() {
        var c = new ClienteSD129("Ana Lopez");
        assertTrue(Ej129SoftDelete.nombreContiene(c, "Lopez"));
        assertFalse(Ej129SoftDelete.nombreContiene(c, "Marta"));
    }

    @Test
    void testRetoExtra09() {
        var c = new ClienteSD129("Ana");
        assertTrue(Ej129SoftDelete.estaActivo(c));
    }

    @Test
    void testRetoExtra10() {
        var c = new ClienteSD129("Ana");
        assertEquals("Cliente[Id=null, Activo=true]", Ej129SoftDelete.formatearCliente(c));
    }
"""
    },
    "Ej130InheritanceStrategies": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el importe de un pago de forma segura.
     */
    public static double obtenerImporte(Pago130 p) {
        return p == null ? 0.0 : p.getImporte();
    }

    /**
     * Reto Extra 2: Comprueba si un pago es con tarjeta.
     */
    public static boolean esPagoTarjeta(Pago130 p) {
        return p instanceof PagoTarjeta130;
    }

    /**
     * Reto Extra 3: Comprueba si un pago es por transferencia.
     */
    public static boolean esPagoTransferencia(Pago130 p) {
        return p instanceof PagoTransferencia130;
    }

    /**
     * Reto Extra 4: Crea un pago con tarjeta.
     */
    public static PagoTarjeta130 crearPagoTarjeta(double imp, String pan) {
        return new PagoTarjeta130(imp, pan);
    }

    /**
     * Reto Extra 5: Crea un pago por transferencia.
     */
    public static PagoTransferencia130 crearPagoTransferencia(double imp, String iban) {
        return new PagoTransferencia130(imp, iban);
    }

    /**
     * Reto Extra 6: Comprueba si el importe del pago supera un limite.
     */
    public static boolean importeSupera(Pago130 p, double limite) {
        return p != null && p.getImporte() > limite;
    }

    /**
     * Reto Extra 7: Obtiene el ID del pago.
     */
    public static Long obtenerId(Pago130 p) {
        return p == null ? null : p.getId();
    }

    /**
     * Reto Extra 8: Obtiene el PAN de un pago con tarjeta si aplica.
     */
    public static String obtenerPan(Pago130 p) {
        if (p instanceof PagoTarjeta130) {
            try {
                var f = PagoTarjeta130.class.getDeclaredField("pan");
                f.setAccessible(true);
                return (String) f.get(p);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Reto Extra 9: Obtiene el IBAN de un pago con transferencia si aplica.
     */
    public static String obtenerIban(Pago130 p) {
        if (p instanceof PagoTransferencia130) {
            try {
                var f = PagoTransferencia130.class.getDeclaredField("iban");
                f.setAccessible(true);
                return (String) f.get(p);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Reto Extra 10: Retorna formato del pago.
     */
    public static String formatearPago(Pago130 p) {
        if (p == null) return "SIN_PAGO";
        String tipo = esPagoTarjeta(p) ? "TARJETA" : (esPagoTransferencia(p) ? "TRANSFERENCIA" : "DESCONOCIDO");
        return "Pago[Tipo=" + tipo + ", Importe=" + p.getImporte() + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var p = new PagoTarjeta130(100, "1234");
        assertEquals(100.0, Ej130InheritanceStrategies.obtenerImporte(p), 0.001);
    }

    @Test
    void testRetoExtra02() {
        var p = new PagoTarjeta130(100, "1234");
        assertTrue(Ej130InheritanceStrategies.esPagoTarjeta(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new PagoTransferencia130(100, "ES12");
        assertTrue(Ej130InheritanceStrategies.esPagoTransferencia(p));
    }

    @Test
    void testRetoExtra04() {
        var p = Ej130InheritanceStrategies.crearPagoTarjeta(100, "1234");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra05() {
        var p = Ej130InheritanceStrategies.crearPagoTransferencia(100, "ES12");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra06() {
        var p = new PagoTarjeta130(100, "1234");
        assertTrue(Ej130InheritanceStrategies.importeSupera(p, 50));
    }

    @Test
    void testRetoExtra07() {
        var p = new PagoTarjeta130(100, "1234");
        assertNull(Ej130InheritanceStrategies.obtenerId(p));
    }

    @Test
    void testRetoExtra08() {
        var p = new PagoTarjeta130(100, "1234");
        assertEquals("1234", Ej130InheritanceStrategies.obtenerPan(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new PagoTransferencia130(100, "ES12");
        assertEquals("ES12", Ej130InheritanceStrategies.obtenerIban(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new PagoTarjeta130(100, "1234");
        assertEquals("Pago[Tipo=TARJETA, Importe=100.0]", Ej130InheritanceStrategies.formatearPago(p));
    }
"""
    },
    "Ej131FlushModesBatching": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el nombre del item de forma segura.
     */
    public static String obtenerNombre(Item131 i) {
        if (i == null) return "";
        try {
            var f = i.getClass().getDeclaredField("nombre");
            f.setAccessible(true);
            return (String) f.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 2: Crea un nuevo item.
     */
    public static Item131 crearItem(String nombre) {
        return new Item131(nombre);
    }

    /**
     * Reto Extra 3: Comprueba si el item tiene ID.
     */
    public static boolean tieneId(Item131 i) {
        if (i == null) return false;
        try {
            var f = i.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return f.get(i) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Reto Extra 4: Comprueba si el item es nuevo.
     */
    public static boolean esNuevo(Item131 i) {
        return i != null && !tieneId(i);
    }

    /**
     * Reto Extra 5: Comprueba si el nombre del item contiene una palabra.
     */
    public static boolean nombreContiene(Item131 i, String palabra) {
        return i != null && palabra != null && obtenerNombre(i).contains(palabra);
    }

    /**
     * Reto Extra 6: Valida si el item es valido.
     */
    public static boolean esValido(Item131 i) {
        return i != null && !obtenerNombre(i).trim().isEmpty();
    }

    /**
     * Reto Extra 7: Obtiene el ID del item de forma segura.
     */
    public static Long obtenerId(Item131 i) {
        if (i == null) return null;
        try {
            var f = i.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return (Long) f.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 8: Comprueba si el FlushModeType es COMMIT.
     */
    public static boolean esFlushModeCommit(FlushModeType mode) {
        return mode == FlushModeType.COMMIT;
    }

    /**
     * Reto Extra 9: Comprueba si el FlushModeType es AUTO.
     */
    public static boolean esFlushModeAuto(FlushModeType mode) {
        return mode == FlushModeType.AUTO;
    }

    /**
     * Reto Extra 10: Retorna formato del item.
     */
    public static String formatearItem(Item131 i) {
        if (i == null) return "SIN_ITEM";
        return "Item[Id=" + obtenerId(i) + ", Nombre=" + obtenerNombre(i) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var i = new Item131("Laptop");
        assertEquals("Laptop", Ej131FlushModesBatching.obtenerNombre(i));
    }

    @Test
    void testRetoExtra02() {
        var i = Ej131FlushModesBatching.crearItem("Teclado");
        assertNotNull(i);
    }

    @Test
    void testRetoExtra03() {
        var i = new Item131("Laptop");
        assertFalse(Ej131FlushModesBatching.tieneId(i));
    }

    @Test
    void testRetoExtra04() {
        var i = new Item131("Laptop");
        assertTrue(Ej131FlushModesBatching.esNuevo(i));
    }

    @Test
    void testRetoExtra05() {
        var i = new Item131("Laptop Dell");
        assertTrue(Ej131FlushModesBatching.nombreContiene(i, "Dell"));
        assertFalse(Ej131FlushModesBatching.nombreContiene(i, "HP"));
    }

    @Test
    void testRetoExtra06() {
        var i = new Item131("Laptop");
        assertTrue(Ej131FlushModesBatching.esValido(i));
    }

    @Test
    void testRetoExtra07() {
        var i = new Item131("Laptop");
        assertNull(Ej131FlushModesBatching.obtenerId(i));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej131FlushModesBatching.esFlushModeCommit(FlushModeType.COMMIT));
        assertFalse(Ej131FlushModesBatching.esFlushModeCommit(FlushModeType.AUTO));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej131FlushModesBatching.esFlushModeAuto(FlushModeType.AUTO));
        assertFalse(Ej131FlushModesBatching.esFlushModeAuto(FlushModeType.COMMIT));
    }

    @Test
    void testRetoExtra10() {
        var i = new Item131("Laptop");
        assertEquals("Item[Id=null, Nombre=Laptop]", Ej131FlushModesBatching.formatearItem(i));
    }
"""
    },
    "Ej132FlywayMigrations": {
        "package": "b14_jpaadv",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si el nombre de migración tiene formato valido.
     */
    public static boolean esFormatoValido(String fichero) {
        try {
            versionDe(fichero);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Reto Extra 2: Extrae la descripcion de un fichero de migracion.
     */
    public static String descripcionDe(String fichero) {
        if (fichero == null || !fichero.contains("__") || !fichero.endsWith(".sql")) {
            return "";
        }
        int idx = fichero.indexOf("__") + 2;
        int end = fichero.lastIndexOf(".sql");
        return fichero.substring(idx, end).replace("_", " ");
    }

    /**
     * Reto Extra 3: Comprueba si es una migracion repetible (empieza por 'R').
     */
    public static boolean esMigracionRepetible(String fichero) {
        return fichero != null && fichero.startsWith("R") && fichero.contains("__");
    }

    /**
     * Reto Extra 4: Comprueba si es una migracion versionada.
     */
    public static boolean esMigracionVersionada(String fichero) {
        return fichero != null && fichero.startsWith("V") && fichero.contains("__");
    }

    /**
     * Reto Extra 5: Filtra y devuelve solo las versiones de las migraciones validas.
     */
    public static java.util.List<Integer> obtenerVersiones(java.util.List<String> ficheros) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        if (ficheros != null) {
            for (String f : ficheros) {
                if (esFormatoValido(f)) {
                    res.add(versionDe(f));
                }
            }
        }
        return res;
    }

    /**
     * Reto Extra 6: Comprueba si hay versiones duplicadas en una lista de ficheros.
     */
    public static boolean tieneDuplicados(java.util.List<String> ficheros) {
        var versiones = obtenerVersiones(ficheros);
        var unicas = new java.util.HashSet<>(versiones);
        return versiones.size() != unicas.size();
    }

    /**
     * Reto Extra 7: Retorna el prefijo del fichero de migracion.
     */
    public static String prefijoDe(String fichero) {
        if (fichero == null || fichero.isEmpty()) return "";
        return fichero.substring(0, 1);
    }

    /**
     * Reto Extra 8: Comprueba si el fichero de migracion es de tipo rollback (empieza por 'U').
     */
    public static boolean esRollback(String fichero) {
        return fichero != null && fichero.startsWith("U") && fichero.contains("__");
    }

    /**
     * Reto Extra 9: Compara dos migraciones por su version.
     */
    public static int compararVersiones(String f1, String f2) {
        return Integer.compare(versionDe(f1), versionDe(f2));
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto estructurada.
     */
    public static String formatearMigracion(String fichero) {
        if (!esFormatoValido(fichero)) return "MIGRACION_INVALIDA";
        return "Migracion[V=" + versionDe(fichero) + ", Desc=" + descripcionDe(fichero) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej132FlywayMigrations.esFormatoValido("V1__init.sql"));
        assertFalse(Ej132FlywayMigrations.esFormatoValido("V__init.sql"));
    }

    @Test
    void testRetoExtra02() {
        assertEquals("add email", Ej132FlywayMigrations.descripcionDe("V2__add_email.sql"));
    }

    @Test
    void testRetoExtra03() {
        assertTrue(Ej132FlywayMigrations.esMigracionRepetible("R__view.sql"));
        assertFalse(Ej132FlywayMigrations.esMigracionRepetible("V1__init.sql"));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej132FlywayMigrations.esMigracionVersionada("V1__init.sql"));
        assertFalse(Ej132FlywayMigrations.esMigracionVersionada("R__view.sql"));
    }

    @Test
    void testRetoExtra05() {
        var v = Ej132FlywayMigrations.obtenerVersiones(java.util.List.of("V1__init.sql", "V2__x.sql"));
        assertEquals(java.util.List.of(1, 2), v);
    }

    @Test
    void testRetoExtra06() {
        var f = java.util.List.of("V1__init.sql", "V1__dup.sql");
        assertTrue(Ej132FlywayMigrations.tieneDuplicados(f));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("V", Ej132FlywayMigrations.prefijoDe("V1__init.sql"));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej132FlywayMigrations.esRollback("U1__undo.sql"));
        assertFalse(Ej132FlywayMigrations.esRollback("V1__init.sql"));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej132FlywayMigrations.compararVersiones("V1__a.sql", "V2__b.sql") < 0);
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Migracion[V=1, Desc=init]", Ej132FlywayMigrations.formatearMigracion("V1__init.sql"));
    }
"""
    },

    # ==================== BLOCK 15: QUERY ====================
    "Ej133Pagination": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Calcula el offset de paginacion de forma segura.
     */
    public static int calcularOffset(int pagina, int tamano) {
        return pagina * tamano;
    }

    /**
     * Reto Extra 2: Valida los parametros de paginacion.
     */
    public static boolean esValida(int pagina, int tamano) {
        return pagina >= 0 && tamano > 0;
    }

    /**
     * Reto Extra 3: Comprueba si hay una pagina siguiente.
     */
    public static boolean tieneSiguiente(Pagina<?> p) {
        return p != null && p.pagina() < p.totalPaginas() - 1;
    }

    /**
     * Reto Extra 4: Comprueba si hay una pagina anterior.
     */
    public static boolean tieneAnterior(Pagina<?> p) {
        return p != null && p.pagina() > 0 && p.totalPaginas() > 1;
    }

    /**
     * Reto Extra 5: Comprueba si es la primera pagina.
     */
    public static boolean esPrimera(Pagina<?> p) {
        return p != null && p.pagina() == 0;
    }

    /**
     * Reto Extra 6: Comprueba si es la ultima pagina.
     */
    public static boolean esUltima(Pagina<?> p) {
        return p != null && p.pagina() >= p.totalPaginas() - 1;
    }

    /**
     * Reto Extra 7: Obtiene el indice de la siguiente pagina.
     */
    public static int indiceSiguiente(Pagina<?> p) {
        if (p == null) return 0;
        return tieneSiguiente(p) ? p.pagina() + 1 : p.pagina();
    }

    /**
     * Reto Extra 8: Obtiene el indice de la anterior pagina.
     */
    public static int indiceAnterior(Pagina<?> p) {
        if (p == null) return 0;
        return tieneAnterior(p) ? p.pagina() - 1 : 0;
    }

    /**
     * Reto Extra 9: Comprueba si el contenido de la pagina esta vacio.
     */
    public static boolean estaVacia(Pagina<?> p) {
        return p == null || p.contenido() == null || p.contenido().isEmpty();
    }

    /**
     * Reto Extra 10: Retorna formato descriptivo de la pagina.
     */
    public static String formatearPagina(Pagina<?> p) {
        if (p == null) return "SIN_PAGINA";
        return "Pagina[" + p.pagina() + "/" + p.totalPaginas() + ", Contenido=" + (p.contenido() != null ? p.contenido().size() : 0) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertEquals(20, Ej133Pagination.calcularOffset(2, 10));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej133Pagination.esValida(0, 10));
        assertFalse(Ej133Pagination.esValida(-1, 10));
    }

    @Test
    void testRetoExtra03() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertTrue(Ej133Pagination.tieneSiguiente(p));
    }

    @Test
    void testRetoExtra04() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 1, 10);
        assertTrue(Ej133Pagination.tieneAnterior(p));
    }

    @Test
    void testRetoExtra05() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertTrue(Ej133Pagination.esPrimera(p));
    }

    @Test
    void testRetoExtra06() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 2, 10);
        assertTrue(Ej133Pagination.esUltima(p));
    }

    @Test
    void testRetoExtra07() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertEquals(1, Ej133Pagination.indiceSiguiente(p));
    }

    @Test
    void testRetoExtra08() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 1, 10);
        assertEquals(0, Ej133Pagination.indiceAnterior(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of(), 25, 0, 10);
        assertTrue(Ej133Pagination.estaVacia(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new Ej133Pagination.Pagina<>(java.util.List.of("A"), 25, 0, 10);
        assertEquals("Pagina[0/3, Contenido=1]", Ej133Pagination.formatearPagina(p));
    }
"""
    },
    "Ej134Sorting": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si un campo de ordenacion esta en la whitelist.
     */
    public static boolean esCampoPermitido(String campo) {
        return campo != null && CAMPOS_PERMITIDOS.contains(campo);
    }

    /**
     * Reto Extra 2: Determina la direccion de ordenacion ("asc" o "desc").
     */
    public static String determinarDireccion(boolean ascendente) {
        return ascendente ? "asc" : "desc";
    }

    /**
     * Reto Extra 3: Genera una clausula JPQL ORDER BY de forma segura.
     */
    public static String construirOrderJpql(String campo, boolean ascendente) {
        if (!esCampoPermitido(campo)) {
            throw new IllegalArgumentException("Invalido");
        }
        return "order by p." + campo + " " + determinarDireccion(ascendente);
    }

    /**
     * Reto Extra 4: Valida si la whitelist esta inicializada correctamente.
     */
    public static boolean whitelistValida() {
        return CAMPOS_PERMITIDOS != null && !CAMPOS_PERMITIDOS.isEmpty();
    }

    /**
     * Reto Extra 5: Devuelve una copia de los campos permitidos.
     */
    public static java.util.Set<String> obtenerCamposPermitidos() {
        return java.util.Set.copyOf(CAMPOS_PERMITIDOS);
    }

    /**
     * Reto Extra 6: Comprueba si la direccion indicada es descendente.
     */
    public static boolean esDireccionDescendente(String dir) {
        return dir != null && dir.equalsIgnoreCase("desc");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de un campo (trim y lowercase).
     */
    public static String normalizarCampo(String campo) {
        return campo == null ? "" : campo.trim().toLowerCase();
    }

    /**
     * Reto Extra 8: Comprueba si el campo es de tipo ID.
     */
    public static boolean esCampoId(String campo) {
        return "id".equalsIgnoreCase(normalizarCampo(campo));
    }

    /**
     * Reto Extra 9: Comprueba si la whitelist contiene al menos 3 campos.
     */
    public static boolean tieneSuficientesCampos() {
        return CAMPOS_PERMITIDOS.size() >= 3;
    }

    /**
     * Reto Extra 10: Retorna un string formateado con el criterio de ordenacion.
     */
    public static String formatearOrdenacion(String campo, boolean ascendente) {
        if (!esCampoPermitido(campo)) return "ORDENACION_INVALIDA";
        return campo + ":" + determinarDireccion(ascendente).toUpperCase();
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej134Sorting.esCampoPermitido("nombre"));
        assertFalse(Ej134Sorting.esCampoPermitido("password"));
    }

    @Test
    void testRetoExtra02() {
        assertEquals("asc", Ej134Sorting.determinarDireccion(true));
        assertEquals("desc", Ej134Sorting.determinarDireccion(false));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("order by p.nombre asc", Ej134Sorting.construirOrderJpql("nombre", true));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej134Sorting.whitelistValida());
    }

    @Test
    void testRetoExtra05() {
        var set = Ej134Sorting.obtainCamposPermitidosHelper();
        assertNotNull(set);
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej134Sorting.esDireccionDescendente("desc"));
        assertFalse(Ej134Sorting.esDireccionDescendente("asc"));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("nombre", Ej134Sorting.normalizarCampo("  Nombre  "));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej134Sorting.esCampoId("id"));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej134Sorting.tieneSuficientesCampos());
    }

    @Test
    void testRetoExtra10() {
        assertEquals("nombre:ASC", Ej134Sorting.formatearOrdenacion("nombre", true));
    }
"""
    },
    "Ej135SliceVsPage": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si un Slice tiene pagina siguiente de forma logica.
     */
    public static boolean tieneSiguiente(Slice<?> s) {
        return s != null && s.hasNext();
    }

    /**
     * Reto Extra 2: Comprueba si un Slice tiene pagina anterior.
     */
    public static boolean tieneAnterior(Slice<?> s) {
        return s != null && s.pagina() > 0;
    }

    /**
     * Reto Extra 3: Comprueba si es la primera pagina de un Slice.
     */
    public static boolean esPrimera(Slice<?> s) {
        return s != null && s.pagina() == 0;
    }

    /**
     * Reto Extra 4: Valida los parametros de Slice.
     */
    public static boolean esValida(int pagina, int tamano) {
        return pagina >= 0 && tamano > 0;
    }

    /**
     * Reto Extra 5: Calcula el offset de forma segura.
     */
    public static int calcularOffset(int pagina, int tamano) {
        return pagina * tamano;
    }

    /**
     * Reto Extra 6: Retorna el tamaño del contenido actual.
     */
    public static int tamanoContenido(Slice<?> s) {
        return s == null || s.contenido() == null ? 0 : s.contenido().size();
    }

    /**
     * Reto Extra 7: Comprueba si el Slice esta vacio.
     */
    public static boolean estaVacio(Slice<?> s) {
        return s == null || s.contenido() == null || s.contenido().isEmpty();
    }

    /**
     * Reto Extra 8: Obtiene el numero de pagina actual.
     */
    public static int numeroPagina(Slice<?> s) {
        return s == null ? 0 : s.pagina();
    }

    /**
     * Reto Extra 9: Comprueba si el tamano de pagina es correcto.
     */
    public static boolean tamanoEsCorrecto(Slice<?> s, int esperado) {
        return s != null && s.tamano() == esperado;
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearSlice(Slice<?> s) {
        if (s == null) return "SIN_SLICE";
        return "Slice[Pagina=" + s.pagina() + ", HasNext=" + s.hasNext() + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, true);
        assertTrue(Ej135SliceVsPage.tieneSiguiente(s));
    }

    @Test
    void testRetoExtra02() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 1, 10, false);
        assertTrue(Ej135SliceVsPage.tieneAnterior(s));
    }

    @Test
    void testRetoExtra03() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertTrue(Ej135SliceVsPage.esPrimera(s));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej135SliceVsPage.esValida(0, 10));
    }

    @Test
    void testRetoExtra05() {
        assertEquals(20, Ej135SliceVsPage.calcularOffset(2, 10));
    }

    @Test
    void testRetoExtra06() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertEquals(1, Ej135SliceVsPage.tamanoContenido(s));
    }

    @Test
    void testRetoExtra07() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of(), 0, 10, false);
        assertTrue(Ej135SliceVsPage.estaVacio(s));
    }

    @Test
    void testRetoExtra08() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 3, 10, false);
        assertEquals(3, Ej135SliceVsPage.numeroPagina(s));
    }

    @Test
    void testRetoExtra09() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertTrue(Ej135SliceVsPage.tamanoEsCorrecto(s, 10));
    }

    @Test
    void testRetoExtra10() {
        var s = new Ej135SliceVsPage.Slice<>(java.util.List.of("A"), 0, 10, false);
        assertEquals("Slice[Pagina=0, HasNext=false]", Ej135SliceVsPage.formatearSlice(s));
    }
"""
    },
    "Ej136DynamicFiltering": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        return f == null || f.trim().isEmpty();
    }

    /**
     * Reto Extra 2: Comprueba si un filtro numerico es valido (positivo).
     */
    public static boolean esPrecioValido(Double precio) {
        return precio != null && precio > 0;
    }

    /**
     * Reto Extra 3: Genera una clausula select base de JPQL.
     */
    public static String selectBase() {
        return "select p from Prod136 p";
    }

    /**
     * Reto Extra 4: Comprueba si el filtro de precio es nulo.
     */
    public static boolean esPrecioNulo(Double precio) {
        return precio == null;
    }

    /**
     * Reto Extra 5: Comprueba si el filtro de categoria es nulo o vacio.
     */
    public static boolean esCategoriaVacia(String cat) {
        return esFiltroVacio(cat);
    }

    /**
     * Reto Extra 6: Limpia e normaliza un filtro de texto.
     */
    public static String normalizarFiltro(String f) {
        return esFiltroVacio(f) ? "" : f.trim().toLowerCase();
    }

    /**
     * Reto Extra 7: Comprueba si se debe aplicar algun filtro.
     */
    public static boolean debeFiltrar(String cat, Double maxPrecio) {
        return !esCategoriaVacia(cat) || esPrecioValido(maxPrecio);
    }

    /**
     * Reto Extra 8: Retorna un parametro de tipo LIKE formateado (%valor%).
     */
    public static String formatearLike(String f) {
        return "%" + normalizarFiltro(f) + "%";
    }

    /**
     * Reto Extra 9: Comprueba si dos filtros de categoria son iguales sin importar mayusculas.
     */
    public static boolean sonCategoriasIguales(String c1, String c2) {
        return normalizarFiltro(c1).equals(normalizarFiltro(c2));
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto de los filtros activos.
     */
    public static String formatearFiltrosActivos(String cat, Double maxPrecio) {
        if (!debeFiltrar(cat, maxPrecio)) return "SIN_FILTROS";
        return "Filtros[Cat=" + cat + ", MaxPrecio=" + maxPrecio + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej136DynamicFiltering.esFiltroVacio(" "));
        assertFalse(Ej136DynamicFiltering.esFiltroVacio("Ropa"));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej136DynamicFiltering.esPrecioValido(10.0));
        assertFalse(Ej136DynamicFiltering.esPrecioValido(-5.0));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("select p from Prod136 p", Ej136DynamicFiltering.selectBase());
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej136DynamicFiltering.esPrecioNulo(null));
        assertFalse(Ej136DynamicFiltering.esPrecioNulo(10.0));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej136DynamicFiltering.esCategoriaVacia(" "));
    }

    @Test
    void testRetoExtra06() {
        assertEquals("ropa", Ej136DynamicFiltering.normalizarFiltro("  Ropa  "));
    }

    @Test
    void testRetoExtra07() {
        assertTrue(Ej136DynamicFiltering.debeFiltrar("Ropa", null));
        assertFalse(Ej136DynamicFiltering.debeFiltrar(null, null));
    }

    @Test
    void testRetoExtra08() {
        assertEquals("%ropa%", Ej136DynamicFiltering.formatearLike("Ropa"));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej136DynamicFiltering.sonCategoriasIguales("Ropa", "ROPA"));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Filtros[Cat=Ropa, MaxPrecio=10.0]", Ej136DynamicFiltering.formatearFiltrosActivos("Ropa", 10.0));
    }
"""
    },
    "Ej137Specifications": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        return f == null || f.trim().isEmpty();
    }

    /**
     * Reto Extra 2: Comprueba si el precio minimo es valido.
     */
    public static boolean esPrecioValido(Double precio) {
        return precio != null && precio >= 0;
    }

    /**
     * Reto Extra 3: Genera una expresion LIKE de forma segura.
     */
    public static String formatearLike(String f) {
        return f == null ? "%" : "%" + f.trim().toLowerCase() + "%";
    }

    /**
     * Reto Extra 4: Comprueba si el precio maximo es nulo.
     */
    public static boolean esPrecioNulo(Double p) {
        return p == null;
    }

    /**
     * Reto Extra 5: Comprueba si la categoria esta vacia.
     */
    public static boolean esCategoriaVacia(String c) {
        return esFiltroVacio(c);
    }

    /**
     * Reto Extra 6: Comprueba si se requiere filtrar por nombre.
     */
    public static boolean requiereFiltroNombre(String nombre) {
        return !esFiltroVacio(nombre);
    }

    /**
     * Reto Extra 7: Normaliza el nombre de la categoria.
     */
    public static String normalizarCategoria(String cat) {
        return esFiltroVacio(cat) ? "" : cat.trim().toUpperCase();
    }

    /**
     * Reto Extra 8: Comprueba si hay especificaciones activas.
     */
    public static boolean tieneEspecificaciones(String nombre, Double min, Double max) {
        return !esFiltroVacio(nombre) || esPrecioValido(min) || esPrecioValido(max);
    }

    /**
     * Reto Extra 9: Compara dos filtros de precio de forma logica.
     */
    public static boolean preciosCoherentes(Double min, Double max) {
        if (min == null || max == null) return true;
        return min <= max;
    }

    /**
     * Reto Extra 10: Retorna representacion de especificaciones activas.
     */
    public static String formatearEspecificacion(String nom, Double min, Double max) {
        if (!tieneEspecificaciones(nom, min, max)) return "SIN_ESPECIFICACIONES";
        return "Specs[Nom=" + nom + ", Min=" + min + ", Max=" + max + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej137Specifications.esFiltroVacio(" "));
        assertFalse(Ej137Specifications.esFiltroVacio("Ropa"));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej137Specifications.esPrecioValido(10.0));
        assertFalse(Ej137Specifications.esPrecioValido(-5.0));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("%ropa%", Ej137Specifications.formatearLike("Ropa"));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej137Specifications.esPrecioNulo(null));
        assertFalse(Ej137Specifications.esPrecioNulo(10.0));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej137Specifications.esCategoriaVacia(" "));
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej137Specifications.requiereFiltroNombre("Laptop"));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("ROPA", Ej137Specifications.normalizarCategoria("  Ropa  "));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej137Specifications.tieneEspecificaciones("Laptop", null, null));
        assertFalse(Ej137Specifications.tieneEspecificaciones(null, null, null));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej137Specifications.preciosCoherentes(10.0, 20.0));
        assertFalse(Ej137Specifications.preciosCoherentes(20.0, 10.0));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Specs[Nom=Ropa, Min=10.0, Max=20.0]", Ej137Specifications.formatearEspecificacion("Ropa", 10.0, 20.0));
    }
"""
    },
    "Ej138CriteriaApi": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        return f == null || f.trim().isEmpty();
    }

    /**
     * Reto Extra 2: Comprueba si un valor es positivo.
     */
    public static boolean esPrecioValido(Double precio) {
        return precio != null && precio > 0;
    }

    /**
     * Reto Extra 3: Retorna expresion LIKE normalizada.
     */
    public static String formatearLike(String f) {
        return f == null ? "%" : "%" + f.trim().toLowerCase() + "%";
    }

    /**
     * Reto Extra 4: Comprueba si la categoria esta vacia.
     */
    public static boolean esCategoriaVacia(String cat) {
        return esFiltroVacio(cat);
    }

    /**
     * Reto Extra 5: Comprueba si el ID es valido (no nulo).
     */
    public static boolean esIdValido(Long id) {
        return id != null && id > 0;
    }

    /**
     * Reto Extra 6: Comprueba si la lista de ordenacion es segura.
     */
    public static boolean esOrdenacionValida(String campo) {
        return campo != null && (campo.equals("id") || campo.equals("nombre") || campo.equals("precio"));
    }

    /**
     * Reto Extra 7: Normaliza el nombre de un campo.
     */
    public static String normalizarCampo(String c) {
        return c == null ? "" : c.trim().toLowerCase();
    }

    /**
     * Reto Extra 8: Comprueba si se debe filtrar por precio.
     */
    public static boolean debeFiltrarPrecio(Double precio) {
        return esPrecioValido(precio);
    }

    /**
     * Reto Extra 9: Compara de forma logica dos precios.
     */
    public static boolean rangoPreciosValido(Double min, Double max) {
        if (min == null || max == null) return true;
        return min <= max;
    }

    /**
     * Reto Extra 10: Retorna formato de criteria.
     */
    public static String formatearCriteria(String cat, Double maxPrecio) {
        if (esCategoriaVacia(cat) && !debeFiltrarPrecio(maxPrecio)) return "SIN_CRITERIA";
        return "Criteria[Cat=" + cat + ", MaxPrecio=" + maxPrecio + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        assertTrue(Ej138CriteriaApi.esFiltroVacio(" "));
        assertFalse(Ej138CriteriaApi.esFiltroVacio("Ropa"));
    }

    @Test
    void testRetoExtra02() {
        assertTrue(Ej138CriteriaApi.esPrecioValido(10.0));
        assertFalse(Ej138CriteriaApi.esPrecioValido(-5.0));
    }

    @Test
    void testRetoExtra03() {
        assertEquals("%ropa%", Ej138CriteriaApi.formatearLike("Ropa"));
    }

    @Test
    void testRetoExtra04() {
        assertTrue(Ej138CriteriaApi.esCategoriaVacia(" "));
    }

    @Test
    void testRetoExtra05() {
        assertTrue(Ej138CriteriaApi.esIdValido(1L));
        assertFalse(Ej138CriteriaApi.esIdValido(null));
    }

    @Test
    void testRetoExtra06() {
        assertTrue(Ej138CriteriaApi.esOrdenacionValida("nombre"));
        assertFalse(Ej138CriteriaApi.esOrdenacionValida("password"));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("nombre", Ej138CriteriaApi.normalizarCampo("  Nombre  "));
    }

    @Test
    void testRetoExtra08() {
        assertTrue(Ej138CriteriaApi.debeFiltrarPrecio(10.0));
    }

    @Test
    void testRetoExtra09() {
        assertTrue(Ej138CriteriaApi.rangoPreciosValido(10.0, 20.0));
        assertFalse(Ej138CriteriaApi.rangoPreciosValido(20.0, 10.0));
    }

    @Test
    void testRetoExtra10() {
        assertEquals("Criteria[Cat=Ropa, MaxPrecio=10.0]", Ej138CriteriaApi.formatearCriteria("Ropa", 10.0));
    }
"""
    },
    "Ej139QueryByExample": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el nombre de un producto de forma segura.
     */
    public static String obtenerNombre(Prod139 p) {
        if (p == null) return "";
        try {
            var f = p.getClass().getDeclaredField("nombre");
            f.setAccessible(true);
            return (String) f.get(p);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 2: Obtiene la categoria de forma segura.
     */
    public static String obtenerCategoria(Prod139 p) {
        if (p == null) return "";
        try {
            var f = p.getClass().getDeclaredField("categoria");
            f.setAccessible(true);
            return (String) f.get(p);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 3: Comprueba si el producto tiene campos asignados para Example.
     */
    public static boolean tieneCamposDeEjemplo(Prod139 p) {
        return p != null && (!obtenerNombre(p).isEmpty() || !obtenerCategoria(p).isEmpty());
    }

    /**
     * Reto Extra 4: Crea un nuevo producto de ejemplo.
     */
    public static Prod139 crearEjemplo(String nombre, String categoria) {
        return new Prod139(nombre, categoria, 0.0);
    }

    /**
     * Reto Extra 5: Comprueba si el precio de ejemplo esta asignado (mayor que 0).
     */
    public static boolean tienePrecioAsignado(Prod139 p) {
        if (p == null) return false;
        try {
            var f = p.getClass().getDeclaredField("precio");
            f.setAccessible(true);
            double val = (double) f.get(p);
            return val > 0.0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Reto Extra 6: Obtiene el ID del producto de ejemplo.
     */
    public static Long obtenerId(Prod139 p) {
        if (p == null) return null;
        try {
            var f = p.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return (Long) f.get(p);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 7: Normaliza el texto de los campos.
     */
    public static String normalizarTexto(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    /**
     * Reto Extra 8: Comprueba si el ejemplo es nuevo (ID nulo).
     */
    public static boolean esNuevo(Prod139 p) {
        return p != null && obtenerId(p) == null;
    }

    /**
     * Reto Extra 9: Valida el producto de ejemplo.
     */
    public static boolean esValido(Prod139 p) {
        return p != null && (!obtenerNombre(p).trim().isEmpty() || !obtenerCategoria(p).trim().isEmpty());
    }

    /**
     * Reto Extra 10: Retorna formato del ejemplo.
     */
    public static String formatearEjemplo(Prod139 p) {
        if (p == null) return "SIN_EJEMPLO";
        return "Example[Nombre=" + obtenerNombre(p) + ", Cat=" + obtenerCategoria(p) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertEquals("Laptop", Ej139QueryByExample.obtenerNombre(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertEquals("Tech", Ej139QueryByExample.obtenerCategoria(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertTrue(Ej139QueryByExample.tieneCamposDeEjemplo(p));
    }

    @Test
    void testRetoExtra04() {
        var p = Ej139QueryByExample.crearEjemplo("PC", "Tech");
        assertNotNull(p);
    }

    @Test
    void testRetoExtra05() {
        var p = new Prod139("Laptop", "Tech", 100.0);
        assertTrue(Ej139QueryByExample.tienePrecioAsignado(p));
    }

    @Test
    void testRetoExtra06() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertNull(Ej139QueryByExample.obtenerId(p));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("laptop", Ej139QueryByExample.normalizarTexto("  Laptop  "));
    }

    @Test
    void testRetoExtra08() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertTrue(Ej139QueryByExample.esNuevo(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertTrue(Ej139QueryByExample.esValido(p));
    }

    @Test
    void testRetoExtra10() {
        var p = new Prod139("Laptop", "Tech", 100);
        assertEquals("Example[Nombre=Laptop, Cat=Tech]", Ej139QueryByExample.formatearEjemplo(p));
    }
"""
    },
    "Ej140InterfaceProjections": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el nombre del producto de forma segura.
     */
    public static String obtenerNombre(Prod140 p) {
        if (p == null) return "";
        try {
            var f = p.getClass().getDeclaredField("nombre");
            f.setAccessible(true);
            return (String) f.get(p);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 2: Obtiene la categoria del producto.
     */
    public static String obtenerCategoria(Prod140 p) {
        if (p == null) return "";
        try {
            var f = p.getClass().getDeclaredField("categoria");
            f.setAccessible(true);
            return (String) f.get(p);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 3: Comprueba si el precio es valido.
     */
    public static boolean esPrecioValido(Prod140 p) {
        if (p == null) return false;
        try {
            var f = p.getClass().getDeclaredField("precio");
            f.setAccessible(true);
            double val = (double) f.get(p);
            return val > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Reto Extra 4: Crea un nuevo producto.
     */
    public static Prod140 crearProducto(String nombre, String categoria, double precio) {
        return new Prod140(nombre, categoria, precio);
    }

    /**
     * Reto Extra 5: Comprueba si un producto tiene ID asignado.
     */
    public static boolean tieneId(Prod140 p) {
        return p != null && obtenerId(p) != null;
    }

    /**
     * Reto Extra 6: Obtiene el ID del producto de forma segura.
     */
    public static Long obtenerId(Prod140 p) {
        if (p == null) return null;
        try {
            var f = p.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return (Long) f.get(p);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 7: Normaliza el texto.
     */
    public static String normalizarTexto(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    /**
     * Reto Extra 8: Comprueba si el producto es nuevo.
     */
    public static boolean esNuevo(Prod140 p) {
        return p != null && obtenerId(p) == null;
    }

    /**
     * Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
     */
    public static boolean categoriaContiene(Prod140 p, String keyword) {
        return p != null && keyword != null && obtenerCategoria(p).toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearProducto(Prod140 p) {
        if (p == null) return "SIN_PRODUCTO";
        return "Prod[Nombre=" + obtenerNombre(p) + ", Cat=" + obtenerCategoria(p) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertEquals("Laptop", Ej140InterfaceProjections.obtenerNombre(p));
    }

    @Test
    void testRetoExtra02() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertEquals("Tech", Ej140InterfaceProjections.obtenerCategoria(p));
    }

    @Test
    void testRetoExtra03() {
        var p = new Prod140("Laptop", "Tech", 100.0);
        assertTrue(Ej140InterfaceProjections.esPrecioValido(p));
    }

    @Test
    void testRetoExtra04() {
        var p = Ej140InterfaceProjections.crearProducto("PC", "Tech", 200.0);
        assertNotNull(p);
    }

    @Test
    void testRetoExtra05() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertFalse(Ej140InterfaceProjections.tieneId(p));
    }

    @Test
    void testRetoExtra06() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertNull(Ej140InterfaceProjections.obtenerId(p));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("laptop", Ej140InterfaceProjections.normalizarTexto("  Laptop  "));
    }

    @Test
    void testRetoExtra08() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertTrue(Ej140InterfaceProjections.esNuevo(p));
    }

    @Test
    void testRetoExtra09() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertTrue(Ej140InterfaceProjections.categoriaContiene(p, "ch"));
    }

    @Test
    void testRetoExtra10() {
        var p = new Prod140("Laptop", "Tech", 100);
        assertEquals("Prod[Nombre=Laptop, Cat=Tech]", Ej140InterfaceProjections.formatearProducto(p));
    }
"""
    },
    "Ej141AggregationsGroupBy": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el nombre del item de forma segura.
     */
    public static String obtenerNombre(Item141 i) {
        if (i == null) return "";
        try {
            var f = i.getClass().getDeclaredField("nombre");
            f.setAccessible(true);
            return (String) f.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 2: Obtiene la categoria de forma segura.
     */
    public static String obtenerCategoria(Item141 i) {
        if (i == null) return "";
        try {
            var f = i.getClass().getDeclaredField("categoria");
            f.setAccessible(true);
            return (String) f.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 3: Obtiene el precio de forma segura.
     */
    public static double obtenerPrecio(Item141 i) {
        if (i == null) return 0.0;
        try {
            var f = i.getClass().getDeclaredField("precio");
            f.setAccessible(true);
            return (double) f.get(i);
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * Reto Extra 4: Crea un nuevo item.
     */
    public static Item141 crearItem(String nombre, String categoria, double precio) {
        return new Item141(nombre, categoria, precio);
    }

    /**
     * Reto Extra 5: Comprueba si el item tiene ID.
     */
    public static boolean tieneId(Item141 i) {
        return i != null && obtenerId(i) != null;
    }

    /**
     * Reto Extra 6: Obtiene el ID del item de forma segura.
     */
    public static Long obtenerId(Item141 i) {
        if (i == null) return null;
        try {
            var f = i.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return (Long) f.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 7: Normaliza el texto de los campos.
     */
    public static String normalizarTexto(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    /**
     * Reto Extra 8: Comprueba si el item es nuevo (ID nulo).
     */
    public static boolean esNuevo(Item141 i) {
        return i != null && obtenerId(i) == null;
    }

    /**
     * Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
     */
    public static boolean categoriaContiene(Item141 i, String keyword) {
        return i != null && keyword != null && obtenerCategoria(i).toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Reto Extra 10: Retorna formato del item.
     */
    public static String formatearItem(Item141 i) {
        if (i == null) return "SIN_ITEM";
        return "Item[Nombre=" + obtenerNombre(i) + ", Cat=" + obtenerCategoria(i) + ", Precio=" + obtenerPrecio(i) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var i = new Item141("Laptop", "Tech", 100);
        assertEquals("Laptop", Ej141AggregationsGroupBy.obtenerNombre(i));
    }

    @Test
    void testRetoExtra02() {
        var i = new Item141("Laptop", "Tech", 100);
        assertEquals("Tech", Ej141AggregationsGroupBy.obtenerCategoria(i));
    }

    @Test
    void testRetoExtra03() {
        var i = new Item141("Laptop", "Tech", 100.0);
        assertEquals(100.0, Ej141AggregationsGroupBy.obtenerPrecio(i), 0.001);
    }

    @Test
    void testRetoExtra04() {
        var i = Ej141AggregationsGroupBy.crearItem("PC", "Tech", 200.0);
        assertNotNull(i);
    }

    @Test
    void testRetoExtra05() {
        var i = new Item141("Laptop", "Tech", 100);
        assertFalse(Ej141AggregationsGroupBy.tieneId(i));
    }

    @Test
    void testRetoExtra06() {
        var i = new Item141("Laptop", "Tech", 100);
        assertNull(Ej141AggregationsGroupBy.obtenerId(i));
    }

    @Test
    void testRetoExtra07() {
        assertEquals("laptop", Ej141AggregationsGroupBy.normalizarTexto("  Laptop  "));
    }

    @Test
    void testRetoExtra08() {
        var i = new Item141("Laptop", "Tech", 100);
        assertTrue(Ej141AggregationsGroupBy.esNuevo(i));
    }

    @Test
    void testRetoExtra09() {
        var i = new Item141("Laptop", "Tech", 100);
        assertTrue(Ej141AggregationsGroupBy.categoriaContiene(i, "ch"));
    }

    @Test
    void testRetoExtra10() {
        var i = new Item141("Laptop", "Tech", 100);
        assertEquals("Item[Nombre=Laptop, Cat=Tech, Precio=100.0]", Ej141AggregationsGroupBy.formatearItem(i));
    }
"""
    },
    "Ej142KeysetPagination": {
        "package": "b15_query",
        "methods": """
    /**
     * Reto Extra 1: Obtiene el ID de un item de Keyset de forma segura.
     */
    public static Long obtenerId(ItemKeyset142 i) {
        if (i == null) return null;
        try {
            var f = i.getClass().getDeclaredField("id");
            f.setAccessible(true);
            return (Long) f.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 2: Obtiene la fecha de creacion de forma segura.
     */
    public static java.time.Instant obtenerCreadoEn(ItemKeyset142 i) {
        if (i == null) return null;
        try {
            var f = i.getClass().getDeclaredField("creadoEn");
            f.setAccessible(true);
            return (java.time.Instant) f.get(i);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reto Extra 3: Comprueba si un item es posterior a otro por ID.
     */
    public static boolean esPosteriorId(ItemKeyset142 a, ItemKeyset142 b) {
        if (a == null || b == null) return false;
        var idA = obtenerId(a);
        var idB = obtenerId(b);
        if (idA == null || idB == null) return false;
        return idA > idB;
    }

    /**
     * Reto Extra 4: Crea un nuevo item.
     */
    public static ItemKeyset142 crearItem(String nombre) {
        return new ItemKeyset142(nombre);
    }

    /**
     * Reto Extra 5: Comprueba si el item tiene fecha de creacion.
     */
    public static boolean tieneCreadoEn(ItemKeyset142 i) {
        return i != null && obtenerCreadoEn(i) != null;
    }

    /**
     * Reto Extra 6: Comprueba si el item es nuevo (ID nulo).
     */
    public static boolean esNuevo(ItemKeyset142 i) {
        return i != null && obtenerId(i) == null;
    }

    /**
     * Reto Extra 7: Obtiene el nombre de forma segura.
     */
    public static String obtenerNombre(ItemKeyset142 i) {
        if (i == null) return "";
        try {
            var f = i.getClass().getDeclaredField("nombre");
            f.setAccessible(true);
            return (String) f.get(i);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Reto Extra 8: Comprueba si el nombre del item contiene una palabra clave.
     */
    public static boolean nombreContiene(ItemKeyset142 i, String keyword) {
        return i != null && keyword != null && obtenerNombre(i).toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Reto Extra 9: Compara dos items por ID de forma segura.
     */
    public static int compararPorId(ItemKeyset142 a, ItemKeyset142 b) {
        var idA = obtenerId(a);
        var idB = obtenerId(b);
        if (idA == null && idB == null) return 0;
        if (idA == null) return -1;
        if (idB == null) return 1;
        return Long.compare(idA, idB);
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearItem(ItemKeyset142 i) {
        if (i == null) return "SIN_ITEM";
        return "ItemKeyset[Id=" + obtenerId(i) + ", Nombre=" + obtenerNombre(i) + "]";
    }
""",
        "tests": """
    @Test
    void testRetoExtra01() {
        var i = new ItemKeyset142("Laptop");
        assertNull(Ej142KeysetPagination.obtenerId(i));
    }

    @Test
    void testRetoExtra02() {
        var i = new ItemKeyset142("Laptop");
        assertNull(Ej142KeysetPagination.obtenerCreadoEn(i));
    }

    @Test
    void testRetoExtra03() {
        var a = new ItemKeyset142("A");
        var b = new ItemKeyset142("B");
        assertFalse(Ej142KeysetPagination.esPosteriorId(a, b));
    }

    @Test
    void testRetoExtra04() {
        var i = Ej142KeysetPagination.crearItem("Laptop");
        assertNotNull(i);
    }

    @Test
    void testRetoExtra05() {
        var i = new ItemKeyset142("Laptop");
        assertFalse(Ej142KeysetPagination.tieneCreadoEn(i));
    }

    @Test
    void testRetoExtra06() {
        var i = new ItemKeyset142("Laptop");
        assertTrue(Ej142KeysetPagination.esNuevo(i));
    }

    @Test
    void testRetoExtra07() {
        var i = new ItemKeyset142("Laptop");
        assertEquals("Laptop", Ej142KeysetPagination.obtenerNombre(i));
    }

    @Test
    void testRetoExtra08() {
        var i = new ItemKeyset142("Laptop");
        assertTrue(Ej142KeysetPagination.nombreContiene(i, "lap"));
    }

    @Test
    void testRetoExtra09() {
        var a = new ItemKeyset142("A");
        var b = new ItemKeyset142("B");
        assertEquals(0, Ej142KeysetPagination.compararPorId(a, b));
    }

    @Test
    void testRetoExtra10() {
        var i = new ItemKeyset142("Laptop");
        assertEquals("ItemKeyset[Id=null, Nombre=Laptop]", Ej142KeysetPagination.formatearItem(i));
    }
"""
    }
}

# Add helper methods for some exercises to avoid private-field access compilation issues or customize
# like CAMPOS_PERMITIDOS in Ej134.
# Ej134 needs obtainCamposPermitidosHelper in the test, so we can define it in the production class.
# Let's check how to run the refactoring.
print("Iniciando refactorización...")

for ex, data in exercises.items():
    pkg = data["package"]
    prod_file = os.path.join(base_path, "src", "main", "java", "com", "masterclass", "api", pkg, f"{ex}.java")
    test_file = os.path.join(base_path, "src", "test", "java", "com", "masterclass", "api", pkg, f"{ex}Test.java")

    if not os.path.exists(prod_file):
        print(f"Error: No existe el archivo {prod_file}")
        continue
    if not os.path.exists(test_file):
        print(f"Error: No existe el archivo {test_file}")
        continue

    # Read production file
    with open(prod_file, "r", encoding="utf-8") as f:
        content = f.read()

    # Find the pasoExtra block
    idx01 = content.find("public static void pasoExtra01")
    if idx01 == -1:
        # Check if it was already refactored
        if "Reto Extra 1" in content:
            print(f"[{ex}] Ya refactorizado en producción.")
        else:
            print(f"Error: No se encontró pasoExtra01 en {prod_file}")
        continue

    # Find matching closing brace for pasoExtra10
    idx10 = content.find("public static void pasoExtra10", idx01)
    if idx10 == -1:
        print(f"Error: No se encontró pasoExtra10 en {prod_file}")
        continue

    brace_open = content.find("{", idx10)
    if brace_open == -1:
        print(f"Error: No se encontró la llave de apertura en pasoExtra10")
        continue

    brace_count = 1
    idx = brace_open + 1
    while idx < len(content) and brace_count > 0:
        if content[idx] == '{':
            brace_count += 1
        elif content[idx] == '}':
            brace_count -= 1
        idx += 1

    if brace_count != 0:
        print(f"Error: No se encontró la llave de cierre correspondiente en {prod_file}")
        continue

    end_idx = idx

    # Perform replacement in production file
    methods_replacement = data["methods"]
    # If Ej134, add obtainCamposPermitidosHelper to allow test access to CAMPOS_PERMITIDOS
    if ex == "Ej134Sorting":
        methods_replacement += "\n    public static java.util.Set<String> obtainCamposPermitidosHelper() {\n        return CAMPOS_PERMITIDOS;\n    }\n"

    new_content = content[:idx01] + methods_replacement.strip() + "\n\n" + content[end_idx:]

    with open(prod_file, "w", encoding="utf-8") as f:
        f.write(new_content)
    print(f"[{ex}] Archivo de producción refactorizado con éxito.")

    # Read test file
    with open(test_file, "r", encoding="utf-8") as f:
        test_content = f.read()

    # If already refactored, skip
    if "testRetoExtra01" in test_content:
        print(f"[{ex}] Ya refactorizado en pruebas.")
        continue

    # Find the closing brace of the test class
    # The last closing brace of the file
    last_brace = test_content.rfind("}")
    if last_brace == -1:
        print(f"Error: No se encontró la llave de cierre de la clase de prueba en {test_file}")
        continue

    new_test_content = test_content[:last_brace] + data["tests"] + "\n}\n"

    with open(test_file, "w", encoding="utf-8") as f:
        f.write(new_test_content)
    print(f"[{ex}] Archivo de pruebas refactorizado con éxito.")

print("Refactorización finalizada.")
