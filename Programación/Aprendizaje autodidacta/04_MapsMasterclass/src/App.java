import java.util.Scanner;

import nivel1_fundamentos_map.Ejercicio01_PutGetRemove;
import nivel1_fundamentos_map.Ejercicio02_MetodosBasicos;
import nivel2_recorridos_avanzados.Ejercicio03_KeySetValues;
import nivel2_recorridos_avanzados.Ejercicio04_EntrySet;
import nivel2_recorridos_avanzados.Ejercicio05_ForEachLambda;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n============ 04 MAPS MASTERCLASS ============");
            System.out.println("1. Nivel 1: Ejercicio 01 - Put, Get, Remove");
            System.out.println("2. Nivel 1: Ejercicio 02 - Métodos Analíticos");
            System.out.println("3. Nivel 2: Ejercicio 03 - Vistas Parciales (keySet/values)");
            System.out.println("4. Nivel 2: Ejercicio 04 - EntrySet");
            System.out.println("5. Nivel 2: Ejercicio 05 - ForEach (Java 8)");
            System.out.println("6. Nivel 3: Ejercicio 06 - HashMap y Caos");
            System.out.println("7. Nivel 3: Ejercicio 07 - LinkedHashMap y Orden");
            System.out.println("8. Nivel 3: Ejercicio 08 - TreeMap y Orden Natural");
            System.out.println("9. Nivel 3: Ejercicio 09 - TreeMap y NavigableMap");
            System.out.println("10. Nivel 4: Ejercicio 10 - putIfAbsent");
            System.out.println("11. Nivel 4: Ejercicio 11 - getOrDefault y replace");
            System.out.println("12. Nivel 4: Ejercicio 12 - compute");
            System.out.println("13. Nivel 4: Ejercicio 13 - computeIfAbsent (Magia Map-List)");
            System.out.println("14. Nivel 4: Ejercicio 14 - computeIfPresent");
            System.out.println("15. Nivel 4: Ejercicio 15 - merge");
            System.out.println("16. Nivel 5: Ejercicio 16 - toMap básico");
            System.out.println("17. Nivel 5: Ejercicio 17 - toMap con colisiones");
            System.out.println("18. Nivel 5: Ejercicio 18 - toMap con Linked/TreeMap");
            System.out.println("19. Nivel 5: Ejercicio 19 - groupingBy");
            System.out.println("20. Nivel 5: Ejercicio 20 - partitioningBy");
            System.out.println("21. Nivel 5: Ejercicio 21 - groupingBy dinámico");
            System.out.println("22. Nivel 6: Ejercicio 22 - grouping con counting");
            System.out.println("23. Nivel 6: Ejercicio 23 - grouping con summing");
            System.out.println("24. Nivel 6: Ejercicio 24 - grouping con mapping");
            System.out.println("25. Nivel 6: Ejercicio 25 - grouping con filtering");
            System.out.println("26. Nivel 7: Ejercicio 26 - Map.entrySet().stream()");
            System.out.println("27. Nivel 7: Ejercicio 27 - flatMap en Maps");
            System.out.println("28. Nivel 7: Ejercicio 28 - Invertir Diccionarios");
            System.out.println("29. Nivel 8: Ejercicio 29 - DESAFÍO Frecuencias");
            System.out.println("30. Nivel 8: Ejercicio 30 - DESAFÍO BOSS FINAL E-Commerce");
            System.out.println("--- FASE 2: DOCTORADO ---");
            System.out.println("31. Nivel 9: Ejercicio 31 - Factórías Inmutables (Map.of)");
            System.out.println("32. Nivel 9: Ejercicio 32 - Copia Dura vs Vista Lógica (Map.copyOf)");
            System.out.println("33. Nivel 10: Ejercicio 33 - EnumMap (Eficiencia Extrema)");
            System.out.println("34. Nivel 10: Ejercicio 34 - IdentityHashMap (Destruir Equals)");
            System.out.println("35. Nivel 10: Ejercicio 35 - WeakHashMap (Cachés del GC)");
            System.out.println("36. Nivel 11: Ejercicio 36 - Romper el Contrato de Hash");
            System.out.println("37. Nivel 11: Ejercicio 37 - HashCode y Equals Inmutables");
            System.out.println("38. Nivel 11: Ejercicio 38 - El Peligro de Mutar la Llave");
            System.out.println("39. Nivel 12: Ejercicio 39 - Patrón Command Dispatcher");
            System.out.println("40. Nivel 12: Ejercicio 40 - Caché y Memoization Avanzada");
            System.out.println("41. Nivel 12: Ejercicio 41 - ConcurrentHashMap (Atómicos)");
            System.out.println("42. Nivel 12: Ejercicio 42 - Segment Locks vs Legacy Sync");
            System.out.println("43. Nivel 13: Ejercicio 43 - Vistas Relativas (headMap, tailMap)");
            System.out.println("44. Nivel 13: Ejercicio 44 - Consumo Destructivo Atómico");
            System.out.println("45. Nivel 13: Ejercicio 45 - Simular BiMap Estricto (Prólogo Guava)");
            System.out.println("46. Nivel 14: Ejercicio 46 - Bifurcación en un Paso (Teeing)");
            System.out.println("47. Nivel 14: Ejercicio 47 - GroupingByConcurrent (Parallel Streams)");
            System.out.println("48. Nivel 14: Ejercicio 48 - Map.merge() Avanzado");
            System.out.println("49. Nivel 14: Ejercicio 49 - Tablas Dobles Vanilla");
            System.out.println("50. Nivel 14: Ejercicio 50 - DESAFÍO MASTER (Router Web Vanilla)");
            System.out.println("--- FASE 3: GOOGLE GUAVA Y ENTORNOS EMPRESARIALES ---");
            System.out.println("51. Nivel 15: Ejercicio 51 - Guava Multimap (Listas encadenadas automáticas)");
            System.out.println("52. Nivel 15: Ejercicio 52 - Guava BiMap (Diccionarios Inversos O(1))");
            System.out.println("53. Nivel 15: Ejercicio 53 - Guava Table (Matrices Bidimensionales Limpias)");
            System.out.println("54. Nivel 15: Ejercicio 54 - Guava Builder (ImmutableMaps Corporativos)");
            System.out.println("0. Salir");
            System.out.print("Elige un ejercicio para ejecutar: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    Ejercicio01_PutGetRemove.demostracion();
                    Ejercicio01_PutGetRemove.ejercicio();
                    break;
                case 2:
                    Ejercicio02_MetodosBasicos.demostracion();
                    Ejercicio02_MetodosBasicos.ejercicio();
                    break;
                case 3:
                    Ejercicio03_KeySetValues.demostracion();
                    Ejercicio03_KeySetValues.ejercicio();
                    break;
                case 4:
                    Ejercicio04_EntrySet.demostracion();
                    Ejercicio04_EntrySet.ejercicio();
                    break;
                case 5:
                    Ejercicio05_ForEachLambda.demostracion();
                    Ejercicio05_ForEachLambda.ejercicio();
                    break;
                case 6:
                    nivel3_implementaciones_map.Ejercicio06_HashMapDesorden.demostracion();
                    nivel3_implementaciones_map.Ejercicio06_HashMapDesorden.ejercicio();
                    break;
                case 7:
                    nivel3_implementaciones_map.Ejercicio07_LinkedHashMapOrden.demostracion();
                    nivel3_implementaciones_map.Ejercicio07_LinkedHashMapOrden.ejercicio();
                    break;
                case 8:
                    nivel3_implementaciones_map.Ejercicio08_TreeMapOrdenNatural.demostracion();
                    nivel3_implementaciones_map.Ejercicio08_TreeMapOrdenNatural.ejercicio();
                    break;
                case 9:
                    nivel3_implementaciones_map.Ejercicio09_TreeMapNavegacion.demostracion();
                    nivel3_implementaciones_map.Ejercicio09_TreeMapNavegacion.ejercicio();
                    break;
                case 10:
                    nivel4_metodos_default_java8.Ejercicio10_PutIfAbsent.demostracion();
                    nivel4_metodos_default_java8.Ejercicio10_PutIfAbsent.ejercicio();
                    break;
                case 11:
                    nivel4_metodos_default_java8.Ejercicio11_GetOrDefaultYReplace.demostracion();
                    nivel4_metodos_default_java8.Ejercicio11_GetOrDefaultYReplace.ejercicio();
                    break;
                case 12:
                    nivel4_metodos_default_java8.Ejercicio12_Compute.demostracion();
                    nivel4_metodos_default_java8.Ejercicio12_Compute.ejercicio();
                    break;
                case 13:
                    nivel4_metodos_default_java8.Ejercicio13_ComputeIfAbsent.demostracion();
                    nivel4_metodos_default_java8.Ejercicio13_ComputeIfAbsent.ejercicio();
                    break;
                case 14:
                    nivel4_metodos_default_java8.Ejercicio14_ComputeIfPresent.demostracion();
                    nivel4_metodos_default_java8.Ejercicio14_ComputeIfPresent.ejercicio();
                    break;
                case 15:
                    nivel4_metodos_default_java8.Ejercicio15_Merge.demostracion();
                    nivel4_metodos_default_java8.Ejercicio15_Merge.ejercicio();
                    break;
                case 16:
                    nivel5_map_y_streams.Ejercicio16_ToMapBasico.demostracion();
                    nivel5_map_y_streams.Ejercicio16_ToMapBasico.ejercicio();
                    break;
                case 17:
                    nivel5_map_y_streams.Ejercicio17_ToMapColisiones.demostracion();
                    nivel5_map_y_streams.Ejercicio17_ToMapColisiones.ejercicio();
                    break;
                case 18:
                    nivel5_map_y_streams.Ejercicio18_ToMapImplementacion.demostracion();
                    nivel5_map_y_streams.Ejercicio18_ToMapImplementacion.ejercicio();
                    break;
                case 19:
                    nivel5_map_y_streams.Ejercicio19_GroupingBy.demostracion();
                    nivel5_map_y_streams.Ejercicio19_GroupingBy.ejercicio();
                    break;
                case 20:
                    nivel5_map_y_streams.Ejercicio20_PartitioningBy.demostracion();
                    nivel5_map_y_streams.Ejercicio20_PartitioningBy.ejercicio();
                    break;
                case 21:
                    nivel5_map_y_streams.Ejercicio21_GroupingByMultiple.demostracion();
                    nivel5_map_y_streams.Ejercicio21_GroupingByMultiple.ejercicio();
                    break;
                case 22:
                    nivel6_operaciones_avanzadas_streams.Ejercicio22_GroupingAndCounting.demostracion();
                    nivel6_operaciones_avanzadas_streams.Ejercicio22_GroupingAndCounting.ejercicio();
                    break;
                case 23:
                    nivel6_operaciones_avanzadas_streams.Ejercicio23_GroupingAndSumming.demostracion();
                    nivel6_operaciones_avanzadas_streams.Ejercicio23_GroupingAndSumming.ejercicio();
                    break;
                case 24:
                    nivel6_operaciones_avanzadas_streams.Ejercicio24_GroupingAndMapping.demostracion();
                    nivel6_operaciones_avanzadas_streams.Ejercicio24_GroupingAndMapping.ejercicio();
                    break;
                case 25:
                    nivel6_operaciones_avanzadas_streams.Ejercicio25_GroupingAndFiltering.demostracion();
                    nivel6_operaciones_avanzadas_streams.Ejercicio25_GroupingAndFiltering.ejercicio();
                    break;
                case 26:
                    nivel7_experto.Ejercicio26_MapToStream.demostracion();
                    nivel7_experto.Ejercicio26_MapToStream.ejercicio();
                    break;
                case 27:
                    nivel7_experto.Ejercicio27_FlatMapValues.demostracion();
                    nivel7_experto.Ejercicio27_FlatMapValues.ejercicio();
                    break;
                case 28:
                    nivel7_experto.Ejercicio28_InvertirMapa.demostracion();
                    nivel7_experto.Ejercicio28_InvertirMapa.ejercicio();
                    break;
                case 29:
                    nivel8_desafios_finales.Ejercicio29_DesafioFrecuencias.demostracion();
                    nivel8_desafios_finales.Ejercicio29_DesafioFrecuencias.ejercicio();
                    break;
                case 30:
                    nivel8_desafios_finales.Ejercicio30_DesafioCestaCompra.demostracion();
                    nivel8_desafios_finales.Ejercicio30_DesafioCestaCompra.ejercicio();
                    break;
                case 31:
                    nivel9_inmutabilidad.Ejercicio31_MapOf.demostracion();
                    nivel9_inmutabilidad.Ejercicio31_MapOf.ejercicio();
                    break;
                case 32:
                    nivel9_inmutabilidad.Ejercicio32_CopyOfVsUnmodifiable.demostracion();
                    nivel9_inmutabilidad.Ejercicio32_CopyOfVsUnmodifiable.ejercicio();
                    break;
                case 33:
                    nivel10_rendimiento_y_memoria.Ejercicio33_EnumMap.demostracion();
                    nivel10_rendimiento_y_memoria.Ejercicio33_EnumMap.ejercicio();
                    break;
                case 34:
                    nivel10_rendimiento_y_memoria.Ejercicio34_IdentityHashMap.demostracion();
                    nivel10_rendimiento_y_memoria.Ejercicio34_IdentityHashMap.ejercicio();
                    break;
                case 35:
                    nivel10_rendimiento_y_memoria.Ejercicio35_WeakHashMap.demostracion();
                    nivel10_rendimiento_y_memoria.Ejercicio35_WeakHashMap.ejercicio();
                    break;
                case 36:
                    nivel11_anatomia_llave.Ejercicio36_RomperContrato.demostracion();
                    nivel11_anatomia_llave.Ejercicio36_RomperContrato.ejercicio();
                    break;
                case 37:
                    nivel11_anatomia_llave.Ejercicio37_ImplementacionCorrecta.demostracion();
                    nivel11_anatomia_llave.Ejercicio37_ImplementacionCorrecta.ejercicio();
                    break;
                case 38:
                    nivel11_anatomia_llave.Ejercicio38_MutacionPeligrosa.demostracion();
                    nivel11_anatomia_llave.Ejercicio38_MutacionPeligrosa.ejercicio();
                    break;
                case 39:
                    nivel12_arquitectura_concurrencia.Ejercicio39_CommandDispatcher.demostracion();
                    nivel12_arquitectura_concurrencia.Ejercicio39_CommandDispatcher.ejercicio();
                    break;
                case 40:
                    nivel12_arquitectura_concurrencia.Ejercicio40_CacheMemoization.demostracion();
                    nivel12_arquitectura_concurrencia.Ejercicio40_CacheMemoization.ejercicio();
                    break;
                case 41:
                    nivel12_arquitectura_concurrencia.Ejercicio41_ConcurrentHashMap1.demostracion();
                    nivel12_arquitectura_concurrencia.Ejercicio41_ConcurrentHashMap1.ejercicio();
                    break;
                case 42:
                    nivel12_arquitectura_concurrencia.Ejercicio42_SegmentLocks.demostracion();
                    nivel12_arquitectura_concurrencia.Ejercicio42_SegmentLocks.ejercicio();
                    break;
                case 43:
                    nivel13_magia_arboles.Ejercicio43_VistasRelativas.demostracion();
                    nivel13_magia_arboles.Ejercicio43_VistasRelativas.ejercicio();
                    break;
                case 44:
                    nivel13_magia_arboles.Ejercicio44_ConsumoDestructivo.demostracion();
                    nivel13_magia_arboles.Ejercicio44_ConsumoDestructivo.ejercicio();
                    break;
                case 45:
                    nivel13_magia_arboles.Ejercicio45_SimularBiMap.demostracion();
                    nivel13_magia_arboles.Ejercicio45_SimularBiMap.ejercicio();
                    break;
                case 46:
                    nivel14_master_streams.Ejercicio46_Teeing.demostracion();
                    nivel14_master_streams.Ejercicio46_Teeing.ejercicio();
                    break;
                case 47:
                    nivel14_master_streams.Ejercicio47_GroupingByConcurrent.demostracion();
                    nivel14_master_streams.Ejercicio47_GroupingByConcurrent.ejercicio();
                    break;
                case 48:
                    nivel14_master_streams.Ejercicio48_MezclandoMapas.demostracion();
                    nivel14_master_streams.Ejercicio48_MezclandoMapas.ejercicio();
                    break;
                case 49:
                    nivel14_master_streams.Ejercicio49_TablasDobles.demostracion();
                    nivel14_master_streams.Ejercicio49_TablasDobles.ejercicio();
                    break;
                case 50:
                    nivel14_master_streams.Ejercicio50_DoctoradoFinal.demostracion();
                    nivel14_master_streams.Ejercicio50_DoctoradoFinal.ejercicio();
                    break;
                case 51:
                    nivel15_google_guava.Ejercicio51_Multimap.demostracion();
                    nivel15_google_guava.Ejercicio51_Multimap.ejercicio();
                    break;
                case 52:
                    nivel15_google_guava.Ejercicio52_BiMap.demostracion();
                    nivel15_google_guava.Ejercicio52_BiMap.ejercicio();
                    break;
                case 53:
                    nivel15_google_guava.Ejercicio53_Table.demostracion();
                    nivel15_google_guava.Ejercicio53_Table.ejercicio();
                    break;
                case 54:
                    nivel15_google_guava.Ejercicio54_ImmutableMap.demostracion();
                    nivel15_google_guava.Ejercicio54_ImmutableMap.ejercicio();
                    break;
                case 0:
                    System.out.println("¡Hasta pronto! Sigue practicando.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
