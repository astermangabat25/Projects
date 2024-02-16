import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.sound.sampled.*;

/**
 * This is the class for the frame. This is responsible for listening to the keyboard clicks, and
 * animating the graphics depending on what the user clicked. Furthermore, this class is used to initialize the frame,
 * which makes the graphics appear on a window.
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

public class SceneFrame implements ActionListener, KeyListener{

  private int width, height;
  private JFrame frame;
  private SceneCanvas sceneCanvas;
  private double amount, charSpeed;
  private boolean moveDown, once, dashBack, dashEnd;
  private ArrayList<DrawingObject> objects;
  private Timer sunTimer;
  private Timer timer;
  private Timer fireTimer;
  private Timer dashTimer;
  private AudioInputStream getAudio;
  private Clip clip;
  private InputStream is;

  /**
   * Constructor for the class. Also contains the block for starting the audio
   * @param w contains the width of the window
   * @param h contains the height of the window
   * @throws Exception is used mainly for the background music
   */
  public SceneFrame(int w, int h) throws Exception{
    width = w;
    height = h;
    frame = new JFrame();
    sceneCanvas = new SceneCanvas(width, height);
    frame.addKeyListener(this);
    once=true;
    dashEnd = true;
    moveDown=false;
    dashBack=false;
    amount=10;
    charSpeed = 10;
    
    objects = sceneCanvas.getObjects();
    
    // is = getClass().getResourceAsStream("pokemon.wav");
    // getAudio = AudioSystem.getAudioInputStream(is);
    // clip = AudioSystem.getClip();
    // clip.open(getAudio);
    // clip.loop(Clip.LOOP_CONTINUOUSLY);
    // clip.start();
    
  }

  /**
   * Method for setting the Timers
   */
  public void setTimers(){
    sunTimer = new Timer(25, this);
    sunTimer.start();

    timer = new Timer(10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i;
        amount=10;
        for(i=0; i<objects.size(); i++){
          if(objects.get(i).getName().equals("Character")){
            break;
          }
        }

        if (moveDown && once) {
          if(objects.get(i).getY()>=290){
            once=false;
            ((Timer)e.getSource()).stop();
          }
          objects.get(i).adjustY(amount);
        } else if(!moveDown && once) {
          objects.get(i).adjustY(-amount);
        }

        sceneCanvas.repaint();

        if (objects.get(i).getY() <= 170) {
            moveDown = true;
        } else if (objects.get(i).getY() > 290) {
            moveDown = false;
        }
        
      }
    });

    dashTimer = new Timer(10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i;
        amount=10;
        boolean isRight = sceneCanvas.getLastFaced();
        for(i=0; i<objects.size(); i++){
          if(objects.get(i).getName().equals("Character")){
            break;
          }
        }
        if (isRight){
          if (dashBack && dashEnd) {
            if(objects.get(i).getX()<=522){
              dashEnd=false;
              ((Timer)e.getSource()).stop();
            }
            objects.get(i).adjustX(-amount);
          } else if(!dashBack && dashEnd) {
            objects.get(i).adjustX(amount);
          }

          sceneCanvas.repaint();
          if (objects.get(i).getX() <= 512) {
            dashBack = false;
          } else if (objects.get(i).getX() >= 812) {
            dashBack = true;
          }
          
          
        }
        else if (!isRight){
          if (dashBack && dashEnd) {
            if(objects.get(i).getX()>=502){
              dashEnd=false;
              ((Timer)e.getSource()).stop();
            }
            objects.get(i).adjustX(amount);
          } else if(!dashBack && dashEnd) {
            objects.get(i).adjustX(-amount);
          }

          sceneCanvas.repaint();
          if (objects.get(i).getX() >= 512) {
            dashBack = false;
          } else if (objects.get(i).getX() <= 212) {
            dashBack = true;
          }
        }
      }
    });
    fireTimer = new Timer(10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int i;
        amount=10;
        
        for(i=0; i<objects.size();i++){
          if(objects.get(i).getName().equals("Fire") && sceneCanvas.getLastFaced()){
            break;
          }
          else if(objects.get(i).getName().equals("Fire Left") && !sceneCanvas.getLastFaced()){
            break;
          }
        }
        
        if(sceneCanvas.getLastFaced()){
          objects.get(i).adjustX(amount);
          sceneCanvas.repaint();

          if (objects.get(i).getX()>=800){
            ((Timer)e.getSource()).stop();
            objects.get(i).reset();
            sceneCanvas.attack(false);
          }
        }
        
        else if(!sceneCanvas.getLastFaced()){
          objects.get(i).adjustX(-amount);
          sceneCanvas.repaint();

          if (objects.get(i).getX()<=100){
            ((Timer)e.getSource()).stop();
            objects.get(i).reset();
            sceneCanvas.attack(false);
          }
        }
      }
    });
  }

  /**
   * Method for the animation done immediately after the program is started
   */
  @Override
  public void actionPerformed(ActionEvent e){
    int sunindex=0;
    int speed=5;
    
    for(int i=0; i<objects.size(); i++){
      if (objects.get(i).getName().equals("Circle")){
        sunindex=i;
        break;
      }
    }

    DrawingObject sun = objects.get(sunindex);

    if(sun.getX()<=-99){
      sceneCanvas.changeColor(Color.BLACK);
    }

    if(sun.getX()>=-1150){
      for (int i=0; i<=sunindex+1; i++){
        objects.get(i).adjustX(-speed);
      }
    } else{
      for (int i=0; i<=sunindex+1; i++){
        objects.get(i).reset();
      }
      sceneCanvas.changeColor(Color.BLUE);
    }

    sceneCanvas.repaint();
  }

  /**
   * One of the methods required because the class implemented KeyListener.
   * Not used in the program
   */
  @Override
  public void keyTyped(KeyEvent e){
  }
  
  /**
   * Method done if the key is pressed.
   * Triggers continuously if the key is pressed down for a long time
   */
  @Override
  public void keyPressed(KeyEvent e){
    
    once = true;
    int i;
    
    for(i=0; i<objects.size(); i++){
      if(objects.get(i).getName().equals("BG")){
        break;
      }
    }
    if(e.getKeyChar()=='d'){
      if(objects.get(i).getX()>=-988){   
        sceneCanvas.setShow(false, true);
        for(i=0; i<objects.size(); i++){
          if(objects.get(i).getName().equals("BG")){
            objects.get(i).adjustX(-charSpeed);
            
          }
        }
        sceneCanvas.lastFace(true); 
      }
    }

    else if(e.getKeyChar()=='a'){
      if(objects.get(i+5).getX()<=700){
        sceneCanvas.setShow(false, false);
        for(i=0; i<objects.size(); i++){
          if(objects.get(i).getName().equals("BG")){
            objects.get(i).adjustX(charSpeed);
            
          }
        }
        sceneCanvas.lastFace(false);
      }
    }
    else if(e.getKeyChar()==']'){
      if(charSpeed<130){
        charSpeed+=10;
      }
    }
    else if(e.getKeyChar()=='['){
      if(charSpeed>10){
        charSpeed-=10;
      }
    }
    sceneCanvas.repaint();
  }

  /**
   * Method for the animations done after the key is released
   */
  @Override
  public void keyReleased(KeyEvent e){
    once=true;
    dashEnd = true;
    sceneCanvas.setShow(true, true);
    if(e.getKeyChar()=='e'){
      dashTimer.start();
    }
    if(e.getKeyChar()=='w'){
      timer.start();
    }
    if(e.getKeyChar()==' '){
      sceneCanvas.attack(true);
      fireTimer.start();
      
    }
  }

  /**
   * Method for setting up GUI
   */
  public void setUpGUI() {
    Container contentPane = frame.getContentPane();

    contentPane.add(sceneCanvas, BorderLayout.CENTER);

    frame.setTitle("Midterm Project- Mangabat, Aster Benedict - 224003");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
