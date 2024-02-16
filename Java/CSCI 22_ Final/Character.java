import java.awt.*;

public interface Character{
    public void draw(Graphics2D g2d);
    public void setX(double amount);
    public void setY(double amount);
    public void adjustX(double amount);
    public void adjustY(double amount);
    public double getX();
    public double getY();
    public double getWidth();
    public double getHeight();
    public void setVisible(boolean visible);
}
