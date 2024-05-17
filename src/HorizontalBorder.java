import java.awt.image.BufferedImage;

public class HorizontalBorder extends Border{
    public BufferedImage image;
    public HorizontalBorder(){
        super();
        super.setImageFile("sprites/TILES/horizontal.png");
        super.image = loadImage(super.getImageFile());

    }
}
