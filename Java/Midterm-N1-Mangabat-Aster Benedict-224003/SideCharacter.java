import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the character drawing facing right. This is achieved
 * through the use of multiple Square and Triangle objects.
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

public class SideCharacter implements DrawingObject{
    private double x, y, size, getx, gety;
    private double initx, inity;
    private Square body, tailConnection, tailBody, eyeBlack;
    private Triangle innerFire, outerFire;
    AffineTransform transform = new AffineTransform();

    /**
     * Constructor for the class. Initializes the initial values based on the parameters given.
     * @param x contains the initial value for the x position
     * @param y contains the initial value for the y position
     * @param size contains the initial value for the size
     */
    public SideCharacter(double x, double y, double size){
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

        body = new Square(x-x*0.113,y+y*0.838+y*0.005,size*0.32,size-size*0.26, Color.decode("#ff7d3e"));
        body.draw(g2d);

        tailConnection = new Square(x-size*0.448,y+size*1.63,size*0.17,size*0.1, Color.decode("#ff7d3e"));
        tailConnection.draw(g2d);
        
        tailBody = new Square(x-size*0.45,y+size*1.32,size*0.1,size*0.34, Color.decode("#ff7d3e"));
        tailBody.draw(g2d);

        innerFire = new Triangle(x-size*0.48, y+size*1.1, size*0.2, Color.decode("#de2000"));
        innerFire.draw(g2d);
        
        outerFire = new Triangle(x-size*0.44, y+size*1.15, size*0.12, Color.decode("#ffff00"));
        outerFire.draw(g2d);

        eyeBlack = new Square(x-size*0.1*0.6,y+size*1.32,size*0.1,size*0.17, Color.BLACK);
        eyeBlack.draw(g2d);

        eyeBlack = new Square(x-size*0.1*0.1,y+size*1.32,size*0.05,size*0.05, Color.WHITE);
        eyeBlack.draw(g2d);
        
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
        return "Side Character";
    }
}