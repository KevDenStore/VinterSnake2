import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake extends GameObject {
    private ArrayList<Point> body;
    private Direction direction;
    private boolean growing = false;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(int x, int y, int initialSize) {
        super(x, y);
        body = new ArrayList<>();
        direction = Direction.RIGHT;
        for (int i = 0; i < initialSize; i++) {
            body.add(new Point(x - i * 20, y));
        }
    }

    public void setDirection(Direction newDirection) {
        if ((direction == Direction.UP && newDirection != Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection != Direction.UP) ||
                (direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection != Direction.LEFT)) {
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
        Point head = getHead();
        Point newHead = new Point(head);
        switch (direction) {
            case UP:
                newHead.y -= 20;
                break;
            case DOWN:
                newHead.y += 20;
                break;
            case LEFT:
                newHead.x -= 20;
                break;
            case RIGHT:
                newHead.x += 20;
                break;
        }
        body.add(0, newHead);
        if (!growing) {
            body.remove(body.size() - 1);
        } else {
            growing = false;
        }
    }

    public void setGrowing(boolean growing) {
        this.growing = growing;
    }

    public boolean intersects(Food food) {
        Point head = getHead();
        return head.equals(food.getPosition());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point segment : body) {
            g.fillRect(segment.x, segment.y, 20, 20);
        }
    }
}
