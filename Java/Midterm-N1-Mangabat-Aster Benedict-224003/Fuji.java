import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Mt. Fuji drawing. This is responsible for drawing the Mt. Fuji drawing
 * through the use of Triangle, InvertedTriangle, and Square objects.
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

public class Fuji implements DrawingObject{
    double x, y, size, getx, gety;
    double initx, inity;
    AffineTransform transform = new AffineTransform();

    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public Fuji(double x, double y, double size){
        this.x = x;
        this.y = y;
        this.size = size;
        initx = x;
        inity = y;
        getx=x;
        gety=y;
    }

    /**
     * Method for drawing the shape.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();

        g2d.setTransform(transform);

        //the main structure of Mt. Fuji
        Triangle base = new Triangle(x, y, size, Color.decode("#354379"));
        base.draw(g2d);

        //the flat area on top
        Square top = new Square(x+size*0.45,y-size*0.26,size*0.1,size-size*0.82, Color.decode("#354379"));
        top.draw(g2d);

        //attempting to recreate the white area surrounding the crater
        InvertedTriangle top1 = new InvertedTriangle(x+size*0.35, y-size*0.43, size*0.3, 0, Color.WHITE);
        top1.draw(g2d);
        InvertedTriangle top2 = new InvertedTriangle(x+size*0.46, y-size*0.26, size*0.2, 50, Color.WHITE);
        top2.draw(g2d);
        InvertedTriangle top3 = new InvertedTriangle(x+size*0.34, y-size*0.26, size*0.2, -50, Color.WHITE);
        top3.draw(g2d);
        InvertedTriangle top4 = new InvertedTriangle(x+size*0.3, y-size*0.5, size*0.4, 0, Color.WHITE);
        top4.draw(g2d);

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
