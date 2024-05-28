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
    Player player = new Player();;
    Map map = new Map(player.getX(), player.getY(), 10);
    double FPS = 60;
    ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    ArrayList<OtherPlayers> otherPlayers = new ArrayList<OtherPlayers>();

    public GamePanel(){
        this.setPreferredSize(new Dimension(512, 512));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
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
        if (!keyHandler.up && !keyHandler.down && !keyHandler.left && !keyHandler.right){
            if (keyHandler.lastDirection.equals("W")){
                player.image = player.loadImage("sprites/FORWARD/0.gif");
            }
            else if (keyHandler.lastDirection.equals("S")){
                player.image = player.loadImage("sprites/BACKWARD/0.gif");
            }
            else if (keyHandler.lastDirection.equals("A")){
                player.image = player.loadImage("sprites/LEFT/0.gif");
            }
            else if (keyHandler.lastDirection.equals("D")){
                player.image = player.loadImage("sprites/RIGHT/0.gif");
            }
        }
        if (keyHandler.up){
            map.y = map.y + map.speed;
            if (!checkInMap()) {
                map.y = map.y - map.speed - 1;
            }
            if (keyHandler.left) {
                map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()) {
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2)) ;
                }
            }

            if (keyHandler.right) {
                map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            }

            player.changeForwardFrame();
        }
        else if (keyHandler.down){
            map.y = map.y - map.speed;
            if (!checkInMap()){
                map.y = map.y + map.speed + 1;
            }
            if (keyHandler.left) {
                map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()) {
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            }
            if (keyHandler.right) {
                map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()) {
                    map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            }

            player.changeBackwardFrame();
        }
        else if (keyHandler.left){
            map.x = map.x + map.speed;
            if (!checkInMap()){
                map.x = map.x - map.speed - 1;
            }
            player.changeLeftFrame();
        }
        else if (keyHandler.right){
            map.x = map.x - map.speed;
            if (!checkInMap()){
                map.x = map.x + map.speed + 1;
            }
            player.changeRightFrame();
        }
        player.setY(map.y);
        player.setX(map.x);
        rectangles.clear();
//        System.out.println("X = " + map.getX() + ", Y = " + map.getY()); // test
//        System.out.println("X = " + player.getX() + ", Y = " + player.getY()); // test

    }

    public void paintComponent(Graphics g){ // repaint
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        for (int row = 0; row < map.tiles.length; row++){
            for (int col = 0; col < map.tiles[0].length; col++){
                g2.drawImage(map.tiles[row][col].getImage(), map.x + 64 * row, map.y + 64 * col, null,null);
            }
        }
        for (int i = 0;i < otherPlayers.size(); i++){
            g2.drawImage(otherPlayers.get(i).getImage(), otherPlayers.get(i).getX(), otherPlayers.get(i).getY(), null, null);
        }
        g2.drawImage(player.getImage(), 210, 192, null, null);
        g2.drawRect(225, 202,34,54);
        g2.dispose();
    }

    public boolean checkInMap(){
        Rectangle playerHitBox = new Rectangle(225, 202,34,54); // player hit-box
        for (int row = 0; row < map.tiles.length; row++) {
            for (int col = 0; col < map.tiles[0].length; col++) {
                if (map.tiles[row][col] instanceof Border) {
                    rectangles.add(new Rectangle(map.x + 64 * row, map.y + 64 * col, 64, 64));
                }
            }
        }
        boolean contains = true;
        for (Rectangle r : rectangles){
            if (r.intersects(playerHitBox)){
                contains = false;
            }
        }
        return contains;
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

    public void setOtherPlayers(ArrayList<OtherPlayers> otherPlayers) {
        this.otherPlayers = otherPlayers;
    }
}
