import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    Map map = new Map(0, 0, 5);
    double FPS = 60;

    Player player;

    public GamePanel(){
        player = new Player(0, 0, 300);
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
                player.image = player.loadImage("sprites/FORWARD/frame_15_delay-0.12s.gif");
            }
            else if (keyHandler.lastDirection.equals("S")){
                player.image = player.loadImage("sprites/BACKWARD/0.gif");
            }
            else if (keyHandler.lastDirection.equals("A")){
                player.image = player.loadImage("sprites/LEFT/frame_15_delay-0.12s.gif");
            }
            else if (keyHandler.lastDirection.equals("D")){
                player.image = player.loadImage("sprites/RIGHT/frame_15_delay-0.12s.gif");
            }
        }
        if (keyHandler.up){
            if (keyHandler.left) {
                map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
                map.y = map.y + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.y = map.y - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            } else if (keyHandler.right) {
                map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
                map.y = map.y + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.y = map.y - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            } else {
                map.y = map.y + map.speed;
                if (!checkInMap()){
                    map.y = map.y - map.speed;
                }
            }
            player.changeForwardFrame();
        }
        else if (keyHandler.down){
            if (keyHandler.left) {
                map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
                map.y = map.y - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.y = map.y + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            }
            else if (keyHandler.right) {
                map.x = map.x - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.x = map.x + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
                map.y = map.y - (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                if (!checkInMap()){
                    map.y = map.y + (int) Math.sqrt((Math.pow(map.speed, 2) / 2));
                }
            }
            else{
                map.y = map.y - map.speed;
                if (!checkInMap()){
                    map.y = map.y + map.speed;
                }
            }
            player.changeBackwardFrame();
        }
        else if (keyHandler.left){
            map.x = map.x + map.speed;
            if (!checkInMap()){
                map.x = map.x - map.speed;
            }
            player.changeLeftFrame();
        }
        else if (keyHandler.right){
            map.x = map.x - map.speed;
            if (!checkInMap()){
                map.x = map.x + map.speed;
            }
            player.changeRightFrame();
        }
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
        g2.drawImage(player.getImage(), 210, 192, null, null);
//        g2.drawRect(225, 202,34,54);
//        g2.drawRect(map.x + 64,map.y + 64,2560 - 128,2560 - 128); // visual aid
        g2.dispose();
    }

    public boolean checkInMap(){
        Rectangle playerHitBox = new Rectangle(225, 202,34,54); // player hit-box
        Rectangle mapRec = new Rectangle(map.x + 64,map.y + 64,2560 - 128,2560 - 128); // map range
        return mapRec.contains(playerHitBox);
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
