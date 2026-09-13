# Mapa del código

## Archivos que NO necesitan modificar

`WavReader.java`
- convierte WAV a `short[]`.

`MatrixImage.java`
- convierte PNG/JPG a `int[][]`;
- rotación;
- brillo;
- blur;
- sharpen;
- Sobel.

`ImageLibrary.java`
- carga todas las imágenes de `input/images/`.

`FrameGenerator.java`
- contiene versión serial y paralela.

`Metrics.java`
- tiempos, speedup y CSV.

## Archivo que SÍ deben trabajar

`StudentWork.java`

Contiene los tres puntos principales:
1. analizar audio;
2. seleccionar imagen;
3. aplicar efectos.

## Flujo

```text
audio WAV
   ↓
short[]
   ↓
StudentWork.calculateAudioLevel()
   ↓
nivel 0..1
   ↓
StudentWork.chooseImageIndex()
   ↓
MatrixImage[]
   ↓
StudentWork.applyEffects()
   ↓
int[][]
   ↓
frame_###.png
   ↓
FFmpeg
   ↓
MP4
```
