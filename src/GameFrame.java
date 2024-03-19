import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() { // Konstruktor för GameFrame
        setTitle("Snake Game"); // Sätter titeln på fönstret
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Gör så programmet avslutas när fönstret stängs
        add(new GameBoard()); // Lägger till spelbrädet till fönstret
        pack(); // Anpassar fönstrets storlek efter innehållet (GameBoard)
        setLocationRelativeTo(null); // Centrerar fönstret på skärmen
        setVisible(true); // Gör fönstret synligt
    }
}
