import java.awt.*;
import java.awt.geom.*;

/**
 * This is the class for the Character facing front drawing. This is responsible for drawing
 * the character facing in front.
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

public class Character implements DrawingObject{
    private double x, y, size, getx, gety;
    private double initx, inity;
    private InvertedTriangle middleTriangle;
    private Square headRegion, leftFoot, rightFoot, body, leftEye, rightEye, leftArm, rightArm, tail;
    private Triangle innerFire, outerFire;
    AffineTransform transform = new AffineTransform();

    /**
     * Constructor for the class. This initializes the values for the x, y and size.
     * @param x contains the initial value for x
     * @param y contains the initial value for y
     * @param size contains the initial value for the size
     */
    public Character(double x, double y, double size){
        this.x=x;
        this.y=y;
        this.size=size;
        initx = x;
        inity = y;
        gety=y;
        getx=x;
    }

    /**
     * The method for drawing the objects. Squares, Triangles, and Inverted Triangles are used.
     * The mathematical computations were done so that each part is on the right place.
     */
    public void draw(Graphics2D g2d){
        AffineTransform reset = g2d.getTransform();
        g2d.setTransform(transform);
        
        headRegion = new Square(x-x*0.113,y+y*0.838+y*0.005,size*0.32,size-size*0.6, Color.decode("#ff7d3e"));
        headRegion.draw(g2d);

        leftFoot = new Square(x-x*0.113,y+y*1.29,size*0.12,size-size*0.92, Color.decode("#ff7d3e"));
        leftFoot.draw(g2d);

        rightFoot = new Square(x-x*0.033,y+y*1.29,size*0.12,size-size*0.92, Color.decode("#ff7d3e"));
        rightFoot.draw(g2d);

        body = new Square(x-x*0.113,y+y*1.113,size*0.32,size-size*0.73, Color.decode("#ff7d3e"));
        body.draw(g2d);

        middleTriangle = new InvertedTriangle(x-size*0.63, y+size*0.53, size, 0, Color.decode("#ffe38f"));
        middleTriangle.draw(g2d);

        rightEye = new Square(x-x*0.028,y+y*0.9,size*0.11,size-size*0.85, Color.BLACK);
        rightEye.draw(g2d);
        leftEye = new Square(x-x*0.115,y+y*0.9,size*0.11,size-size*0.85, Color.BLACK);
        leftEye.draw(g2d);

        rightArm = new Square(x+x*0.013,y+y*1.08,size*0.1,size-size*0.75, Color.decode("#ff7d3e"));
        rightArm.draw(g2d);
        leftArm = new Square(x-x*0.15,y+y*1.08,size*0.1,size-size*0.75, Color.decode("#ff7d3e"));
        leftArm.draw(g2d);
        
        Rectangle2D.Double eyesRight = new Rectangle.Double(x-x*0.03,y+y*0.9,size*0.05,size-size*0.93);
        g2d.setColor(Color.WHITE);
        g2d.fill(eyesRight);
        
        Rectangle2D.Double eyesLeft = new Rectangle.Double(x-x*0.09,y+y*0.9,size*0.05,size-size*0.93);
        g2d.setColor(Color.WHITE);
        g2d.fill(eyesLeft);

        tail = new Square(x-x*0.18,y+y*0.9,size*0.1,size-size*0.6, Color.decode("#ff7d3e"));
        tail.draw(g2d);
        
        innerFire = new Triangle(x-size*0.48, y+size*1.1, size*0.2, Color.decode("#de2000"));
        innerFire.draw(g2d);
        
        outerFire = new Triangle(x-size*0.44, y+size*1.15, size*0.12, Color.decode("#ffff00"));
        outerFire.draw(g2d);
        
        g2d.setTransform(reset);
    }

    /**
     * Adjusts the position of the x position of the character. 
     * Translate is done instead of the x+=amount because the class contains other objects.
     * Translate makes it so that all of the objects move together as one.
     * GetX is also added so that the x can be returned properly.
     */
    public void adjustX(double amount){
        getx+=amount;
        transform.translate(amount, 0);
    }

    /**
     * Adjusts the position of the h position of the character. 
     * Translate is done instead of the y+=amount because the class contains other objects.
     * Translate makes it so that all of the objects move together as one.
     * GetY is also added so that the y can be returned properly.
     */
    public void adjustY(double amount){
        gety+=amount;
        transform.translate(0, amount);
    }

    /**
     * Returns the current X position of the character.
     */
    public double getX(){
        return getx;
    }

    /**
     * Returns the current Y position of the character.
     */
    public double getY(){
        return gety;
    }

    /**
     * Resets the x and y position of the Character to their initial values. 
     * getx and gety is the one that is reset because they are the ones that change
     * when the character moves.
     */
    public void reset(){
        getx = initx;
        gety = inity;
    }
    /**
     * Returns the category/name of the character which is useful for finding through the use of looping
     */
    public String getName(){
        return "Character";
    }
}