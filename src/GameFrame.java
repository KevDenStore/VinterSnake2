import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new GameBoard());
        this.pack(); // Anpassar storleken efter komponenterna
        this.setLocationRelativeTo(null); // Centrerar fönstret på skärmen
        this.setVisible(true);
        this.setResizable(false);
    }
}