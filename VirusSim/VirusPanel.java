import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class VirusPanel extends JPanel {
    private ArrayList<Virus> viruses = new ArrayList<>();
    private final Random random = new Random();

    public VirusPanel() {
        setBackground(Color.WHITE);
        // Animate chaotic motion
        Timer motionTimer = new Timer(100, e -> {
            for (Virus v : viruses) {
                v.move();
            }
            repaint();
        });
        motionTimer.start();
    }

    public void addVirus() {
        viruses.add(new Virus(random.nextInt(getWidth()), random.nextInt(getHeight())));
        repaint();
    }

    public void clearViruses() {
        viruses.clear();
        repaint();
    }

    public void updateViruses() {
        for (Virus v : viruses) {
            v.grow();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Virus v : viruses) {
            v.draw(g);
        }
    }
}