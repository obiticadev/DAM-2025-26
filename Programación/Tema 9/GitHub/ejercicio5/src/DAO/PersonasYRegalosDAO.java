package DAO;

import Clases.Persona;
import Clases.Regalo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PersonasYRegalosDAO {
        private Map<Persona, Set<Regalo>> listaPersonasConRegalo;

        public PersonasYRegalosDAO() {
                this.listaPersonasConRegalo = new HashMap<>();
                cargarDatos();
        }

        private void cargarDatos() {
                // 1. Reloj de pulsera
                listaPersonasConRegalo.put(new Persona("12345678A", "Carlos", "García"),
                                new HashSet<>(Set.of(new Regalo("Reloj de pulsera", "Cumpleaños"))));

                // 2. Libro de cocina
                listaPersonasConRegalo.put(new Persona("23456789B", "Ana", "Martínez"),
                                new HashSet<>(Set.of(new Regalo("Libro de cocina", "Inauguración de casa"))));

                // 3. Entradas de cine
                listaPersonasConRegalo.put(new Persona("34567890C", "Luis", "Rodríguez"),
                                new HashSet<>(Set.of(new Regalo("Entradas de cine", "Aniversario"))));

                // 4. Cafetera espresso
                listaPersonasConRegalo.put(new Persona("45678901D", "Elena", "Sánchez"),
                                new HashSet<>(Set.of(new Regalo("Cafetera espresso", "Boda"))));

                // 5. Auriculares inalámbricos
                listaPersonasConRegalo.put(new Persona("56789012E", "Javier", "López"),
                                new HashSet<>(Set.of(new Regalo("Auriculares inalámbricos", "Graduación"))));

                // 6. Planta de interior
                listaPersonasConRegalo.put(new Persona("67890123F", "Marta", "Pérez"),
                                new HashSet<>(Set.of(new Regalo("Planta de interior", "Agradecimiento"))));

                // 7. Maleta de viaje
                listaPersonasConRegalo.put(new Persona("78901234G", "Diego", "Gómez"),
                                new HashSet<>(Set.of(new Regalo("Maleta de viaje", "Jubilación"))));

                // 8. Set de pintura
                listaPersonasConRegalo.put(new Persona("89012345H", "Sofía", "Ruiz"),
                                new HashSet<>(Set.of(new Regalo("Set de pintura", "Navidad"))));

                // 9. Suscripción a revista
                listaPersonasConRegalo.put(new Persona("90123456I", "Pablo", "Hernández"),
                                new HashSet<>(Set.of(new Regalo("Suscripción a revista", "Día del padre"))));

                // 10. Caja de bombones
                listaPersonasConRegalo.put(new Persona("01234567J", "Lucía", "Díaz"),
                                new HashSet<>(Set.of(new Regalo("Caja de bombones", "San Valentín"))));
        }

        public HashMap<Persona, Set<Regalo>> devolverdatos() {
                return new HashMap<>(this.listaPersonasConRegalo);
        }

}
