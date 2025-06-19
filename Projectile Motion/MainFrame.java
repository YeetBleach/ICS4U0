import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private SimulationPanel simulationPanel;
    private JTextField velocityField;
    private JTextField angleField;
    private JButton startButton;
    private JButton resetButton;
    private JSlider targetSlider;
    private JLabel statusLabel;
    private JLabel positionLabel;

    public MainFrame() {
        setTitle("Projectile Motion Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        positionLabel = new JLabel("Position: (0.00, 0.00) | Time: 0.00 s | Velocity: (0.00, 0.00) m/s");
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        simulationPanel = new SimulationPanel(statusLabel, positionLabel);

        JPanel inputPanel = new JPanel(new FlowLayout());

        // Velocity input
        inputPanel.add(new JLabel("Initial Velocity (m/s):"));
        velocityField = new JTextField("25", 5);
        inputPanel.add(velocityField);

        // Angle input
        inputPanel.add(new JLabel("Launch Angle (degrees):"));
        angleField = new JTextField("45", 5);
        inputPanel.add(angleField);

        // Target position slider
        inputPanel.add(new JLabel("Target Position:"));
        targetSlider = new JSlider(50, 800, 400);
        targetSlider.setPreferredSize(new Dimension(150, 30));
        targetSlider.addChangeListener(e -> {
            simulationPanel.setTargetPosition(targetSlider.getValue());});
        inputPanel.add(targetSlider);

        // Start button
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            try {
                double velocity = Double.parseDouble(velocityField.getText());
                double angle = Double.parseDouble(angleField.getText());

                if (velocity <= 0) {
                    statusLabel.setText("Velocity must be positive");
                    return;
                }

                if (angle < 0 || angle > 90) {
                    statusLabel.setText("Angle must be between 0 and 90 degrees");
                    return;
                }

                simulationPanel.startSimulation(velocity, angle);
                statusLabel.setText("Simulation running...");
            } catch (NumberFormatException ex) {
                statusLabel.setText("Invalid input values");
            }
        });
        inputPanel.add(startButton);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            simulationPanel.resetSimulation();
        });
        inputPanel.add(resetButton);

        // Create a panel status and position labels
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        bottomPanel.add(positionLabel, BorderLayout.SOUTH);

        // Add all panels to frame
        add(simulationPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        pack(); // Adjusts the frame size to fit the components
    }
}