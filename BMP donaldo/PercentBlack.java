// javac -cp .:image4j-0.7.2.jar PercentBlack.java
// java -cp .:image4j-0.7.2.jar PercentBlack

import java.awt.image.BufferedImage;
import net.sf.image4j.codec.bmp.BMPDecoder;
import java.text.DecimalFormat;
import java.io.File;
class PercentBlack{

public static void main(String args[]){
try{
long solo_negro=0;
long  total=0;
BufferedImage mapa = BMPDecoder.read(new File("donal4.bmp"));

final int xmin = mapa.getMinX();
final int ymin = mapa.getMinY();

final int ymax = ymin + mapa.getHeight();
final int xmax = xmin + mapa.getWidth();

/// System.out.println("si funciona 3");

for (int i = xmin;i<xmax;i++)
{
   for (int j = ymin;j<ymax;j++,total++)
   {

    int pixel = mapa.getRGB(i, j);

    if (((pixel & 0x00FF) == 0))
    {
        //System.out.println("black at "+i+","+j);
        solo_negro++;
    }
   }
}
 System.out.println("negro es "+solo_negro+", total es "+total);
 double porcentaje = ((double)(solo_negro))/((double)(total));
 double porcentaje2= porcentaje*100;
 DecimalFormat df = new DecimalFormat("##.###");


 System.out.println("El porcentaje de negro es "+df.format(porcentaje2)+"%");

}catch(Exception e ){
 System.out.println("no funciona aa");
}
}
}
