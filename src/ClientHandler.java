import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
// give x y of current player / using client
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private int x;
    private int y;
    private String image;
    private String nextLine;

    public ClientHandler(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = bufferedReader.readLine();
            x = Integer.parseInt(line.substring(line.indexOf("X:") + 1, line.indexOf("Y")));
            y = Integer.parseInt(line.substring(line.indexOf("Y:") + 1, line.indexOf("I")));
            image = line.substring(line.indexOf("IMAGE:") + 1);
            clientHandlers.add(this);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    public void run() {
        while (socket.isConnected()) {
            readFromClient();
            writeToClientHandlers(nextLine);
        }
    }

    public void readFromClient(){ // gets movement from other ppl / reads it
        try{
            nextLine = bufferedReader.readLine();
            x = Integer.parseInt(nextLine.substring(nextLine.indexOf("X:") + 1, nextLine.indexOf("Y")));
            y = Integer.parseInt(nextLine.substring(nextLine.indexOf("Y:") + 1, nextLine.indexOf("I")));
            image = nextLine.substring(nextLine.indexOf("IMAGE:") + 1);
        }
        catch(IOException e){
            System.out.println("UNABLE TO READ OTHER PLAYERS MOVEMENT");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void writeToClientHandlers(String lineOfMovement){ // sets movement / writes it
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.username.equals(username)) {
                    clientHandler.bufferedWriter.write(lineOfMovement + "USERNAME:" + username);
                    clientHandler.bufferedWriter.newLine();
                    //manually sent the data in buffer over
                    clientHandler.bufferedWriter.flush();
                }
            }
            catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeClientHandler() {
        clientHandlers.remove(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImage() {
        return image;
    }
}