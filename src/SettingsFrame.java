import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    private JTextField unitSizeField;
    private JButton applyButton;
    private Gameframe gameFrame;  // Reference to the game frame for later enabling

    public SettingsFrame(Gameframe gameFrame) {
        this.gameFrame = gameFrame;
        initComponents();
    }

    private void initComponents() {
        JLabel unitSizeLabel = new JLabel("Unit Size:");
        unitSizeField = new JTextField(Integer.toString(Panel.getUnitSize()));
        applyButton = new JButton("Apply");

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newUnitSize = Integer.parseInt(unitSizeField.getText());
                    if (newUnitSize > 0) {
                        Panel.setUnitSize(newUnitSize);
                        JOptionPane.showMessageDialog(SettingsFrame.this, "Settings Applied!");
                    } else {
                        JOptionPane.showMessageDialog(SettingsFrame.this, "Invalid Unit Size! Please enter a positive number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SettingsFrame.this, "Invalid Unit Size! Please enter a valid number.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(unitSizeLabel);
        panel.add(unitSizeField);
        panel.add(applyButton);

        this.add(panel);
        this.setTitle("Settings");
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Enabling the game frame when the settings frame is closed
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (gameFrame != null) {
                    gameFrame.setEnabled(true);
                    gameFrame.requestFocusInWindow();
                }
            }
        });
    }
}
