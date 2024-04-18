import javax.swing.JPanel;
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
        for (int row = 0; row < tiles.length; row++){
            for (int col = 0; col < tiles[0].length; col++){
                tiles[row][col] = new Tile();
            }
        }
    }
}
