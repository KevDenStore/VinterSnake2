import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

// Klassen för ormen i spelet
public class Snake extends GameObject {
    private ArrayList<Point> body; // Ormens kropp representerad av en lista med punkter
    private Direction direction; // Nuvarande riktning för ormens rörelse
    private boolean growing = false; // Om ormen är i växande tillstånd

    // Enum för att definiera möjliga rörelseriktningar
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(int x, int y, int initialSize) {
        super(x, y);
        body = new ArrayList<>();
        direction = Direction.RIGHT; // Standardriktning vid start
        for (int i = 0; i < initialSize; i++) {
            body.add(new Point(x - i * 20, y)); // Skapar ormens kropp
        }
    }

    public void setDirection(Direction newDirection) {
        // Ändrar inte riktning om det är motsatt till nuvarande riktning
        if (Math.abs(direction.ordinal() - newDirection.ordinal()) % 2 != 0) {
            this.direction = newDirection;
        }
    }

    // Ormens huvud är alltid den första punkten i listan
    public Point getHead() {
        return body.get(0);
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    // Flyttar ormen i den aktuella riktningen
    public void move() {
        Point head = getHead();
        Point newHead = new Point(head);

        switch (direction) {
            case UP:    newHead.y -= 20; break;
            case DOWN:  newHead.y += 20; break;
            case LEFT:  newHead.x -= 20; break;
            case RIGHT: newHead.x += 20; break;
        }

        body.add(0, newHead); // Lägger till nytt huvud i början av listan
        if (!growing) {
            body.remove(body.size() - 1); // Tar bort sista segmentet om ormen inte växer
        }
        growing = false; // Återställer växande tillståndet
    }

    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    // Ritar ut ormen på spelbrädet
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point segment : body) {
            g.fillRect(segment.x, segment.y, 20, 20); // Ritar varje segment av ormen
        }
    }
}
