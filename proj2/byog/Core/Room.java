package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.RandomSeq;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

public class Room {

    //Instance
    private int width;
    private int height;
    private final Position position;

    //Constructor
    public Room(int w, int h, Position p){
        width = w;
        height = h;
        position = p;//top left of the room
    }
    /**
     * print rooms
     */
    public static void printRoom(List<Room> Rooms,TETile[][] map){
        for (Room r: Rooms) {
            printSingleRoom(r,map);
        }
    }
    private static void printSingleRoom(Room r,TETile[][] map){
        //print wall
        //vertical
        for (int i = 0; i < r.height; i++) {
            map[r.position.x][r.position.y - i] = Tileset.WALL;
            map[r.position.x + r.width - 1][r.position.y - i] = Tileset.WALL;
        }
        //horizontal
        for (int i = 1; i < r.width-1; i++){
            map[r.position.x + i][r.position.y] = Tileset.WALL;
            map[r.position.x + i][r.position.y - r.height + 1] = Tileset.WALL;
        }
        //print floor
        for (int i = 1; i < r.height - 1; i++) {
            for (int j = 1; j < r.width - 1; j++) {
                map[r.position.x + j][r.position.y - i] = Tileset.FLOOR;
            }
        }
    }
    /**
     * Overlap checking and remove
     */
    public static boolean isOverlap(List<Room> Rooms,Room r){
        Position[] corner = roomPosition(r.width,r.height,r.position);
        for (Room R: Rooms){
            for (Position p:corner){
                // if one of any corner within a room than is overlap
                if (p.x >= R.position.x - 1 && p.x <= (R.position.x + R.width)){
                    if (p.y <= R.position.y + 1 && p.y >= (R.position.y - R.height)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static List<Room> removeOverlap(List<Room> Rooms){
        List<Room> validRooms = new LinkedList<>();
        for (Room R: Rooms){
            if(isOverlap(validRooms,R) == false){
                validRooms.add(R);
            }
        }
        return validRooms;
    }

    /**
     * Generate room
     */
    public static void generateRoom(Random random,TETile[][] map){
        int num_of_room = 10 + random.nextInt(100);
        List<Room> Rooms = new LinkedList<>();
        for (int i = 0; i <= num_of_room; i++){
            Rooms.add(randomRoom(random,map));
        }
        List<Room> newRooms = removeOverlap(Rooms);
        printRoom(newRooms,map);
        //return Rooms;
    }
    /**
     * Create single room in random size
     */
    private static Room randomRoom(Random random, TETile[][] map) {
        // at least 2 tiles away from the edge
        int xP = 2 + random.nextInt((int) ((map.length - 11)));
        int yP = 11 + random.nextInt((int) ((map[0].length - 13)));
        Position p = new Position(xP, yP);

        // Height and width are among [5,7,9]
        int height = (5 + (random.nextInt(3))*2);
        int width = (5 + (random.nextInt(3))*2);
        return new Room(height, width, p);
    }
    /**
     * helper function
     * [0]..width..[3]
     * .            .
     * height       height
     * .            .
     * [1]..width..[2]
     */
    private static Position[] roomPosition(int width, int height,Position position){
        Position[] corner = new Position[4];

        corner[0] = new Position(position.x , position.y);
        corner[1] = new Position(position.x , position.y - height + 1);
        corner[2] = new Position(position.x + width - 1, position.y + height +1);
        corner[3] = new Position(position.x + width - 1 , position.y);
        return corner;
    }

    //test Room class
    /**
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
        generateRoom(Random,newmap);
        ter.renderFrame(newmap);
    }*/
}//end of class
