import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class PlayerFrame extends JFrame implements KeyListener{
    private int width, height;
    private Container contentPane;
    private Character me;
    private Character enemy;
    private DrawingComponent dc;
    private Timer animationTimer;
    private boolean up, down, left, right;
    private Socket socket;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private Walls walls;
    private double speed;
    private Exit exit;
    private Goal goal;
    private boolean stolen;
    private boolean firstLevel, secondLevel, thirdLevel;

    public PlayerFrame(int w, int h){
        width = w;
        height = h;
        up=false;
        down=false;
        left=false;
        right=false;
        stolen=false;
        addKeyListener(this);
        speed=5;
        firstLevel=true;
        secondLevel=false;
        thirdLevel=false;
    }
    @Override
    public void keyTyped(KeyEvent ke){

    }
    @Override
    public void keyPressed(KeyEvent ke){
       int keyCode = ke.getKeyCode();

       switch (keyCode){
            case KeyEvent.VK_W :
                up = true;
                break;
            case KeyEvent.VK_A :
                left = true;
                break;
            case KeyEvent.VK_S :
                down = true;
                break;
            case KeyEvent.VK_D :
                right = true;
                break;
       }
    }
    @Override
    public void keyReleased(KeyEvent ke){
        int keyCode = ke.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_W :
                up = false;
                break;
            case KeyEvent.VK_A :
                left = false;
                break;
            case KeyEvent.VK_S :
                down = false;
                break;
            case KeyEvent.VK_D :
                right = false;
                break;
        }
    }
    public void setUpGUI(){
        contentPane = this.getContentPane();
        if(playerID==1){
            this.setTitle("Thief");
        }
        else{
            this.setTitle("Police");
        }
        
        contentPane.setPreferredSize(new Dimension(width, height));
        createSprites();
        dc = new DrawingComponent();
        contentPane.add(dc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        setUpAnimationTimer();
    }
    //create function to generate random numbers (make sure that the numbers does not collide with the walls); set boundaries!!!

    // change orientation here

    //add solid lines as walls
    //find floor design and import
    public void createSprites(){
        exit = new Exit(0, 0, width, height);
        walls = new Walls(0, 0, width, height);
        goal = new Goal(0, 0, width, height);

        if(playerID==1){
            me = new ThiefMoving(340, 450); 
            enemy = new PoliceMoving(10, 20); 
            // enemy = new PoliceMoving(340, 400); 
        }
        else{
            enemy = new ThiefMoving(340, 450); 
            me = new PoliceMoving(10, 20); 
            // me = new PoliceMoving(340, 400); 
        }
    }
    private void setUpAnimationTimer(){
        int interval = 10;
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                if(up && !isColliding(me.getX(), me.getY()-speed, me.getWidth(), me.getHeight())){
                    me.adjustY(-speed);
                }
                if(down && !isColliding(me.getX(), me.getY()+speed, me.getWidth(), me.getHeight())){
                    me.adjustY(speed);
                }
                if(left && !isColliding(me.getX()-speed, me.getY(), me.getWidth(), me.getHeight())){
                    me.adjustX(-speed);
                }
                if(right && !isColliding(me.getX()+speed, me.getY()-speed, me.getWidth(), me.getHeight())){
                    me.adjustX(speed);
                }
                dc.repaint();
            }
        };
        animationTimer = new Timer(interval, al);
        animationTimer.start();
    }
    public boolean isColliding(double x, double y, double width, double height){
        if(firstLevel){
            for (Rectangle2D list: walls.getWalls()){
                if(list.intersects(x, y, width, height)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void isCaught(){
        while(true){
            if(!(me.getX()+me.getWidth()<=enemy.getX() || 
            me.getX() >= enemy.getX()+enemy.getWidth() || 
            me.getY()+me.getHeight()<=enemy.getY() ||
            me.getY()>= enemy.getY()+enemy.getHeight())){
                speed=0;
                if(playerID==1){
                    System.out.println("You Lost!");
                    
                }
                else{
                    System.out.println("You Win!");
                    enemy.setVisible(true);
                }
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException from isCaught()");
            }
        }
    }
    
    private void connectToServer(){
        try{
            socket = new Socket("localhost", 45371);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            if(playerID==1){
                System.out.println("You will play as the thief");
            }
            else{
                System.out.println("You will play as the police");
            }
            if(playerID==1){
                System.out.println("Waiting for Player #2 to connect...");
            }
            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();
        }
        catch(IOException e){
            System.out.println("IOException from connectToServer()");
        }
    }
    private class DrawingComponent extends JComponent{
        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            me.draw(g2d);
            enemy.draw(g2d);
            exit.draw(g2d);
            goal.draw(g2d);
            //thief
            if(playerID==1){
                walls.draw(g2d, false);
            }
            //police
            else{
                walls.draw(g2d, true);
                
                if(Math.abs(me.getX()-enemy.getX())<50 && Math.abs(me.getY()-enemy.getY())<50){
                    contentPane.setBackground(Color.red);
                    enemy.setVisible(true);
                }
                else{
                    contentPane.setBackground(Color.white);
                    enemy.setVisible(false);
                }
            }
            
        }
    }
    public void itemStolen(){
        while(true){
            if(playerID==1){
                if(goal.getObject().intersects(me.getX(), me.getY(), me.getWidth(), me.getHeight())){
                    stolen = true;
                    goal.setColor(new Color(0, 0, 0, 0));
                    break;
                }
            }
            else{
                if(goal.getObject().intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())){
                    stolen = true;
                    goal.setColor(new Color(0, 0, 0, 0));
                    break;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException from isCaught()");
            }
        }
    }
    
    public void hasPassed(){
        itemStolen();
        while(true){
            
            if(playerID==1){
                if(exit.getExit().intersects(me.getX(), me.getY(), me.getWidth(), me.getHeight()) && stolen){
                    
                    if(firstLevel &&!secondLevel &&!thirdLevel){
                        System.out.println("Level 2");
                        firstLevel=false;
                        secondLevel=true;
                        walls.setSecondLevel();
                        stolen=false;
                        itemStolen();
                    }
                    else if(secondLevel && !firstLevel && !thirdLevel){
                        System.out.println("Level 3");
                        secondLevel=false;
                        thirdLevel=true;
                        stolen=false;
                        itemStolen();
                    }
                    if(thirdLevel && !firstLevel &&!secondLevel){
                        System.out.println("You Win");
                        break;
                    }
                    

                }
            }
            else{
                if(exit.getExit().intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()) && stolen){
                    if(firstLevel &&!secondLevel &&!thirdLevel){
                        System.out.println("Level 2");
                        firstLevel=false;
                        secondLevel=true;
                        walls.setSecondLevel();
                        stolen=false;
                        itemStolen();
                    }
                    else if(secondLevel && !firstLevel && !thirdLevel){
                        System.out.println("Level 3");
                        secondLevel=false;
                        thirdLevel=true;
                        stolen=false;
                        itemStolen();
                    }
                    if(thirdLevel && !firstLevel &&!secondLevel){
                        System.out.println("You Win");
                        break;
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException from isCaught()");
            }
            

        }
    }
    public void result(){
        Thread catchThief = new Thread(new Runnable() {
            public void run(){
                isCaught();
            }
        });
        Thread thiefExit = new Thread(new Runnable() {
            public void run(){
                hasPassed();
            }
        });
        catchThief.start();
        thiefExit.start();
    }
    private class ReadFromServer implements Runnable{
        private DataInputStream dataIn;
        
        public ReadFromServer (DataInputStream in){
            dataIn = in;
            System.out.println("RFS Runnable created");
        }
        public void run(){
            try{
                while(true){
                    
                    double enemyX = dataIn.readDouble();
                    double enemyY = dataIn.readDouble();
                    if(enemy!=null){
                        enemy.setX(enemyX);
                        enemy.setY(enemyY);
                    }
                }
            }
            catch(IOException e){
                System.out.println("IOException from RFS run()");
            }
        }
        public void waitForStartMsg(){
            try{
                String startMsg = dataIn.readUTF();
                System.out.println("Message from server: "+startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
            }
            catch(IOException e){
                System.out.println("IOException from waitForStartMsg()");
            }
        }
    }
    private class WriteToServer implements Runnable{
        private DataOutputStream dataOut;
        
        public WriteToServer (DataOutputStream out){
            dataOut = out;
            System.out.println("WTS Runnable created");
        }
        public void run(){
            try{
                while(true){
                    if(me!=null){
                        dataOut.writeDouble(me.getX());
                        dataOut.writeDouble(me.getY());
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    }
                    catch(InterruptedException e){
                        System.out.println("Interrupted Exception from WTS run()");
                    }
                }
            }
            catch(IOException e){
                System.out.println("IOException from WTS run()");
            }
        }
    }
    public static void main(String[] args){
        PlayerFrame pf = new PlayerFrame(640, 480);
        pf.connectToServer();
        pf.setUpGUI();
        pf.result();
        
    }
}
