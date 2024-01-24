import javax.swing.*;
import javax.swing.JFrame;

public class Gameframe extends JFrame {
    private static final long serialVersionUID = 1L;

    Gameframe() {
        Panel panel = new Panel();
        this.add(panel);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}