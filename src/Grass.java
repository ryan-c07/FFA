import java.awt.image.BufferedImage;

public class Grass extends Tile {
    public BufferedImage image;

    public Grass(){
        super();
        super.setImageFile("sprites/TILES/grass.png");
        super.image = loadImage(super.getImageFile());
    }
}
