import javax.swing.JFrame;

// Spelfönstret som innehåller spelets huvudsakliga GUI-komponenter
public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Snake Game"); // Titeln på fönstret
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stänger av programmet vid stängning
        this.setResizable(false); // Förhindrar ändring av fönsterstorlek
        this.add(new GameBoard()); // Lägger till spelbrädet i fönstret
        this.pack(); // Packar ihop fönsterinnehållet
        this.setVisible(true); // Gör fönstret synligt
        this.setLocationRelativeTo(null); // Centrerar fönstret på skärmen
    }
}
