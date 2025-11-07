graph TD
    subgraph " "
        direction LR
        subgraph "ANTES (Diagrama E/R)"
            A["
                <b>DEPARTAMENTO</b>
                <hr>
                🔑 id_depto (PK)
                <br>nombre_depto
                <br>ubicacion
            "]
            
            R{"
                <b>💎 TRABAJA_EN</b>
                <hr>
                fecha_incorporacion
                <br>tipo_contrato
            "}

            B["
                <b>EMPLEADO</b>
                <hr>
                🔑 id_emp (PK)
                <br>nombre_empleado
                <br>apellido
                <br>salario
            "]

            A -- "(1,1)" --> R -- "(1,N)" --> B
        end

        subgraph "DESPUÉS (Modelo Relacional)"
            T1["
                <b>Tabla DEPARTAMENTO</b>
                <hr>
                🔑 id_depto (PK)
                <br>nombre_depto
                <br>ubicacion
            "]

            T2["
                <b>Tabla EMPLEADO</b>
                <hr>
                🔑 id_emp (PK)
                <br>nombre_empleado
                <br>apellido
                <br>salario
                <br>🔗 <b>id_depto (FK)</b>
                <br><b>fecha_incorporacion</b>
                <br><b>tipo_contrato</b>
            "]
        end
    end
    
    style R fill:#d4edda,stroke:#155724
    style A fill:#e2e3e5,stroke:#383d41
    style B fill:#e2e3e5,stroke:#383d41
    style T1 fill:#cce5ff,stroke:#004085
    style T2 fill:#cce5ff,stroke:#004085