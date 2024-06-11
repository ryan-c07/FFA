import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    public BufferedImage image;
    private int x, y, hp, forwardImageNumber, backwardImageNumber, rightImageNumber, leftImageNumber;
    private boolean isDead;
    public String imageFile = "sprites/BACKWARD/0.gif";

    private GamePanel panel;

    public Player(GamePanel panel) {
        forwardImageNumber = 15;
        backwardImageNumber = 15;
        rightImageNumber = 15;
        leftImageNumber = 15;
        x = 210;
        y = 192;
        image = loadImage(imageFile);
        isDead = false;
        this.panel = panel;
    }

    public void aliveMovement(){
        if (!panel.keyHandler.up && !panel.keyHandler.down && !panel.keyHandler.left && !panel.keyHandler.right) {
            if (panel.keyHandler.lastDirection.equals("W")) {
                panel.player.image = panel.player.loadImage("sprites/FORWARD/0.gif");
                panel.player.imageFile = "sprites/FORWARD/0.gif";
            } else if (panel.keyHandler.lastDirection.equals("S")) {
                panel.player.image = panel.player.loadImage("sprites/BACKWARD/0.gif");
                panel.player.imageFile = "sprites/BACKWARD/0.gif";
            } else if (panel.keyHandler.lastDirection.equals("A")) {
                panel.player.image = panel.player.loadImage("sprites/LEFT/0.gif");
                panel.player.imageFile = "sprites/LEFT/0.gif";
            } else if (panel.keyHandler.lastDirection.equals("D")) {
                panel.player.image = panel.player.loadImage("sprites/RIGHT/0.gif");
                panel.player.imageFile = "sprites/RIGHT/0.gif";
            }
        }
        if (panel.keyHandler.up) {
            panel.map.y = panel.map.y + panel.map.speed;
            if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                panel.map.y = panel.map.y - panel.map.speed - 1;
            }
            if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                panel.map.y = panel.map.y - panel.map.speed - 1;
            }
            if (panel.keyHandler.left) {
                panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                    panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
                if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                    panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
            }

            if (panel.keyHandler.right) {
                panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                    panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
                if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                    panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
            }
            panel.player.changeForwardFrame();
        } else if (panel.keyHandler.down) {
            panel.map.y = panel.map.y - panel.map.speed;
            if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                panel.map.y = panel.map.y + panel.map.speed + 1;
            }
            if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                panel.map.y = panel.map.y + panel.map.speed + 1;
            }
            if (panel.keyHandler.left) {
                panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                    panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
                if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                    panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
            }
            if (panel.keyHandler.right) {
                panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                    panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
                if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                    panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
                }
            }
            panel.player.changeBackwardFrame();
        }
        else if (panel.keyHandler.left) {
            panel.map.x = panel.map.x + panel.map.speed;
            if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                panel.map.x = panel.map.x - panel.map.speed - 1;
            }
            if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                panel.map.x = panel.map.x - panel.map.speed - 1;
            }
            panel.player.changeLeftFrame();
        }
        else if (panel.keyHandler.right) {
            panel.map.x = panel.map.x - panel.map.speed;
            if (!checkInMap(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54), panel.map.x, panel.map.y)) {
                panel.map.x = panel.map.x + panel.map.speed + 1;
            }
            if (checkPlayerIntersects(new Rectangle(panel.player.getX() + 15, panel.player.getY() + 10, 34, 54))) {
                panel.map.x = panel.map.x + panel.map.speed + 1;
            }
            panel.player.changeRightFrame();
        }
        if (panel.keyHandler.shift) {
            panel.map.speed = 10;
        }
        if (!panel.keyHandler.shift) {
            panel.map.speed = 4;
        }
        if (panel.keyHandler.invis){
            setDead(true);
        }
    }

    public void deadMovement(){
        panel.player.imageFile = "sprites/OTHERS/nothing.png";
        panel.player.image = loadImage("sprites/OTHERS/nothing.png");
        if (panel.keyHandler.up) {
            panel.map.y = panel.map.y + panel.map.speed;
            if (panel.keyHandler.left) {
                panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
            }
            if (panel.keyHandler.right) {
                panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
            }
        } else if (panel.keyHandler.down) {
            panel.map.y = panel.map.y - panel.map.speed;
            if (panel.keyHandler.left) {
                panel.map.x = panel.map.x + (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
            }
            if (panel.keyHandler.right) {
                panel.map.x = panel.map.x - (int) Math.sqrt((Math.pow(panel.map.speed, 2) / 2));
            }
        }
        else if (panel.keyHandler.left) {
            panel.map.x = panel.map.x + panel.map.speed;
        }
        else if (panel.keyHandler.right) {
            panel.map.x = panel.map.x - panel.map.speed;
        }
        if (panel.keyHandler.shift) {
            panel.map.speed = 10;
        }
        if (!panel.keyHandler.shift) {
            panel.map.speed = 4;
        }
        if (!panel.keyHandler.invis){
            setDead(false);
        }
    }

    public boolean checkInMap(Rectangle hitBox, int x, int y){
        for (int row = 0; row < panel.map.tiles.length; row++) {
            for (int col = 0; col < panel.map.tiles[0].length; col++) {
                if (panel.map.tiles[row][col] instanceof Border) {
                    panel.borderRectangles.add(new Rectangle(x + 64 * row, y + 64 * col, 64, 64));
                }
            }
        }
        for (Rectangle r : panel.borderRectangles) {
            if (hitBox.intersects(r)) {
                panel.borderRectangles.clear();
                return false;
            }
        }
        panel.borderRectangles.clear();
        return true;
    }

    public boolean checkPlayerIntersects(Rectangle hitBox){
        for (int i = 0; i < panel.otherPlayers.size(); i++) {
            if (!panel.otherPlayers.get(i).isDead()) {
                panel.otherPlayersHitBoxes.add(new Rectangle(panel.map.x - panel.otherPlayers.get(i).getX() + panel.player.getX() + 15, panel.map.y - panel.otherPlayers.get(i).getY() + panel.player.getY() + 10, 34, 54));
            }
        }
        boolean contains = false;
        for (Rectangle hitboxes : panel.otherPlayersHitBoxes){
            if (hitboxes.intersects(hitBox)){
                contains = true;
            }
        }
        panel.otherPlayersHitBoxes.clear();
        return contains;
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

    public void setImage(String image) {
        this.image = loadImage(image);
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