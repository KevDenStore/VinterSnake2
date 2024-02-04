import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel {
    private ArrayList<GameObject> gameObjects;
    private Snake snake;
    private Food food;
    private Timer gameTimer;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private int score;

    public GameBoard() {
        initializeGame();
        setFocusable(true);
        addKeyListener(new KeyboardListener(snake));
        // Det är viktigt att anropa requestFocusInWindow för att säkerställa att GameBoard tar emot tangentbordsinput.
        requestFocusInWindow();
    }

    private KeyboardListener keyboardListener; // Antag att detta är en instansvariabel i GameBoard

    public void initializeGame() {
        // Återställ eller initiera spelvariabler
        score = 0;
        if (gameObjects != null) {
            gameObjects.clear(); // Rensa tidigare spelobjekt om listan redan finns
        } else {
            gameObjects = new ArrayList<>();
        }

        // Skapa ormen och maten, lägg till dem i spelobjektslistan
        snake = new Snake(100, 100, 5);
        food = new RegularFood(0, 0); // Positionen kommer att genereras om i generateFood
        gameObjects.add(snake);
        gameObjects.add(food);

        // Återstarta eller starta timern
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameTimer = new Timer(100, e -> gameUpdate());
        gameTimer.start();

        // Återställ/generera matens position
        generateFood();

        // Hantera tangentbordslyssnaren
        setupKeyboardListener();

        // Be om fokus för att kunna fånga tangentbordshändelser
        requestFocusInWindow();
    }

    private void setupKeyboardListener() {
        if (keyboardListener != null) {
            removeKeyListener(keyboardListener); // Ta bort tidigare lyssnare för att undvika dubbla händelser
        }
        keyboardListener = new KeyboardListener(snake);
        addKeyListener(keyboardListener);
        setFocusable(true);
        requestFocusInWindow();
    }


    private void generateFood() {
        Random rand = new Random();
        int foodX, foodY;
        do {
            foodX = rand.nextInt(BOARD_WIDTH / Food.SIZE) * Food.SIZE;
            foodY = rand.nextInt(BOARD_HEIGHT / Food.SIZE) * Food.SIZE;
        } while (snake.getBody().contains(new Point(foodX, foodY)));

        food.setPosition(foodX, foodY); // Uppdaterar matens position
    }

    private void gameUpdate() {
        snake.move();
        checkCollisions();
        repaint();
    }

    private void checkCollisions() {
        Point head = snake.getHead();

        // Kontroll för kollision med spelplanens gränser
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT) {
            gameOver();
            return; // Avsluta metoden här för att undvika ytterligare kontroller efter game over
        }

        // Kontroll för kollision med ormens egen kropp
        for (int i = 1; i < snake.getBody().size(); i++) { // Börja med index 1 för att hoppa över huvudet
            Point segment = snake.getBody().get(i);
            if (head.equals(segment)) {
                gameOver();
                return; // Avsluta metoden här för att undvika ytterligare kontroller efter game over
            }
        }

        // Kontroll för kollision med maten
        if (head.equals(food.getPosition())) {
            snake.setGrowing(true);
            score++;
            generateFood();  // Skapa ny mat
        }
    }

    private void gameOver() {
        gameTimer.stop();
        int highScore = HighScoreManager.loadHighScore();
        if (score > highScore) {
            HighScoreManager.saveHighScore(score);
            JOptionPane.showMessageDialog(this, "New Highscore! Your score: " + score);
        } else {
            JOptionPane.showMessageDialog(this, "Game Over. Your score: " + score + "\nHigh Score: " + highScore);
        }
        showRestartOption();
    }

    private void showRestartOption() {
        int playAgain = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (playAgain == JOptionPane.YES_OPTION) {
            initializeGame();
        } else {
            System.exit(0); // Eller en annan logik för att stänga spelet eller återgå till en huvudmeny
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
