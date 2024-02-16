import java.awt.*;

/**
 * This is the interface for the classes that draw graphics so that
 * they can be grouped in the ArrayList. This is used mainly for the animation of the graphics objects.
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

public interface DrawingObject {
    public void draw(Graphics2D g2d);
    public void adjustX(double amount);
    public void adjustY(double amount);
    public double getX();
    public double getY();
    public void reset();
    public String getName();
}
