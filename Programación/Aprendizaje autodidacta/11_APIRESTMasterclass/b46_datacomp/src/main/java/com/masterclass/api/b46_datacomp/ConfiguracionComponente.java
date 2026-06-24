package com.masterclass.api.b46_datacomp;

import java.io.Serializable;
import java.util.Objects;

/**
 * Estado serializable de un componente de acceso a datos (clase de apoyo, fully built).
 *
 * <p>Es {@link Serializable}: su estado se puede convertir a {@code byte[]} y reconstruir. La
 * contraseña está marcada {@code transient}: NO se serializa (un secreto nunca debe viajar en el
 * volcado), así que tras un round-trip vuelve como {@code null} y hay que "reconectar". El
 * {@code serialVersionUID} explícito fija la versión del formato: si lo cambias, los bytes viejos
 * dejan de leerse (eso protege de mezclar versiones incompatibles).
 */
public class ConfiguracionComponente implements Serializable {

    private static final long serialVersionUID = 46L;

    private String url;
    private String usuario;
    private transient String password; // secreto: NO se serializa
    private int timeout;

    public ConfiguracionComponente() {
    }

    public ConfiguracionComponente(String url, String usuario, String password, int timeout) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        this.timeout = timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /** Igualdad por url + usuario + timeout (la contraseña transient NO entra: tras serializar es null). */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfiguracionComponente otra)) {
            return false;
        }
        return timeout == otra.timeout
                && Objects.equals(url, otra.url)
                && Objects.equals(usuario, otra.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, usuario, timeout);
    }

    @Override
    public String toString() {
        return "ConfiguracionComponente[url=" + url + ", usuario=" + usuario + ", timeout=" + timeout + "]";
    }
}
