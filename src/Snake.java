import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Snake extends GameObject {
    private ArrayList<Point> body;
    private int bodySize;
    private Direction direction;
    private boolean growing = false;
    private boolean rainbow;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(int x, int y, int initialSize) {
        super(x, y);
        this.bodySize = 20;
        this.body = new ArrayList<>();
        this.direction = Direction.RIGHT;

        for (int i = 0; i < initialSize; i++) {
            body.add(new Point(x, y - i * bodySize));
        }
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection; // Enkel riktningsändring
        if (Math.abs(direction.ordinal() - newDirection.ordinal()) % 2 != 0) {
            this.direction = newDirection;
        }
    }

    public Point getHead() {
        return body.get(0);
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public void move() {
        Point newHead = new Point(getHead());
        switch (direction) {
            case UP: newHead.y -= bodySize; break;
            case DOWN: newHead.y += bodySize; break;
            case LEFT: newHead.x -= bodySize; break;
            case RIGHT: newHead.x += bodySize; break;
        }

        body.add(0, newHead); // Lägg till nytt huvud
        if (growing) {
            growing = false; // Återställ växtflaggan
        } else {
            body.remove(body.size() - 1); // Ta bort sista segmentet endast om ormen inte växer
        }
    }

    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow;
    }

    @Override
    public void draw(Graphics g) {
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
        int colorIndex = 0;

        for (Point segment : body) {
            if (rainbow) {
                g.setColor(colors[colorIndex % colors.length]);
                colorIndex++;
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(segment.x, segment.y, bodySize, bodySize);
        }
    }
}