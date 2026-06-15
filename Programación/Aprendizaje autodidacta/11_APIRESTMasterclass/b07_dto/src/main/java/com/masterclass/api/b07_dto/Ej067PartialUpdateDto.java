package com.masterclass.api.b07_dto;

import java.util.Optional;

/**
 * Ejercicio 067 · DTO de actualización parcial (PATCH).
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.2).
 *
 * <p>Un PatchDto usa Optional para distinguir "no enviado" de "enviado como null".
 */
public final class Ej067PartialUpdateDto {

    public record Usuario(String nombre, String email, boolean activo) {
    }

    /** Campos opcionales: ausente = no tocar. */
    public record PatchDto(Optional<String> nombre, Optional<String> email, Optional<Boolean> activo) {
    }

    private Ej067PartialUpdateDto() {
    }

    /**
     * Aplica el parche: solo los Optional presentes modifican el usuario.
     *
     * @param actual estado actual
     * @param patch  cambios opcionales
     * @return nuevo Usuario con los cambios aplicados
     * @throws IllegalArgumentException si actual o patch son null
     */
    public static Usuario aplicar(Usuario actual, PatchDto patch) {
        // TODO 1: si actual o patch son null -> IllegalArgumentException.
        // TODO 2: parte de los valores actuales como base.
        // TODO 3: si patch.nombre() está presente, usa su valor; si no, conserva el actual.
        // TODO 4: aplica la misma regla para email.
        // TODO 5: aplica la misma regla para activo.
        // TODO 6: usa Optional.orElse(valorActual) para resolver cada campo.
        // TODO 7: NO trates Optional.empty() como "poner a null": significa "no tocar".
        // TODO 8: el record es inmutable: crea uno nuevo (no muta 'actual').
        // TODO 9: construye el nuevo Usuario con los 3 campos resueltos.
        // TODO 10: devuélvelo.
        return null;
    }

