import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the fire used in the Dragon Breath Attack. This is responsible for drawing the 
 * fire facing the left side through the use of multiple Triangle objects.
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

public class FireLeft implements DrawingObject{
    private double x, y, size;
    private double initx, inity;
    private Triangle innerFire, outerFire;
    AffineTransform transform = new AffineTransform();
    
    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public FireLeft(double x, double y, double size){
        this.x=x;
        this.y=y;
        this.size=size;
        initx = x;
        inity = y;
    }

    /**
     * Method for drawing the fire.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();
        g2d.setTransform(transform);

        g2d.rotate(Math.toRadians(270),x+size*0.1,y+size*1.5);
        innerFire = new Triangle(x-size*0.48+size*0.555, y+size*1.1, size*0.2, Color.decode("#de2000"));
        innerFire.draw(g2d);
        
        outerFire = new Triangle(x-size*0.44+size*0.555, y+size*1.15, size*0.12, Color.decode("#ffff00"));
        outerFire.draw(g2d);

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
        return "Fire Left";
    }
}