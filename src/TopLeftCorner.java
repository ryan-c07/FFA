import java.awt.image.BufferedImage;

public class TopLeftCorner extends Border{
    public BufferedImage image;
    public TopLeftCorner(){
        super();
        super.setImageFile("sprites/TILES/border/corner_tl.png");
        super.image = loadImage(super.getImageFile());

    }
}
