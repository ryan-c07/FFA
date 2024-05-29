import javax.swing.JFrame;
import java.io.IOException;
import java.net.Socket;

public class FFARunner {
    private static GameFrame frame;
    private static Client client;
    public static void main(String[] args) {
        frame = new GameFrame();
        try {
            client = new Client(new Socket("10.8.36.134", 1025), frame.getGamePanel());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // client.readFromClientHandler();
        client.writeToClientHandler(frame.getGamePanel().getMap(), frame.getGamePanel().getPlayer());
    }

    public static GameFrame getFrame() {
        return frame;
    }
}
