@echo off
echo ============================================
echo   SETUP - Bootcamp Arrays Java
echo ============================================
echo.

:: Verificar Java
echo [1/3] Comprobando Java...
java -version 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Java no esta instalado o no esta en el PATH.
    echo.
    echo Descarga Java 17+ desde: https://adoptium.net/
    echo Instala y asegurate de marcar "Add to PATH" durante la instalacion.
    echo Luego vuelve a ejecutar este script.
    pause
    exit /b 1
)
echo    Java OK
echo.

:: Verificar JAVA_HOME
echo [2/3] Comprobando JAVA_HOME...
if "%JAVA_HOME%"=="" (
    echo.
    echo AVISO: JAVA_HOME no esta configurado.
    echo Maven podria no funcionar correctamente.
    echo.
    echo Para configurarlo:
    echo   1. Busca "Variables de entorno" en el menu Inicio
    echo   2. En "Variables del sistema" pulsa "Nueva"
    echo   3. Nombre: JAVA_HOME
    echo   4. Valor: la ruta donde instalaste Java (ej: C:\Program Files\Eclipse Adoptium\jdk-17...)
    echo.
) else (
    echo    JAVA_HOME = %JAVA_HOME%
)

:: Descargar Maven wrapper (no necesita Maven instalado globalmente)
echo [3/3] Configurando Maven Wrapper (no necesitas instalar Maven)...
if not exist ".mvn\wrapper" mkdir .mvn\wrapper

:: Crear mvnw.cmd si no existe
if not exist "mvnw.cmd" (
    echo Descargando Maven Wrapper...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper-distribution/3.2.0/maven-wrapper-distribution-3.2.0-bin.zip' -OutFile 'maven-wrapper.zip'" 2>nul
    if exist "maven-wrapper.zip" (
        powershell -Command "Expand-Archive -Path 'maven-wrapper.zip' -DestinationPath '.mvn/wrapper/temp' -Force" 2>nul
        del maven-wrapper.zip 2>nul
    )
    
    :: Crear mvnw.cmd manualmente como fallback
    echo @echo off > mvnw.cmd
    echo mvn %%* >> mvnw.cmd
    echo    Maven Wrapper creado (fallback a mvn global^)
) else (
    echo    Maven Wrapper ya existe
)

echo.
echo ============================================
echo   Probando compilacion del proyecto...
echo ============================================
echo.

call mvn compile -q 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo La compilacion fallo. Posibles causas:
    echo   - Maven no esta instalado. Descargalo de https://maven.apache.org/download.cgi
    echo   - JAVA_HOME no apunta a un JDK valido
    echo.
    echo Si no tienes Maven instalado:
    echo   1. Descarga el zip de https://maven.apache.org/download.cgi
    echo   2. Descomprime en C:\apache-maven
    echo   3. Anade C:\apache-maven\bin al PATH del sistema
    echo   4. Reinicia esta terminal y ejecuta este script de nuevo
    echo.
    pause
    exit /b 1
)

echo Compilacion exitosa!
echo.

echo ============================================
echo   Ejecutando tests para verificar...
echo ============================================
echo.

call mvn test -q 2>nul
echo.
echo ============================================
echo   SETUP COMPLETO
echo   Usa "mvn test" para ejecutar los tests
echo   Usa "mvn test -pl -Dtest=NombreTest" para un test concreto
echo ============================================
pause
