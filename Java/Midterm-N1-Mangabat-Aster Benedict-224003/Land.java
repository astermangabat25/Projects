import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the surface (land) drawing. This is responsible for drawing the grass land and the soil which can be
 * seen on the bottom of the screen. This is done through the combination of Line objects.
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

public class Land implements DrawingObject{
    private double x, y, size, gety, getx;
    private double initx, inity;
    AffineTransform transform = new AffineTransform();
    
    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public Land(double x, double y, double size){
        this.x=x;
        this.y=y;
        this.size=size;
        initx = x;
        inity = y;
        gety=y;
        getx=x;
    }

    /**
     * Method for drawing the fire.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();

        g2d.setTransform(transform);

        Line grassLand = new Line(x-size*3.9, y+size*1.335, x+size*5.3, y+size*1.335, 20, Color.GREEN);
        grassLand.draw(g2d);
        Line soil = new Line(x-size*3.855, y+size*1.45, x+size*5.255, y+size*1.45, 50, Color.decode("#8b624c"));
        soil.draw(g2d);

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