import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App extends JFrame {
    // create app components from Swing
    // JPanels
    private final JPanel rootPanel = new JPanel(new BorderLayout(10, 10));
    private final JPanel topBarPanel = new JPanel(new BorderLayout());
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    // JLabels
    private final JLabel titleLabel = new JLabel("No Problem Loaded");

    // JButtons
    private final JButton randomButton = new JButton("Random Problem");
    private final JButton resetButton = new JButton("Reset Starter");
    private final JButton hintButton = new JButton("Hint");
    private final JButton checkButton = new JButton("Check Solution");

    // JTextAreas
    private final JTextArea resultArea = new JTextArea();
    private final JTextArea problemArea = new JTextArea();

    // JComboBoxs
    private final JComboBox<String> fontComboBox = new JComboBox<>();

    // JScrollPanes
    // puts problemArea and resultArea into scrollable container
    private final JScrollPane problemScrollPane = new JScrollPane(problemArea);
    private final JScrollPane resultScrollPane = new JScrollPane(resultArea);

    // JSplitPanes
    private final JSplitPane verticalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private final JSplitPane bottomSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

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
