import java.awt.image.BufferedImage;

public class TopRightCorner extends Border{
    public BufferedImage image;
    public TopRightCorner(){
        super();
        super.setImageFile("sprites/TILES/border/corner_tr.png");
        super.image = loadImage(super.getImageFile());

    }
}
