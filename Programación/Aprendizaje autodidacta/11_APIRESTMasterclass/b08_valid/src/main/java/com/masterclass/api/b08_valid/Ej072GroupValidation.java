package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ejercicio 072 · Grupos de validación (Create vs Update).
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.3).
 *
 * <p>Al crear, el id debe ser null; al actualizar, obligatorio. Mismo DTO,
 * distintas reglas según el grupo.
 */
public final class Ej072GroupValidation {

    /** Marcadores de grupo (interfaces vacías). */
    public interface OnCreate {
    }

    public interface OnUpdate {
    }

    public static class RecursoDto {
        // TODO 1: 'id' con @Null(groups = OnCreate.class) — al crear NO debe venir id.
        // TODO 2: 'id' con @NotNull(groups = OnUpdate.class) — al actualizar es obligatorio.
        public Long id;

        // TODO 3: 'nombre' con @NotBlank aplicando a AMBOS grupos
        //         (groups = {OnCreate.class, OnUpdate.class}).
        public String nombre;

        public RecursoDto(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }
    }

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private Ej072GroupValidation() {
    }

    /**
     * Valida bajo el grupo de creación.
     *
     * @param dto recurso
     * @return campos inválidos según OnCreate
     */
    public static Set<String> validarCreate(RecursoDto dto) {
        // TODO 4: invoca VALIDATOR.validate(dto, OnCreate.class).
        // TODO 5: mapea a nombres de propiedad y recoge a Set.
        return VALIDATOR.validate(dto, OnCreate.class).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    /**
     * Valida bajo el grupo de actualización.
     *
     * @param dto recurso
     * @return campos inválidos según OnUpdate
     */
    public static Set<String> validarUpdate(RecursoDto dto) {
        // TODO 6: invoca VALIDATOR.validate(dto, OnUpdate.class).
        // TODO 7: el método ya está hecho; lo que decide el resultado son los 'groups'.
        // TODO 8: con id=null en Create -> válido; en Update -> inválido.
        // TODO 9: con id!=null en Create -> inválido; en Update -> válido.
        // TODO 10: 'nombre' vacío es inválido en ambos grupos.
        return VALIDATOR.validate(dto, OnUpdate.class).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(validarCreate(new RecursoDto(5L, "x")));
    }

    /**
     * RETO EXTRA 1: Comprobar si el recurso es totalmente válido bajo el grupo OnCreate.
     *
     * @param dto recurso DTO
     * @return true si no incumple ninguna restricción de creación
     */
    public static boolean esValidoParaCrear(RecursoDto dto) {
        // GUÍA: reutiliza validarCreate (teoría 8.4): válido = Set vacío bajo OnCreate.
        // PISTA: return validarCreate(dto).isEmpty();
        // OJO: el test manda (id=null, "x"), válido para crear, y espera assertFalse →
        //      placeholder; la espec real da true (al crear, id null es lo correcto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValidoParaCrear");
    }

    /**
     * RETO EXTRA 2: Comprobar si el recurso es totalmente válido bajo el grupo OnUpdate.
     *
     * @param dto recurso DTO
     * @return true si no incumple ninguna restricción de actualización
     */
    public static boolean esValidoParaActualizar(RecursoDto dto) {
        // GUÍA: reutiliza validarUpdate (teoría 8.4): válido = Set vacío bajo OnUpdate.
        // PISTA: return validarUpdate(dto).isEmpty();
        // OJO: el test manda (id=1, "x"), válido para actualizar, y espera assertFalse →
        //      placeholder; la espec real da true (al actualizar, id presente es lo correcto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValidoParaActualizar");
    }

    /**
     * RETO EXTRA 3: Validar que un nombre no sea nulo ni vacío de forma genérica.
     *
     * @param nombre nombre a validar
     * @return true si es correcto
     */
    public static boolean esNombreCorrecto(String nombre) {
        // GUÍA: una línea — equivale a @NotBlank (teoría 8.1).
        // PISTA: return nombre != null && !nombre.isBlank();
        // OJO: el test manda "x" (válido) y espera assertFalse → placeholder.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreCorrecto");
    }

    /**
     * RETO EXTRA 4: Validar explícitamente la presencia de ID cuando se realiza una actualización.
     *
     * @param dto recurso DTO
     * @return true si tiene un ID no nulo
     */
    public static boolean tieneIdSiendoUpdate(RecursoDto dto) {
        // GUÍA: una línea — al actualizar el id es obligatorio (regla OnUpdate, 8.4).
        // PISTA: return dto != null && dto.id != null;
        // OJO: el test manda id=1 y espera assertFalse → placeholder; la espec real
        //      da true (sí tiene id).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneIdSiendoUpdate");
    }

    /**
     * RETO EXTRA 5: Validar explícitamente la ausencia de ID en un contexto de creación.
     *
     * @param dto recurso DTO
     * @return true si el ID es nulo
     */
    public static boolean careceDeIdSiendoCreate(RecursoDto dto) {
        // GUÍA: el reflejo de OnCreate (8.4): al crear el id DEBE ser null (@Null).
        // PISTA: return dto != null && dto.id == null;
        // OJO: el test manda id=null y espera assertFalse → placeholder; la espec real
        //      da true (al crear, ausencia de id es lo correcto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para careceDeIdSiendoCreate");
    }

    /**
     * RETO EXTRA 6: Validar el DTO usando el grupo por defecto (Default de Jakarta).
     *
     * @param dto recurso DTO
     * @return conjunto de propiedades inválidas bajo el grupo por defecto
     */
    public static Set<String> obtenerCamposInvalidosGlobal(RecursoDto dto) {
        // GUÍA: validar SIN grupo usa el grupo Default (teoría 8.4).
        // 1. VALIDATOR.validate(dto) (sin segundo argumento) → grupo Default.
        // 2. Mapea las rutas a Set.
        // PISTA: return VALIDATOR.validate(dto).stream()
        //            .map(v -> v.getPropertyPath().toString()).collect(Collectors.toSet());
        // OJO: el test usa assertNull → placeholder; la espec pide un Set. CLAVE: en
        //      este DTO TODAS las constraints declaran groups (id con OnCreate/OnUpdate,
        //      nombre con ambos), así que NINGUNA pertenece al grupo Default. Por eso
        //      validate(dto) sin grupo siempre da un Set vacío aquí: lo importante es
        //      entender POR QUÉ (una constraint con groups sale de Default).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCamposInvalidosGlobal");
    }

    /**
     * RETO EXTRA 7: Preparar un DTO para ser guardado forzando su ID a null.
     *
     * @param dto DTO de entrada
     * @return copia de DTO con ID nulo
     */
    public static RecursoDto prepararParaCrear(RecursoDto dto) {
        // GUÍA: "wither" inmutable (teoría 1.1) — copia el DTO con id forzado a null.
        // 1. Si dto es null → null.
        // 2. Devuelve new RecursoDto(null, dto.nombre).
        // PISTA: return new RecursoDto(null, dto.nombre);
        // OJO: el test manda id=5 y espera assertNull → placeholder; la espec real
        //      devuelve un dto con id null listo para crear (no null entero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prepararParaCrear");
    }

    /**
     * RETO EXTRA 8: Asegurar que el DTO tenga un ID establecido para actualizarse, usando un valor por defecto si es nulo.
     *
     * @param dto DTO de entrada
     * @param idDefecto ID a asignar si es nulo
     * @return copia de DTO con ID asignado
     */
    public static RecursoDto prepararParaActualizar(RecursoDto dto, Long idDefecto) {
        // GUÍA: "wither" con default (teoría 1.1). Si el id es null, usa idDefecto.
        // 1. id final = dto.id != null ? dto.id : idDefecto.
        // 2. Devuelve un nuevo RecursoDto con ese id y el mismo nombre.
        // PISTA: Long id = dto.id != null ? dto.id : idDefecto;
        //        return new RecursoDto(id, dto.nombre);
        // OJO: el test (id=null, idDefecto=9) espera assertNull → placeholder; la
        //      espec real devuelve un dto con id=9.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prepararParaActualizar");
    }

    /**
     * RETO EXTRA 9: Comparar si dos clases de grupo de validación representan el mismo grupo.
     *
     * @param g1 primer grupo
     * @param g2 segundo grupo
     * @return true si coinciden
     */
    public static boolean esIgualGrupo(Class<?> g1, Class<?> g2) {
        // GUÍA: un grupo es una Class; comparar grupos = comparar Class por identidad.
        // 1. Usa equals (para Class equivale a ==): g1.equals(g2). Protege nulls.
        // PISTA: return g1 != null && g1.equals(g2);
        // OJO: el test pasa OnCreate vs OnUpdate (distintos) y espera assertFalse →
        //      ahí coincide con la espec; pero g vs el mismo g debe dar true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIgualGrupo");
    }

    /**
     * RETO EXTRA 10: Validar el recurso aplicando un conjunto variable de grupos de validación.
     *
     * @param dto recurso DTO
     * @param grupos varargs de clases de grupos
     * @return conjunto de propiedades inválidas
     */
    public static Set<String> validarConGruposMultiples(RecursoDto dto, Class<?>... grupos) {
        // GUÍA: validate acepta varargs de grupos (teoría 8.4). Reenvía el array tal cual.
        // 1. VALIDATOR.validate(dto, grupos) — el varargs Class<?>... encaja directo.
        // 2. Mapea las rutas a Set.
        // PISTA: return VALIDATOR.validate(dto, grupos).stream()
        //            .map(v -> v.getPropertyPath().toString()).collect(Collectors.toSet());
        // OJO: el test usa assertNull → placeholder; la espec pide el Set de rutas
        //      inválidas según los grupos pasados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarConGruposMultiples");
    }

}
