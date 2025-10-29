import greenfoot.*;

public class Bullet extends Actor {
    private double x, y;
    private double speed = 8;
    private double angle;

    public Bullet(double startX, double startY, double angle) {
        this.x = startX;
        this.y = startY;
        this.angle = angle;
        setRotation((int) angle);
    }

    public void act() {
        moveForward();
        checkBounds();
    }
   
    // Move a bala com base no ângulo
    private void moveForward() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
        setLocation((int) x, (int) y);
    }

    // Remove a bala se encostar na borda do mundo
    private void checkBounds() {
        if (isAtEdge()) getWorld().removeObject(this);
    }
}