import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Clouds drawing. This is responsible for drawing the clouds and adjusting its x and y values, resetting their position and
 * returning the name/category for the class.
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

public class Cloud implements DrawingObject{
    private double x, y, size, initx, inity;
    private Color color;

    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     * @param color contains the color of the drawing
     */
    public Cloud(double x, double y, double size, Color color){
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
        initx = x;
        inity = y;
    }

    /**
     * Responsible for creating the clouds.
     * 4 ellipses were used to draw the clouds.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();

        double change = size*0.3;   //setting a change variable to make the cloud spacing more uniform

        Ellipse2D.Double cloud1 = new Ellipse2D.Double(x+change,y+change,size*.5,size*.3);
        Ellipse2D.Double cloud2 = new Ellipse2D.Double(x+change+change*.5,y+change-change*.5,size*.5,size*.3);
        Ellipse2D.Double cloud3 = new Ellipse2D.Double(x+change+change+change*.3,y+change-change*.1,size*.5,size*.3);
        Ellipse2D.Double cloud4 = new Ellipse2D.Double(x+change+change,y+change+change*.3,size*.5,size*.3);

        g2d.setColor(color);

        g2d.fill(cloud1);
        g2d.fill(cloud2);
        g2d.fill(cloud3);
        g2d.fill(cloud4);

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
     * 1080 is added to x to achieve the "looping" effect
     */
    public void reset(){
        x = initx+1080; 
        y = inity;
    }
    
    /**
     * Returns the category/name for the class
     */
    public String getName(){
        return "Cloud";
    }
}
