import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// En anpassad KeyAdapter för att hantera ormens rörelse via tangentbordet
public class KeyboardListener extends KeyAdapter {
    private final Snake snake; // Referens till ormen som ska styras

    // Konstruktor som tar en orminstans
    public KeyboardListener(Snake snake) {
        this.snake = snake;
    }

    // Hanterar tangenttryckningar för att ändra riktning på ormen
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    snake.setDirection(Snake.Direction.UP);    break;
            case KeyEvent.VK_DOWN:  snake.setDirection(Snake.Direction.DOWN);  break;
            case KeyEvent.VK_LEFT:  snake.setDirection(Snake.Direction.LEFT);  break;
            case KeyEvent.VK_RIGHT: snake.setDirection(Snake.Direction.RIGHT); break;
        }
    }
}