    public static void main(String[] args) {
        var u = new Usuario("Ana", "a@b.com", true);
        System.out.println(aplicar(u, new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.empty())));
    }

    /**
     * RETO EXTRA 1: Comprobar si el PatchDto está completamente vacío.
     *
     * @param patch parche DTO
     * @return true si todos los campos son Optional.empty()
     */
    public static boolean esVacio(PatchDto patch) {
        // GUÍA: teoría 7.5 (empty en TODOS los campos = "no toca nada").
        // 1. Si patch es null -> devuelve true (nada que aplicar) o lanza IAE; sé coherente.
        // 2. Devuelve true solo si los tres Optional están vacíos (isEmpty()).
        // PISTA: return patch.nombre().isEmpty() && patch.email().isEmpty()
        //          && patch.activo().isEmpty();
        // OJO: el test pasa un patch con los 3 empty -> debería devolver true. Es
        //      PLACEHOLDER (assertFalse); ajústalo al resolver. Recuerda: isEmpty()
        //      del Optional, NO isBlank() de String.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVacio");
    }

    /**
     * RETO EXTRA 2: Comprobar si el PatchDto es completo (todos los campos presentes).
     *
     * @param patch parche DTO
     * @return true si todos los campos están presentes (of/ofNullable)
     */
    public static boolean esCompleto(PatchDto patch) {
        // GUÍA: el opuesto de esVacio (los 3 presentes = un PUT encubierto).
        // 1. Si patch es null -> devuelve false.
        // 2. Devuelve true solo si los tres Optional están presentes (isPresent()).
        // PISTA: return patch.nombre().isPresent() && patch.email().isPresent()
        //          && patch.activo().isPresent();
        // OJO: el test pasa un patch con los 3 presentes -> debería devolver true. Es
        //      PLACEHOLDER (assertFalse); ajústalo al resolver.
        // CULTURA: si un PATCH llega "completo", el cliente en realidad quería un PUT;
        //      detectarlo sirve para validar o para optimizar el guardado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCompleto");
    }

    /**
     * RETO EXTRA 3: Validar el formato del email en PatchDto si está presente.
     *
     * @param patch parche DTO
     * @return true si no está presente o, si lo está, contiene '@'
     */
    public static boolean validarEmail(PatchDto patch) {
        // GUÍA: validar solo SI el campo viene (ausente = válido por defecto).
        // 1. Si patch es null -> false (o IAE; sé coherente).
        // 2. Si el email NO está presente -> true (no hay nada que validar).
        // 3. Si está presente -> true solo si su valor contiene '@'.
        //    Idiom elegante con map+orElse: map(e -> e.contains("@")).orElse(true).
        // PISTA: return patch.email().map(e -> e.contains("@")).orElse(true);
        // OJO: el test pasa email Optional.of("b@c.com") (válido) -> debería dar true.
        //      Es PLACEHOLDER (assertFalse); ajústalo al resolver. La clave: ausente
        //      devuelve true, NO false (no enviar un campo no es un error de formato).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarEmail");
    }

    /**
     * RETO EXTRA 4: Crear un PatchDto que solo actualice el email.
     *
     * @param email nuevo email
     * @return PatchDto con solo email presente
     */
    public static PatchDto crearSoloEmail(String email) {
        // GUÍA: fabricar un PatchDto con un solo campo "presente".
        // 1. nombre y activo van Optional.empty() (no tocar); email va Optional.of(email).
        // 2. Si quieres tolerar email null, usa Optional.ofNullable(email).
        // PISTA: return new PatchDto(Optional.empty(),
        //            Optional.ofNullable(email), Optional.empty());
        // OJO: el test es PLACEHOLDER (assertNull); ajústalo al resolver. Importa
        //      java.util.Optional (ya importado en la clase). Compara con
        //      desactivarUsuario (reto 6): mismo patrón, distinto campo presente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearSoloEmail");
    }

    /**
     * RETO EXTRA 5: Combinar dos PatchDto (el segundo sobrescribe al primero si tiene valores presentes).
     *
     * @param p1 primer parche (base)
     * @param p2 segundo parche (prioritario)
     * @return parche combinado
     */
    public static PatchDto combinarParches(PatchDto p1, PatchDto p2) {
        // GUÍA: fusión campo a campo, p2 tiene prioridad si está presente.
        // 1. Si p1 o p2 son null -> IAE (o devuelve el otro; decide y documenta).
        // 2. Para cada campo: si p2 está presente úsalo; si no, usa el de p1.
        //    El idiom: p2.nombre().isPresent() ? p2.nombre() : p1.nombre()
        //    o bien p2.nombre().or(() -> p1.nombre())  (Optional.or, Java 9+).
        // PISTA: return new PatchDto(
        //            p2.nombre().or(() -> p1.nombre()),
        //            p2.email().or(() -> p1.email()),
        //            p2.activo().or(() -> p1.activo()));
        // OJO: el test combina p1={nombre:"Bea"} y p2={email:"b@c.com"} -> resultado
        //      esperado {nombre:"Bea", email:"b@c.com"}. Es PLACEHOLDER (assertNull).
        //      No confundas Optional.or (devuelve Optional) con orElse (devuelve el valor).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarParches");
    }

    /**
     * RETO EXTRA 6: Crear un PatchDto para desactivar explícitamente a un usuario.
     *
     * @return PatchDto con activo=false
     */
    public static PatchDto desactivarUsuario() {
        // GUÍA: teoría 7.5 (of(false) significa "ponlo a false", NO empty="no tocar").
        // 1. nombre y email van empty; activo va Optional.of(false).
        // PISTA: return new PatchDto(Optional.empty(), Optional.empty(), Optional.of(false));
        // OJO: el test es PLACEHOLDER (assertNull); ajústalo al resolver. Aquí ves la
        //      diferencia clave del bloque: Optional.of(false) = "desactiva"; mientras
        //      Optional.empty() en activo = "deja el activo como esté". No son lo mismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desactivarUsuario");
    }

    /**
     * RETO EXTRA 7: Contar cuántos campos solicita modificar el PatchDto.
     *
     * @param patch parche DTO
     * @return cantidad de campos presentes (0 a 3)
     */
    public static int contarCamposModificados(PatchDto patch) {
        // GUÍA: contar cuántos Optional están presentes (0 a 3).
        // 1. Si patch es null -> devuelve 0.
        // 2. Suma 1 por cada campo presente. Truco limpio: pon los tres en un Stream
        //    y filtra los presentes.
        // PISTA: return (int) java.util.stream.Stream.of(
        //            patch.nombre(), patch.email(), patch.activo())
        //          .filter(java.util.Optional::isPresent).count();
        //        (o sencillamente tres if con un contador).
        // OJO: el test pasa {nombre, activo} presentes (2) -> esperado 2. Es
        //      PLACEHOLDER (assertEquals(0,...)); ajústalo al resolver.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCamposModificados");
    }

    /**
     * RETO EXTRA 8: Determinar si el nombre está marcado para modificación.
     *
     * @param patch parche DTO
     * @return true si el nombre está presente
     */
    public static boolean esNombreModificado(PatchDto patch) {
        // GUÍA: una línea (¿el nombre viene en el parche?).
        // 1. Si patch es null -> false.
        // 2. Devuelve patch.nombre().isPresent().
        // PISTA: return patch != null && patch.nombre().isPresent();
        // OJO: el test pasa nombre Optional.of("Bea") -> debería devolver true. Es
        //      PLACEHOLDER (assertFalse); ajústalo al resolver.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreModificado");
    }

    /**
     * RETO EXTRA 9: Crear un PatchDto a partir de valores que pueden ser nulos.
     *
     * @param nombre nombre nullable
     * @param email email nullable
     * @param activo activo nullable
     * @return PatchDto correspondiente (nulo mapea a empty)
     */
    public static PatchDto crearDesdeNullables(String nombre, String email, Boolean activo) {
        // GUÍA: teoría 7.5 (ofNullable convierte null -> empty automáticamente).
        // 1. Cada parámetro nullable se envuelve con Optional.ofNullable: null -> empty,
        //    valor -> present.
        // PISTA: return new PatchDto(Optional.ofNullable(nombre),
        //            Optional.ofNullable(email), Optional.ofNullable(activo));
        // OJO: el test pasa ("Bea", null, null) -> nombre presente, email y activo
        //      empty. Es PLACEHOLDER (assertNull); ajústalo al resolver.
        // CULTURA: este es el puente entre el mundo "nullable" (lo que deserializa
        //      Jackson de un JSON donde faltan claves) y el mundo Optional del dominio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDesdeNullables");
    }

    /**
     * RETO EXTRA 10: Aplicar el reverso de un parche (revertir cambios para auditoría).
     *
     * @param modificado usuario ya modificado
     * @param patch parche que fue aplicado
     * @return Usuario con los campos revertidos a su estado original (vacíos en el parche)
     */
    public static Usuario aplicarInverso(Usuario modificado, PatchDto patch) {
        // GUÍA: el reto más conceptual -> "deshacer" un parche aplicado (auditoría).
        // 1. Si modificado o patch son null -> IAE.
        // 2. La idea: para cada campo que el parche TOCÓ (Optional presente), no
        //    podemos saber su valor previo, así que lo dejamos a null como marca de
        //    "campo revertido / desconocido"; los campos NO tocados se conservan.
        //    Es la inversa lógica de aplicar(): donde aplicar usa orElse(actual),
        //    aquí usas "si estaba presente en el parche -> null, si no -> conserva".
        // PISTA: String nombre = patch.nombre().isPresent() ? null : modificado.nombre();
        //        (igual para email; activo es boolean -> decide un valor por defecto, p.ej. false).
        // OJO: el test es PLACEHOLDER (assertNull); diseña tú la semántica exacta y
        //      ajusta la aserción. Lo importante es entender que un parche, por sí
        //      solo, NO contiene la info para revertirse: necesitarías el estado previo.
        // CULTURA: por esto los sistemas de auditoría guardan el "antes" Y el "después"
        //      de cada cambio, no solo el parche.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarInverso");
    }

}
