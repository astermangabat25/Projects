import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * This is the class that extends JComponent and overrides the paintComponent method
 * so that the drawing can be made. This is responsible for putting the graphics in an arraylist,
 * drawing the graphics themselves, showing the right side of the graphics(character facing right or left, fire attack facing right or left),
 * as well as giving the arrayList to other classes.
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
public class SceneCanvas extends JComponent{
  private int width;
  private int height;
  private ArrayList<DrawingObject> list;
  private Color backgroundColor;
  private boolean show = true;
  private boolean facingRight = true;
  private boolean launchAttack = false;
  private boolean lastFacedRight = true;

  /**
   * Constructor for the class. Initializes the width and height of the drawing area.
   * Adds the graphics to the arrayList, sets the initial background color
   * @param w Contains the width of the drawing area
   * @param h Contains the height of the drawing area
   */
  public SceneCanvas(int w, int h) {
    width = w;
    height = h;

    setPreferredSize(new Dimension(width, height));

    list = new ArrayList<DrawingObject>();

    backgroundColor = Color.BLUE;
    
    //Adding the Clouds
    list.add(new Cloud(width*0.7,height*0.5-width*0.3,width*0.2, Color.WHITE));
    list.add(new Cloud(width*0.1,height*0.001,width*0.25, Color.WHITE));
    list.add(new Cloud(width*0.5,height*0.0001-height*0.05,width*0.21, Color.WHITE));
    list.add(new Cloud(width*0.35,height*0.0001+height*0.1,width*0.24, Color.WHITE));
    list.add(new Cloud(width*0.1-width*0.2,height*0.0001+height*0.1,width*0.23, Color.WHITE));
    list.add(new Cloud(width*0.1-width*0.3,height*0.0001-height*0.05,width*0.22, Color.WHITE));

    //Adding the Sun to the list
    list.add(new Circle(width*0.85,height*0.3-width*0.3*0.6,width*0.1, Color.decode("#FDB813")));
    
    //Adding the Moon to the List
    list.add(new Circle(width*0.85+width,height*0.3-width*0.3*0.6,width*0.1, Color.WHITE));

    //Adding Mt. Fuji
    list.add(new Fuji(width*0.5,300,width*0.4));

    //Adding the Torii Gate
    list.add(new Torii(width*0.5+width,300,width*0.4));

    //Adding different views of the character
    list.add(new Character(width*0.5,300,width*0.2));  
    list.add(new SideCharacter(width*0.5,300,width*0.2));    
    list.add(new SideCharacter2(width*0.5,300,width*0.2));   

    //Adding a Stop Sign
    list.add(new Stop(width*0.5-width*0.8,300,width*0.3));

    
    //Setting up and adding the soil region
    list.add(new Land(width*0.5,300,width*0.3));
    list.add(new Spikes(width*0.1-width*0.9,height+height*0.1,width*0.1));   
    list.add(new Spikes(width*0.1+width*2.3,height+height*0.1,width*0.1));

    list.add(new Fire(width*0.5,300,width*0.2));

    list.add(new FireLeft(width*0.5-width*0.1,300,width*0.2));
    
  }

  /**
   * Draws the components of the ArrayList
   * Checks and displays the right view of the character and the fire
   */
  @Override
  protected void paintComponent(Graphics g) {
    
    Graphics2D g2d = (Graphics2D) g;

    RenderingHints rh = new RenderingHints(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHints(rh);
    
    Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
    g2d.setColor(backgroundColor);
    g2d.fill(background);

    for(int i=0; i<list.size();i++){

      //Conditions to show the right character view depending on the key pressed
      if(list.get(i).getName().equals("Character")){
        if(show){
          list.get(i).draw(g2d);
        } else{
          continue;
        }
      }

      if(list.get(i).getName().equals("Side Character")){
        if(!show && facingRight){
          list.get(i).draw(g2d);
        }
        else{
          continue;
        }
      }

      if(list.get(i).getName().equals("Side Character 2")){
        if(!show && !facingRight){
          list.get(i).draw(g2d);
        } else{
          continue;
        }
      }

      //condition to show the correct attacking direction
      if(list.get(i).getName().equals("Fire")){
        if(launchAttack && lastFacedRight){
          list.get(i).draw(g2d);
        }
        else{
          continue;
        }
      }

      if(list.get(i).getName().equals("Fire Left")){
        if(launchAttack && !lastFacedRight){
          list.get(i).draw(g2d);
        }
        else{
          continue;
        }
      }

      list.get(i).draw(g2d);
    }
    
  }

  /**
   * Allows the change of the color of the sky
   * @param color contains the new color of the background
   */
  public void changeColor (Color color){
    backgroundColor = color;
  }

  /**
   * Returns the arraylist containing the graphic objects
   */
  public ArrayList<DrawingObject> getObjects() {
    return list;
  }

  /**
   * Checks if the character attack is pressed
   * @param attackPressed contains the boolean checking if the attack key is pressed
   */
  public void attack(boolean attackPressed){
    launchAttack = attackPressed;
  }

  /**
   * returns the last direction that the character faced
   * if right, it is true
   * mainly used for setting the right view of the character and the attack
   */
  public boolean getLastFaced(){
    return lastFacedRight;
  }

  /**
   * gets the last position faced by the character
   * mainly used for the attacks
   * @param isRight contains if the character last faced right
   */
  public void lastFace(boolean isRight){
    lastFacedRight = isRight;
  }

  /**
   * Getting the right condition to show the correct side of the character
   * @param show contains if the character is facing front or left/right
   * @param direction contains if the character will face right or left
   */
  public void setShow(boolean show, boolean direction){
    this.show=show;
    facingRight = direction;
  }

}
