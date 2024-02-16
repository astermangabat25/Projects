import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Walls {
    private double x, y, sizex, sizey;
    private Color color;
    private ArrayList<Rectangle2D> list1, list2, list3;
    private boolean firstLevel, secondLevel, thirdLevel;

    public Walls(double x, double y, double sizex, double sizey){
        this.x=x;
        this.y=y;
        this.sizex=sizex;
        this.sizey=sizey;
        list1 = new ArrayList<Rectangle2D>();
        list2 = new ArrayList<Rectangle2D>();
        list3 = new ArrayList<Rectangle2D>();
        firstLevel=true;
        secondLevel=false;
        thirdLevel=false;
        
    }
    public void draw(Graphics2D g2d, boolean isPolice){
        if(isPolice){
            color=Color.black;
        }
        else{
            color= new Color(0, 0, 0, 0);
        }
        
        //outer
        if(firstLevel && !secondLevel && !thirdLevel){
            Rectangle2D.Double square1 = new Rectangle.Double(x, y, sizex*1, 10);
            g2d.setColor(color);
            g2d.fill(square1);
            list1.add(square1);
            Rectangle2D.Double square2 = new Rectangle.Double(x, y, 10, sizey);
            g2d.fill(square2);
            list1.add(square2);
            Rectangle2D.Double square3 = new Rectangle.Double(x, sizey*0.98, sizex*0.5, 10);
            g2d.fill(square3);
            list1.add(square3);
            Rectangle2D.Double square4 = new Rectangle.Double(sizex*0.6, sizey*0.98, sizex*0.4, 10);
            g2d.fill(square4);
            list1.add(square4);
            Rectangle2D.Double square5 = new Rectangle.Double(sizex*0.99, y, 10, sizey);
            g2d.fill(square5);
            list1.add(square5);
            
            //lower right room
            Rectangle2D.Double square6 = new Rectangle.Double(sizex*0.75, sizey*0.93, 10, sizey*0.05);
            g2d.fill(square6);
            list1.add(square6);
            Rectangle2D.Double square7 = new Rectangle.Double(sizex*0.75, sizey*0.65, 10, sizey*0.13);
            g2d.fill(square7);
            list1.add(square7);
            Rectangle2D.Double square8 = new Rectangle.Double(sizex*0.75, sizey*0.45, 10, sizey*0.05);
            g2d.fill(square8);
            list1.add(square8);
            Rectangle2D.Double square9 = new Rectangle.Double(sizex*0.75, sizey*0.45, sizex*0.1, 10);
            g2d.fill(square9);
            list1.add(square9);
            Rectangle2D.Double square10 = new Rectangle.Double(sizex*0.92, sizey*0.45, sizex*0.08, 10);
            g2d.fill(square10);
            list1.add(square10);

            //cross upper right
            Rectangle2D.Double square11 = new Rectangle.Double(sizex*0.79, sizey*0.22, sizex*0.1, 10);
            g2d.fill(square11);
            list1.add(square11);
            Rectangle2D.Double square12 = new Rectangle.Double(sizex*0.83, sizey*0.17, 10, sizey*0.12);
            g2d.fill(square12);
            list1.add(square12);
            
            //cross upper right (left)
            Rectangle2D.Double square13 = new Rectangle.Double(sizex*0.59, sizey*0.22, sizex*0.1, 10);
            g2d.fill(square13);
            list1.add(square13);
            Rectangle2D.Double square14 = new Rectangle.Double(sizex*0.63, sizey*0.17, 10, sizey*0.12);
            g2d.fill(square14);
            list1.add(square14);

            //cross lower right (left)
            Rectangle2D.Double square15 = new Rectangle.Double(sizex*0.59, sizey*0.62, sizex*0.1, 10);
            g2d.fill(square15);
            list1.add(square15);
            Rectangle2D.Double square16 = new Rectangle.Double(sizex*0.63, sizey*0.57, 10, sizey*0.12);
            g2d.fill(square16);
            list1.add(square16);

            Rectangle2D.Double square17 = new Rectangle.Double(sizex*0.29, sizey*0.42, sizex*0.1, 10);
            g2d.fill(square17);
            list1.add(square17);
            Rectangle2D.Double square18 = new Rectangle.Double(sizex*0.33, sizey*0.37, 10, sizey*0.12);
            g2d.fill(square18);
            list1.add(square18);
            
            //room lower left
            Rectangle2D.Double leftRoom1 = new Rectangle.Double(sizex*0.2, sizey*0.93, 10, sizey*0.05);
            g2d.fill(leftRoom1);
            list1.add(leftRoom1);
            Rectangle2D.Double leftRoom2 = new Rectangle.Double(sizex*0.2, sizey*0.6, 10, sizey*0.17);
            g2d.fill(leftRoom2);
            list1.add(leftRoom2);
            Rectangle2D.Double leftRoom3 = new Rectangle.Double(sizex*0.2, sizey*0.4, 10, sizey*0.05);
            g2d.fill(leftRoom3);
            list1.add(leftRoom3);

            //room lower left (right)
            Rectangle2D.Double leftRoom4 = new Rectangle.Double(sizex*0.2, sizey*0.65, sizex*0.1, 10);
            g2d.fill(leftRoom4);
            list1.add(leftRoom4);
            Rectangle2D.Double leftRoom5 = new Rectangle.Double(sizex*0.4, sizey*0.65, sizex*0.05, 10);
            g2d.fill(leftRoom5);
            list1.add(leftRoom5);
            Rectangle2D.Double leftRoom6 = new Rectangle.Double(sizex*0.45, sizey*0.65, 10, sizey*0.35);
            g2d.fill(leftRoom6);
            list1.add(leftRoom6);

            //room upper left
            Rectangle2D.Double leftRoom7 = new Rectangle.Double(x, sizey*0.4, sizex*0.13, 10);
            g2d.fill(leftRoom7);
            list1.add(leftRoom7);
            Rectangle2D.Double leftRoom8 = new Rectangle.Double(sizex*0.2, sizey*0.185, 10, sizey*0.05);
            g2d.fill(leftRoom8);
            list1.add(leftRoom8);
            Rectangle2D.Double leftRoom9 = new Rectangle.Double(sizex*0.2, y, 10, sizey*0.05);
            g2d.fill(leftRoom9);
            list1.add(leftRoom9);

            //room upper left (right)
            Rectangle2D.Double leftRoom10 = new Rectangle.Double(sizex*0.2, sizey*0.2, sizex*0.1, 10);
            g2d.fill(leftRoom10);
            list1.add(leftRoom10);
            Rectangle2D.Double leftRoom11 = new Rectangle.Double(sizex*0.4, sizey*0.2, sizex*0.08, 10);
            g2d.fill(leftRoom11);
            list1.add(leftRoom11);
            Rectangle2D.Double leftRoom12 = new Rectangle.Double(sizex*0.48, y, 10, sizey*0.222);
            g2d.fill(leftRoom12);
            list1.add(leftRoom12);
        }
    }

    public ArrayList<Rectangle2D> getWalls(){
        if(firstLevel){
            return list1;
        }
        else if(secondLevel){
            return list2;
        }
        else{
            return list3;
        }
    }
    public void setFirstLevel(){
        firstLevel=true;
        secondLevel=false;
        thirdLevel=false;
    }
    public void setSecondLevel(){
        firstLevel=false;
        secondLevel=true;
        thirdLevel=false;
    }
    public void setThirdLevel(){
        secondLevel=false;
        thirdLevel=true;
        firstLevel=false;
    }
}
