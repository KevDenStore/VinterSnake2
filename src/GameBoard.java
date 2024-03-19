import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel {
    private ArrayList<GameObject> gameObjects;
    private Snake snake;
    private Food food;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private int score;
    private Timer gameTimer;

    public GameBoard() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK); // Optionally set a background color
        initializeGame();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyboardListener(snake));
    }

    private void initializeGame() {
        score = 0;
        gameObjects = new ArrayList<>();
        snake = new Snake(100, 100, 5);
        food = new RegularFood(0, 0); // Initially place the food at the top-left corner
        gameObjects.add(snake);
        gameObjects.add(food);

        gameTimer = new Timer(100, e -> gameUpdate());
        gameTimer.start();

        generateFood();
    }

    private void generateFood() {
        Random rand = new Random();
        int foodX, foodY;
        do {
            foodX = rand.nextInt(BOARD_WIDTH / Food.SIZE) * Food.SIZE;
            foodY = rand.nextInt(BOARD_HEIGHT / Food.SIZE) * Food.SIZE;
        } while (snake.getBody().contains(new Point(foodX, foodY)));

        // Correctly instantiate RegularFood with the new coordinates
        food = new RegularFood(foodX, foodY);
        gameObjects.set(1, food); // Update the food object in your gameObjects list
    }

    private void gameUpdate() {
        snake.move();
        checkCollisions();
        repaint();
    }

    private void checkCollisions() {
        Point head = snake.getHead();

        // Check for collision with walls
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT) {
            gameTimer.stop();
            // Implement game over logic here
            JOptionPane.showMessageDialog(this, "Game Over. Your score: " + score);
            System.exit(0); // Or reset the game
        }

        // Check for collision with itself
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.equals(snake.getBody().get(i))) {
                gameTimer.stop();
                JOptionPane.showMessageDialog(this, "Game Over. Your score: " + score);
                System.exit(0); // Or reset the game
                return;
            }
        }

        // Check for eating food
        if (head.equals(food.getPosition())) {
            snake.setGrowing(true);
            score++;
            generateFood();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject obj : gameObjects) {
            obj.draw(g);
        }
    }
}

