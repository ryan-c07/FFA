import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Array;
import java.util.ArrayList;
import java.awt.Rectangle;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this);
    Map map = new Map(0, 0, 2);
    double FPS = 60;
    ArrayList<Rectangle> borderRectangles = new ArrayList<Rectangle>();
    ArrayList<Rectangle> otherPlayersHitBoxes = new ArrayList<Rectangle>();
    ArrayList<OtherPlayers> otherPlayers = new ArrayList<OtherPlayers>();

    public GamePanel(){
        this.setPreferredSize(new Dimension(512, 512));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        spawnInRandomLocation();

    }

    public void startGameThread(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS; // # of times that you need to draw within a second, to maintain 60fps
        double drawNext = System.nanoTime() + drawInterval;
        while (true){
            update();
            repaint();
            try{
                double remainingTime = drawNext - System.nanoTime(); // how much time is left before the next draw
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                drawNext += drawInterval;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (!player.isDead()){
            player.aliveMovement();
        }
        else{
            player.deadMovement();
        }
    }

    public void paintComponent(Graphics g){ // repaint
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        for (int row = 0; row < map.tiles.length; row++){
            for (int col = 0; col < map.tiles[0].length; col++){
                g2.drawImage(map.tiles[row][col].getImage(), map.x + 64 * row, map.y + 64 * col, null,null);
                if (map.tiles[row][col] instanceof Border){
                    // g2.drawRect(map.x + 64 * row, map.y + 64 * col, 64, 64);
                }
            }
        }
        for (int i = 0;i < otherPlayers.size(); i++){
            if (!otherPlayers.get(i).isDead()) {
                g2.drawImage(otherPlayers.get(i).getImage(), map.x - otherPlayers.get(i).getX() + player.getX(), map.y - otherPlayers.get(i).getY() + player.getY(), null, null);
                g2.drawRect(map.x - otherPlayers.get(i).getX() + player.getX() + 15, map.y - otherPlayers.get(i).getY() + player.getY() + 10, 34, 54);
            }
            if (otherPlayers.get(i).isDead()){
                otherPlayers.get(i).setImage("sprites/OTHERS/nothing.png");
                otherPlayers.get(i).setDead(true);
            }
        }
        if (player.isDead()){
            player.imageFile = "sprites/OTHERS/nothing.png";
            player.setImage(player.imageFile);
        }
        if (!player.isDead()) {
            g2.drawImage(player.getImage(), player.getX(), player.getY(), null, null);
            g2.drawRect(player.getX() + 15, player.getY() + 10, 34, 54);
        }
        g2.dispose();
    }
    public void spawnInRandomLocation(){
        int x = 0;
        int y = 0;
        boolean notInWall = false;
        Rectangle playerHitBox = new Rectangle(player.getX() + 15 + x, player.getY() + 10 + y, 34, 54);
        while (!notInWall){
            x = (int) (Math.random() * (-17 + 2311) - 2311);
            y = (int) (Math.random() * (-94 + 3926) - 3926);
            borderRectangles.clear();
            for (int row = 0; row < map.tiles.length; row++) {
                for (int col = 0; col < map.tiles[0].length; col++) {
                    if (map.tiles[row][col] instanceof Border) {
                        borderRectangles.add(new Rectangle(x + 64 * row, y + 64 * col, 64, 64));
                    }
                }
            }
            notInWall = true;
            for (Rectangle r : borderRectangles) {
                if (playerHitBox.intersects(r)) {
                    notInWall = false;
                }
            }
            if (notInWall) {
                map.x = x;
                map.y = y;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public Map getMap() {
        return map;
    }
    public Player getPlayer(){
        return player;
    }
    public ArrayList<OtherPlayers> getOtherPlayers() {
        return otherPlayers;
    }
}
