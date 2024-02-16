import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Goal {
    private double x, y, sizex, sizey;
    private ArrayList<Rectangle2D> list;
    private Rectangle2D square;
    private Color color;

    public Goal(double x, double y, double sizex, double sizey){
        this.x=x;
        this.y=y;
        this.sizex=sizex;
        this.sizey=sizey;
        list = new ArrayList<Rectangle2D>();
        color = Color.green;
        square = new Rectangle.Double(sizex*0.5, sizey*0.5, sizey*0.1, sizey*0.1);
        
    }
    public void draw(Graphics2D g2d){
        
        g2d.setColor(color);
        g2d.fill(square);
        
    }
    public Rectangle2D getObject(){
        return square;
    }
    public void setColor(Color color){
        this.color = color;
    }
}