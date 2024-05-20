import java.awt.image.BufferedImage;

public class Background extends Tile{
    public BufferedImage image;
    public Background(){
        super();
        super.setImageFile("sprites/TILES/background.png");
        super.image = loadImage(super.getImageFile());

    }
}
