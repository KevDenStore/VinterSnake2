import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameBoard extends JPanel implements ActionListener {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Snake snake;
    private Food food;
    private Timer gameTimer;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private boolean gameOver = false;

    public GameBoard() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyboardListener());
        initializeGame();
    }

    private void initializeGame() {
        snake = new Snake(100, 100, 5);
        food = new RegularFood(300, 300);
        gameObjects.clear();
        gameObjects.add(snake);
        gameObjects.add(food);

        gameTimer = new Timer(100, this);
        gameTimer.start();
    }

    private void gameUpdate() {
        snake.move();
        if (snake.intersects(food)) {
            snake.setGrowing(true);
            generateFood();
        }
        checkCollision();
        repaint();
    }

    private void generateFood() {
        int x = (int) (Math.random() * BOARD_WIDTH);
        int y = (int) (Math.random() * BOARD_HEIGHT);
        food.setPosition(x, y);
    }

    private void checkCollision() {
        // Kollision med väggarna
        if (snake.getHead().x < 0 || snake.getHead().x >= BOARD_WIDTH ||
                snake.getHead().y < 0 || snake.getHead().y >= BOARD_HEIGHT) {
            gameOver = true;
        }

        // Kollision med ormen själv
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (snake.getHead().equals(snake.getBody().get(i))) {
                gameOver = true;
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
        if (gameOver) {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game Over!", BOARD_WIDTH / 2 - 50, BOARD_HEIGHT / 2);
        gameTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            gameUpdate();
        }
    }

    private class KeyboardListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    snake.setDirection(Snake.Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Snake.Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Snake.Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Snake.Direction.RIGHT);
                    break;
            }
        }
    }
}
