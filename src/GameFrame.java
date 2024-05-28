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
        window.setSize(512, 512); // 512 512
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
