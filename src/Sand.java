import java.awt.image.BufferedImage;

public class Sand extends Tile{
    public BufferedImage image;
    public Sand(){
        super();
        super.setImageFile("sprites/TILES/sand.png");
        super.image = loadImage(super.getImageFile());

    }
}
