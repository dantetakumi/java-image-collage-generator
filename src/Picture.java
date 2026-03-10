import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
 * A filter to convert pixels in the given index range to grayscale
 * @param start the start (inclusive) index of the Picture to apply this filter
 * @param end the end (exclusive) index of the Picture to apply this filter
 */
public void grayscale(int start, int end)
{
    // Get all pixels into a 1D array
    Pixel[] pixels = this.getPixels();

    // Begin at the starting index
    int i = start;

    // While loop until reaching end index
    while (i <= end && i < pixels.length)
    {
        // Get current pixel
        Pixel p = pixels[i];

        // Compute grayscale intensity by avg RGB values
        int avg = (p.getRed() + p.getGreen() + p.getBlue()) / 3;

        // Set all three colors to the same intensity
        p.setRed(avg);
        p.setGreen(avg);
        p.setBlue(avg);

        // Move to next pixel
        i++;
    }
}

/**
 * A filter to convert pixels in the given index range to their negative
 * @param start the start (inclusive) index of the Picture to apply this filter
 * @param end the end (exclusive) index of the Picture to apply this filter
 */
public void negative(int start, int end)
{
    // Get all pixels into a 1D array
    Pixel[] pixels = this.getPixels();

    // Begin at the starting index
    int i = start;

    // While loop until reaching end index
    while (i <= end && i < pixels.length)
    {
        // Get current pixel
        Pixel p = pixels[i];

        // Invert each color (255 - current color value)
        p.setRed(255 - p.getRed());
        p.setGreen(255 - p.getGreen());
        p.setBlue(255 - p.getBlue());

        // Move to next pixel
        i++;
    }
}

/**
 * Mirrors the picture around a vertical line in the middle
 * of the picture based on the width.
 */
public void mirrorVertical()
{
    int width = this.getWidth();
    int mirrorPoint = width / 2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;

    // loop through all the rows
    for (int y = 0; y < getHeight(); y++)
    {
        // loop from 0 to the middle (mirror point)
        for (int x = 0; x < mirrorPoint; x++)
        {
            leftPixel = getPixel(x, y);
            rightPixel = getPixel(width - 1 - x, y);
            rightPixel.setColor(leftPixel.getColor());
        }
    }
}

/**
 * Creates a collage from three pictures
 * @param p1 the left picture
 * @param p2 the middle picture
 * @param p3 the right picture
 */
public void collage(Picture p1, Picture p2, Picture p3){
    //Pixel from one of the parameter pics
    Pixel sourcePixel = null; 
    //Pixel on the calling object (canvas)
    Pixel targetPixel = null;
    
    //First picture
    //targetX starts at 0 because p1 is placed on the far left
    for (int sourceX = 0, targetX = 0;
         sourceX < p1.getWidth();
         sourceX++, targetX++)
    {
        for (int sourceY = 0, targetY = 0;
             sourceY < p1.getHeight();
             sourceY++, targetY++)
        {
            sourcePixel = p1.getPixel(sourceX, sourceY);
            targetPixel = this.getPixel(targetX, targetY);
            targetPixel.setColor(sourcePixel.getColor());
        }
    }
    
    //Second picture
    //targetX starts at p1.getWidth() because pic2
    //should be placed immediately to the right of pic1
    for (int sourceX = 0, targetX = p1.getWidth();
         sourceX < p2.getWidth();
         sourceX++, targetX++)
    {
        for (int sourceY = 0, targetY = 0;
             sourceY < p2.getHeight();
             sourceY++, targetY++)
        {
            sourcePixel = p2.getPixel(sourceX, sourceY);
            targetPixel = this.getPixel(targetX, targetY);
            targetPixel.setColor(sourcePixel.getColor());
        }
    }
    
    //Third picture
    //targetX starts at 2*p1.getWidth() becuase this pic
    //should be placed after pic1 and pic2
     for (int sourceX = 0, targetX = 2 * p1.getWidth();
         sourceX < p3.getWidth();
         sourceX++, targetX++)
    {
        for (int sourceY = 0, targetY = 0;
             sourceY < p3.getHeight();
             sourceY++, targetY++)
        {
            sourcePixel = p3.getPixel(sourceX, sourceY);
            targetPixel = this.getPixel(targetX, targetY);
            targetPixel.setColor(sourcePixel.getColor());
        }
    }
}

  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
 
  public static void main(String[] args) 
  {
     String fileName = FileChooser.pickAFile();
     Picture pictObj = new Picture(fileName);
     pictObj.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
 