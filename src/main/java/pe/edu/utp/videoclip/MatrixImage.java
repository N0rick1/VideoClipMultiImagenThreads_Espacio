package pe.edu.utp.videoclip;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.file.Path;


public final class MatrixImage {


    private final int[][][] data;
    private final String name;



    public MatrixImage(
            int[][][] source,
            String name
    ){

        this.name = name;


        data =
                new int[source.length]
                [source[0].length]
                [3];



        for(int y=0;y<source.length;y++){

            for(int x=0;x<source[0].length;x++){

                for(int c=0;c<3;c++){

                    data[y][x][c] =
                            source[y][x][c];

                }
            }
        }

    }





    public String name(){
        return name;
    }



    public int height(){
        return data.length;
    }



    public int width(){
        return data[0].length;
    }






    // =====================================================
    // CARGAR IMAGEN COLOR
    // =====================================================

    public static MatrixImage load(Path path)
            throws Exception{


        BufferedImage image =
                ImageIO.read(
                        path.toFile()
                );



        if(image==null){

            throw new IllegalArgumentException(
                    "No se pudo leer "+path
            );
        }




        int[][][] pixels =
                new int[
                        image.getHeight()
                ]
                [
                        image.getWidth()
                ]
                [
                        3
                ];




        for(int y=0;y<image.getHeight();y++){

            for(int x=0;x<image.getWidth();x++){



                int rgb =
                        image.getRGB(x,y);



                pixels[y][x][0] =
                        (rgb>>16)&255;


                pixels[y][x][1] =
                        (rgb>>8)&255;


                pixels[y][x][2] =
                        rgb&255;


            }
        }



        return new MatrixImage(
                pixels,
                path.getFileName().toString()
        );

    }







    // =====================================================
    // BRILLO
    // =====================================================

    public MatrixImage brighten(double factor){


        int[][][] out =
                new int[height()]
                [width()]
                [3];



        for(int y=0;y<height();y++){

            for(int x=0;x<width();x++){


                for(int c=0;c<3;c++){


                    out[y][x][c] =
                            clamp(
                              (int)
                              (
                               data[y][x][c]
                               *
                               factor
                              )
                            );

                }
            }
        }



        return new MatrixImage(
                out,
                name
        );

    }






    // =====================================================
    // INVERTIR
    // =====================================================

    public MatrixImage invert(){


        int[][][] out =
                new int[height()]
                [width()]
                [3];



        for(int y=0;y<height();y++){

            for(int x=0;x<width();x++){


                for(int c=0;c<3;c++){


                    out[y][x][c] =
                            255 -
                            data[y][x][c];

                }

            }
        }



        return new MatrixImage(
                out,
                name
        );

    }






    // =====================================================
    // ROTACION
    // =====================================================

    public MatrixImage rotate(double degrees){


        int h=height();
        int w=width();


        int[][][] out =
                new int[h][w][3];



        double angle =
                Math.toRadians(degrees);


        double cos =
                Math.cos(angle);


        double sin =
                Math.sin(angle);



        double cx =
                (w-1)/2.0;


        double cy =
                (h-1)/2.0;




        for(int y=0;y<h;y++){

            for(int x=0;x<w;x++){


                double dx=x-cx;
                double dy=y-cy;



                int sx =
                    (int)Math.round(
                        cos*dx+
                        sin*dy+
                        cx
                    );


                int sy =
                    (int)Math.round(
                        -sin*dx+
                        cos*dy+
                        cy
                    );



                if(
                  sx>=0 &&
                  sx<w &&
                  sy>=0 &&
                  sy<h
                ){


                    out[y][x][0]=
                            data[sy][sx][0];

                    out[y][x][1]=
                            data[sy][sx][1];

                    out[y][x][2]=
                            data[sy][sx][2];

                }

            }
        }



        return new MatrixImage(
                out,
                name
        );

    }







