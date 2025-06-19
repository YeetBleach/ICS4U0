public class MotionCalculator {
    private static final double GRAVITY = 9.8;

    public static double calculateX(double initialVelocity, double angle, double time) {
        double vx = initialVelocity * Math.cos(angle);
        return vx * time;
    }

    public static double calculateY(double initialVelocity, double angle, double time) {
        double vy = initialVelocity * Math.sin(angle);
        return vy * time - 0.5 * GRAVITY * time * time;
    }
}