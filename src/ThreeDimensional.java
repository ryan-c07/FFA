import java.awt.image.BufferedImage;

public class ThreeDimensional extends Border{
    public BufferedImage image;
    public ThreeDimensional(){
        super();
        super.setImageFile("sprites/TILES/border/3D.png");
        super.image = loadImage(super.getImageFile());

    }
}
