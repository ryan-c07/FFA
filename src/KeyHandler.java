import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class KeyHandler implements KeyListener, MouseListener {
    public boolean up, down, left, right;
    String lastDirection = "";
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            up = true;
            lastDirection = "W";
        }
        if (code == KeyEvent.VK_A){
            left = true;
            lastDirection = "A";
        }
        if (code == KeyEvent.VK_S){
            down = true;
            lastDirection = "S";
        }
        if (code == KeyEvent.VK_D){
            right = true;
            lastDirection = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            up = false;
        }
        if (code == KeyEvent.VK_A){
            left = false;
        }
        if (code == KeyEvent.VK_S){
            down = false;
        }
        if (code == KeyEvent.VK_D){
            right = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
