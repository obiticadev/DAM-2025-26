-- ============================================================
-- 🔧 SCRIPT DE INICIALIZACIÓN — BOOTCAMP PL/SQL
-- ============================================================
-- Ejecuta este archivo en DBeaver para crear las tablas y los
-- datos semilla del curso. Si las tablas ya existen, se borran
-- primero para garantizar un estado limpio.
--
-- Tablas: sedes, items, ventas
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- 1. LIMPIEZA: Eliminar tablas si ya existen
-- ────────────────────────────────────────────────────────────

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE ventas';
EXCEPTION
  WHEN OTHERS THEN NULL; -- No existe, no pasa nada
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE items';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE sedes';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- 2. CREACIÓN DE TABLAS
-- ────────────────────────────────────────────────────────────

CREATE TABLE sedes (
  depnu     NUMBER(4)    PRIMARY KEY,
  nombre    VARCHAR2(30) NOT NULL,
  localidad VARCHAR2(30)
);

CREATE TABLE items (
  productonu NUMBER(4)     PRIMARY KEY,
  nombre     VARCHAR2(40)  NOT NULL,
  precio     NUMBER(6,2),
  stock      NUMBER(6)
);

CREATE TABLE ventas (
  pedidonu   NUMBER(6)  PRIMARY KEY,
  clientenu  NUMBER(4)  NOT NULL,
  productonu NUMBER(4)  REFERENCES items(productonu),
  unidades   NUMBER(6)
);

-- ────────────────────────────────────────────────────────────
-- 3. DATOS SEMILLA — sedes
-- ────────────────────────────────────────────────────────────

INSERT INTO sedes VALUES (10, 'CONTABILIDAD', 'SEVILLA');
INSERT INTO sedes VALUES (20, 'INVESTIGACION', 'MADRID');
INSERT INTO sedes VALUES (30, 'VENTAS', 'BARCELONA');
INSERT INTO sedes VALUES (40, 'PRODUCCION', 'BILBAO');

-- ────────────────────────────────────────────────────────────
-- 4. DATOS SEMILLA — items
-- ────────────────────────────────────────────────────────────

INSERT INTO items VALUES (10, 'TORNILLO',   1.50,  500);
INSERT INTO items VALUES (20, 'TUERCA',     2.00,  300);
INSERT INTO items VALUES (30, 'MARTILLO',  15.50,  200);
INSERT INTO items VALUES (40, 'DESTORNILLADOR', 8.75, 150);
INSERT INTO items VALUES (50, 'LLAVE INGLESA',  22.00, 80);

-- ────────────────────────────────────────────────────────────
-- 5. DATOS SEMILLA — ventas
-- ────────────────────────────────────────────────────────────

INSERT INTO ventas VALUES (1001, 101, 20,  5);
INSERT INTO ventas VALUES (1002, 103, 30, 10);
INSERT INTO ventas VALUES (1003, 101, 10, 25);
INSERT INTO ventas VALUES (1004, 102, 20, 15);
INSERT INTO ventas VALUES (1005, 104, 40,  3);
INSERT INTO ventas VALUES (1006, 103, 50,  7);
INSERT INTO ventas VALUES (1007, 101, 20,  8);

COMMIT;

-- ────────────────────────────────────────────────────────────
-- 6. VERIFICACIÓN RÁPIDA
-- ────────────────────────────────────────────────────────────

BEGIN
  DBMS_OUTPUT.PUT_LINE('=== VERIFICACIÓN DE DATOS ===');
  DBMS_OUTPUT.PUT_LINE('');

  FOR r IN (SELECT COUNT(*) AS total FROM sedes) LOOP
    DBMS_OUTPUT.PUT_LINE('sedes: ' || r.total || ' filas');
  END LOOP;

  FOR r IN (SELECT COUNT(*) AS total FROM items) LOOP
    DBMS_OUTPUT.PUT_LINE('items:     ' || r.total || ' filas');
  END LOOP;

  FOR r IN (SELECT COUNT(*) AS total FROM ventas) LOOP
    DBMS_OUTPUT.PUT_LINE('ventas:       ' || r.total || ' filas');
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('');
  DBMS_OUTPUT.PUT_LINE('✅ Base de datos inicializada correctamente.');
END;
/
