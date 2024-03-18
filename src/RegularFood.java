import java.awt.Graphics;
import java.awt.Color;

public class RegularFood extends Food {
    public RegularFood(int x, int y) {
        super(x, y); // Använder x, y för matens position
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public void effect(Snake snake) {
        snake.setGrowing(true);
    }
}