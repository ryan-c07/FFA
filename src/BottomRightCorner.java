import java.awt.image.BufferedImage;

public class BottomRightCorner extends Border{
    public BufferedImage image;
    public BottomRightCorner(){
        super();
        super.setImageFile("sprites/TILES/border/corner_br.png");
        super.image = loadImage(super.getImageFile());

    }
}
