param(
    [Parameter(Mandatory = $true)]
    [string]$ProjectName,
    [string]$BaseDir = "proyectos_repaso",
    [string]$PackageName = "com.repaso.generated"
)

$ErrorActionPreference = "Stop"

if ($ProjectName -notmatch "^[A-Za-z0-9_-]+$") {
    throw "ProjectName may only contain letters, digits, underscores, and hyphens."
}

$projectDir = Join-Path $BaseDir $ProjectName
$mainDir = Join-Path $projectDir ("src/main/java/" + ($PackageName -replace "\.", "/"))
$testDir = Join-Path $projectDir ("src/test/java/" + ($PackageName -replace "\.", "/"))

New-Item -ItemType Directory -Force -Path $mainDir | Out-Null
New-Item -ItemType Directory -Force -Path $testDir | Out-Null
New-Item -ItemType Directory -Force -Path (Join-Path $projectDir "src/main/resources") | Out-Null

$pom = @"
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.repaso</groupId>
    <artifactId>$ProjectName</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>$ProjectName</name>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.10.2</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>`$`{junit.version`}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.25.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <mainClass>$PackageName.App</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
"@

$app = @"
package $PackageName;

public class App {
    public static void main(String[] args) {
        System.out.println("Proyecto de repaso: $ProjectName");
    }
}
"@

$test = @"
package $PackageName;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @Test
    void projectNameIsAvailable() {
        assertThat("$ProjectName").isNotBlank();
    }
}
"@

$readme = @"
# $ProjectName

Proyecto de repaso generado por `$generate-review-project`.

## Ejecutar

```powershell
mvn test
mvn exec:java
```

Personalizar este README con el enunciado, los conceptos practicados, los puntos flojos trabajados y los criterios de aceptacion.
"@

Set-Content -Encoding UTF8 -Path (Join-Path $projectDir "pom.xml") -Value $pom
Set-Content -Encoding UTF8 -Path (Join-Path $mainDir "App.java") -Value $app
Set-Content -Encoding UTF8 -Path (Join-Path $testDir "AppTest.java") -Value $test
Set-Content -Encoding UTF8 -Path (Join-Path $projectDir "README.md") -Value $readme

Write-Output (Resolve-Path $projectDir).Path
