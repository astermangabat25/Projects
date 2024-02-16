/**
 * This is the class that contains the main method to start the program. This is responsible
 * for creating the instance of the SceneFrame which basically starts the entire program. Furthermore, 
 * this class is responsible for indicating the window size.
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
public class SceneStarter {
    /**
     * Main method
     * @throws Exception mainly used for the sound
     */
    public static void main(String [] args) throws Exception{
        SceneFrame sf = new SceneFrame(1024,768);
        sf.setUpGUI();
        sf.setTimers();
        System.out.println("!!!!!!!Sound Warning!!!!!!!");
        System.out.println("============================");
        System.out.println("          Controls          ");
        System.out.println("============================");
        System.out.println("a: Move to the left");
        System.out.println("d: Move to the right");
        System.out.println("w: jump");
        System.out.println("e: Take Down");
        System.out.println("space: Ember");
        System.out.println("]: Increase character speed");
        System.out.println("[: Decrease character speed");
        
    }
}
