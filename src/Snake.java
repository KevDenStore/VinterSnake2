import java.awt.Color; // Importerar Color-klassen för färgsättning.
import java.awt.Graphics; // Importerar Graphics-klassen för grafisk rendering.
import java.awt.Point; // Importerar Point-klassen för att representera ormens segmentpositioner.
import java.util.ArrayList; // Importerar ArrayList-klassen för att hantera ormens segment.

// Klassen för ormen i spelet
public class Snake extends GameObject {
    private ArrayList<Point> body; // Ormens kropp representerad av en lista med punkter.
    private Direction direction; // Nuvarande riktning för ormens rörelse.
    private boolean growing = false; // Flagga för om ormen är i växande tillstånd.

    // Enum för att definiera möjliga rörelseriktningar.
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(int x, int y, int initialSize) {
        super(x, y); // Anropar GameObject-konstruktorn.
        body = new ArrayList<>(); // Initierar kroppens lista.
        direction = Direction.RIGHT; // Sätter standardriktning till höger.
        // Initierar ormens kropp baserat på angiven startstorlek.
        for (int i = 0; i < initialSize; i++) {
            body.add(new Point(x - i * 20, y)); // Lägger till segment i ormens kropp.
        }
    }

    public void setDirection(Direction newDirection) {
        // Kontrollerar att den nya riktningen inte är motsatt till den nuvarande för att undvika att ormen går in i sig själv.
        if (Math.abs(direction.ordinal() - newDirection.ordinal()) % 2 != 0) {
            this.direction = newDirection;
        }
    }

    public Point getHead() { // Returnerar positionen för ormens huvud.
        return body.get(0);
    }

    public ArrayList<Point> getBody() { // Returnerar hela kroppen.
        return body;
    }

    public void move() { // Flyttar ormen i den angivna riktningen.
        Point head = getHead(); // Hämtar nuvarande huvud.
        Point newHead = new Point(head); // Skapar en kopia av huvudet för att flytta.

        // Flyttar det nya huvudet baserat på riktningen.
        switch (direction) {
            case UP:    newHead.y -= 20; break;
            case DOWN:  newHead.y += 20; break;
            case LEFT:  newHead.x -= 20; break;
            case RIGHT: newHead.x += 20; break;
        }

        body.add(0, newHead); // Lägger till det nya huvudet i början av listan.
        if (!growing) {
            body.remove(body.size() - 1); // Tar bort det sista segmentet om ormen inte växer.
        }
        growing = false; // Återställer växttillståndet till false.
    }

    public void setGrowing(boolean growing) { // Sätter om ormen ska växa eller inte.
        this.growing = growing;
    }

    @Override
    public void draw(Graphics g) { // Ritar ut ormen på spelbrädet.
        g.setColor(Color.GREEN); // Ställer in färgen för ormens segment.
        for (Point segment : body) {
            g.fillRect(segment.x, segment.y, 20, 20); // Ritar varje segment av ormen.
        }
    }
}
