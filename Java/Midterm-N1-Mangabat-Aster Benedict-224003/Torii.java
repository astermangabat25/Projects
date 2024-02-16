import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Torii Gate drawing. This is done through the use multiple Squares with differing sizes.
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

public class Torii implements DrawingObject{
    double x, y, size, getx, gety;
    double initx, inity;
    AffineTransform transform = new AffineTransform();

    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public Torii(double x, double y, double size){
        this.x = x;
        this.y = y;
        this.size = size;
        initx = x;
        inity = y;
        getx = x;
        gety = y;
    }

    /**
     * Method for drawing the shape.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();
        g2d.setTransform(transform);
        Square top = new Square (x, y, size, size*0.1, Color.RED);
        top.draw(g2d);

        Square top2 = new Square (x, y+size*0.25, size, size*0.1*0.6, Color.RED);
        top2.draw(g2d);

        Square middleTop = new Square (x+size*0.45, y+size*0.01, size*0.1, size*0.28, Color.RED);
        middleTop.draw(g2d);
        
        Square leftLeg = new Square (x+size*0.1, y+size*0.01, size*0.1, size, Color.RED);
        leftLeg.draw(g2d);

        Square rightLeg = new Square (x+size*0.8, y+size*0.01, size*0.1, size, Color.RED);
        rightLeg.draw(g2d);

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
        return x;
    }

    /**
     * Adjusts the y position
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
        return "BG";
    }
}
