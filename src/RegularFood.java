import java.awt.Color; // Importerar Color-klassen för att hantera färger.
import java.awt.Graphics; // Importerar Graphics-klassen för ritning.

public class RegularFood extends Food { // Definierar klassen RegularFood som ärver från Food.
    public RegularFood(int x, int y) { // Konstruktor som tar positionen för maten.
        super(x, y); // Anropar Food-klassens konstruktor med position.
    }

    @Override
    public void effect(Snake snake) { // Implementerar effekten av att äta denna mat.
        snake.setGrowing(true); // Sätter ormen i växande tillstånd.
    }

    @Override
    public void draw(Graphics g) { // Implementerar hur maten ritas ut.
        g.setColor(Color.RED); // Ställer in färgen till röd för ritningen.
        g.fillRect(x, y, SIZE, SIZE); // Ritar en fyrkant på matens position med given storlek.
    }
}

