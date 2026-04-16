package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interfaz.Dao;

public class DaoMemoria<T> implements Dao<T> {
    private Map<Integer, T> datos = new HashMap<>();
    private int contador = 1;

    @Override
    public void guardar(T t) {
        datos.put(contador++, t);
    }

    @Override
    public T obtener(int id) {
        return datos.get(id);
    }

    @Override
    public void eliminar(int id) {
        datos.remove(id);
    }

    @Override
    public List<T> obtenerTodos() {
        return new ArrayList<>(datos.values());
    }
}
