Aquí va una **cheatsheet compacta y útil** de `LocalDate`, pensada como si te diera un pequeño “manual API exprés” para tenerlo todo a mano. No pretende ser exhaustiva al nivel de la documentación oficial, pero sí **prácticamente todo lo que se usa en el día a día**.

---

### 🚀 **LOCALDATE — CHEATSHEET EXPRESS**

Para usarlo:

```java
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.DateTimeFormatter;
```

---

## 📌 **Crear LocalDate**

```java
LocalDate hoy = LocalDate.now();
LocalDate fecha = LocalDate.of(2025, 12, 25);
LocalDate fecha2 = LocalDate.of(2025, Month.DECEMBER, 25);
LocalDate fromString = LocalDate.parse("2025-03-01");        // ISO
LocalDate fromStringCustom = LocalDate.parse("01-03-2025",
    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
```

---

## 📌 **Obtener componentes**

```java
int year = fecha.getYear();
int month = fecha.getMonthValue();      // 1–12
Month mes = fecha.getMonth();           // Month enum
int day = fecha.getDayOfMonth();
DayOfWeek dow = fecha.getDayOfWeek();   // MONDAY…
int dayOfYear = fecha.getDayOfYear();
```

---

## 📌 **Operaciones (suma/resta)**

```java
LocalDate d1 = fecha.plusDays(3);
LocalDate d2 = fecha.plusWeeks(2);
LocalDate d3 = fecha.plusMonths(1);
LocalDate d4 = fecha.plusYears(5);

LocalDate d5 = fecha.minusDays(10);
LocalDate d6 = fecha.minusWeeks(1);
LocalDate d7 = fecha.minusMonths(2);
LocalDate d8 = fecha.minusYears(1);
```

---

## 📌 **Ajustes (con XX)**

```java
LocalDate cambiado = fecha.withYear(2030);
LocalDate cambiado2 = fecha.withMonth(7);
LocalDate cambiado3 = fecha.withDayOfMonth(1);
LocalDate cambiado4 = fecha.withDayOfYear(200);
```

---

## 📌 **Comparaciones**

```java
boolean antes = fecha.isBefore(hoy);
boolean despues = fecha.isAfter(hoy);
boolean igual = fecha.isEqual(hoy);
```

---

## 📌 **Entre fechas**

```java
long dias = ChronoUnit.DAYS.between(hoy, fecha);
long semanas = ChronoUnit.WEEKS.between(hoy, fecha);
long meses = ChronoUnit.MONTHS.between(hoy, fecha);
long años = ChronoUnit.YEARS.between(hoy, fecha);

Period periodo = Period.between(hoy, fecha);
int añosP = periodo.getYears();
int mesesP = periodo.getMonths();
int diasP = periodo.getDays();
```

---

## 📌 **Inicio / fin de mes y año**

```java
LocalDate primeroMes = fecha.withDayOfMonth(1);
LocalDate ultimoMes = fecha.withDayOfMonth(fecha.lengthOfMonth());

LocalDate primeroAño = fecha.withDayOfYear(1);
LocalDate ultimoAño = fecha.withDayOfYear(fecha.lengthOfYear());
```

---

## 📌 **Comprobaciones rápidas**

```java
boolean bisiesto = fecha.isLeapYear();
int diasMes = fecha.lengthOfMonth();
int diasAño = fecha.lengthOfYear();
```

---

## 📌 **Formatear**

```java
DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String s1 = fecha.format(f1);

DateTimeFormatter f2 = DateTimeFormatter.ofPattern("EEEE dd 'de' MMMM yyyy");
String s2 = fecha.format(f2);
```

---

## 📌 **Convertir a LocalDateTime (añadir hora)**

```java
LocalDateTime conHora = fecha.atStartOfDay();      // 00:00
LocalDateTime conHora2 = fecha.atTime(15, 30);     // 15:30
```

---

## 📌 **Moverse por el calendario (TemporalAdjusters)**

```java
LocalDate proximoLunes = fecha.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
LocalDate proximoOIgual = fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
LocalDate anteriorLunes = fecha.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
LocalDate primerDiaMes = fecha.with(TemporalAdjusters.firstDayOfMonth());
LocalDate ultimoDiaMes2 = fecha.with(TemporalAdjusters.lastDayOfMonth());
```

---

## 📌 **Conversión “fina” a String ISO**

```java
String iso = fecha.toString();  // yyyy-MM-dd
```

---

### Cuando entiendes esta hoja, ya manejas LocalDate como quien corta pan con un buen cuchillo afilado: con seguridad, precisión y sin miedo a las migas que caigan.

Si quieres, puedo hacerte otra cheatsheet de `LocalTime`, `LocalDateTime`, `ZonedDateTime`, o incluso una mini-guía para evitar errores típicos de fechas en Java.
