import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Spikes drawing. This is done
 * through the use of multiple Triangles.
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

public class Spikes implements DrawingObject{
    private double x, y, size, gety, getx;
    private double initx, inity;
    AffineTransform transform = new AffineTransform();
    
    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public Spikes(double x, double y, double size){
        this.x=x;
        this.y=y;
        this.size=size;
        initx = x;
        inity = y;
        gety=y;
        getx=x;
    }

    /**
     * Method for drawing the shape.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();

        g2d.setTransform(transform);

        Triangle spike1 = new Triangle(x, y, size, Color.decode("#93a3ac"));
        spike1.draw(g2d);
        Triangle spike2 = new Triangle(x-size*0.5, y-size*0.1, size, Color.decode("#93a3ac"));
        spike2.draw(g2d);
        Triangle spike3 = new Triangle(x-size, y-size*0.3, size, Color.decode("#93a3ac"));
        spike3.draw(g2d);
        Triangle spike4 = new Triangle(x-size*1.5, y-size*0.5, size, Color.decode("#93a3ac"));
        spike4.draw(g2d);
        Triangle spike5 = new Triangle(x-size*2, y-size*0.3, size, Color.decode("#93a3ac"));
        spike5.draw(g2d);
        Triangle spike6 = new Triangle(x-size*2.5, y-size*0.2, size, Color.decode("#93a3ac"));
        spike6.draw(g2d);

        g2d.setTransform(reset);
    }

    /**
     * Adjusts the x position
     */
    public void adjustX(double amount){
        getx+=amount;
        transform.translate(amount, 0);
    }

    /**
     * Adjusts the y position
     */
    public void adjustY(double amount){
        gety+=amount;
        transform.translate(0, amount);
    }

    /**
     * Returns the x value
     */
    public double getX(){
        return getx;
    }

    /**
     * Returns the y value
     */
    public double getY(){
        return gety;
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
        return "BG";
    }
}