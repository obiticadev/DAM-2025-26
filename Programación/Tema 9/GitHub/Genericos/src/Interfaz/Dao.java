package Interfaz;

import java.util.List;

public interface Dao<T> {
    void guardar(T t);

    T obtener(int id);

    void eliminar(int id);

    List<T> obtenerTodos();
}
