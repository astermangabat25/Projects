import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Inverted Triangle. This is responsible for drawing the Inverted Triangle basic shape.
 *
 * @author Aster Benedict A. Mangabat (224003)
 * @version March 6, 2023
 *
 */
/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/

public class InvertedTriangle implements DrawingObject{
    double x, y, size, rotate;
    Color color;
    double initx, inity;

    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     * @param color contains the color of the drawing
     */
    public InvertedTriangle(double x, double y, double size, double rotate, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.rotate = rotate;
        initx = x;
        inity = y;
    }

    /**
     * Method for drawing the fire.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();
        g2d.rotate(Math.toRadians(rotate),x+size*.5,y+size*.5);
        Path2D.Double mid = new Path2D.Double();
        mid.moveTo(x+size*.35,y+size*1.05);  
        mid.lineTo(x+size*.65, y+size*1.05);
        mid.lineTo(x+size*.5, y+size*1.25);
        mid.closePath();  
        g2d.setPaint(color);
        g2d.fill(mid);
        g2d.draw(mid);
        g2d.setTransform(reset);
    }

    /**
     * Adjusts the x position
     */
    public void adjustX(double amount){
        x+=amount;
    }

    /**
     * Adjusts the y position
     */
    public void adjustY(double amount){
        y+=amount;
    }

    /**
     * Returns the x value
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the y value
     */
    public double getY(){
        return y;
    }

    /**
     * sets the x and y value to their initial values.
     */
    public void reset(){
        x = initx;
        y = inity;
    }
    
    /**
     * Returns the category/name for the class
     */
    public String getName(){
        return "Inverted Triangle";
    }
}
