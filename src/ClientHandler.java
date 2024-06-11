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
    private String nextLine;
    private boolean disconnected = false;
    private String tempLine = "";
    private String writtenAlready = "";

    public ClientHandler(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            nextLine = bufferedReader.readLine();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    public void run() {
        while (socket.isConnected()) {
            try{
                readFromClient();
                writeToClientHandlers(nextLine);
            }
            catch(IOException e){
                System.out.println("Client disconnected!");
                disconnected = true;
                writeToClientHandlers(nextLine);
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void readFromClient() throws IOException { // gets movement from other ppl / reads it
        if (!tempLine.contains(nextLine)){
            nextLine = bufferedReader.readLine();
            tempLine += nextLine;
            System.out.println("read");
        }
    }

    public void writeToClientHandlers(String lineOfMovement){ // sets movement / writes it
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.username.equals(this.username) && !writtenAlready.contains(lineOfMovement)) {
                    clientHandler.bufferedWriter.write(lineOfMovement + "USERNAME:" + username + "STATUS:" + disconnected);
                    clientHandler.bufferedWriter.newLine();
                    // manually sent the data in buffer over
                    clientHandler.bufferedWriter.flush();
                    writtenAlready += lineOfMovement;
                }
            }
            catch (IOException e) {
                System.out.println("test");
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
}