# Oracle DB — Guía de conexión

## Infraestructura

| Campo | Valor |
|-------|-------|
| Imagen Docker | `gvenzl/oracle-free:latest` |
| Contenedor | `oracledb` |
| Puerto | `1521` |
| PDB | `FREEPDB1` |
| Datos | `/opt/oracle/oradata` (volumen `oracle_data`) |
| Red Docker | `oracle_net` |

## Credenciales

| Rol | Usuario | Contraseña |
|-----|---------|------------|
| SYS / SYSTEM | — | `dam2026` |
| App user | `oliwheel` | `oliwheel123` |

## Acceso remoto via Tailscale

Oracle usa el protocolo SQL\*Net (TCP/1521), no HTTP, por lo que **no se puede exponer mediante reverse proxy de subdominios**. La solución es Tailscale.

### Requisitos
- Tailscale instalado y activo en el NAS y en el PC cliente
- Ambos dispositivos logueados en la misma cuenta Tailscale

### Obtener la IP Tailscale del NAS
```bash
tailscale ip -4
# Ejemplo: 100.74.167.38
```

### Configuración en DBeaver

| Campo | Valor |
|-------|-------|
| Host | IP Tailscale del NAS (ej. `100.74.167.38`) |
| Port | `1521` |
| Database | `FREEPDB1` |
| Connection type | Service name |
| User | `oliwheel` |
| Password | `oliwheel123` |

## Esquema de trabajo

Las tablas se crean en el esquema del usuario `OLIWHEEL` (sin prefijo de esquema en las consultas).

### Tablas creadas

```sql
CREATE TABLE departamentose (
    depnu     NUMBER(2)    PRIMARY KEY,
    dnombre   VARCHAR2(20),
    localidad VARCHAR2(15)
);

CREATE TABLE empleadose (
    empnu    NUMBER(4)   PRIMARY KEY,
    apellido VARCHAR2(15),
    oficio   VARCHAR2(12),
    director NUMBER(4)   REFERENCES empleadose(empnu),
    fechalta DATE,
    salario  NUMBER(8,2),
    comision NUMBER(8,2),
    depnu    NUMBER(2)   REFERENCES departamentose(depnu)
);

CREATE TABLE productose (
    productonu      NUMBER(4)   PRIMARY KEY,
    descripcion     VARCHAR2(40),
    precioactual    NUMBER(8,2),
    stockdisponible NUMBER(6)
);
```

## Notas operativas

- El primer arranque tarda ~5 minutos (Oracle inicializa la base de datos desde cero)
- Usar `INSERT` individuales en DBeaver en lugar de `INSERT ALL` para evitar el error `ORA-03405`
- Para ejecutar un bloque de sentencias en DBeaver: seleccionar todo el bloque y pulsar **Alt+X**
- Siempre terminar los inserts con `COMMIT;`

## Gestión del contenedor

```bash
# Ver logs en tiempo real
docker logs -f oracledb

# Reiniciar
docker restart oracledb

# Parar
docker compose -f oracledb.yaml --env-file .env down
```