    // =====================================================
    // ZOOM CINEMATICO
    // =====================================================

    public MatrixImage zoom(double factor){


        int h=height();
        int w=width();



        int[][][] out =
                new int[h][w][3];



        double cx =
                w/2.0;


        double cy =
                h/2.0;




        for(int y=0;y<h;y++){

            for(int x=0;x<w;x++){



                int sx =
                    (int)
                    (
                     (x-cx)/factor
                     +
                     cx
                    );


                int sy =
                    (int)
                    (
                     (y-cy)/factor
                     +
                     cy
                    );



                if(
                  sx>=0 &&
                  sx<w &&
                  sy>=0 &&
                  sy<h
                ){


                    out[y][x][0]=
                            data[sy][sx][0];

                    out[y][x][1]=
                            data[sy][sx][1];

                    out[y][x][2]=
                            data[sy][sx][2];

                }

            }
        }



        return new MatrixImage(
                out,
                name
        );

    }







    // =====================================================
    // SHARPEN
    // =====================================================

    public MatrixImage sharpen(){


        return convolve(
                new double[][]{

                    {0,-1,0},
                    {-1,5,-1},
                    {0,-1,0}

                },
                1
        );

    }





    // =====================================================
    // BLUR
    // =====================================================

    public MatrixImage blur(){


        return convolve(
                new double[][]{

                    {1,1,1},
                    {1,1,1},
                    {1,1,1}

                },
                9
        );

    }






    private MatrixImage convolve(
            double[][] kernel,
            double divisor
    ){


        int[][][] out =
                new int[height()]
                [width()]
                [3];



        for(int y=1;y<height()-1;y++){

            for(int x=1;x<width()-1;x++){



                for(int c=0;c<3;c++){


                    double sum=0;



                    for(int ky=-1;ky<=1;ky++){

                        for(int kx=-1;kx<=1;kx++){


                            sum +=
                            data[y+ky][x+kx][c]
                            *
                            kernel[ky+1][kx+1];

                        }

                    }



                    out[y][x][c]=
                            clamp(
                             (int)
                             (sum/divisor)
                            );

                }

            }

        }



        return new MatrixImage(
                out,
                name
        );

    }








    // =====================================================
    // SOBEL
    // =====================================================

    public MatrixImage sobel(){


        int[][][] out =
                new int[height()]
                [width()]
                [3];



        for(int y=1;y<height()-1;y++){

            for(int x=1;x<width()-1;x++){


                int value=0;



                for(int c=0;c<3;c++){


                    int gx =
                    -data[y-1][x-1][c]
                    +
                    data[y-1][x+1][c]
                    -
                    2*data[y][x-1][c]
                    +
                    2*data[y][x+1][c]
                    -
                    data[y+1][x-1][c]
                    +
                    data[y+1][x+1][c];



                    value =
                    Math.max(
                        value,
                        Math.abs(gx)
                    );

                }



                out[y][x][0]=value;
                out[y][x][1]=value;
                out[y][x][2]=value;

            }

        }



        return new MatrixImage(
                out,
                name
        );

    }








    // =====================================================
    // GUARDAR COLOR
    // =====================================================

    public void save(Path path)
            throws Exception{


        path.toFile()
            .getParentFile()
            .mkdirs();



        BufferedImage image =
                new BufferedImage(
                        width(),
                        height(),
                        BufferedImage.TYPE_INT_RGB
                );




        for(int y=0;y<height();y++){

            for(int x=0;x<width();x++){


                int rgb =

                (data[y][x][0]<<16)
                |
                (data[y][x][1]<<8)
                |
                data[y][x][2];



                image.setRGB(
                        x,
                        y,
                        rgb
                );

            }

        }



        ImageIO.write(
                image,
                "png",
                path.toFile()
        );

    }







    private static int clamp(int v){

        return Math.max(
                0,
                Math.min(
                        255,
                        v
                )
        );

    }

}