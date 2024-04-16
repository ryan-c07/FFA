import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    double FPS = 60;

    Player player;

    public GamePanel(){
        player = new Player(100, 100, 5);
        this.setPreferredSize(new Dimension(1920, 1024));
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
            player.setPlayerY(player.getPlayerY() - player.getSpeed());
            player.changeForwardFrame();
        }
        else if (keyHandler.left){
            player.setPlayerX(player.getPlayerX() - player.getSpeed());
            player.changeLeftFrame();
        }
        else if (keyHandler.down){
            player.setPlayerY(player.getPlayerY() + player.getSpeed());
            player.changeBackwardFrame();
        }
        else if (keyHandler.right){
            player.setPlayerX(player.getPlayerX() + player.getSpeed());
            player.changeRightFrame();
        }
    }
    public void paintComponent(Graphics g){ // repaint
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.drawImage(player.getImage(), player.getPlayerX(), player.getPlayerY(), null, null);
        g2.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
