import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Snake extends GameObject { // Klassen Snake representerar ormen i spelet
    private ArrayList<Point> body; // En lista som innehåller positioner för ormens kropp
    private int bodySize; // Storleken på varje segment i ormens kropp
    private Direction direction; // Ormens nuvarande riktning
    private boolean growing = false; // En flagga som indikerar om ormen ska växa
    private boolean rainbow; // En flagga för att aktivera regnbågseffekt på ormen

    public enum Direction { // Enum för olika riktningar ormen kan ha
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(int x, int y, int initialSize) { // Konstruktor
        super(x, y); // Anropar GameObjects konstruktor med startposition
        this.bodySize = 20; // Sätter storleken på ormens segment
        this.body = new ArrayList<>(); // Initialiserar listan för ormens kropp
        this.direction = Direction.RIGHT; // Sätter start riktning till höger

        for (int i = 0; i < initialSize; i++) { // Loop för att skapa ormens kropp baserat på initialSize
            body.add(new Point(x - i * bodySize, y)); // Lägger till segment i ormens kropp
        }
    }

    public void setDirection(Direction newDirection) { // Metod för att ändra riktning på ormen
        // Förhindrar att ormen rör sig direkt motsatt mot sin nuvarande riktning
        if (Math.abs(direction.ordinal() - newDirection.ordinal()) % 2 != 0) {
            this.direction = newDirection;
        }
    }

    public Point getHead() { // Hämtar huvudets position
        return body.get(0);
    }

    public ArrayList<Point> getBody() { // Hämtar hela kroppen
        return body;
    }

    public void move() { // Flyttar ormen
        Point newHead = new Point(getHead()); // Skapar en ny huvudpunkt baserat på nuvarande huvudposition
        // Ändrar position baserat på riktning
        switch (direction) {
            case UP: newHead.y -= bodySize; break;
            case DOWN: newHead.y += bodySize; break;
            case LEFT: newHead.x -= bodySize; break;
            case RIGHT: newHead.x += bodySize; break;
        }

        body.add(0, newHead); // Lägger till det nya huvudet i början av listan (kroppen)
        if (!growing) { // Om ormen inte ska växa, ta bort det sista segmentet
            body.remove(body.size() - 1);
        } else {
            growing = false; // Återställer växande statusen efter att ha lagt till ett nytt segment
        }
    }

    public void setGrowing(boolean growing) { // Sätter om ormen ska växa eller inte
        this.growing = growing;
    }

    @Override
    public void draw(Graphics g) { // Ritar ut ormen
        // Loopar igenom varje segment av ormen och ritar ut det
        for (Point segment : body) {
            g.setColor(Color.GREEN); // Sätter färgen på segmenten
            g.fillRect(segment.x, segment.y, bodySize, bodySize); // Ritar ett rektangulärt segment
        }
    }
}
