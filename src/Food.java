import java.awt.Point;
import java.awt.Graphics;

public abstract class Food extends GameObject {
    public static final int SIZE = 20;

    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        // Implementationen här som tidigare beskriven
    }

    // Hämtar matens position som ett Point-objekt
    public Point getPosition() {
        return new Point(x, y);
    }

    // Uppdaterar matens position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Fortsätt med den abstrakta effect metoden som subklasser måste implementera
    public abstract void effect(Snake snake);
}
