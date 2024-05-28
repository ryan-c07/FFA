import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OtherPlayers {
    private int x;
    private int y;

    private String username;
    private BufferedImage image;

    public OtherPlayers(int x, int y, String image, String username){
        this.username = username;
        this.x = x;
        this.y = y;
        this.image = loadImage(image);
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
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = loadImage(image);
    }

    public String getUsername() {
        return username;
    }
}