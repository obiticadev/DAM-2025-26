# 🚀 Guía de Arranque desde Terminal (Vanilla Java)

¡Felicidades por poseer la Masterclass Completa! 

Este proyecto está configurado bajo **Maven** (el gestor de dependencias moderno de Java). Eso significa que si no dispones de un IDE súper vitaminado (como IntelliJ IDEA, Eclipse o la extensión full de VSC), puedes poner en marcha **todo este sistema compilándolo puramente en la negra terminal de tu PC**.

A continuación tienes todos los pasos necesarios, listos para copiar y pegar, que se encargarán de descargar las bases de datos (SQLite), los optmizadores (HikariCP) y compilar cada uno de tus 70 ejercicios.

---

## Requisitos Previos

Antes de arrancar, tienes que haber descargado e instalado:
1. **Java Development Kit (JDK 25 LTS** o superior). Puedes verificarlo escribiendo en CMD:
   ```bash
   java -version
   ```
2. **Apache Maven (Opcional si usas el wrapper `mvnw` que te crea VSC, pero ideal instalarlo globalmente)**. 
   ```bash
   mvn -version
   ```

---

## 1. Descarga e Instalación de Dependencias 🛠️

Este proyecto cuenta con un archivo `pom.xml` donde declaré al inicio de la masterclass librerías muy pesadas subyacentes como Hikari o SQLite.

Para que tu terminal se conecte a internet (A Repositorios Maven Centrales) y se descargue todos estos binarios `.jar` requeridos a tu carpeta `.m2` oculta en Windows:

1. Abre tu Terminal (Símbolo en Sistema, o PowerShell).
2. Dirígete a la carpeta raíz de este proyecto (Donde está el archivo `pom.xml`):
   ```bash
   cd "c:\Users\obitica\Java\DAM-2025-26\Programación\Aprendizaje autodidacta\GenericosCRUDMasterclass"
   ```
3. Fuerza la compilación pura e descarga de todo el ecosistema:
   ```bash
   mvn clean install
   ```
   *Deberías ver una lluvia de textos en consola y terminar en un enorme [INFO] BUILD SUCCESS verde.*

---

## 2. Compilación Manual (`javac`) 🖥️

Al haber usado Maven en el paso anterior, el comando `mvn install` no sólo ha traido internet las cosas, sino que ha creado magicamente una carpeta llamada `target/classes`.
Ahí dentro, ha traducido tus .java del Nivel 1 hasta el 19 al idioma informático que puede ejecutar el sistema (.class bytecode).

Si por un casual tú editaras el `Ejercicio01.java` en bloque, deberías volver a compilarlo actualizándolo diciéndole a maven:
```bash
mvn compile
```

---

## 3. Ejecutar un Ejercicio por Terminal ▶️

Como todos y cada uno de los 70 ejercicios disponen de zona de pruebas propia (`public static void main(String[] args)`), puedes ejecutarlos independientemente.

Sin embargo, como ahora el ecosistema usa componentes de internet, no basta con decirle a la terminal "Oye Corre el ejercicio uno". Hay que indicarle dónde guardó Maven las librerías al momento de iniciar la máquina virtual JVM usando el tag de `ClassPath (-cp)`.

Afortunadamente, existe un comando atómico y unificado en Maven que lo hace todo de golpe por ti (`exec:java`):

> **Ejecutar el Ejercicio 1 (Genéricos)**
```bash
mvn exec:java -D"exec.mainClass"="com.masterclass.nivel1_fundamentos_genericos.Ejercicio01_BoxGenerico"
```

> **Ejecutar el Ejercicio 23 (Entrelazado de Hilos)**
```bash
mvn exec:java -D"exec.mainClass"="com.masterclass.nivel6_hilos_basicos.Ejercicio23_Entrelazado"
```

> **Ejecutar el Ejercicio 70 (El gran ecommerce Transaccional)**
```bash
mvn exec:java -D"exec.mainClass"="com.masterclass.nivel19_reto_final.Ejercicio70_ElGranEcommerce"
```

### El Poder Total
Solo debes sustituir lo que hay dentro de las comillas `"exec.mainClass"` copiando el "nombre del paquete punto nombre de la clase".

Enhorabuena, no dependes de VSC para respirar en la Matrix de Java corporativa. ¡Un abrazo ingeniero!
