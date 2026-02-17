# Práctica 1 - IPC1

## Descripción
Esta práctica consiste en la realización de un juego en consola, utilizando el lenguaje de
programación JAVA. Se trata de una versión simple del conocido videojuego PAC MAN, con
una interfaz simple pero amigable.
El juego se desarrollará en un tablero de tamaño variable, donde el jugador (PACMAN)
deberá moverse para recolectar pre


## ¿Cómo ejecutar el programa?

1. Correr el programa.
2. Seleccionar una opción del menú principal:

- 1 → Iniciar juego
- 2 → Ver historial
- 3 → Salir


## Iniciar el Juego

Al elegir la opción 1, se deben seguir los siguientes pasos:

1. Ingresar tu nombre  
   (Presionar Enter al finalizar)

2. Elegir el tamaño del tablero:
   - "P" → Pequeño  
   - "G" → Grande  
   (Presionar Enter al finalizar)

3. Elegir la cantidad de premios  
   (Presionar Enter al finalizar)

4. Elegir la cantidad de paredes  
   (Presionar Enter al finalizar)

5. Elegir la cantidad de fantasmas  
   (Presionar Enter al finalizar)


## Selección de Posición Inicial

Cuando se muestre el tablero:

- Contar desde 0 en adelante para elegir la posición.
- Fila: contar de arriba hacia abajo.
- Columna: contar de izquierda hacia derecha.


NOTA: No puedes elegir una casilla donde haya una pared "X".



## ¿Cómo moverse por el tablero?

Se utilizan las siguientes teclas:

- 8 → ARRIBA
- 5 → ABAJO
- 6 → DERECHA
- 4 → IZQUIERDA


## Reglas del Juego

1. El jugador inicia con 3 vidas y el puntaje en 0.
2. Gana si obtiene todos los premios.
3. No hay bordes externos:
   - Si el jugador se pasa del extremo derecho, reaparece en el izquierdo (y viceversa).
   - Lo mismo aplica para arriba y abajo.
4. No es posible atravesar paredes (X).
5. Al pasar por un fantasma (@):
   - El jugador pierde una vida.
   - El fantasma desaparece.
6. Al pasar sobre un premio (0 o $):
   - El jugador gana puntos.
   - El premio desaparece.
7. El juego termina si:
   - El jugador gana.
   - Pierde todas sus vidas.
   - Decide pausar y terminar la partida.


## Requisitos
- Consola o entorno como NetBeans.
