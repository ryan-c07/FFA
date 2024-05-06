import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    public BufferedImage image;
    private int hp, dmg, forwardImageNumber, backwardImageNumber, rightImageNumber, leftImageNumber;
    private final String IMAGE_FILE = "sprites/FORWARD/frame_15_delay-0.12s.gif";
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    }catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
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

    public Player(int playerX, int playerY, int speed, Socket socket) {
        forwardImageNumber = 15;
        backwardImageNumber = 15;
        rightImageNumber = 15;
        leftImageNumber = 15;
        this.hp = 100;
        this.dmg = 5;

        image = loadImage(IMAGE_FILE);
    }

    public BufferedImage loadImage(String fileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(fileName));
            return image;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public void changeForwardFrame() {
        if (forwardImageNumber >= 15 && forwardImageNumber < 24) {
            forwardImageNumber++;
            image = loadImage("sprites/FORWARD/frame_" + forwardImageNumber + "_delay-0.12s.gif");
        } else if (getForwardImageNumber() >= 24) {
            forwardImageNumber = 15;
            image = loadImage("sprites/FORWARD/frame_" + forwardImageNumber + "_delay-0.12s.gif");
        }
    }

    public void changeBackwardFrame() {
        if (backwardImageNumber >= 15 && backwardImageNumber < 24) {
            backwardImageNumber++;
            image = loadImage("sprites/BACKWARD/frame_" + backwardImageNumber + "_delay-0.12s.gif");
        } else if (getBackwardImageNumber() >= 24) {
            backwardImageNumber = 15;
            image = loadImage("sprites/BACKWARD/frame_" + backwardImageNumber + "_delay-0.12s.gif");
        }
    }

    public void changeRightFrame() {
        if (rightImageNumber >= 15 && rightImageNumber < 23) {
            rightImageNumber++;
            image = loadImage("sprites/RIGHT/frame_" + rightImageNumber + "_delay-0.12s.gif");
        } else if (getRightImageNumber() >= 23) {
            rightImageNumber = 15;
            image = loadImage("sprites/RIGHT/frame_" + rightImageNumber + "_delay-0.12s.gif");
        }
    }

    public void changeLeftFrame() {
        if (leftImageNumber >= 15 && leftImageNumber < 23) {
            leftImageNumber++;
            image = loadImage("sprites/LEFT/frame_" + leftImageNumber + "_delay-0.12s.gif");
        } else if (getLeftImageNumber() >= 23) {
            leftImageNumber = 15;
            image = loadImage("sprites/LEFT/frame_" + leftImageNumber + "_delay-0.12s.gif");
        }
    }


    public BufferedImage getImage() {
        return image;
    }

    public int getForwardImageNumber() {
        return forwardImageNumber;
    }

    public void setForwardImageNumber(int forwardImageNumber) {
        this.forwardImageNumber = forwardImageNumber;
    }

    public int getBackwardImageNumber() {
        return backwardImageNumber;
    }

    public void setBackwardImageNumber(int backwardImageNumber) {
        this.backwardImageNumber = backwardImageNumber;
    }

    public int getRightImageNumber() {
        return rightImageNumber;
    }

    public void setRightImageNumber(int rightImageNumber) {
        this.rightImageNumber = rightImageNumber;
    }

    public int getLeftImageNumber() {
        return leftImageNumber;
    }

    public void setLeftImageNumber(int leftImageNumber) {
        this.leftImageNumber = leftImageNumber;
    }
}