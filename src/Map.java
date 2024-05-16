import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Map extends JPanel{
    public Tile[][] tiles = new Tile[40][40];
    public int x, y, speed;

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
            f = new File("MAPS/2");
            Scanner s = new Scanner(f);
            int col = 0;
            while (s.hasNextLine()){
                String str = s.nextLine();
                for (int i = 0;i < str.length();i++){
                    if (str.charAt(i) == '0'){
                        tiles[i][col] = new Sand();
                    }
                    if (str.charAt(i) == '1'){
                        tiles[i][col] = new Grass();
                    }
                }
                col++;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("gg");
        }
    }
}
