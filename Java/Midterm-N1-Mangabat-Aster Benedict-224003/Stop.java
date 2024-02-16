import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Stop sign drawing. This is done through the use of multiple
 * Octagon objects and a Line.
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

public class Stop implements DrawingObject{
    private double x, y, size, gety, getx;
    private double initx, inity;
    AffineTransform transform = new AffineTransform();
    
    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public Stop(double x, double y, double size){
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

        Line post = new Line(x-size*1.18, y+size*0.5, x-size*1.18, y+size*1.3, 10, Color.WHITE);
        post.draw(g2d);

        Octagon outerMostSign = new Octagon(x, y, size*1.1, Color.RED);
        outerMostSign.draw(g2d);

        Octagon middleSign = new Octagon(x-size*0.16, y+size*0.06, size*0.95, Color.WHITE);
        middleSign.draw(g2d);

        Octagon innerMostSign = new Octagon(x-size*0.213, y+size*0.08, size*0.9, Color.RED);
        innerMostSign.draw(g2d);

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
     * Adjusts the y position
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