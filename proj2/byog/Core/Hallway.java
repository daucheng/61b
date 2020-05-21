package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hallway {

    /**
     * check available tiles (not edge and room)
     *
     */
    public static List<Position> checktiles(TETile[][] map){
        List<Position> validtiles = new LinkedList<>();
        int height = map[0].length;
        int width = map.length;
        for (int x = 1; x < width - 1; x += 1) {
            for (int y = 1; y < height - 1; y += 1) {
                if (map[x][y] == Tileset.WATER){
                    Position t = new Position(x,y);
                    validtiles.add(t);
                }
            }
        }
        return validtiles;
    }
    /**
    available direction
    check a tile surrounding
    */
    public static List<Position> availdirection(Position p, TETile[][] map){
        return null;
    }

    /**
     * generate Hallway from a random position
     *
     */
    public static void generateHallway(){

    }

    public static void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.WATER;
            }
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(80, 40);
        Random Random= new Random(12345);

        TETile[][] newmap = new TETile[80][40];
        fillWithNothing(newmap);
        Room.generateRoom(Random,newmap);
        ter.renderFrame(newmap);
    }
}//end of class