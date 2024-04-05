import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private BufferedImage image;
    private int row;
    private int column;
    private final String IMAGE_FILE = "sprites/FORWARD/frame_15_delay-0.12s.png";

    public Player(int row, int column) {
        this.row = row;
        this.column = column;
        image = loadImage(IMAGE_FILE);
    }

    public BufferedImage loadImage(String fileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(fileName));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String toString() {
        return "Player at " + row + "," + column;
    }
}