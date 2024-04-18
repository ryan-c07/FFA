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
        player = new Player(0, 0, 100);
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
        if (keyHandler.up){
            map.y = map.y + map.speed;
            if (keyHandler.left) {
                map.x = map.x + map.speed;
            }
            else if (keyHandler.right) {
                map.x = map.x - map.speed;
            }
            player.changeForwardFrame();
        }
        else if (keyHandler.down){
            map.y = map.y - map.speed;
            if (keyHandler.left) {
                map.x = map.x + map.speed;
            }
            else if (keyHandler.right) {
                map.x = map.x - map.speed;
            }
            player.changeBackwardFrame();
        }
        else if (keyHandler.left){
            map.x = map.x + map.speed;
            player.changeLeftFrame();
        }
        else if (keyHandler.right){
            map.x = map.x - map.speed;
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
        g2.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
