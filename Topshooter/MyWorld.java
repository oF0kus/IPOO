import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class MyWorld extends World {
    private int lives = 3;
    private List<Heart> hearts = new ArrayList<>();
    private int spawnTimer = 0;
    private int enemiesSpawned = 0;
    private int enemiesKilled = 0;
    private final int MAX_ENEMIES = 5;

    public MyWorld() {    
        super(800, 600, 1); 
        prepare();
    }

    public void act() {
        spawnEnemies(); // gera inimigos com o tempo
        checkNextLevel();
    }

    private void prepare() {
        Player player = new Player();
        addObject(player, getWidth() / 2, getHeight() / 2);
        showHearts();
    }

    // cria os corações
    private void showHearts() {
        int x = 30;
        for (int i = 0; i < lives; i++) {
            Heart h = new Heart();
            addObject(h, x, 30);
            hearts.add(h);
            x += 40;
        }
    }

    // remove um coração e vida
    public void removeLife() {
        if (lives > 0) {
            lives--;
            Heart h = hearts.get(lives);
            removeObject(h);
        }

        if (lives == 0) {
            Greenfoot.stop();
            showText("GAME OVER", getWidth() / 2, getHeight() / 2);
        }
    }

    // cria inimigos fora da tela
    private void spawnEnemies() {

        if (enemiesSpawned >= MAX_ENEMIES) return;
        spawnTimer++;
        if (spawnTimer >= 60) { // a cada 1 segundo
            spawnTimer = 0;

            int side = Greenfoot.getRandomNumber(4); // 0=topo, 1=baixo, 2=esquerda, 3=direita
            int x = 0, y = 0;

            switch (side) {
                case 0: // topo
                    x = Greenfoot.getRandomNumber(getWidth());
                    y = -20;
                    break;
                case 1: // baixo
                    x = Greenfoot.getRandomNumber(getWidth());
                    y = getHeight() + 20;
                    break;
                case 2: // esquerda
                    x = -20;
                    y = Greenfoot.getRandomNumber(getHeight());
                    break;
                case 3: // direita
                    x = getWidth() + 20;
                    y = Greenfoot.getRandomNumber(getHeight());
                    break;
            }

            addObject(new Enemy(), x, y);
            enemiesSpawned++;
        }
    }
    // chamado quando um inimigo morre
    public void enemyKilled() {
        enemiesKilled++;
    }

    // avança pra próxima fase
    private void checkNextLevel() {
        if (enemiesSpawned == MAX_ENEMIES && enemiesKilled >= MAX_ENEMIES) {
            Greenfoot.setWorld(new MyWorld2()); // muda pra fase 2
        }
    }
}