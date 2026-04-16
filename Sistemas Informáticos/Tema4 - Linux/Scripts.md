# 📘 Guía de Estudio: Scripts de Bash para Examen Linux

A continuación encontrarás los scripts divididos por temática y funcionalidad.

---

## 1. Funciones y Captura de Valores
Este script demuestra cómo declarar funciones, pasar argumentos (`$1`, `$2`) y capturar el resultado de una función en una variable.

```bash
#!/bin/bash

# Declaración de funciones

nombre_de_la_funcion(){
	echo "Hola..."
}

suma(){
    # > $1 es el primer argumento recibido, $2 el segundo
    # Usamos echo para "devolver" el valor al flujo de salida
    echo $(( $1 + $2 ))
}

# > Llamamos a la función 'suma' con 1050 y 5.
# > $() ejecuta el comando dentro y guarda la salida en la variable 'resultado'.
resultado=$(suma 1050 5)

echo "El resultado es: $resultado"
```

---

## 2. Conversor Decimal a Binario
Script matemático que utiliza un bucle `while` para realizar divisiones sucesivas entre 2 y construir la cadena binaria.

```bash
#!/bin/bash

read -p "Introduce un decimal para convertirlo en binario: " decimal

# > Inicialización de variables
cociente=$((decimal/2))
resto=$((decimal%2))

binario=$resto

# > Bucle: Mientras el cociente sea mayor que 1, seguimos dividiendo
while [ $cociente -gt 1 ]; do
	resto=$((cociente%2))     # Calculamos el nuevo resto
	cociente=$((cociente/2))  # Reducimos el cociente
	
	# > Concatenamos el resto al principio de la cadena
	binario="$resto$binario"
done

# > Añadimos el último cociente al final del proceso
binario="$cociente$binario"
echo "$decimal = $binario"
```

---

## 3. Cajero Automático (Desglose de Billetes)
Este script calcula la cantidad de billetes de 100, 50, 20 y 10 necesarios para una cantidad dada, asegurando primero que sea múltiplo de 10.

```bash
#!/bin/bash

read -p "Introduce una cantidad: " monto
numBilletes100=0
numBilletes50=0
numBilletes20=0
numBilletes10=0
resto=0

# > Verificamos si el monto es divisible por 10
if [ $(( monto % 10 )) -eq 0 ]; then
	echo "Es múltiplo de 10"
	
    # > Bloque Billetes de 100
	if [ $(( monto / 100 )) -ge 0 ]; then
		numBilletes100=$(( monto / 100 ))
		# > Calculamos cuánto dinero sobra
		resto=$(( monto - (numBilletes100 * 100) ))
	fi
	monto=$resto # > Actualizamos el monto con lo que sobra
	
    # > Bloque Billetes de 50
	if [ $(( monto / 50 )) -ge 0 ]; then
		numBilletes50=$(( monto / 50 ))
		resto=$(( monto - (numBilletes50 * 50) ))
	fi
	monto=$resto
	
    # > Bloque Billetes de 20
	if [ $(( monto / 20 )) -ge 0 ]; then
		numBilletes20=$(( monto / 20 ))
		resto=$(( monto - (numBilletes20 * 20) ))
	fi
	monto=$resto
	
    # > Bloque Billetes de 10
	if [ $(( monto / 10 )) -ge 0 ]; then
		numBilletes10=$(( monto / 10 ))
		resto=$(( monto - (numBilletes10 * 10) ))
	fi
	
    # > Imprimir resultados
	echo "Billetes de 100: $numBilletes100"
	echo "Billetes de 50: $numBilletes50"
	echo "Billetes de 20: $numBilletes20"
	echo "Billetes de 10: $numBilletes10"
else
	echo "No es múltiplo de 10"
fi
```

---

## 4. Gestión de Usuarios: Borrado Masivo
Script que acepta argumentos (`$1`, `$2`) para borrar un usuario individual o una serie de usuarios secuenciales (ej: user1, user2...).

