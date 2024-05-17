import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Map extends JPanel{
    public Tile[][] tiles = new Tile[40][40];
    public int x, y, speed; // make private

    public Map(int x, int y, int speed){
        fillTiles();
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void fillTiles(){
//        for (int row = 0; row < tiles.length; row++){
//            for (int col = 0; col < tiles[0].length; col++){
//                tiles[row][col] = new Tile();
//            }
//        }


        File f = null;
        try{
            f = new File("MAPS/4");
            Scanner s = new Scanner(f);
            int col = 0;
            while (s.hasNextLine()){
                String str = s.nextLine();
                for (int i = 0;i < str.length();i++){
                    if (i == 0 && col == 0){
                        tiles[i][col] = new TopLeftCorner();
                    }
                    else if (i == 0 && col == 39){
                        tiles[i][col] = new BottomLeftCorner();
                    }
                    else if (i == 39 && col == 0){
                        tiles[i][col] = new TopRightCorner();
                    }
                    else if (i == 39 && col == 39){
                        tiles[i][col] = new BottomRightCorner();
                    }
                    else if (str.charAt(i) == '0'){
                        tiles[i][col] = new VerticalBorder();
                    }
                    else if (str.charAt(i) == '1'){
                        tiles[i][col] = new HorizontalBorder();
                    }
                    else if (str.charAt(i) == '2'){
                        tiles[i][col] = new Floor();
                    }
                    else if (str.charAt(i) == '3'){
                        tiles[i][col] = new ThreeDimensional();
                    }
                }
                col++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("gg");
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
