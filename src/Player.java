import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    public BufferedImage image;
    private int playerX, playerY, speed, hp, dmg, forwardImageNumber, backwardImageNumber, rightImageNumber, leftImageNumber;
    private final String IMAGE_FILE = "sprites/FORWARD/frame_15_delay-0.12s.gif";

    public Player(int playerX, int playerY, int speed) {
        forwardImageNumber = 15;
        backwardImageNumber = 15;
        rightImageNumber = 15;
        leftImageNumber = 15;
        this.playerX = playerX;
        this.playerY = playerY;
        this.speed = speed;
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

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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