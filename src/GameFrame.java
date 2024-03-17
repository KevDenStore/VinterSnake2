import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.add(new GameBoard());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // Anpassar fönsterstorleken efter komponenterna
        this.setVisible(true);
        this.setLocationRelativeTo(null); // Centrerar fönstret
    }

    public static void main(String[] args) {
        new GameFrame(); // Skapar och visar spelets fönster
    }
}
