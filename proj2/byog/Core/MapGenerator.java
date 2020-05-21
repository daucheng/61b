package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class MapGenerator {
    //Instance
    private static final int MAXROOMWIDTH = 6;
    private static final int MAXROOMHEIGHT = 6;
    private static final long SEED = 2873123;

    private int width;
    private int height;
    private Position initialPosition;
    private Random random;
    private TETile[][] world;

    //Constructor
    MapGenerator(int w, int h, int initialX, int initialY) {
        width = w;
        height = h;
        initialPosition = new Position(initialX, initialY);
        random = new Random(SEED);
    }



    //Initialize
    private static void Initialize(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    //Method
}//end of class
