# Trabajo práctico — Mini película con audio, varias imágenes y threads

## Producto final
Crear un clip MP4 de 8–12 segundos en el que el audio controle una animación formada por múltiples imágenes.

## El proyecto base ya contiene
- lectura de WAV;
- lectura automática de todas las imágenes de `input/images/`;
- generación de frames;
- modo serial;
- modo paralelo con threads;
- medición de tiempos;
- scripts para ensamblar el MP4 con FFmpeg.

## Ustedes deben completar `StudentWork.java`

### TODO 1 — Audio
Completar:

```java
calculateAudioLevel(...)
```

Debe recorrer el bloque de `short[]` correspondiente al frame y devolver un valor entre 0 y 1.

### TODO 2 — Selección de imagen
Modificar:

```java
chooseImageIndex(...)
```

La imagen puede cambiar:
- según la amplitud;
- según el tiempo;
- combinando ambas reglas.

### TODO 3 — Efectos
Completar/personalizar:

```java
applyEffects(...)
```

Debe contener mínimo:
- 1 transformación geométrica;
- 1 filtro por matriz/convolución;
- 3 estados visuales diferentes.

## Imágenes
Pueden:
- reemplazar las imágenes incluidas;
- agregar más imágenes;
- quitar imágenes.

Solo deben colocarlas en:

```text
input/images/
```

El programa las carga automáticamente.

Formatos:
- PNG
- JPG
- JPEG

## Audio
Pueden reemplazar:

```text
input/audio/audio_base.wav
```

por otro WAV.

## Serial y paralelo

Deben ejecutar:

1. SERIAL
2. PARALLEL con 2 threads
3. PARALLEL con 4 threads

Opcional: 8 threads.

Registrar:
- tiempo;
- speedup;
- observaciones.

## Requisito del clip

- duración: 8–12 s;
- mínimo 3 imágenes;
- mínimo 3 efectos;
- mínimo 96 frames;
- salida MP4;
- audio sincronizado.

## Entrega

```text
Equipo_X/
├── proyecto/
├── videoclip_serial.mp4
├── videoclip_parallel.mp4
├── output/metrics.csv
├── README_EQUIPO.md
└── capturas/
```

## Defensa
1. ¿Qué representa `short[]`?
2. ¿Qué representa `int[][]`?
3. ¿Cómo se elige una imagen para cada frame?
4. ¿Por qué distintos frames pueden ejecutarse en distintos threads?
5. ¿Qué cambiaría si dos threads escribieran el mismo archivo?
6. ¿Cuál fue su speedup?
7. ¿Más threads siempre significa más velocidad?
8. Muestre sus cambios en `StudentWork.java`.
