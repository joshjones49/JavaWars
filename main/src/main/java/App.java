import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App extends JFrame {
    // create app components from Swing library
    private final JPanel rootPanel = new JPanel(new BorderLayout(10, 10));
    private final JLabel titleLabel = new JLabel("No Problem Loaded");
    private final JPanel topBarPanel = new JPanel(new BorderLayout());

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }

    // create JFrame
    public App() {
        setTitle("Java Wars");
        // ensures app closes on x click
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 900);
        // this will set the window to open in the center of the screen
        setLocationRelativeTo(null);

        buildUI();
    }

    // method used to build the UI for application
    private void buildUI() {
        // set border angles for rootPanel
        rootPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // makes rootPanel the visible layout root for all components
        setContentPane(rootPanel);

        topBarPanel.add(titleLabel, BorderLayout.WEST);
        rootPanel.add(topBarPanel, BorderLayout.NORTH);
    }
}
