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
    private int highScore;
    private Timer gameTimer;

    public GameBoard() {
        highScore = HighScoreManager.loadHighScore();
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        initializeGame();
        setFocusable(true);
        requestFocusInWindow();
    }

    private void initializeGame() {
        score = 0;
        gameObjects = new ArrayList<>();
        snake = new Snake(100, 100, 5);
        food = new RegularFood(0, 0);
        gameObjects.add(snake);
        gameObjects.add(food);
        addKeyListener(new KeyboardListener(snake)); // Re-adding the listener each reset
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
        food = new RegularFood(foodX, foodY);
        gameObjects.set(1, food);
    }

    private void gameUpdate() {
        snake.move();
        checkCollisions();
        repaint();
    }

    private void checkCollisions() {
        Point head = snake.getHead();
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT ||
                snake.getBody().stream().anyMatch(segment -> segment != head && segment.equals(head))) {
            gameTimer.stop();
            updateHighScore();
            int playAgain = JOptionPane.showConfirmDialog(this, "Game Over. Your score: " + score +
                    "\nHigh Score: " + highScore + "\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION) {
                removeAll();
                initializeGame(); // Reset the game state
            } else {
                System.exit(0);
            }
        } else if (head.equals(food.getPosition())) {
            snake.setGrowing(true);
            score++;
            generateFood();
        }
    }

    private void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            HighScoreManager.saveHighScore(highScore);
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
