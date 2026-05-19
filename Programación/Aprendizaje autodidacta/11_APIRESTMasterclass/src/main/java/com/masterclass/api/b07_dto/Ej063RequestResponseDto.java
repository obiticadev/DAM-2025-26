package com.masterclass.api.b07_dto;

/**
 * Ejercicio 063 · Separar entidad de DTO.
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.1).
 *
 * <p>La entidad tiene password; el ResponseDto JAMÁS debe exponerla.
 */
public final class Ej063RequestResponseDto {

    /** Entidad interna (tiene datos sensibles). */
    public static final class UsuarioEntity {
        public Long id;
        public String email;
        public String passwordHash;

        public UsuarioEntity(Long id, String email, String passwordHash) {
            this.id = id;
            this.email = email;
            this.passwordHash = passwordHash;
        }
    }

    /** Lo que el cliente envía al registrarse. */
    public record RegistroRequest(String email, String password) {
    }

    /** Lo que la API devuelve (SIN password). */
    public record UsuarioResponse(Long id, String email) {
    }

    private Ej063RequestResponseDto() {
    }

    /**
     * Convierte el request en entidad, "hasheando" la contraseña.
     *
     * @param req datos de registro
     * @param id  id a asignar a la nueva entidad
     * @return entidad lista para persistir
     * @throws IllegalArgumentException si email o password son inválidos
     */
    public static UsuarioEntity toEntity(RegistroRequest req, Long id) {
        // TODO 1: si req es null -> IllegalArgumentException.
        // TODO 2: valida email no null y que contenga '@'.
        // TODO 3: valida password no null y longitud >= 6.
        // TODO 4: simula el hash: "hash:" + password (en real sería BCrypt).
        // TODO 5: construye y devuelve UsuarioEntity(id, email, hash).
        return null;
    }

    /**
     * Convierte la entidad en response público.
     *
     * @param e entidad
     * @return DTO sin datos sensibles
     */
    public static UsuarioResponse toResponse(UsuarioEntity e) {
        // TODO 6: si e es null -> IllegalArgumentException.
        // TODO 7: copia SOLO id y email.
        // TODO 8: NO incluyas passwordHash bajo ningún concepto.
        // TODO 9: el ResponseDto es el contrato público: estable y mínimo.
        // TODO 10: devuelve new UsuarioResponse(e.id, e.email).
        return null;
    }

    public static void main(String[] args) {
        var e = toEntity(new RegistroRequest("a@b.com", "secreto"), 1L);
        System.out.println(toResponse(e));
    }
}
