import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    public BufferedImage image;
    private int x, y, forwardImageNumber, backwardImageNumber, rightImageNumber, leftImageNumber;
    private boolean hasPotato, isDead;
    public String imageFile = "sprites/BACKWARD/0.gif";

    public Player() {
        forwardImageNumber = 15;
        backwardImageNumber = 15;
        rightImageNumber = 15;
        leftImageNumber = 15;
        x = 210;
        y = 192;
        image = loadImage(imageFile);
        isDead = false;
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

    public boolean isHasPotato() {
        return hasPotato;
    }

    public void setHasPotato(boolean hasPotato) {
        this.hasPotato = hasPotato;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
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
            imageFile = "sprites/FORWARD/" + forwardImageNumber + ".gif";
            image = loadImage(imageFile);
        } else if (getForwardImageNumber() >= 8) {
            forwardImageNumber = 0;
            imageFile = "sprites/FORWARD/" + forwardImageNumber + ".gif";
            image = loadImage(imageFile);
        }
    }

    public void changeBackwardFrame() {
        if (backwardImageNumber >= 0 && backwardImageNumber < 8) {
            backwardImageNumber++;
            imageFile = "sprites/BACKWARD/" + backwardImageNumber + ".gif";
            image = loadImage(imageFile);
        } else if (getBackwardImageNumber() >= 8) {
            backwardImageNumber = 0;
            imageFile = "sprites/BACKWARD/" + backwardImageNumber + ".gif";
            image = loadImage(imageFile);
        }
    }

    public void changeRightFrame() {
        if (rightImageNumber >= 0 && rightImageNumber < 8) {
            rightImageNumber++;
            imageFile = "sprites/RIGHT/" + rightImageNumber + ".gif";
            image = loadImage(imageFile);
        } else if (getRightImageNumber() >= 8) {
            rightImageNumber = 0;
            imageFile = "sprites/RIGHT/" + rightImageNumber + ".gif";
            image = loadImage(imageFile);
        }
    }

    public void changeLeftFrame() {
        if (leftImageNumber >= 0 && leftImageNumber < 8) {
            leftImageNumber++;
            imageFile = "sprites/LEFT/" + leftImageNumber + ".gif";
            image = loadImage(imageFile);
        } else if (getLeftImageNumber() >= 8) {
            leftImageNumber = 0;
            imageFile = "sprites/LEFT/" + leftImageNumber + ".gif";
            image = loadImage(imageFile);
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