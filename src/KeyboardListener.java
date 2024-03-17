import java.awt.event.KeyAdapter; //Gör KeyAdapter tillgänglig för tangenthändelser
import java.awt.event.KeyEvent; //Gör KeyEvent tillgänglig för detaljer om tangenttryck.


// En anpassad KeyAdapter för att hantera ormens rörelse via tangentbordet
public class KeyboardListener extends KeyAdapter {
    private final Snake snake; // Referens till ormen som ska styras

    // Konstruktor som tar en orminstans
    public KeyboardListener(Snake snake) {
        this.snake = snake; //Konstruktor som kopplar en Snake-instans till lyssnaren.
    }

    // Hanterar tangenttryckningar för att ändrariktning på ormen
    @Override // Överskriver metod för att hantera tangenttryckningar.
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) { // switch funktion som Kontrollerar vilken tangent som
            // tryckts och utför åtgärd baserat på detta, exempelvis ändrar riktning på ormen.
            case KeyEvent.VK_UP:    snake.setDirection(Snake.Direction.UP);    break;
            case KeyEvent.VK_DOWN:  snake.setDirection(Snake.Direction.DOWN);  break;
            case KeyEvent.VK_LEFT:  snake.setDirection(Snake.Direction.LEFT);  break;
            case KeyEvent.VK_RIGHT: snake.setDirection(Snake.Direction.RIGHT); break;
        }
    }
}
