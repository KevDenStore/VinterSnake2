import javax.swing.JPanel; // Används för att skapa en anpassad panel.
import javax.swing.Timer; // Timer för att schemalägga upprepade uppgifter.
import java.awt.Graphics; // Bibliotek för grafikoperationer, såsom ritning.
import java.awt.event.KeyAdapter; // Lyssnare för tangentbordshändelser.
import java.awt.event.KeyEvent; // Händelseobjekt för tangenttryckningar.
import java.util.ArrayList; // Stöd för dynamiska listor

public class GameBoard extends JPanel {
    private ArrayList<GameObject> gameObjects = new ArrayList<>(); //Skapar en lista för spelets objekt.
    private Snake snake;
    private Food food;
    private Timer gameTimer;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;

    public GameBoard() { //Konstruktor som initierar spelbrädet.
        setPreferredSize(new java.awt.Dimension(BOARD_WIDTH, BOARD_HEIGHT)); //Ställer in storleken.
        setBackground(java.awt.Color.BLACK); //Sätter bakgrundsfärg
        setFocusable(true); //Gör att panelen kan få fokus för tangentbordshändelser
        initializeGame(); //Initierar spelet
    }

    private void initializeGame() { //Metod för att starta om eller starta spelet
        snake = new Snake(100, 100, 5); //Skapar en ny orm. med set storlek och start plats
        food = new RegularFood(300, 300); // Placerar ut mat på brädet
        gameObjects.clear(); //Rensar lista med spelets objekt
        gameObjects.add(snake); //Lägger till ormen i listan
        gameObjects.add(food);

        addKeyListener(new KeyAdapter() { //Lägger till tangentbordslyssnare klassen för att hantera tangenttryckningar.
            @Override
            public void keyPressed(KeyEvent e) { //Reagerar på tangenttryckningar.
                // Ändrar ormens riktning baserat på vilken tangent som trycks.
                switch (e.getKeyCode()) { // switch funktion som Kontrollerar vilken tangent som
                    // tryckts och utför åtgärd baserat på detta, exempelvis ändrar riktning på ormen.
                    case KeyEvent.VK_UP:    snake.setDirection(Snake.Direction.UP);    break;
                    case KeyEvent.VK_DOWN:  snake.setDirection(Snake.Direction.DOWN);  break;
                    case KeyEvent.VK_LEFT:  snake.setDirection(Snake.Direction.LEFT);  break;
                    case KeyEvent.VK_RIGHT: snake.setDirection(Snake.Direction.RIGHT); break;
                }
            }
        });

        gameTimer = new Timer(100, e -> gameUpdate());
        // Skapar en timer som kallar på gameUpdate()// var 100:e millisekund för att uppdatera spelet.
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
        super.paintComponent(g); // Ritar panelens bakgrund och komponenter.
        for (GameObject gameObject : gameObjects) { // Itererar över spelobjekt.
            gameObject.draw(g); // Kallar på varje objekts ritmetod.
        }
    }
}
