import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    private int speed = 4;
    private int shootCooldown = 0;

    public void act()
    {
        movePlayer();
        shoot();
        if (shootCooldown > 0) shootCooldown--;
    }
    // Controla o movimento com as teclas W, A, S, D
    private void movePlayer()
    {
        int dx = 0;
        int dy = 0;

        if (Greenfoot.isKeyDown("w")) dy -= speed;
        if (Greenfoot.isKeyDown("s")) dy += speed;
        if (Greenfoot.isKeyDown("a")) dx -= speed;
        if (Greenfoot.isKeyDown("d")) dx += speed;

        setLocation(getX() + dx, getY() + dy);
    }
    // Controla os tiros nas direções das setas
    private void shoot() {
        double angle = Double.NaN;

        boolean up = Greenfoot.isKeyDown("up");
        boolean down = Greenfoot.isKeyDown("down");
        boolean left = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");

        // diagonais
        if (up && right) angle = -45;
        else if (up && left) angle = -135;
        else if (down && right) angle = 45;
        else if (down && left) angle = 135;
        // direções simples
        else if (up) angle = -90;
        else if (down) angle = 90;
        else if (left) angle = 180;
        else if (right) angle = 0;

        if (!Double.isNaN(angle) && shootCooldown == 0) {
            Bullet b = new Bullet(getX(), getY(), angle);
            getWorld().addObject(b, getX(), getY());
            shootCooldown = 10;
        }
    }
}
