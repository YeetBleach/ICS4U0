public class Projectile {
    private double initialVelocity; // in m/s
    private double angle; // in rads
    private double time; // in secs
    private double x; // current x position in ,
    private double y; // current y position in m

    public Projectile(double initialVelocity, double angleDegrees) {
        this.initialVelocity = initialVelocity;
        this.angle = Math.toRadians(angleDegrees);
        this.time = 0.0;
        this.x = 0.0;
        this.y = 0.0;
    }

    public void update(double deltaTime) {
        time += deltaTime;
        x = MotionCalculator.calculateX(initialVelocity, angle, time);
        y = MotionCalculator.calculateY(initialVelocity, angle, time);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTime() {
        return time;
    }
    }