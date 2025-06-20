import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomNumGenerator extends JFrame{ // Class
    private final JTextField minField;
    private final JTextField maxField;
    private final JLabel numberLabel1;
    private final JLabel numberLabel2;
    private final JLabel sumLabel;
    private final JButton generateButton;
    private Random random;

    public RandomNumGenerator(int min, int max){ // Constructor
        random = new Random();
        minField = new JTextField(String.valueOf(min));
        maxField = new JTextField(String.valueOf(max));
        numberLabel1 = new JLabel("Number 1: ");
        numberLabel2  = new JLabel("Number 2:");
        sumLabel = new JLabel("Sum: ");
        generateButton = new JButton("Generate Random Numbers");

        setLayout(new GridLayout(5,2,5,5));
        add(new JLabel("Enter Minimum Value:   "));
        add(minField);
        add(new JLabel("Enter Maximum Value:   "));
        add(maxField);
        add(new JLabel(" "));
        add(generateButton);
        add(numberLabel1);
        add(numberLabel2);
        add(sumLabel);

        setSize(600,500);
        setVisible(true);
        setTitle("Random Number Generator With User Range");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateNumber(minField.getText(), maxField.getText());
            }
        });
    }

    public RandomNumGenerator(){ //Constructor without params
        this(1,100); // Hardcode params
    }

    public void generateNumber(String min, String max){ // Method

        //Parsing min and max Strings into ints
        int minInt = Integer.parseInt(min);
        int maxInt = Integer.parseInt(max);

        int randomNumber1 = random.nextInt(maxInt - minInt + 1) + minInt;
        numberLabel1.setText(numberLabel1.getText() + String.valueOf(randomNumber1));

        int randomNumber2 = random.nextInt(maxInt - minInt + 1) + minInt;
        numberLabel2.setText( numberLabel2.getText() + String.valueOf(randomNumber2));

        int sum = randomNumber1 + randomNumber2;
        sumLabel.setText(String.valueOf(sum));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RandomNumGenerator frame = new RandomNumGenerator();
            frame.setVisible(true);
        });
    }
}
