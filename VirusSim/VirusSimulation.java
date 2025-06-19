import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class VirusSimulation extends JFrame {
    private VirusPanel virusPanel;
    private JButton startButton, resetButton, addVirusButton;
    private Timer timer;

    public VirusSimulation() {
        super("Virus Simulation");

        virusPanel = new VirusPanel();
        add(virusPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        addVirusButton = new JButton("Add Virus");

        buttonPanel.add(startButton);
        buttonPanel.add(addVirusButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Timer for animation and growth
        timer = new Timer(1000, e -> virusPanel.updateViruses());

        // Button actions
        startButton.addActionListener(e -> timer.start());
        resetButton.addActionListener(e -> {
            timer.stop();
            virusPanel.clearViruses();
        });
        addVirusButton.addActionListener(e -> virusPanel.addVirus());

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}