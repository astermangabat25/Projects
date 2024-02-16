import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the line. This is responsible for creating and drawing the basic shape, Line.
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

public class Line implements DrawingObject{
    private double initx, inity, finalx, finaly;
    private int thickness;
    private Color color;
    private double firstx, firsty;
    
    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     * @param color contains the color of the drawing
     */
    public Line(double x1, double y1, double x2, double y2, int thick, Color color){
        initx = x1;
        inity = y1;
        finalx = x2;
        finaly = y2;
        thickness = thick;
        this.color = color;
        initx = x1;
        inity = y1;
    }

    /**
     * Method for drawing the shape.
     */
    public void draw(Graphics2D g2d){
        Line2D.Double line = new Line2D.Double(initx, inity, finalx, finaly); 
        g2d.setColor(color);    
        g2d.setStroke(new BasicStroke(thickness));    
        g2d.draw(line);            
    }

    public void adjustX(double amount){
        initx+=amount;
    }

    public void adjustY(double amount){
        inity+=amount;
    }

    public double getX(){
        return initx;
    }

    public double getY(){
        return inity;
    }

    public void reset(){
        initx = firstx;
        inity = firsty;
    }
    
    public String getName(){
        return "Line";
    }
}
