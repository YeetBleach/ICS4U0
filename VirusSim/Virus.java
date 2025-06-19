import java.awt.*;
import java.util.Random;

public class Virus {
    private int x, y;
    private int radius = 5;
    private int spikes = 3;
    private Color color = new Color(0, 150, 0);
    private int dx; // chaotic motion
    private int dy;

    public Virus(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.dx = rand.nextInt(6)+3;
        this.dy = rand.nextInt(6)+3;
    }

    public void move() {
        x += dx;
        y += dy;

        // Bounce off walls
        if (x < 0 || x > 800) dx = -dx;
        if (y < 0 || y > 600) dy = -dy;
    }

    public void grow() {
        if (radius < 15) radius += 1;
        if (spikes < 12) spikes += 1;

        if (radius >= 15 && spikes >= 12) {
            color = new Color(180, 0, 0); // dangerous red
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Draw 3 concentric circles
        g2.setColor(color);
        for (int i=0; i<3; i++) {
            int r = radius - i * 2;
            if (r > 0)
                g2.drawOval(x - r, y - r, 2 * r, 2 * r);
        }

        // Draw spikes
        double angleStep = 2 * Math.PI / spikes;
        for (int i = 0; i < spikes; i++) {
            double angle = i * angleStep;
            int spikeX = (int) (x + (radius + 5) * Math.cos(angle));
            int spikeY = (int) (y + (radius + 5) * Math.sin(angle));
            g2.fillOval(spikeX - 3, spikeY - 3, 6, 6); // small spike
        }
    }
}