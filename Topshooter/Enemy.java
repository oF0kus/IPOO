import greenfoot.*;
import java.util.List;
public class Enemy extends Actor {
    private int speed = 2;  

    public void act() {
        followPlayer();   
        checkHit();       
    }

    // faz o inimigo se mover em direção ao jogador
    private void followPlayer() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0); // pega o player
        if (player != null) {
            turnTowards(player.getX(), player.getY()); 
            move(speed); 
        }
    }

    // remove o inimigo se for atingido por uma bala
    private void checkHit() {
        Bullet b = (Bullet) getOneIntersectingObject(Bullet.class);
        if (b != null) {
            getWorld().removeObject(b);   
            ((MyWorld) getWorld()).enemyKilled();
            getWorld().removeObject(this); 
        }
    }
    
    
}
