import java.awt.Color;
import java.awt.Graphics; // Importerar Graphics-klassen för att rita grafiska element.
import java.awt.Point; // Importerar Point-klassen som representerar en punkt (x, y) i ett koordinatsystem.

public abstract class Food extends GameObject { // Food är en abstrakt subklass till GameObject.
    public static final int SIZE = 20; // Konstant som definierar storleken på matobjektet.

    public Food(int x, int y) { // Konstruktor som tar positionen för maten.
        super(x, y); // Anropar GameObject-konstruktorn med x och y positioner.
    }

    @Override
    public void draw(Graphics g) { // Implementerar ritningsmetoden från GameObject.
        g.setColor(Color.RED); // Ställer in färgen till röd som standard, kan ändras av subklasser.
        g.fillRect(x, y, SIZE, SIZE); // Ritar en fyrkant som representerar maten.
    }

    // Abstrakt metod som definierar effekten av att äta maten. Måste implementeras av subklasser.
    public abstract void effect(Snake snake);

    public Point getPosition() { // Metod för att få matens position som ett Point-objekt.
        return new Point(x, y); // Returnerar ett nytt Point-objekt med matens position.
    }

    public void setPosition(int x, int y) { // Metod för att sätta matens position.
        this.x = x; // Uppdaterar x-positionen.
        this.y = y; // Uppdaterar y-positionen.
    }
}

