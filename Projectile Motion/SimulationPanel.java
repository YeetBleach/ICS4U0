import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationPanel extends JPanel {
    private Projectile projectile;
    private Timer timer;
    private List<Point> trajectoryPoints; // stores trajectory points in pixels
    private Target target;
    private boolean targetHit;
    private JLabel statusLabel;
    private JLabel positionLabel;
    private boolean simulationRunning;

    private final int GROUND_Y = 400; // ground pixel height
    private final double PIXELS_PER_METER = 10.0; // 1m = 10px

    public SimulationPanel(JLabel statusLabel, JLabel positionLabel) {
        this.statusLabel = statusLabel;
        this.positionLabel = positionLabel;
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.WHITE);
        trajectoryPoints = new ArrayList<>();
        targetHit = false;
        simulationRunning = false;

        setTarget();
    }

    public void setTarget() { // Places target at random position on start
        int targetX = 300 + (int) (Math.random() * 400);
        target = new Target(targetX, GROUND_Y - 10, 20);
    }

    public void setTargetPosition(int x) {
        target = new Target(x, GROUND_Y - 10, 20);
        repaint();
    }

    public void startSimulation(double initialVelocity, double angleDegrees) {
        stopSimulation();

        projectile = new Projectile(initialVelocity, angleDegrees);
        trajectoryPoints.clear();
        targetHit = false;
        simulationRunning = true;

        //New frame every 16 ms (60 fps)
        timer = new Timer(16, e -> {
            if (!simulationRunning) return; // if simulation is not running, exit

            projectile.update(0.03); //30 ms seconds per timer tick

            // Convert meters to pixels for drawing
            int xPixels = (int) (projectile.getX() * PIXELS_PER_METER);
            int yPixels = GROUND_Y - (int) (projectile.getY() * PIXELS_PER_METER);

            trajectoryPoints.add(new Point(xPixels, yPixels));
            updatePositionLabel(xPixels, yPixels);

            // Check if projectile hits target in pixels
            double dx = xPixels - target.x;
            double dy = yPixels - target.y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance <= target.radius) {
                statusLabel.setText("Target hit!");
                targetHit = true;
                stopSimulation();
            }

            // If projectile hits ground (y <= 0 in meters)
            if (projectile.getY() <= 0) {
                if (!targetHit) {
                    statusLabel.setText("Missed target");
                }
                stopSimulation();
            }

            repaint();
        });

        timer.start();
    }

    private void updatePositionLabel(int x, int y) {
        positionLabel.setText(String.format("Position: (%d, %d) px | Time: %.2f s",
                x, y, projectile.getTime()));
    }

    public void stopSimulation() {
        if (timer != null) timer.stop();
        simulationRunning = false;
    }

    public void resetSimulation() {
        stopSimulation();
        projectile = null;
        trajectoryPoints.clear();
        targetHit = false;
        statusLabel.setText(" ");
        positionLabel.setText("Position: (0, 0) px | Time: 0.00 s");
        repaint();
    }

    @Override //If not overridden, displays empty panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw ground
        g2.setColor(Color.GREEN);
        g2.fillRect(0, GROUND_Y, getWidth(), getHeight() - GROUND_Y);

        // Draw target
        g2.setColor(Color.RED);
        g2.fillOval(target.x - target.radius, target.y - target.radius, target.radius * 2, target.radius * 2);
        g2.setColor(Color.WHITE);
        g2.fillOval(target.x - target.radius / 2, target.y - target.radius / 2, target.radius, target.radius);

        // Draw trajectory
        g2.setColor(Color.BLUE);
        for (int i = 0; i < trajectoryPoints.size() - 1; i++) {
            Point p1 = trajectoryPoints.get(i);
            Point p2 = trajectoryPoints.get(i + 1);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Draw projectile
        if (projectile != null && !targetHit) {
            int px = (int) (projectile.getX() * PIXELS_PER_METER);
            int py = GROUND_Y - (int) (projectile.getY() * PIXELS_PER_METER);
            g2.setColor(Color.BLACK);
            g2.fillOval(px - 5, py - 5, 10, 10);
        }
    }

    //Target class for position and radius
    public static class Target {
        int x, y, radius;

        public Target(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }
}