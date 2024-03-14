import java.awt.Color;
import java.awt.Graphics;

public class RegularFood extends Food {
    public RegularFood(int x, int y) {
        super(x, y);
    }

    // Make sure this method signature exactly matches the one it overrides in Food (if Food defines it)
    // or GameObject (if it's abstract there).
    @Override
    public void effect(Snake snake) {
        // Assuming the effect of this food is to make the snake grow,
        // this method should contain logic to handle that.
        snake.setGrowing(true);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Sets the color for drawing
        g.fillRect(x, y, SIZE, SIZE); // Draws a rectangle (in this case, the food) on the screen
    }
}
