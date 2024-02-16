import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Exit {
    private double x, y, sizex, sizey;
    private ArrayList<Rectangle2D> list;
    private Rectangle2D square;
    private Color color;

    public Exit(double x, double y, double sizex, double sizey){
        this.x=x;
        this.y=y;
        this.sizex=sizex;
        this.sizey=sizey;
        list = new ArrayList<Rectangle2D>();
        square = new Rectangle.Double(sizex*0.5, sizey*0.98, sizex*0.1, 10);
        color = Color.black;
        
    }
    public void draw(Graphics2D g2d){
        
        g2d.setColor(color);
        g2d.fill(square);
        
    }

    public Rectangle2D getExit(){
        return square;
    }
}
