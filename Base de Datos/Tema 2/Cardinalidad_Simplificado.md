¡Perfecto! Revisado y corregido. Aquí tienes la guía definitiva, detallando explícitamente qué hacer con los atributos de las entidades y de las relaciones en cada uno de los casos.

Esta versión es una "chuleta" de alta precisión para que no tengas ninguna duda.

***

# Guía Definitiva: Transformación de E/R a Relacional (con Atributos)

### Leyenda
*   `PK`: Clave Primaria (Primary Key)
*   `FK`: Clave Foránea (Foreign Key)
*   `Atributos_Entidad`: Atributos propios de una entidad.
*   `Atributos_Relación`: Atributos que pertenecen a la línea de relación.

---

## 1. Relaciones Binarias

*   #### **1.1. Relación 1:1 (Mandatorio 1,1 a 1,1)**
    *   **Regla:** Se mantienen ambas tablas y se cruzan las claves y los atributos de la relación.
    *   **Acción:**
        1.  Cada entidad se convierte en una tabla y **mantiene sus `Atributos_Entidad`**.
        2.  La tabla `ENTIDAD_1` recibe la `PK` de `ENTIDAD_2` como `FK`.
        3.  La tabla `ENTIDAD_2` recibe la `PK` de `ENTIDAD_1` como `FK`.
        4.  Los `Atributos_Relación` se añaden como columnas a **ambas tablas**.

*   #### **1.2. Relación 1:1 (Opcional 0,1 a 0,1)**
    *   **Regla:** Crear una **nueva tabla** para la relación.
    *   **Acción:**
        1.  Las entidades originales se convierten en tablas y **mantienen sus `Atributos_Entidad`**.
        2.  Se crea una nueva tabla `RELACION` que contendrá:
            *   La `PK` de `ENTIDAD_1` (que es `FK`).
            *   La `PK` de `ENTIDAD_2` (que es `FK`).
            *   Todos los `Atributos_Relación`.
        3.  La `PK` de esta nueva tabla es la **combinación de ambas `FK`**.

*   #### **1.3. Relación 1:1 (Opcional en un lado 0,1 a 1,1)**
    *   **Regla:** La `PK` y los `Atributos_Relación` viajan al lado opcional (`0,1`).
    *   **Acción:**
        1.  Ambas entidades se convierten en tablas y **mantienen sus `Atributos_Entidad`**.
        2.  La tabla del lado opcional (`0,1`) recibe:
            *   La `PK` de la tabla obligatoria (`1,1`) como `FK`.
            *   Todos los `Atributos_Relación`.

### 1.4. Relación 1:N (Uno a Muchos)
*   **Regla:** La `PK` y los `Atributos_Relación` viajan al lado `N`.
*   **Acción:**
    1.  Ambas entidades se convierten en tablas y **mantienen sus `Atributos_Entidad`**.
    2.  La tabla del lado `N` recibe:
        *   La `PK` de la tabla del lado `1` como `FK`.
        *   Todos los `Atributos_Relación`.

### 1.5. Relación N:M (Muchos a Muchos)
*   **Regla:** **Siempre** se crea una nueva tabla.
*   **Acción:**
    1.  Las entidades originales se convierten en tablas y **mantienen sus `Atributos_Entidad`**. No cambian.
    2.  Se crea una nueva tabla `RELACION` que contendrá:
        *   La `PK` de `ENTIDAD_1` (como `FK`).
        *   La `PK` de `ENTIDAD_2` (como `FK`).
        *   Todos los `Atributos_Relación`.
    3.  La `PK` de esta nueva tabla es la **combinación de ambas `FK`**.

---

## 2. Relaciones Reflexivas

*   #### **2.1. Reflexiva 1:1 y 1:N**
    *   **Regla:** Se añaden una `FK` y los `Atributos_Relación` a la propia tabla.
    *   **Acción:** La tabla de la entidad **mantiene sus `Atributos_Entidad`** y además se le añaden:
        1.  Una nueva columna que actúa como `FK` y apunta a su propia `PK`.
        2.  Nuevas columnas para cada uno de los `Atributos_Relación`.

