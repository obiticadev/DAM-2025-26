# 📘 Bloque 9 — Funciones

[← Volver al Syllabus](../SYLLABUS.md)

---

## ¿Qué es una función?

Como un procedimiento, pero **siempre devuelve un valor** con `RETURN`. Esto permite usarlas en expresiones y asignaciones.

```sql
FUNCTION nombre(parametros) RETURN tipo_retorno IS
  -- variables locales
BEGIN
  -- lógica
  RETURN valor;
END nombre;
```

## Función vs Procedimiento

| | Procedimiento | Función |
|--|---------------|---------|
| Devuelve valor | No (solo por OUT) | Sí, con RETURN |
| Uso en SQL | No | Sí (si almacenada) |
| Se llama con | `proc(args)` | `var := func(args)` |

> **Regla:** valor escalar → función. Varios valores o solo acciones → procedimiento.

## Función local (en DECLARE)

```sql
DECLARE
  FUNCTION f1 RETURN NUMBER IS
    total NUMBER;
  BEGIN
    SELECT COUNT(*) INTO total FROM sedes;
    RETURN total;
  END f1;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Total: ' || f1);
END;
```

## Función almacenada (CREATE OR REPLACE)

```sql
CREATE OR REPLACE FUNCTION f2(num_producto NUMBER) RETURN NUMBER IS
  contador NUMBER := 0;
  CURSOR c IS SELECT pedidonu FROM ventas
              WHERE productonu = num_producto;
BEGIN
  FOR reg IN c LOOP
    contador := contador + 1;
  END LOOP;
  RETURN contador;
END f2;
```

## Llamada

```sql
-- En asignación
resultado := f2(20);

-- Directamente en PUT_LINE
DBMS_OUTPUT.PUT_LINE('Pedidos: ' || f2(20));

-- En SQL (solo almacenadas)
SELECT f2(20) FROM DUAL;
```

[← Volver al Syllabus](../SYLLABUS.md)
