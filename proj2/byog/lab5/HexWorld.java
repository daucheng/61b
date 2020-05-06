package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 23;
    private static final Random RANDOM = new Random(SEED);

    /**
     * draw a empty world
     */
    public static void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.WALL;
            }
        }
    }
    /**
     * helper method for draw hexagon
     */
    /**
     * patent: the "size" row and "size-1" row has no space, and minus or plus one row will increase one space
     * @param size
     * @param row
     * @return return the amount of space
     */
    private static int space(int size, int row){
        if (row == size || row == (size -1)){return 0;}
        if (row < size){
            return size -1 - row;
        }else{
            return row - size;
        }//end of row > size
    }//end of space

    /**
     * how many tiles in this row
     * @param size
     * @param row
     * @return how many tiles in this row
     */
    private static int tile(int size, int row){
        int num = size;
        if (row == 0){return num;}
        if (row < size){
            for (int i = 0; i < row; i++) {
                num = num + 2;
            }}else{
            num = num + (( 2 * size-1 ) - row)*2;
        }
        return num;
    }//end of tile

    /**
     * draw tiles of world
     * @param tiles the world
     * @param xpos start position of x coordinate
     * @param ypos at which row
     * @param num how many tiles or space to draw
     * @param pic what kind of tile
     */
    private static void draw(TETile[][] tiles, int xpos, int ypos, int num,TETile pic) {
        for (int i = 0; i < num; i++) {
            tiles[xpos][ypos] = pic;
            xpos += 1;
        }
    }//end of draw
    private static void draw(TETile[][] tiles,TETile pic) {
        for (int i = 0; i < 10; i++) {
            tiles[20 + i][20] = pic;
        }
    }//end of draw
    /**
     * Adds a hexagon to the world
     * @param tiles the world to draw
     * @param pos_x x coordinate of bottom left
     * @param pos_y y coordinate of bottom left
     * @param size  size of the hexagon
     * @param tile tile to draw
     */
    public static void addHexagon(TETile[][] tiles,int pos_x,int pos_y,int size,TETile tile) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }//end of assertion
        for (int y = 0; y < (2 * size); y++){
            int num_space = space(size,y);
            int num_tiles = tile(size,y);
            draw(tiles,pos_x+num_space,pos_y + y,num_tiles,tile);
        }//draw Hexagon from the bottom row
    }//end of addHexagon
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.WATER;
            default: return Tileset.FLOOR;
        }
    }
    public static void tesselation(TETile[][] tiles, int size,int exp){
        int mid_y = tiles[0].length/2 - size;
        int mid_x = tiles.length/2 - size;
        addHexagon(tiles, mid_x, mid_y, size, randomTile());
        expand(tiles,mid_x,mid_y,size);

    }//end of tesselation
    private static void expand(TETile[][] tiles,int x, int y,int size){
        addHexagon(tiles,x,y + 6,size,randomTile());//top
        addHexagon(tiles,x - 5,y + 3,size ,randomTile());//left top
        addHexagon(tiles,x - 5,y - 3,size,randomTile());//left down
        addHexagon(tiles,x + 5,y + 3,size,randomTile());//right top
        addHexagon(tiles,x + 5,y - 3,size,randomTile());//right down
        addHexagon(tiles,x,y - 6,size,randomTile());//down
    }//end of expand
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] Tiles = new TETile[WIDTH][HEIGHT];
        fillWithNothing(Tiles);
        //addHexagon(Tiles,20,22,3,randomTile());
        tesselation(Tiles,3,1);
        ter.renderFrame(Tiles);
    }//end of Main
}//end of Hexworld
