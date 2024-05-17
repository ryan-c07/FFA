import java.awt.image.BufferedImage;

public class VerticalBorder extends Border{
    public BufferedImage image;
    public VerticalBorder(){
        super();
        super.setImageFile("sprites/TILES/vertical.png");
        super.image = loadImage(super.getImageFile());

    }
}
