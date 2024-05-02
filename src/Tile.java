import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Tile {
    public BufferedImage image;
    private String imageFile;

    public BufferedImage getImage() {
        return image;
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

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFile() {
        return imageFile;
    }
}
