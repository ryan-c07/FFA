import java.awt.image.BufferedImage;

public class Floor extends Tile{
    public BufferedImage image;
    public Floor(){
        super();
        super.setImageFile("sprites/TILES/betterfloor.png");
        super.image = loadImage(super.getImageFile());

    }
}
