import java.awt.Graphics;

// Basen för alla objekt som kan ritas på spelbrädet
public abstract class GameObject {
    protected int x, y; // Position för objektet på spelbrädet

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Varje spelobjekt måste kunna ritas, men hur det ritas är specifikt för varje subklass
    public abstract void draw(Graphics g);
}
