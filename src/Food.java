import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Food extends GameObject {
    public static final int SIZE = 20;

    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Default color, can be overridden
        g.fillRect(x, y, SIZE, SIZE);
    }

    // Effect of eating the food, to be implemented by subclasses
    public abstract void effect(Snake snake);

    public Point getPosition() {
        return new Point(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
