import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class ThiefKO implements Character{
    private double x, y, width, height;
    private boolean isVisible;
    private BufferedImage image;

    public ThiefKO(double x, double y){
        this.x=x;
        this.y=y;
        width=30;
        height=60;
        isVisible=true;
    }

    public void draw(Graphics2D g2d){
        try{
            image = ImageIO.read(new File("Thief KO.png"));
            int width1 = (int)width;
            int height1 = (int)height;
            int x1 = (int)x;
            int y1 = (int)y;
            if(isVisible){
                g2d.drawImage(image, x1, y1,width1, height1, null);
            }        
        }

        catch(Exception e){
        }
    }
    public void setX(double n){
        x=n;
    }
    public void setY(double n){
        y=n;
    }
    public void adjustX(double amount){
        x+=amount;
    }
    public void adjustY(double amount){
        y+=amount;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
    public void setVisible(boolean x){
        isVisible=x;
    }
}
