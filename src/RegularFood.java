import java.awt.Graphics;
import java.awt.Color;

public class RegularFood extends Food { // RegularFood är en konkret subklass till Food som representerar vanlig mat

    public RegularFood(int x, int y) { // Konstruktor för RegularFood
        super(x, y); // Anropar Foods konstruktor med positionen för maten
    }

    @Override
    public void draw(Graphics g) { // Implementerar draw-metoden för att rita ut maten
        g.setColor(Color.RED); // Sätter färgen på maten till röd
        g.fillRect(x, y, SIZE, SIZE); // Ritar ut maten som en röd kvadrat
    }

    @Override
    public void effect(Snake snake) { // Definierar effekten av att äta den här maten
        snake.setGrowing(true); // Får ormen att växa
    }
}
