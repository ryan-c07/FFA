import java.awt.image.BufferedImage;

public class Border extends Tile{
    public BufferedImage image;
    public Border(String side){
        super();
        if (side.equals("bl")){
            super.setImageFile("sprites/TILES/border/corner_bl.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("br")){
            super.setImageFile("sprites/TILES/border/corner_br.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("tl")){
            super.setImageFile("sprites/TILES/border/corner_tl.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("tr")){
            super.setImageFile("sprites/TILES/border/corner_tr.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("h")){
            super.setImageFile("sprites/TILES/horizontal.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("v")){
            super.setImageFile("sprites/TILES/vertical.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("3D")){
            super.setImageFile("sprites/TILES/border/3D.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("dt")){
            super.setImageFile("sprites/TILES/border/topdeadend.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("dd")){
            super.setImageFile("sprites/TILES/border/downdeadend.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("dr")){
            super.setImageFile("sprites/TILES/border/rightdeadend.png");
            super.image = loadImage(super.getImageFile());
        }
        else if (side.equals("dl")){
            super.setImageFile("sprites/TILES/border/leftdeadend.png");
            super.image = loadImage(super.getImageFile());
        }

    }
}