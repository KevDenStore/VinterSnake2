import java.awt.Graphics; // Importerar Graphics-klassen för ritningsfunktioner.

// Basen för alla objekt som kan ritas på spelbrädet
public abstract class GameObject {
    protected int x, y; // Position för objektet på spelbrädet. 'protected' så subklasser kan komma åt direkt.

    public GameObject(int x, int y) { // Konstruktor som sätter objektets position.
        this.x = x; // Sätter x-positionen för detta objekt.
        this.y = y; // Sätter y-positionen för detta objekt.
    }

    // Varje spelobjekt måste kunna ritas, men hur det ritas är specifikt för varje subklass.
    public abstract void draw(Graphics g); // Abstrakt metod som måste implementeras av subklasser för ritning.
}