```bash
#!/bin/bash

user=$1      # > Nombre base del usuario
numUser=$2   # > Cantidad de usuarios (opcional)

# > CASO 1: Solo se pasa un argumento (Borrar un solo usuario)
if [ $# -eq 1 ]; then
	echo "Eliminando el usuario ${user}..."
	sudo userdel -r ${user}
	exit 0
fi

# > CASO 2: Se pasan dos argumentos (Borrar múltiples usuarios)
if [ $# -eq 2 ]; then
    # > Valida que el número esté entre 1 y 5
	if (( 0 < numUser && numUser <= 5 )); then
		for (( i=1; i<=$numUser; i++ ))
		do
			nombre_final="${user}${i}"
			echo "Eliminando el usuario ${nombre_final}..."
			sudo userdel -r ${nombre_final}
		done
	else 
		echo "Error: Introduce los valores correctos. Ej: [User] + [numUser {1..5}]"
		exit 1
	fi
else 
	echo "Error: Debes pasar un nombre de usuario y el número de usuarios a borrar"
	exit 1
fi
```

---

## 5. Gestión de Usuarios: Creación Masiva
Similar al anterior, pero utiliza `useradd` y `chpasswd` para crear usuarios y asignarles contraseña.

```bash
#!/bin/bash

user=$1
numUser=$2

# > CASO 1: Crear un solo usuario
if [ $# -eq 1 ]; then
	echo "Creando el usuario ${user}..."
    # > Crea usuario con home (-m) y shell (-s), luego asigna pass (user:user)
	sudo useradd -m -s /bin/bash ${user} && echo "${user}:${user}" | sudo chpasswd
	exit 0
fi

# > CASO 2: Crear múltiples usuarios (del 1 al 5)
if [ $# -eq 2 ]; then
	if (( 0 < numUser && numUser <= 5 )); then
		for (( i=1; i<=$numUser; i++ ))
		do
			nombre_final="${user}${i}"
			echo "Creando el usuario ${nombre_final}..."
			sudo useradd -m -s /bin/bash ${nombre_final} && echo "${nombre_final}:${nombre_final}" | sudo chpasswd
		done
	else 
		echo "Error: Introduce los valores correctos. Ej: [User] + [numUser {1..5}]"
		exit 1
	fi
else 
	echo "Error: Debes pasar un nombre de usuario y el número de usuarios a crear"
	exit 1
fi
```

---

## 6. Comparación de 3 Números (Lógica Anidada)
Determina cuál es el número mayor utilizando estructuras `if` anidadas.

```bash
#!/bin/bash

read -p "Introduce el número 1: " num1
read -p "Introduce el número 2: " num2
read -p "Introduce el número 3: " num3

numTemp=0

# > Comparamos primero num1 con num2
if [ "$num1" -gt "$num2" ]; then
    # > Si gana num1, lo comparamos con num3
	if [ "$num1" -gt "$num3" ]; then
		echo "$num1 es el más grande"
	else
		echo "$num3 es el más grande"
	fi
else
    # > Si gana num2 (en la primera comparación), lo comparamos con num3
	if [ "$num2" -gt "$num3" ]; then
		echo "$num2 es el más grande"
	else
		echo "$num3 es el más grande"
	fi
fi
```

---

## 7. Comparación de 3 Números (Duplicado)
*Nota: Este script aparecía repetido en tu solicitud original. Lo mantengo aquí para cumplir con la instrucción de no perder ningún código.*

```bash
#!/bin/bash

read -p "Introduce el número 1: " num1
read -p "Introduce el número 2: " num2
read -p "Introduce el número 3: " num3

numTemp=0

if [ "$num1" -gt "$num2" ]; then
	if [ "$num1" -gt "$num3" ]; then
		echo "$num1 es el más grande"
	else
		echo "$num3 es el más grande"
	fi
else
	if [ "$num2" -gt "$num3" ]; then
		echo "$num2 es el más grande"
	else
		echo "$num3 es el más grande"
	fi
fi
```

---

## 8. Contar Usuarios "Humanos"
Este script lee el archivo `/etc/passwd` y cuenta cuántos usuarios tienen un UID superior a 1000 (generalmente usuarios no de sistema).

```bash
#!/bin/bash

usuarios=0

# > `cat ... | cut` extrae el tercer campo (UID) de cada línea
for i in `cat /etc/passwd | cut -d":" -f3`
do
    # > Filtramos UIDs mayores a 1000
    if [ "$i" -gt 1000 ]; then
        ((usuarios++)) # Incrementamos contador
    fi
done
echo "$usuarios"
```

