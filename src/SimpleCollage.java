/* Filename: SimpleCollage.java
 * Name: Dante Burrellmedley
 *
 * Date: 11/30/25
 *
 * Description: This class is designed to ask the user to pick a file
 * makes 3 copies of the picture
 * applies 3 different filters (grayscale, negative, mirrorVertical)
 * builds a collage on a blank canvas and saves it
 */

public class SimpleCollage
{
    //The line below is magic, you don't have to understand it (yet)
    public static void main (String[] args)
    {
        //Let the user pick a file and create original picture
        String fileName = FileChooser.pickAFile();
        Picture original = new Picture(fileName);
        
        //Show the original once
        original.explore();
        
        //Create 3 copies of the original picutre
        Picture pic1 = new Picture(original); // will become grayscale
        Picture pic2 = new Picture(original); // will become negative
        Picture pic3 = new Picture(original); // will be mirrored
        
        //Apply filters to each copy
        pic1.grayscale(0, pic1.getPixels().length - 1);
        pic2.negative(0, pic2.getPixels().length - 1);
        pic3.mirrorVertical();
        
        //Create a blank canvas - same height, 3x width
        int width = original.getWidth();
        int height = original.getHeight();
        Picture canvas = new Picture(width * 3, height);
        
        //Build the collage on the blank canvas
        canvas.collage(pic1, pic2, pic3);
        
        //Show and save final collage
        canvas.explore();
        canvas.write("./collage_gray_negative_mirror.jpg");
    }
}