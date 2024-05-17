import java.awt.image.BufferedImage;

public class BottomLeftCorner extends Border{
    public BufferedImage image;
    public BottomLeftCorner(){
        super();
        super.setImageFile("sprites/TILES/border/corner_bl.png");
        super.image = loadImage(super.getImageFile());

    }
}
