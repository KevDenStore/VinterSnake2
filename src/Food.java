import java.awt.Graphics;
import java.awt.Point;

public abstract class Food extends GameObject { // Food är en abstrakt klass som representerar mat i spelet
    public static final int SIZE = 20; // Storleken på maten

    public Food(int x, int y) { // Konstruktor som anger matens position
        super(x, y); // Anropar GameObjects konstruktor
    }

    @Override
    public void draw(Graphics g) { // En abstrakt metod för att rita maten, implementeras i subklasser
        // Ritlogik kommer att definieras i subklasserna
    }

    public Point getPosition() { // Hämtar matens position som ett Point-objekt
        return new Point(x, y);
    }

    public void setPosition(int x, int y) { // Uppdaterar matens position
        this.x = x;
        this.y = y;
    }

    public abstract void effect(Snake snake); // En abstrakt metod som definierar effekten av maten på ormen
}
