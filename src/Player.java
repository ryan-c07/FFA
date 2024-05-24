import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    public BufferedImage image;
    private int x, y, forwardImageNumber, backwardImageNumber, rightImageNumber, leftImageNumber;
    private final String IMAGE_FILE = "sprites/BACKWARD/0.gif";

    public Player() {
        forwardImageNumber = 15;
        backwardImageNumber = 15;
        rightImageNumber = 15;
        leftImageNumber = 15;
        x = -15;
        y = -42;
        image = loadImage(IMAGE_FILE);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        if (forwardImageNumber >= 0 && forwardImageNumber < 8) {
            forwardImageNumber++;
            image = loadImage("sprites/FORWARD/" + forwardImageNumber + ".gif");
        } else if (getForwardImageNumber() >= 8) {
            forwardImageNumber = 0;
            image = loadImage("sprites/FORWARD/" + forwardImageNumber + ".gif");
        }
    }

    public void changeBackwardFrame() {
        if (backwardImageNumber >= 0 && backwardImageNumber < 8) {
            backwardImageNumber++;
            image = loadImage("sprites/BACKWARD/" + backwardImageNumber + ".gif");
        } else if (getBackwardImageNumber() >= 8) {
            backwardImageNumber = 0;
            image = loadImage("sprites/BACKWARD/" + backwardImageNumber + ".gif");
        }
    }

    public void changeRightFrame() {
        if (rightImageNumber >= 0 && rightImageNumber < 8) {
            rightImageNumber++;
            image = loadImage("sprites/RIGHT/" + rightImageNumber + ".gif");
        } else if (getRightImageNumber() >= 8) {
            rightImageNumber = 0;
            image = loadImage("sprites/RIGHT/" + rightImageNumber + ".gif");
        }
    }

    public void changeLeftFrame() {
        if (leftImageNumber >= 0 && leftImageNumber < 8) {
            leftImageNumber++;
            image = loadImage("sprites/LEFT/" + leftImageNumber + ".gif");
        } else if (getLeftImageNumber() >= 8) {
            leftImageNumber = 0;
            image = loadImage("sprites/LEFT/" + leftImageNumber + ".gif");
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