import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.Luminance;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdStats;

import java.awt.Color;


public class GrayScale {
    // IPJ_7_3.1.4

    public static void main(String[] args) {
        // Show image in grayscale.
        Picture picture = new Picture(args[0]); // use the Picture library to open the file and access individual pixels
        int[] grayscaleCounts = new int[256];
        int y = 0;
        double x = 0;
        for (int col = 0; col < picture.width(); col++)
        {
            for (int row = 0; row < picture.height(); row++)
            {
                Color color = picture.get(col, row); // count each pixel of the image
                Color gray = Luminance.toGray(color); // convert to grayscale (does nothing if already grayscale)
                grayscaleCounts[gray.getBlue()] ++; // count the occurrence of the found value (in grayscale, R=G=B=Y, where Y is the monochrome luminance)
            }
        }
        // use grayscaleCounts to create a histogram using StdDraw
        double[] q = new double[grayscaleCounts.length];
        for(int i = 0; i < 256; i++){
            System.out.println(grayscaleCounts[i]);
            q[i]= grayscaleCounts[i];
        }
        StdDraw.setCanvasSize(1000, 500);
        StdDraw.setYscale(0, 1300);
        StdDraw.setXscale(0,256);
        StdStats.plotBars(q);
    }
}
