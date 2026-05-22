package com.example.ahorcado.Clases;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class AhorcadoASCII {

        public static ArrayList<List<String>> listaAhorcado() {

                // ==========================================
                // ESTILO 1: EL PIRATA (Con olas, parche y pata de palo)
                // ==========================================
                List<String> estiloPirata = new ArrayList<>(Arrays.asList(
                                // 0 fallos: Solo el mar y el tablón flotando
                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "~~~~~~~~~~~",

                                // 1 fallo: Poste vertical
                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 2 fallos: Travesaño superior
                                "    +-----+\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 3 fallos: Soga
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 4 fallos: Cabeza (con pañuelo y parche)
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(o_O)  |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 5 fallos: Torso
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(o_O)  |\n" +
                                                "    |     |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 6 fallos: Brazo izquierdo
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(o_O)  |\n" +
                                                "   /|     |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 7 fallos: Brazo derecho (Garfio)
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(o_O)  |\n" +
                                                "   /|~    |\n" +
                                                "          |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 8 fallos: Pierna izquierdo
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(o_O)  |\n" +
                                                "   /|~    |\n" +
                                                "   /      |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 9 fallos: Pierna derecha (Pata de palo)
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(o_O)  |\n" +
                                                "   /|~    |\n" +
                                                "   / |    |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~",

                                // 10 fallos: ¡Pirata derrotado! (Ojos x_X)
                                "    +-----+\n" +
                                                "    |     |\n" +
                                                "  ~(x_X)  |\n" +
                                                "   /|~    |\n" +
                                                "   / |    |\n" +
                                                "          |\n" +
                                                "~~~~~~~~~~~"));

                // ==========================================
                // ESTILO 2: MODERNO Y MINIMALISTA (Caracteres finos Unicode)
                // ==========================================
                List<String> estiloModerno = new ArrayList<>(Arrays.asList(
                                // 0 fallos: Base
                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "┴─────────┴",

                                // 1 fallo: Poste vertical
                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 2 fallos: Travesaño superior
                                "┌─────────┐\n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 3 fallos: Soga
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 4 fallos: Cabeza viva
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (•‿•)\n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 5 fallos: Torso
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (•‿•)\n" +
                                                "│         │\n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 6 fallos: Brazo izquierdo
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (•‿•)\n" +
                                                "│        /│\n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 7 fallos: Brazo derecho
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (•‿•)\n" +
                                                "│        /│\\    \n" +
                                                "│          \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 8 fallos: Pierna izquierda
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (•‿•)\n" +
                                                "│        /│\\    \n" +
                                                "│        / \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 9 fallos: Pierna derecha
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (•‿•)\n" +
                                                "│        /│\\    \n" +
                                                "│        / \\    \n" +
                                                "│          \n" +
                                                "┴─────────┴",

                                // 10 fallos: Muerto
                                "┌─────────┐\n" +
                                                "│         │\n" +
                                                "│       (x_x)\n" +
                                                "│        /│\\    \n" +
                                                "│        / \\    \n" +
                                                "│          \n" +
                                                "┴─────────┴"));

                // ==========================================
                // ESTILO 3: INDUSTRIAL / CYBERPUNK (Robot)
                // ==========================================
                List<String> estiloRobotico = new ArrayList<>(Arrays.asList(
                                // 0 fallos: Base magnética
                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "           \n" +
                                                "[=======]  ",

                                // 1 fallo: Poste de carga
                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 2 fallos: Brazo de grúa
                                "+====+     \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 3 fallos: Cable conector
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 4 fallos: Módulo CPU (Cabeza)
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [o_o]   \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 5 fallos: Chasis central (Torso)
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [o_o]   \n" +
                                                "|   [#]    \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 6 fallos: Actuador izquierdo (Brazo)
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [o_o]   \n" +
                                                "|  /[#]    \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 7 fallos: Actuador derecho (Ambos brazos)
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [o_o]   \n" +
                                                "|  /[#]\\    \n" +
                                                "|          \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 8 fallos: Soporte izquierdo (Pierna)
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [o_o]   \n" +
                                                "|  /[#]\\    \n" +
                                                "|   /      \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 9 fallos: Soporte derecho (Cuerpo completo)
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [o_o]   \n" +
                                                "|  /[#]\\    \n" +
                                                "|   / \\    \n" +
                                                "|          \n" +
                                                "[=======]  ",

                                // 10 fallos: CPU Apagada / Cortocircuito
                                "+====+     \n" +
                                                "|    |     \n" +
                                                "|  [x_x]   \n" +
                                                "|  /[#]\\    \n" +
                                                "|   / \\    \n" +
                                                "|          \n" +
                                                "[=======]  "));

                // ArrayList principal que inicializamos directamente pasándole los 3 estilos
                return new ArrayList<>(Arrays.asList(
                                estiloPirata,
                                estiloModerno,
                                estiloRobotico));
        }

}