---

## 9. Interacción Básica y Timeout
Ejemplos básicos de `echo`, `read` y `read` con tiempo de espera.

```bash
#!/bin/bash
#---- Esto es un comentario
echo "Hola Mundo"
echo "¿Cómo te llamas?"
read respuesta
echo "Hola $respuesta"
read -p "¿Y cuántos años tienes? " edad
echo "Así que tienes $edad"

# > read -t 5 espera 5 segundos por una entrada. Si no llega, el script continúa.
read -t 5 -p 
```

---

## 10. Saludo según la Hora
Utiliza el comando `date` y condicionales con rangos para dar los buenos días, tardes o noches.

```bash
#!/bin/bash

# > Extrae solo la hora (00-23)
hora=$(date +%H) 

echo "La hora es $hora"

# > Uso de && (AND) dentro de [[ ]] para rangos
if [[ "$hora" -gt 6 && "$hora" -le 12 ]]; then
	echo "Buenos días $USER"
elif [[ "$hora" -gt 12 && "$hora" -lt 20 ]]; then
	echo "Buenas tardes $USER"
else
	echo "Buenas noches $USER"
fi
```

---

## 11. Condicionales Simples y Compuestos
Pruebas de lógica para comparar números y uso de operadores lógicos.

```bash
#!/bin/bash

read -p "¿Cuál es la variable que quieres guardar? " numero
if [ "$numero" -eq 5 ]; then
	echo "La variable $numero es 5"
else
	echo "la variable $numero no es 5"
fi

a=5
b=7

# > Estilo clásico con corchetes separados
if [ "$a" -gt 1 ] && [ "$b" -gt 1 ]; then
	echo "$a y $b son más grandes que 1"
fi

# > Estilo moderno de Bash con dobles corchetes
if [[ "$a" -gt 1 && "$b" -gt 1 ]]; then
	echo "$a y $b son más grandes que 1"
fi
```

---

## 12. Tipos de Bucles For
Diferentes sintaxis para iterar con `for`: lista explícita, rangos y estilo C.

```bash
#!/bin/bash
#for

# > 1. Iterar sobre una lista escrita a mano
for i in 1 2 3 4 5
do
echo -n $i
done

echo # Salto de línea

# > 2. Iterar sobre un rango {inicio..fin}
for i in {1..10}
do
echo -n $i
done

echo

# > 3. Iterar sobre rango con paso {inicio..fin..paso}
for i in {0..10..3}
do
echo -n $i
done

echo

# > 4. Estilo C: (( inic; cond; incremento ))
for (( i=0; i<10; i+=4))
do
echo -n $i
done

echo
```

---

## 13. Validación de Edad y Nombre
Condicional compuesto que mezcla comparación numérica (`-ge`) y de cadena (`=`).

```bash
#!/bin/bash

read -p "Introduce una edad: " edad
read -p "Introduce un nombre: " nombre

# > Comprueba si edad >= 18 Y nombre es "Ana"
if [[ "$edad" -ge 18 && "$nombre" = "Ana" ]]; then
	echo "Tienes $edad y te llamas $nombre, por lo que eres delegada"
else
	echo "Adios"
fi
```

---

## 14. Crear Directorio
Script simple para crear una carpeta en el Escritorio usando la variable de entorno `$HOME`.

```bash
#!/bin/bash

read -p "Dime un nombre para la carpeta: " carpeta
mkdir $HOME/Escritorio/$carpeta
```

---

## 15. Sistema de Copias de Seguridad (Backup)
Genera variables de fecha y crea un archivo comprimido `.tgz` de un directorio de usuario.

```bash
#!/bin/bash
# > Capturamos fecha actual
anio=$(date +%Y)
mes=$(date +%m)
dia=$(date +%d)

# > tar cvzf: c(crear), v(verbose/visual), z(compresión gzip), f(archivo salida)
# > Guarda la carpeta /home/oliwheel en /COPIA/ con el nombre de la fecha
tar cvzf /COPIA/$anio$mes$dia.tgz /home/oliwheel
```