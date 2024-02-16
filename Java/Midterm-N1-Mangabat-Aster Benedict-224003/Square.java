import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Square. This is responsible for creating and drawing the basic shape, Square.
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

public class Square implements DrawingObject{
    private double x1, y1, sizex, sizey;
    private Color color;
    private double initx, inity;

    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     * @param color contains the color of the drawing
     */
    public Square(double x1, double y1, double sizex, double sizey, Color color){
            this.x1=x1;
            this.sizex=sizex;
            this.y1=y1;
            this.sizey=sizey;
            this.color=color;
            initx = x1;
            inity = y1;
    }

    /**
     * Method for drawing the shape.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();

        Rectangle2D.Double square = new Rectangle.Double(x1, y1, sizex, sizey);        
        g2d.setColor(color);           
        g2d.fill(square);                 
        g2d.setTransform(reset);
    }

    /**
     * Adjusts the x position
     */
    public void adjustX(double amount){
        x1+=amount;
    }

    /**
     * Adjusts the y position
     */
    public void adjustY(double amount){
        y1+=amount;
    }

    /**
     * Returns the x value
     */
    public double getX(){
        return x1;
    }

    /**
     * Returns the y value
     */
    public double getY(){
        return y1;
    }

    /**
     * sets the x and y value to their initial values.
     */
    public void reset(){
        x1 = initx;
        y1 = inity;
    }
    
    /**
     * Returns the category/name for the class
     */
    public String getName(){
        return "Square";
    }
}
