import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameBoard extends JPanel {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Snake snake;
    private Food food;
    private Timer gameTimer;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;

    public GameBoard() {
        setPreferredSize(new java.awt.Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(java.awt.Color.BLACK);
        setFocusable(true);
        initializeGame();
    }

    private void initializeGame() {
        snake = new Snake(100, 100, 5);
        food = new RegularFood(300, 300); // Example starting position
        gameObjects.clear();
        gameObjects.add(snake);
        gameObjects.add(food);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:    snake.setDirection(Snake.Direction.UP);    break;
                    case KeyEvent.VK_DOWN:  snake.setDirection(Snake.Direction.DOWN);  break;
                    case KeyEvent.VK_LEFT:  snake.setDirection(Snake.Direction.LEFT);  break;
                    case KeyEvent.VK_RIGHT: snake.setDirection(Snake.Direction.RIGHT); break;
                }
            }
        });

        gameTimer = new Timer(100, e -> gameUpdate());
        gameTimer.start();
    }

    private void gameUpdate() {
        snake.move();
        if (snake.getHead().equals(food.getPosition())) {
            snake.setGrowing(true);
            // Assume generateFood() creates and places new food at a random location
            generateFood();
        }
        repaint();
    }

    private void generateFood() {
        // Logic to place food at a new location, ensuring it doesn't collide with the snake
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }
}
