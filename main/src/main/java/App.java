import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    // create app components
    JPanel rootPanel = new JPanel(new BorderLayout(10, 10));

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

    }
}
