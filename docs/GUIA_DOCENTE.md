# Guía docente rápida

## Main recomendado
`pe.edu.utp.videoclip.VideoClipGUI`

## Para demostrar
1. Ejecutar con los stubs.
2. Mostrar que cambia de imagen por tiempo, pero todavía NO reacciona correctamente al audio.
3. Abrir `StudentWork.java`.
4. Explicar los tres TODO.
5. Ejecutar SERIAL.
6. Ejecutar PARALLEL con 4 threads.
7. Comparar tiempos.

## Qué está intencionalmente incompleto

`calculateAudioLevel()` devuelve 0.25.

Por ello, al inicio el audio NO controla realmente el clip.
El estudiante debe implementar el recorrido de `short[]`.

`chooseImageIndex()` ya cambia imágenes por tiempo, pero debe ser personalizado.

`applyEffects()` solo hace una rotación simple.

## Punto de threads

El código de threads ya está preparado para que el grupo pueda concentrarse en el problema de arreglos.

Cada tarea produce un archivo diferente:

```text
frame_000.png
frame_001.png
...
```

No se comparte escritura sobre una misma matriz porque cada efecto crea una nueva `MatrixImage`.

## Pregunta útil

¿Por qué el resultado puede conservar el orden de la película aunque los threads terminen en distinto orden?

Respuesta esperada:
porque cada tarea guarda el número correcto de frame en el nombre del archivo.
