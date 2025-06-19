import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class passcode extends JFrame {
    private static final int SIZE = 5;
    private static int[][] grid = new int[SIZE][SIZE];
    private static final Random random = new Random();
    private static JTextField passcodeInput;
    private static JButton[][] buttonGrid = new JButton[SIZE][SIZE];
    public static List<int[]> yellowGrid = new ArrayList<>();

    public passcode() {
        setLayout(new BorderLayout()); // Use BorderLayout for better component management

        JPanel gridFrame = new JPanel(new GridLayout(SIZE, SIZE));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton cell = new JButton(" ");
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setBackground(Color.lightGray);
                int finalRow = row;
                int finalCol = col;
                cell.addActionListener(e -> {
                    cell.setBackground(Color.YELLOW);
                    yellowGrid.add(new int[]{finalRow, finalCol});
                });
                buttonGrid[row][col] = cell;
                gridFrame.add(cell);
            }
        }

        add(gridFrame, BorderLayout.CENTER); // Add grid to center of frame

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton button = new JButton("Generate Passcode");
        passcodeInput = new JTextField(); // Initialize the text field

        bottomPanel.add(button);
        bottomPanel.add(passcodeInput);
        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to frame

        button.addActionListener(e -> {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    int randomNumber = random.nextInt(100);
                    grid[row][col] = randomNumber;
                    buttonGrid[row][col].setText(String.valueOf(randomNumber));
                    buttonGrid[row][col].setBackground(Color.lightGray);
                }
            }

            // Print yellowGrid in the requested format
            System.out.print("Yellow Grid Selected Positions: [");
            for (int i = 0; i < yellowGrid.size(); i++) {
                int[] position = yellowGrid.get(i);
                System.out.print("(" + position[0] + "," + position[1] + ")");
                if (i < yellowGrid.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        });

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // Make sure the frame is visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            passcode frame = new passcode();
            // frame.setVisible(true);
        });
    }
}
