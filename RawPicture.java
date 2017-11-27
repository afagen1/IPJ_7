import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.introcs.Picture;

import javax.imageio.ImageIO;

import static edu.princeton.cs.introcs.StdOut.printf;

public class RawPicture {
    private int height, width;
    private Color[] pixels;
    private String toFilename, fromFilename;
    private Picture picture;
    private BufferedImage image;

    public RawPicture() {
        height = width = 0;
        pixels = null;
        toFilename = "picture.RAW";
        fromFilename = null;
    }

    public RawPicture(Picture picture) {
        // initialize relevant private members
        this.picture = picture;
        height = picture.height();
        width = picture.width();

        // read the pixel colors, by columnd and row, from picture using an ArrayList
        ArrayList<Color> tempPix = new ArrayList<>();
        for (int col = 0; col < picture.width(); col ++)
            for (int row = 0; row < picture.height(); row ++)
                tempPix.add(picture.get(col, row));

        // initialize the private pixels array, using the size of the ArrayList
        pixels = new Color[tempPix.size()];

        // populate pixels with the data from the ArrayList
        int i = 0;
        for (Color c: tempPix) pixels[i ++] = c;
    }

    public RawPicture(String filename) {
        // initialize relevant private members
        fromFilename = filename;
        String raw = "RAW";
        int last = fromFilename.length();
        // TODO
        // if image is GIF, JPG, or PNG
        // ... populate pixels from BufferedImage read from a File
        // else
        // ... populate pixels with a call to RawPicture::read()
        if(!(raw.equals(fromFilename.substring(last-3, last)))) {
            if (filename == null) throw new IllegalArgumentException("constructor argument is null");

            this.fromFilename = filename;
            try {
                // try to read from file in working directory
                File file = new File(filename);

                if (file.isFile()) {
                    image = ImageIO.read(file);
                }

                // now try to read from file in same directory as this .class file
                else {
                    URL url = getClass().getResource(filename);
                    if (url == null) {
                        url = new URL(filename);
                    }
                    image = ImageIO.read(url);
                }

                if (image == null) {
                    throw new IllegalArgumentException("could not read image file: " + filename);
                }

                width = image.getWidth(null);
                height = image.getHeight(null);
            } catch (IOException ioe) {
                throw new IllegalArgumentException("could not open image file: " + filename, ioe);
            }
        }
        else read(fromFilename);
    }


    public void write(String toFilename) {
        // TODO
        System.out.println(height);
        System.out.println(width);
        for (Color c : pixels) {
            printf("%d%d%d", c.getRed(), c.getGreen(), c.getBlue());
        }
        try {
            PrintWriter picRaw = new PrintWriter("pic.Raw");
            try {
                picRaw.println();
            } catch (Exception e) {
                System.out.println("Error");
            }
            picRaw.close();
        } catch (Exception e2) {
            System.out.println("File not found");
        }
    }
    public void read(String fromFilename) {
        // TODO
       try {
           Scanner input = new Scanner(new File("pic.RAW"));
           try{

           }catch(Exception e){
               System.out.println();
           }
           input.close();
       }catch(Exception e2){
            System.out.print("File not found");
        }

    }

    public static void main(String[] args) {
        // TODO


    }
}