*   #### **2.2. Reflexiva N:M**
    *   **Regla:** Crear una **nueva tabla** para la relación.
    *   **Acción:**
        1.  La entidad original se convierte en una tabla con sus `Atributos_Entidad` y no cambia.
        2.  Se crea una nueva tabla `RELACION` que contiene:
            *   Dos columnas `FK`, ambas apuntando a la `PK` de la tabla original (una por cada rol).
            *   Todos los `Atributos_Relación`.
        3.  La `PK` de la nueva tabla es la **combinación de esas dos `FK`**.

---

## 3. Jerarquías de Especialización

*   #### **Opción 1: Una Tabla para Todo**
    *   **Acción:** Se crea **una única tabla** que contiene:
        *   Todos los `Atributos` del supertipo.
        *   Todos los `Atributos` de **todos** los subtipos.

*   #### **Opción 2: Tablas solo para Subtipos**
    *   **Acción:** El supertipo desaparece. Se crea una tabla por cada subtipo, y cada una contiene:
        *   Los `Atributos` **específicos** de ese subtipo.
        *   **Una copia de todos** los `Atributos` del supertipo.

*   #### **Opción 3: Tabla por Entidad (Recomendado)**
    *   **Acción:**
        1.  Se crea una tabla para el `SUPERTIPO` que **mantiene sus atributos comunes**.
        2.  Se crea una tabla para cada `SUBTIPO` que contiene:
            *   **Únicamente sus `Atributos` específicos**.
            *   La `PK` del supertipo, que actúa a la vez como `PK` y `FK`.

---

## 4. Relaciones N-arias (Ternarias, etc.)

*   #### **Caso General (N:M:P)**
    *   **Acción:** Se crea una **nueva tabla** `RELACION` que contiene:
        *   Las `PK` de **todas** las entidades participantes (como `FK`).
        *   Todos los `Atributos_Relación`.
        *   La `PK` de esta tabla es la **combinación de todas las `FK`**.

*   #### **Caso Especial (1:N:M)**
    *   **Acción:** Se crea una **nueva tabla** `RELACION` que contiene:
        *   Las `PK` de todas las entidades participantes (como `FK`).
        *   Todos los `Atributos_Relación`.
        *   **Importante:** La `PK` de esta nueva tabla se forma **únicamente** con las `FK` de las entidades con cardinalidad `N` y `M`. La `FK` de la entidad `1` es una columna normal.

---

## 5. Atributos Multivalorados

*   **Regla:** Crear una **nueva tabla** exclusiva para ese atributo.
*   **Acción:**
    1.  La entidad original se convierte en una tabla con **todos sus otros atributos**, pero sin el multivalorado.
    2.  Se crea una **nueva tabla** para el atributo, que contiene:
        *   La `PK` de la tabla original (como `FK`).
        *   Una columna para el valor del atributo.
    3.  La `PK` de esta nueva tabla es la **combinación de ambas columnas**.

---

## 6. Relaciones de Dependencia

*   #### **Dependencia en Existencia**
    *   **Regla:** Idéntico a una relación 1:N.
    *   **Acción:**
        1.  La entidad fuerte y la débil se convierten en tablas con sus respectivos atributos.
        2.  La tabla de la entidad débil recibe la `PK` de la fuerte como `FK`.
        3.  Si la relación de dependencia tuviera atributos, estos **se añaden a la tabla débil**.

*   #### **Dependencia en Identificación**
    *   **Regla:** La `PK` de la fuerte se integra en la `PK` de la débil.
    *   **Acción:**
        1.  Ambas entidades se convierten en tablas con sus atributos.
        2.  La tabla débil recibe la `PK` de la fuerte. Esta `PK` importada actúa como `FK` y, junto al atributo que era la "clave parcial" de la débil, **forma la nueva `PK` compuesta**.