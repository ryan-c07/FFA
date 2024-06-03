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
    Player player = new Player();
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
        if (!player.isDead()) {
            if (!keyHandler.up && !keyHandler.down && !keyHandler.left && !keyHandler.right) {
                if (keyHandler.lastDirection.equals("W")) {
                    player.image = player.loadImage("sprites/FORWARD/0.gif");
                } else if (keyHandler.lastDirection.equals("S")) {
                    player.image = player.loadImage("sprites/BACKWARD/0.gif");
                } else if (keyHandler.lastDirection.equals("A")) {
                    player.image = player.loadImage("sprites/LEFT/0.gif");
                } else if (keyHandler.lastDirection.equals("D")) {
                    player.image = player.loadImage("sprites/RIGHT/0.gif");
                }
            }
            if (keyHandler.up) {
                map.y = map.y + map.speed;
                if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                    map.y = map.y - map.speed - 1;
                }
                if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                    map.y = map.y - map.speed - 1;
                    player.setHasPotato((!player.isHasPotato()));
                }
                if (keyHandler.left) {
                    map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                        map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    }
                    if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                        map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                        player.setHasPotato((!player.isHasPotato()));
                    }
                }

                if (keyHandler.right) {
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                        map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    }
                    if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                        map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                        player.setHasPotato((!player.isHasPotato()));
                    }
                }

                player.changeForwardFrame();
            } else if (keyHandler.down) {
                map.y = map.y - map.speed;
                if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                    map.y = map.y + map.speed + 1;
                }
                if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                    map.y = map.y + map.speed + 1;
                    player.setHasPotato(!player.isHasPotato());
                }
                if (keyHandler.left) {
                    map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                        map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    }
                    if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                        map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                        player.setHasPotato(!player.isHasPotato());
                    }
                }
                if (keyHandler.right) {
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                        map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                    }
                    if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                        map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                        player.setHasPotato(!player.isHasPotato());
                    }
                }

                player.changeBackwardFrame();
            } else if (keyHandler.left) {
                map.x = map.x + map.speed;
                if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                    map.x = map.x - map.speed - 1;
                }
                if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                    map.x = map.x - map.speed - 1;
                    player.setHasPotato(!player.isHasPotato());
                }
                player.changeLeftFrame();
            } else if (keyHandler.right) {
                map.x = map.x - map.speed;
                if (!checkInMap(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54), map.x, map.y)) {
                    map.x = map.x + map.speed + 1;
                }
                if (checkPlayerIntersects(new Rectangle(player.getX() + 15, player.getY() + 10, 34, 54))) {
                    map.x = map.x + map.speed + 1;
                    player.setHasPotato(!player.isHasPotato());
                }
                player.changeRightFrame();
            }
            if (keyHandler.shift) {
                map.speed = 10;
            }
            if (!keyHandler.shift) {
                map.speed = 4;
            }
//            System.out.println("MAP : " + "X = " + map.getX() + ", Y = " + map.getY()); // test
            if (player.isHasPotato()){

            }
            if (!player.isHasPotato()){

            }
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
                    g2.drawRect(map.x + 64 * row, map.y + 64 * col, 64, 64);

                }
            }
        }
        for (int i = 0;i < otherPlayers.size(); i++){
            g2.drawImage(otherPlayers.get(i).getImage(), map.x - otherPlayers.get(i).getX() + player.getX(), map.y - otherPlayers.get(i).getY() + player.getY(), null, null);
            g2.drawRect(map.x - otherPlayers.get(i).getX() + player.getX() + 15, map.y - otherPlayers.get(i).getY() + player.getY() + 10,34,54);
        }
        g2.drawImage(player.getImage(), player.getX(), player.getY(), null, null);
        g2.drawRect(player.getX() + 15, player.getY() + 10, 34,54);
        g2.dispose();
    }
    public boolean checkInMap(Rectangle hitBox, int x, int y){
        for (int row = 0; row < map.tiles.length; row++) {
            for (int col = 0; col < map.tiles[0].length; col++) {
                if (map.tiles[row][col] instanceof Border) {
                    borderRectangles.add(new Rectangle(x + 64 * row, y + 64 * col, 64, 64));
                }
            }
        }
        for (Rectangle r : borderRectangles) {
            if (hitBox.intersects(r)) {
                borderRectangles.clear();
                return false;
            }
        }
        borderRectangles.clear();
        return true;
    }

    public boolean checkPlayerIntersects(Rectangle hitBox){
        for (int i = 0; i < otherPlayers.size(); i++) {
            otherPlayersHitBoxes.add(new Rectangle(map.x - otherPlayers.get(i).getX() + player.getX() + 15, map.y - otherPlayers.get(i).getY() + player.getY() + 10,34,54));
        }
        boolean contains = false;
        for (Rectangle hitboxes : otherPlayersHitBoxes){
            if (hitboxes.intersects(hitBox)){
                contains = true;
            }
        }
        otherPlayersHitBoxes.clear();
        return contains;
    }
    public void spawnInRandomLocation(){
        int x = (int) ((Math.random() * 2330) - 2425);
        int y = (int) ((Math.random() * 4618) - 4027);
        while (true){
            System.out.println(!checkInMap(new Rectangle(player.getX() + 15 + x, player.getY() + 10 + y, 34, 54), x , y));
            System.out.println(x);
            System.out.println(y);
            if (!checkInMap(new Rectangle(player.getX() + 15 + x, player.getY() + 10 + y, 34, 54), x, y)){
                map.x = x;
                map.y = y;
                break;
            }
            x = (int) ((Math.random() * 2330) - 2425);
            y = (int) ((Math.random() * 4618) - 4027);
        }
        System.out.println("MAP : " + "X = " + x + ", Y = " + y); // test
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
