import javax.swing.*; // Importerar Swing-biblioteket för grafiskt användargränssnitt
import java.awt.*; // Importerar AWT-biblioteket för grafik och layout
import java.util.ArrayList; // Importerar ArrayList-klassen för dynamiska listor
import java.util.Random; // Importerar Random-klassen för slumpmässiga tal

public class GameBoard extends JPanel { // GameBoard är en JPanel där spelet ritas ut
    private ArrayList<GameObject> gameObjects; // Lista som håller alla spelobjekt som ormen och maten
    private Snake snake; // Referens till spelets orm
    private Food food; // Referens till maten som ormen ska äta
    private final int BOARD_WIDTH = 600; // Konstant för bredden på spelbrädet
    private final int BOARD_HEIGHT = 600; // Konstant för höjden på spelbrädet
    private int score; // Spelarens nuvarande poäng
    private int highScore; // Högsta poängen som någonsin uppnåtts i spelet
    private Timer gameTimer; // Timer för att regelbundet uppdatera spellogiken

    public GameBoard() { // Konstruktor för GameBoard
        highScore = HighScoreManager.loadHighScore(); // Laddar det högsta poängrekordet från fil
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT)); // Sätter storleken på panelen
        setBackground(Color.BLACK); // Sätter bakgrundsfärgen på spelbrädet till svart
        initializeGame(); // Kallar på metoden som initierar spelet
        setFocusable(true); // Gör så att GameBoard kan fånga upp tangenttryckningar
        requestFocusInWindow(); // Ber om fokus så att tangenttryckningar faktiskt fångas upp
    }

    private void initializeGame() { // Initierar och startar ett nytt spel
        score = 0; // Nollställer spelarens poäng
        gameObjects = new ArrayList<>(); // Skapar en ny lista för spelobjekten
        snake = new Snake(100, 100, 5); // Skapar en ny orm
        food = new RegularFood(0, 0); // Skapar ett nytt matobjekt
        gameObjects.add(snake); // Lägger till ormen i listan av spelobjekt
        gameObjects.add(food); // Lägger till maten i listan av spelobjekt
        addKeyListener(new KeyboardListener(snake)); // Lägger till en lyssnare för tangentbordstryckningar
        gameTimer = new Timer(100, e -> gameUpdate()); // Skapar en timer som uppdaterar spelet varje 100ms
        gameTimer.start(); // Startar timern
        generateFood(); // Placerar ut maten på spelbrädet
    }

    private void generateFood() { // Genererar och placerar ut maten på spelbrädet
        Random rand = new Random(); // Skapar ett objekt för att generera slumpmässiga tal
        int foodX, foodY; // Deklarerar variabler för matens x- och y-position
        do { // Loop som ser till att maten inte placeras där ormen befinner sig
            foodX = rand.nextInt(BOARD_WIDTH / Food.SIZE) * Food.SIZE; // Slumpar fram en x-position för maten
            foodY = rand.nextInt(BOARD_HEIGHT / Food.SIZE) * Food.SIZE; // Slumpar fram en y-position för maten
        } while (snake.getBody().contains(new Point(foodX, foodY))); // Kontrollerar att den nya maten inte placeras där ormen redan är
        food = new RegularFood(foodX, foodY); // Skapar ny mat på den slumpade positionen
        gameObjects.set(1, food); // Ersätter den gamla maten i listan av spelobjekt med den nya
    }

    private void gameUpdate() { // Metod som uppdaterar spelets tillstånd
        snake.move(); // Uppdaterar ormens position baserat på dess riktning
        checkCollisions(); // Kontrollerar kollisioner med väggar, sig själv, eller mat
        repaint(); // Omritar spelbrädet och alla spelobjekt
    }

    private void checkCollisions() { // Kontrollerar om ormen kolliderar med något
        Point head = snake.getHead(); // Hämtar ormens huvudposition
        // Kontrollerar om ormens huvud är utanför spelbrädets gränser
        if (head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT ||
                snake.getBody().stream().anyMatch(segment -> segment != head && segment.equals(head))) { // Kollar även efter kollision med sig själv
            gameTimer.stop(); // Stannar spelets uppdateringar om kollision inträffar
            updateHighScore(); // Uppdaterar det högsta poängrekordet om det är relevant
            // Frågar spelaren om de vill spela igen eller avsluta efter en kollision
            int playAgain = JOptionPane.showConfirmDialog(this, "Game Over. Your score: " + score +
                    "\nHigh Score: " + highScore + "\nPlay again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION) {
                removeAll(); // Tar bort alla komponenter från panelen
                initializeGame(); // Startar om spelet
            } else {
                System.exit(0); // Avslutar programmet om spelaren inte vill spela igen
            }
        } else if (head.equals(food.getPosition())) { // Kontrollerar om ormen äter mat
            snake.setGrowing(true); // Ormen växer om den äter mat
            score++; // Ökar poängen
            generateFood(); // Genererar ny mat
        }
    }

    private void updateHighScore() { // Uppdaterar det högsta poängrekordet om spelarens poäng är högre
        if (score > highScore) {
            highScore = score; // Sätter nytt högsta poängrekord
            HighScoreManager.saveHighScore(highScore); // Sparar det nya rekordet till fil
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // Metod för att rita ut spelbrädet och dess innehåll
        super.paintComponent(g); // Rensar panelen och förbereder den för omritning
        for (GameObject obj : gameObjects) { // Loopar igenom och ritar ut alla spelobjekt
            obj.draw(g); // Anropar varje spelobjekts ritmetod
        }
    }
}
