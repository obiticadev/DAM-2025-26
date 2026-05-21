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

    /**
     * Reto Extra 1: Validación de robustez de contraseña.
     * Comprueba si la contraseña es segura: debe tener al menos 8 caracteres,
     * contener al menos una mayúscula, una minúscula y un dígito.
     */
    public static boolean pasoExtra01(String password) {
        // TODO extra: valida si la contraseña cumple con los criterios de seguridad mínimos.
        return false;
    }

    /**
     * Reto Extra 2: Enmascaramiento dinámico de emails sensible según el rol.
     * Convierte un UsuarioEntity a UsuarioResponse. Si el rol del usuario no es "ADMIN",
     * se debe enmascarar el email sustituyendo la parte local por asteriscos (ej: "a***@dominio.com").
     * Si el rol es "ADMIN", se devuelve el email completo.
     */
    public static UsuarioResponse pasoExtra02(UsuarioEntity entity, String userRole) {
        // TODO extra: mapea el response enmascarando el email según el rol del usuario.
        return null;
    }

    /**
     * Reto Extra 3: Extractor de nombre de usuario local.
     * Extrae la porción anterior al símbolo '@' de una dirección de email.
     * Si la cadena no contiene '@', devuelve null.
     */
    public static String pasoExtra03(String email) {
        // TODO extra: extrae y devuelve la parte local del correo electrónico.
        return null;
    }

    /**
     * Reto Extra 4: Simulador de hash de contraseña con sal (Salted Hashing).
     * Genera un hash simulado concatenando el prefijo "hash:", la sal y la contraseña (ej: "hash:sal:password").
     * Si la sal o la contraseña son nulas o vacías, lanza IllegalArgumentException.
     */
    public static String pasoExtra04(String password, String salt) {
        // TODO extra: genera y devuelve un hash simulado utilizando la sal proporcionada.
        return null;
    }

    /**
     * Reto Extra 5: Validación defensiva de dominios de email autorizados.
     * Comprueba si el email proporcionado termina en alguno de los dominios de la lista blanca permitida.
     * La comparación debe ser insensible a mayúsculas/minúsculas.
     */
    public static boolean pasoExtra05(String email, java.util.List<String> allowedDomains) {
        // TODO extra: valida si el email pertenece a uno de los dominios permitidos.
        return false;
    }

    /**
     * Reto Extra 6: Representación de perfil público ligero.
     * Genera un formato String descriptivo y simplificado para visualización pública (ej: "Usuario [id: 1, email: a@b.com]").
     * Si la entidad es nula, devuelve una cadena vacía.
     */
    public static String pasoExtra06(UsuarioEntity entity) {
        // TODO extra: construye y devuelve el formato de representación del perfil público.
        return null;
    }

    /**
     * Reto Extra 7: Sanitización de datos de RegistroRequest.
     * Crea un nuevo RegistroRequest limpiando espacios adicionales alrededor del email (trim)
     * y convirtiendo el email a minúsculas para normalizar su almacenamiento.
     */
    public static RegistroRequest pasoExtra07(RegistroRequest req) {
        // TODO extra: sanea el DTO de registro limpiando y normalizando los datos.
        return null;
    }

    /**
     * Reto Extra 8: Comparador lógico de contraseñas.
     * Compara si una contraseña en texto claro coincide con el hash almacenado,
     * simulando el hash de la contraseña clara (anteponiendo "hash:") y comparándolo con el storedHash.
     */
    public static boolean pasoExtra08(String plainPassword, String storedHash) {
        // TODO extra: compara la contraseña en texto claro con el hash simulando el cifrado.
        return false;
    }

    /**
     * Reto Extra 9: Entidad temporal de registro con expiración.
     * Convierte un RegistroRequest en un UsuarioEntity asignándole el ID dado, pero simulando que tiene una
     * marca temporal de expiración inyectando el valor del tiempo de expiración (expiryMinutes) en el hash (ej: "hash:temp:expiry_X").
     */
    public static UsuarioEntity pasoExtra09(RegistroRequest req, long expiryMinutes) {
        // TODO extra: construye una entidad de usuario temporal con los parámetros de expiración.
        return null;
    }

    /**
     * Reto Extra 10: Generador estructurado JSON para UsuarioResponse.
     * Convierte el DTO UsuarioResponse en un formato JSON plano simplificado (ej: "{\"id\":1,\"email\":\"a@b.com\"}").
     * Si el DTO es nulo, devuelve null.
     */
    public static String pasoExtra10(UsuarioResponse response) {
        // TODO extra: genera la representación en formato JSON estructurado a partir del response.
        return null;
    }

}
