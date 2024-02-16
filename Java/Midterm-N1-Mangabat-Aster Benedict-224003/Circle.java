import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Circle. This is responsible for drawing the basic shape, Circle through the use of Ellipse2D.
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

public class Circle implements DrawingObject{
    private double x, y, initx, inity, size;
    private Color color;
    
    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     * @param color contains the color of the drawing
     */
    public Circle(double x, double y, double size, Color color){
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        initx = x;
        inity = y;
    }
    
    /**
     * Responsible for drawing the shape. Uses Ellipse2D and the values given in the parameter when the shape was initialized.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();

        Ellipse2D.Double circle = new Ellipse2D.Double(x,y,size,size);        
        g2d.setColor(color);           
        g2d.fill(circle);                 
        g2d.setTransform(reset);
    }

    /**
     * Used for adjusting the x value.
     * X is the one that is added since this is not a composite shape.
     */
    public void adjustX(double amount){
        x+=amount;
    }

    /**
     * Used for adjusting the y value.
     * Y is the one that is added since this is not a composite shape.
     */
    public void adjustY(double amount){
        y+=amount;
    }

    /**
     * Returns the current X value
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the current Y value
     */
    public double getY(){
        return y;
    }

    /**
     * Used for resetting the x and y positions.
     * 1080 is added in the X value so that when the Shape (Sun and Moon) is reset, the new starting position
     * is at the far right making it look like it just completed a loop. 
     */
    public void reset(){
        x = initx+1080;
        y = inity;
    }

    /**
     * Returns the category/name for the class
     */
    public String getName(){
        return "Circle";
    }
}
