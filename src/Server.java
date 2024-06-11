import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private static int playerNum = 0;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket, "" + playerNum);
                Thread thread = new Thread(clientHandler);
                thread.start();
                ClientHandler.clientHandlers.add(clientHandler);
                playerNum++;
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            ServerSocket socket = new ServerSocket();
            socket.bind(new InetSocketAddress(1025));
            Server server = new Server(socket);
            InetAddress iAddress = null;
            try {
                iAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            String server_IP;
            server_IP = iAddress.getHostAddress();
            System.out.println("Server IP address : " + server_IP);
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}