import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Client{
    // x y coordinates of this player
    // write to otherPlayers

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int x;
    private int y;
    private String image;

    public Client(Socket socket, String image, int x, int y) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.x = x;
            this.y = y;
            this.image = image;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void getMovement(){ // gets movement from other ppl / reads it
        new Thread(new Runnable(){
            public void run(){
                try{
                    String line = bufferedReader.readLine();
                    x = Integer.parseInt(line.substring(line.indexOf("X:") + 1, line.indexOf("Y")));
                    y = Integer.parseInt(line.substring(line.indexOf("Y:") + 1, line.indexOf("I")));
                    image = line.substring(line.indexOf("IMAGE:") + 1);
                }
                catch(IOException e){
                    System.out.println("UNABLE TO READ OTHER PLAYERS MOVEMENT");
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }
    public void setMovement(String lineOfMovement){ // sets movement / writes it
        try{
            bufferedWriter.write("X:" + x + "Y:" + y + "IMAGE:" + image);
        }
        catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}