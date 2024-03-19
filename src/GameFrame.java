import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GameBoard());
        pack(); // Adjust size based on components
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }
}
