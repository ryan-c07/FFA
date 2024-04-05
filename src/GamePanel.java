import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable, MouseListener, KeyListener {
    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(1920, 1024));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(this);
        this.addKeyListener(this);
    }

    public void startGameThread(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        while (true){
            update();
//            repaint();
        }
    }
    public void update(){
        System.out.println("test");
    }
    public void paintComponent(Graphics g){
        super.paint(g);
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
