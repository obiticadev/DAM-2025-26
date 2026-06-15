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
        // GUÍA: teoría 7.1 (validación de lo que ENTRA).
        // 1. Si password es null devuelve false (no lances; el método es un test booleano).
        // 2. Comprueba 4 condiciones a la vez: length() >= 8, y que exista al menos
        //    una mayúscula, una minúscula y un dígito.
        // 3. La forma limpia: chars().anyMatch(Character::isUpperCase) (e isLowerCase,
        //    isDigit). Tres anyMatch combinados con && más la comprobación de longitud.
        // PISTA: return password.length() >= 8
        //          && password.chars().anyMatch(Character::isUpperCase)
        //          && password.chars().anyMatch(Character::isLowerCase)
        //          && password.chars().anyMatch(Character::isDigit);
        // OJO: el test exige true para "Abc12345" (8 chars, cumple las 3) y false
        //      para "123" (corta y sin letras). "Abc12345" tiene exactamente 8: el
        //      límite es >= 8, no > 8.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Enmascaramiento dinámico de emails sensible según el rol.
     * Convierte un UsuarioEntity a UsuarioResponse. Si el rol del usuario no es "ADMIN",
     * se debe enmascarar el email sustituyendo la parte local por asteriscos (ej: "a***@dominio.com").
     * Si el rol es "ADMIN", se devuelve el email completo.
     */
    public static UsuarioResponse pasoExtra02(UsuarioEntity entity, String userRole) {
        // GUÍA: teoría 7.1 (el ResponseDto recorta/transforma lo que sale).
        // 1. Si entity es null -> IllegalArgumentException.
        // 2. Si "ADMIN".equals(userRole) (ojo al orden para tolerar userRole null):
        //    devuelve el email completo -> new UsuarioResponse(entity.id, entity.email).
        // 3. Si NO es ADMIN, enmascara la parte local: primer carácter + "***" + "@"
        //    + dominio. Ej: "admin@test.com" con rol "USER" -> "a***@test.com".
        // 4. Reutiliza pasoExtra03 para extraer la parte anterior al '@' y parte
        //    también el dominio con split("@", 2).
        // PISTA: String local = email.split("@",2)[0]; String dom = email.split("@",2)[1];
        //        String mascara = local.charAt(0) + "***@" + dom;
        // OJO: el test solo comprueba que NO es null para rol "USER"; aun así
        //      implementa la regla completa (es una placeholder, no la espec real).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Extractor de nombre de usuario local.
     * Extrae la porción anterior al símbolo '@' de una dirección de email.
     * Si la cadena no contiene '@', devuelve null.
     */
    public static String pasoExtra03(String email) {
        // GUÍA: una línea con guardia de null/sin '@'.
        // 1. Si email es null o no contiene '@' -> devuelve null.
        // 2. Si lo contiene, devuelve la parte anterior: email.substring(0, indexOf('@'))
        //    o, equivalente, email.split("@", 2)[0].
        // PISTA: if (email == null || !email.contains("@")) return null;
        //        return email.substring(0, email.indexOf('@'));
        // OJO: el test manda "user@domain.com" y espera EXACTAMENTE "user"; y
        //      "nodomain" (sin '@') debe devolver null, NO la cadena entera.
        // CULTURA: este método es reutilizable -> apóyate en él desde pasoExtra02.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Simulador de hash de contraseña con sal (Salted Hashing).
     * Genera un hash simulado concatenando el prefijo "hash:", la sal y la contraseña (ej: "hash:sal:password").
     * Si la sal o la contraseña son nulas o vacías, lanza IllegalArgumentException.
     */
    public static String pasoExtra04(String password, String salt) {
        // GUÍA: teoría 7.1 (toEntity "hashea" lo que entra; aquí con sal).
        // 1. Si password o salt son null o isBlank() -> IllegalArgumentException.
        // 2. Construye el hash concatenando: "hash:" + salt + ":" + password.
        // PISTA: return "hash:" + salt + ":" + password;
        // OJO: el test manda ("mypass","mysalt") y espera EXACTAMENTE
        //      "hash:mysalt:mypass" -> el orden es salt ANTES que password.
        // CULTURA: la "sal" es un valor aleatorio por usuario que impide que dos
        //      contraseñas iguales produzcan el mismo hash. BCrypt lo hace de verdad (b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Validación defensiva de dominios de email autorizados.
     * Comprueba si el email proporcionado termina en alguno de los dominios de la lista blanca permitida.
     * La comparación debe ser insensible a mayúsculas/minúsculas.
     */
    public static boolean pasoExtra05(String email, java.util.List<String> allowedDomains) {
        // GUÍA: comprobación con stream sobre la lista blanca.
        // 1. Si email o allowedDomains son null -> devuelve false.
        // 2. Pasa el email a minúsculas una vez (String emailLower = email.toLowerCase()).
        // 3. Recorre los dominios: ¿alguno encaja con el final del email?
        //    anyMatch + endsWith, comparando ambos en minúsculas.
        // PISTA: return allowedDomains.stream()
        //          .anyMatch(d -> emailLower.endsWith(d.toLowerCase()));
        // OJO: la comparación es INSENSIBLE a mayúsculas (por eso toLowerCase en
        //      ambos lados). El test: "test@company.com" con ["company.com"] -> true;
        //      "test@gmail.com" con ["company.com"] -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Representación de perfil público ligero.
     * Genera un formato String descriptivo y simplificado para visualización pública (ej: "Usuario [id: 1, email: a@b.com]").
     * Si la entidad es nula, devuelve una cadena vacía.
     */
    public static String pasoExtra06(UsuarioEntity entity) {
        // GUÍA: teoría 7.1 (vista pública: solo id y email, NUNCA el hash).
        // 1. Si entity es null -> devuelve "" (cadena vacía, no null).
        // 2. Formatea: "Usuario [id: " + id + ", email: " + email + "]".
        // PISTA: return "Usuario [id: " + entity.id + ", email: " + entity.email + "]";
        // OJO: el test solo comprueba que NO es null (placeholder), pero NO incluyas
        //      passwordHash en el texto: es justo el campo sensible que la API oculta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Sanitización de datos de RegistroRequest.
     * Crea un nuevo RegistroRequest limpiando espacios adicionales alrededor del email (trim)
     * y convirtiendo el email a minúsculas para normalizar su almacenamiento.
     */
    public static RegistroRequest pasoExtra07(RegistroRequest req) {
        // GUÍA: teoría 7.1 (normalizar lo que ENTRA: trim + lowercase del email).
        // 1. Si req es null -> IllegalArgumentException.
        // 2. Crea un RegistroRequest NUEVO (el record es inmutable, no lo mutes):
        //    email = req.email().trim().toLowerCase(); password se deja igual.
        // PISTA: return new RegistroRequest(req.email().trim().toLowerCase(), req.password());
        // OJO: el test manda "  USER@domain.COM  " (con espacios y mayúsculas).
        //      El resultado normalizado debe ser "user@domain.com" sin espacios.
        // CULTURA: normalizar el email al guardarlo evita duplicados "Ana@x.com" vs
        //      "ana@x.com" y los típicos fallos de login por espacios pegados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Comparador lógico de contraseñas.
     * Compara si una contraseña en texto claro coincide con el hash almacenado,
     * simulando el hash de la contraseña clara (anteponiendo "hash:") y comparándolo con el storedHash.
     */
    public static boolean pasoExtra08(String plainPassword, String storedHash) {
        // GUÍA: simula el login -> hashea la clave clara y compárala con la guardada.
        // 1. Si plainPassword o storedHash son null -> devuelve false.
        // 2. Calcula el hash de la clave clara igual que toEntity: "hash:" + plainPassword.
        // 3. Compara con storedHash usando equals.
        // PISTA: return ("hash:" + plainPassword).equals(storedHash);
        // OJO: el test manda ("pass","hash:pass") y espera true. Usa equals, no ==
        //      (compara contenido de String, no referencia).
        // CULTURA: en el login real NUNCA se descifra el hash guardado; se hashea lo
        //      que el usuario teclea y se comparan los dos hashes. Esto es justo eso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Entidad temporal de registro con expiración.
     * Convierte un RegistroRequest en un UsuarioEntity asignándole el ID dado, pero simulando que tiene una
     * marca temporal de expiración inyectando el valor del tiempo de expiración (expiryMinutes) en el hash (ej: "hash:temp:expiry_X").
     */
    public static UsuarioEntity pasoExtra09(RegistroRequest req, long expiryMinutes) {
        // GUÍA: variante de toEntity que inyecta la expiración dentro del hash.
        // 1. Si req es null -> IllegalArgumentException.
        // 2. Construye el hash temporal: "hash:temp:expiry_" + expiryMinutes.
        // 3. Devuelve new UsuarioEntity(null, req.email(), hash) -> id puede ir a null
        //    (es una entidad temporal aún no persistida).
        // PISTA: String hash = "hash:temp:expiry_" + expiryMinutes;
        //        return new UsuarioEntity(null, req.email(), hash);
        // OJO: el test solo comprueba que NO es null (placeholder); el formato del
        //      hash imita el patrón "expiry_30" para 30 minutos.
        // CULTURA: los registros "pendientes de confirmar por email" caducan así en
        //      el mundo real; aquí la marca temporal viaja embebida en el propio hash.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Generador estructurado JSON para UsuarioResponse.
     * Convierte el DTO UsuarioResponse en un formato JSON plano simplificado (ej: "{\"id\":1,\"email\":\"a@b.com\"}").
     * Si el DTO es nulo, devuelve null.
     */
    public static String pasoExtra10(UsuarioResponse response) {
        // GUÍA: serialización JSON "a mano" (en b02 lo hace Jackson por ti).
        // 1. Si response es null -> devuelve null.
        // 2. Construye el JSON plano: las claves entre comillas, el id sin comillas
        //    (número) y el email entre comillas (texto).
        // PISTA: return "{\"id\":" + response.id() + ",\"email\":\"" + response.email() + "\"}";
        //        Resultado para (1,"a@b.com"): {"id":1,"email":"a@b.com"}
        // OJO: cuida el escapado de las comillas dobles (\") y que el número NO lleve
        //      comillas pero el string SÍ. El test solo comprueba que no es null.
        // CULTURA: esto es exactamente lo que genera @ResponseBody + Jackson en Spring;
        //      verlo a mano una vez te quita el "misterio" de cómo un record se vuelve JSON.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
