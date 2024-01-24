public class SettingsFrame {
    import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class SettingsFrame extends JFrame {
        private JTextField unitSizeField;

        public SettingsFrame() {
            initComponents();
        }

        private void initComponents() {
            JLabel unitSizeLabel = new JLabel("Unit Size:");
            unitSizeField = new JTextField(Integer.toString(Settings.getUnitSize()));
            JButton applyButton = new JButton("Apply");

            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int newUnitSize = Integer.parseInt(unitSizeField.getText());
                        Settings.setUnitSize(newUnitSize);
                        JOptionPane.showMessageDialog(SettingsFrame.this, "Settings Applied!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(SettingsFrame.this, "Invalid Unit Size! Please enter a number.");
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
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SettingsFrame();
                }
            });
        }
    }

}
