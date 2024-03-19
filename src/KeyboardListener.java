import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {
    private final Snake snake; // Referens till spelets orm

    // Konstruktor som tar emot en orm och lagrar referensen
    public KeyboardListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) { // Metoden som körs när en tangent trycks ned
        switch (e.getKeyCode()) { // Växlar baserat på vilken tangent som tryckts
            case KeyEvent.VK_UP: // Om upp-pilen trycks
                snake.setDirection(Snake.Direction.UP); // Ändra ormens riktning uppåt
                break;
            case KeyEvent.VK_DOWN: // Om ned-pilen trycks
                snake.setDirection(Snake.Direction.DOWN); // Ändra ormens riktning nedåt
                break;
            case KeyEvent.VK_LEFT: // Om vänster-pilen trycks
                snake.setDirection(Snake.Direction.LEFT); // Ändra ormens riktning åt vänster
                break;
            case KeyEvent.VK_RIGHT: // Om höger-pilen trycks
                snake.setDirection(Snake.Direction.RIGHT); // Ändra ormens riktning åt höger
                break;
        }
    }
}
