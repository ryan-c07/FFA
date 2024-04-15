import javax.swing.JFrame;
import java.awt.*;

public class GameFrame extends JFrame{
    private JFrame window;
    private GamePanel gamePanel;
    private Thread windowThread;

    public GameFrame() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("FFA");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.addKeyListener(gamePanel);
        window.pack();
        window.setSize(1920, 1024); // 1920 1024
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}
