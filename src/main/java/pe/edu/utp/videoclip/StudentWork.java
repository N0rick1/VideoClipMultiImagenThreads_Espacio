package pe.edu.utp.videoclip;


public final class StudentWork {


    private StudentWork() {}



    /*
     =====================================================
     ANALISIS DEL AUDIO
     =====================================================
    */

    public static double calculateAudioLevel(
            short[] samples,
            int start,
            int end
    ){

        long sum = 0;


        for(int i=start; i<end; i++){

            sum += Math.abs(samples[i]);

        }


        double average =
                sum /
                (double)(end-start);



        return Math.min(
                1.0,
                average / 30000.0
        );

    }






    /*
     =====================================================
     ELECCION DE IMAGEN
     USA TODAS LAS IMAGENES DISPONIBLES
     =====================================================
    */


    public static int chooseImageIndex(
            double level,
            int frameNumber,
            int totalFrames,
            int imageCount
    ){


        if(imageCount <= 1){

            return 0;

        }



        double progress =
                frameNumber /
                (double) totalFrames;



        int index =
                (int)
                (
                    progress *
                    imageCount
                );



        if(index >= imageCount){

            index = imageCount - 1;

        }



        return index;

    }








    /*
     =====================================================
     EFECTOS VISUALES
     =====================================================
    */


    public static MatrixImage applyEffects(
            MatrixImage base,
            double level,
            int frameNumber,
            int totalFrames
    ){



        double progress =
                frameNumber /
                (double) totalFrames;





        /*
          ZOOM CINEMATICO

          Inicio:
          acercamiento

          Final:
          alejamiento al universo
        */


        double zoom;



        if(progress < 0.5){


            zoom =
                    1.0 +
                    (progress * 0.8);


        }
        else{


            zoom =
                    1.4 +
                    ((progress - 0.5)
                    * 0.8);


        }






        /*
          Movimiento suave de cámara
        */


        double angle =
                Math.sin(
                        frameNumber * 0.04
                )
                *
                1.5;





        MatrixImage result =
                base
                .zoom(zoom)
                .rotate(angle);







        /*
          Efectos dependiendo
          de la intensidad del audio
        */


        if(level < 0.25){


            return result
                    .brighten(1.05);


        }
        else if(level < 0.60){


            return result
                    .sharpen();


        }
        else{


            return result
                    .sharpen()
                    .brighten(1.15);


        }



    }


}