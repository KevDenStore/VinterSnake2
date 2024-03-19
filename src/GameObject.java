import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y; // Positionen för spelobjektet

    // Konstruktor som initierar positionen för spelobjektet
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // En abstrakt metod som måste implementeras av subklasserna för att rita objektet
    public abstract void draw(Graphics g);
}
